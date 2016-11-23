<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.room.model.*"%>
<%@ page import="com.hotel.model.*"%>
<%@ page import="com.serv.model.*"%>
<%@ page import="org.json.JSONArray"%>

<%@ page import="org.json.JSONException"%>
<%@ page import="org.json.JSONObject"%>
<%@ page import="com.room.controler.RoomServlet"%>
<%@ page import="com.room.controler.MyEchoServer"%>

	<%@ include file="../indexHeader.jsp"%>
	<%@ include file="City.file" %>
	<%
	response.setHeader("Cache-Control", "no-store");//http1.1
	response.setHeader("Pragma", "no-cache");//http1.0
	response.setDateHeader("Expires", 0);	
	%>
	
<head>	
	 <script src="<%=request.getContextPath()%>/frontend_mem/map/js/map.js"></script>
	
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<!--     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend_mem/map/css/map.css"> 
 

  <!--googleMap.api-->
   <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAlipz45QaidejTobGNnAZTXw3-sx-Bay0&callback=myMap"></script>
	 
	 
	 <!--價格條-->	
  <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend_mem/map/jq/jquery-ui.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend_mem/map/jq/theme.css">
  <script src="<%=request.getContextPath()%>/frontend_mem/map/jq/jquery-1.12.4.js"></script>
  <script src="<%=request.getContextPath()%>/frontend_mem/map/jq/jquery-ui.js"></script>
	
	

  <script src="<%=request.getContextPath()%>/frontend_mem/map/js/socket.js"></script>	<!-- socket -->
	
	<script>
  $( function() {
    $( "#slider-range-price" ).slider({
      range: true,
      min: 0,
      max: 20000,
      values: [ 3500, 10000 ],
      slide: function( event, ui ) {
        $( "#amount-price" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
      }
    });
    $( "#amount-price" ).val( "$" + $( "#slider-range-price" ).slider( "values", 0 ) +
      " - $" + $( "#slider-range-price" ).slider( "values", 1 ) );
      
  } );
   
  </script>
  
  <style type="text/css">

	#c-panel {
		height: 150px;

		display: none;
	
	}
</style>
<script>
	$(document).ready(function() {
		$("#c-mapBar").click(function() {
			$("#c-panel").slideToggle("slow");
		});
	});
</script>	
<script type="text/javascript">

var hotelMap;
var roomMap;
var context;
	
function getInfo(){	
  var xhr = new XMLHttpRequest();
  //設定好回呼函數 
  xhr.onreadystatechange = function (){
    if( xhr.readyState == 4 ){
      if(xhr.status == 200){ 
    	back(xhr.responseText);
    	
      }else{
        alert( xhr.status);
      }//xhr.status == 200
    }//xhr.readyState == 4
  }//onreadystatechange
  
  //建立好Post連接
  var url = "<%=request.getContextPath()%>/HotelRoomSearch";
 // var data_info = "memId=" + document.getElementById("memId").value;
 
 var cityObj = document.getElementById("city").value;
 var zoneObj = document.getElementById("zone").value;
 
 var city = "city=" + cityObj;	//建立市的key-value
 var zone = "zone=" + zoneObj;	//建立區的key-value
 
 //定位map位置
 setMapLocal(cityObj,zoneObj);
 
 var hotelRatingResult  = "hotelRatingResult=" + document.getElementById("hotelRatingResult").value; //建立評分的key-value
 var amountprice = "Price=" + document.getElementById("amount-price").value;  //建立價錢範圍key-value
 
 var roomCapacity = "roomCapacity=";   //建立幾人房的key-value
 var roomCapacityArray  = document.getElementById("roomCapacity").childNodes;
 for (var i=0; i<roomCapacityArray.length; i++)
 {
    if (roomCapacityArray[i].selected)
    {
       roomCapacity += roomCapacityArray[i].value; 
       break;
    }
 }
 
 var servItemStr = ""; 		//旅館設施checkBox
 var servItem  = document.getElementsByName("servItem");
 for (var i=0; i<servItem.length; i++)
 {
    if (servItem[i].checked)
    {	
    	servItemStr += "servItem="+servItem[i].value + "&"; 
       
    }
 }
 servItemStr = servItemStr.slice(0,servItemStr.length-1);

 var data; 
 if(servItemStr==""){
	 data = city+"&"+zone+"&"+hotelRatingResult+"&"+roomCapacity+"&"+amountprice;
 }else{
	 data = city+"&"+zone+"&"+hotelRatingResult+"&"+roomCapacity+"&"+amountprice+"&"+servItemStr;
 }
 data = "action=search&" +data;

//  console.log(data);
  xhr.open("Post",url,true);
  xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
  xhr.send(data);  
  //送出請求
}//function 


function back(jsonStr){
	context = document.getElementById("context");
	context.innerHTML =null;	//清空之前呈現的旅館
	cancelMark();
	
	roomMap = null;	//每次搜尋都清空roomMap
	roomMap = new Map(); //每次搜尋都重新建立roomMap
	
	hotelMap = null;	//每次搜尋都清空roomMap
	hotelMap = new Map(); //每次搜尋都重新建立roomMap
	
	var HotelArray = JSON.parse(jsonStr);
	if(HotelArray.length!=0){		//有搜到符合的旅館
	 	for(var i =0;i<HotelArray.length;i++){	 		
	 		construct(HotelArray[i]); 	
	 	}
	}else{		//未搜到符合的旅館
		
		var div = document.createElement("div");
		div.style="color:red;text-align:center";
		
		var h2 = document.createElement("h2");		
	    var b = document.createElement("b");
	    b.innerText='查詢未有結果';	    
	    h2.appendChild(b);
	    div.appendChild(h2);
		context.appendChild(div);
	} 

}

function construct(hotel){
	
	
	setMarker(hotel);	//在地圖上建立標記
	
// 	console.log(hotel.hotelLon);
// 	console.log(hotel.hotelLat);
// 	console.log(hotel.bottomPrice);
// 	console.log(hotel.hotelId);
// 	console.log(hotel.hotelName);
	
	var outDiv = document.createElement("div"); // 	<div class="col-xs-12 col-sm-6">
	outDiv.className="col-xs-12 col-sm-6 ";
	outDiv.onmouseover = function(){
		changeMark(hotel);	
	};
	outDiv.onmouseout = function(){
		backMark(hotel);	
	}; 
	
	var innerDiv = document.createElement("div"); // 	<div class="item">
	innerDiv.className="item";
	
	var a = document.createElement("a");
	a.href="<%=request.getContextPath()%>/HotelRoomSearch?action=hotelPage&hotelId=" + hotel.hotelId;
	a.target="_blank";
	
	var imgeg = document.createElement("img"); // 		<img class="imgmap" src="mapImage/room1.jpg">	
	imgeg.src= "<%=request.getContextPath()%>/HotelRoomSearch" + "?action=showHotel&hotelId=" +hotel.hotelId;		
	imgeg.className="imgmap";
	
	
	var h3 = document.createElement("h3"); // 		<h3>新竹豐邑喜來登大飯店</h3>
	h3.innerText=hotel.hotelName;
		
	var starDiv = document.createElement("div");	
	for(var i=1;i<=hotel.hotelRating;i++){
		var star = document.createElement("img"); 
		star.src="mapImage/star.png";
		starDiv.appendChild(star);
	}
	
	var itemLeftDiv = document.createElement("div");
	itemLeftDiv.className="col-xs-12 col-sm-6 ";
	
	var itemMidDiv = document.createElement("div");
	itemMidDiv.className="col-xs-12 col-sm-3 ";

	
	var itemRightDiv = document.createElement("div");
	itemRightDiv.className="col-xs-12 col-sm-3 ";
	itemRightDiv.id=hotel.roomBottomId;
	roomMap.set(hotel.roomBottomId,itemRightDiv); //將此間旅館的變動價格DIV物件裝入roomMap中,並以roomBottomId作為key
	
	
	var nowPeople = document.createElement("div");
	nowPeople.id=hotel.hotelId;
	nowPeople.style="font-size:15px;margin-top:15px;color:blue;";
	hotelMap.set(hotel.hotelId+"",nowPeople);
	
	if(hotel.onTime!=0){
	var PeopleHow = document.createTextNode("正在瀏覽"+hotel.onTime+"人");	
	nowPeople.appendChild(PeopleHow);
	}
	
	
	var roomName = document.createElement("div");
	roomName.style="font-size:15px;margin-top:15px;color:red;";
	roomName.innerText=hotel.roomName;
	
	var price = document.createElement("div");
	price.style="font-size:30px;margin-top:10px;margin-bottom:-20px;";
	
	var text = document.createTextNode(hotel.bottomPrice); //價錢textNode
	
	
	
	
	
	a.appendChild(imgeg);	
	innerDiv.appendChild(a);	
	
	itemLeftDiv.appendChild(h3);
	itemLeftDiv.appendChild(starDiv);
	innerDiv.appendChild(itemLeftDiv);
	
	itemMidDiv.appendChild(roomName);
	itemMidDiv.appendChild(nowPeople);
		
	innerDiv.appendChild(itemMidDiv);
	
	price.appendChild(text);
	itemRightDiv.appendChild(price);	
	innerDiv.appendChild(itemRightDiv);
	outDiv.appendChild(innerDiv);
	context.appendChild(outDiv);
// 	<div class="col-xs-12 col-sm-6">
// 	<div class="item">
// 		<a href="yahoo.com.tw"><img class="imgmap" src="mapImage/room1.jpg"></a>
//      <div class="col-xs-12 col-sm-10">
	// 		<h3>新竹豐邑喜來登大飯店</h3>
	// 		<div><img star>*n</div>
//		</div>
//		<div class="col-xs-12 col-sm-2">
//			<div>RoomName<div>	
//		</div>
//		<div class="col-xs-12 col-sm-2">
//         <div class="price">價錢</div>
//		</div>
// 	</div>
// 	</div>
	
}



</script>
</head>
<body onload="connect();" onunload="disconnect();">

   <!--  ------------------------------------------------------------------- -->
   

   	<% Map item = (HashMap)session.getAttribute("item");%>
  
   
   
    <section>
        <div>
            <div>
                <div>
                    <div>
                        <div>
                            <div>
                                <div>
									<div class="container-fluid">
										<div class="row">
											<div class="col-xs-12 col-sm-7 semi">
											<div id="myDIV"
												style="
												position:absolute;					
												height: 1000px;
												overflow: scroll;
												overflow-x: hidden;">	
												<div class="row">
												<!----------------------搜尋bar----------------------------------->
													
														
													
	
														
															<div style="height:50px" class="col-xs-12 col-sm-12"></div>
														
															
														
																	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/HotelRoomSearch" name="form1"  method="post" >
																	
																	
																	<div class="form-group col-sm-6">
																		<label class="col-sm-2 control-label">縣市</label>
																		
																			<select  name="city" id="city" class="form-control" style="width:100px" id="city">
						
																	  			<% for(int i =0;i<city.length;i++){%>
																					<option value="<%=city[i]%>"  <%=item==null?"":((item.get("city").equals(city[i]))?"selected":"")%>><%=city[i]%>	
																				<%}%>				
																			</select>
																		
																	</div>
																	
																	
																	
														
																	
																	<div class="form-group col-sm-6">
																	<label class="col-sm-2 control-label">幾人房</label>
																		
																				<% int[]roomCap ={1,2,4,6,8};%>
																	 		 	<% String[] roomCapName ={"單人房","雙人房","四人房","六人房","八人房"};%>
																		
																			
																				<select id="roomCapacity" name="roomCapacity" class="form-control" style="width:100px">	
																	  			<% for(int i =0;i<5;i++){%>
																					<option value="<%=roomCap[i]%>"	<%=item==null?"":(item.get("roomCapacity").equals(roomCap[i])?"selected":"")%>	><%=roomCapName[i]%>	
																				<%}%>	  			
																				</select>
																		
																	</div>
																	
																	<div class="form-group col-sm-6">
																		<label class="col-sm-2 control-label">區域</label>
																		
																			<select  name="zone" id="zone" class="form-control" style="width:100px">
						
																	  		<%if(item!=null){%>	
																	  		
																	  			<% if(item.get("city").equals("桃園市")){%>
																		  			<% for(int i =0;i<Taoyuan.length;i++){%>
																						<option value="<%=Taoyuan[i]%>" <%=item==null?"":(item.get("hotelCounty").equals(Taoyuan[i])?"selected":"")%>  ><%=Taoyuan[i]%>	
																					<%}%>				
																				<%}%>
																				
																				<% if(item.get("city").equals("台北市")){%>
																		  			<% for(int i =0;i<Taipei.length;i++){%>
																						<option value="<%=Taipei[i]%>" <%=item==null?"":(item.get("hotelCounty").equals(Taipei[i])?"selected":"")%>  ><%=Taipei[i]%>	
																					<%}%>				
																				<%}%>
																				
																				<% if(item.get("city").equals("新北市")){%>
																		  			<% for(int i =0;i<Xinbei.length;i++){%>
																						<option value="<%=Xinbei[i]%>" <%=item==null?"":(item.get("hotelCounty").equals(Xinbei[i])?"selected":"")%>  ><%=Xinbei[i]%>	
																					<%}%>
																				<%}%>					
																			<%}else{%>
																			
																					<% for(int i =0;i<Taoyuan.length;i++){%>
																						<option value="<%=Taoyuan[i]%>" <%=item==null?"":(item.get("hotelCounty").equals(Taoyuan[i])?"selected":"")%>  ><%=Taoyuan[i]%>	
																					<%}%>
																			<%}%>
																			</select>
																	
																	</div>
																
																	
																	<div class="form-group col-sm-6">
																			<label class="col-sm-2 control-label">評分</label>
																			
																					<select id="hotelRatingResult" name="hotelRatingResult" class="form-control" style="width:100px">			
																					<% for(int i =0;i<6;i++){%>
																						<option value="<%=i%>"	<%=item==null?"":(item.get("hotelRatingResult").equals(i+"")?"selected":"")%>	><%=i%>	
																					<%}%>
																					</select>
																		
																	</div>
																	
			

																	<div class="form-group col-sm-12" style="padding:30px">
																		<p >
																		  <label for="amount-price">Price range:</label>
																		  <input type="text" name="Price"  id="amount-price" readonly style="border:0; color:#f6931f; font-weight:bold;">
																		</p>
																		 
																		<div id="slider-range-price"></div>
																		
																		
																		
																		<div class="panel " style="margin:0px;background:#dff0d8;padding:30px;height:80px;"  >	
																			<input type="button" onclick="getInfo()" class="btn btn-success"  value="搜尋"  style="margin-top:-10px">
																			<input type="button"  id="c-mapBar" class="btn btn-info"  value="進階細項"  style="margin-top:-10px">
																		</div>
																		
																		<div id="c-panel" >
																		
																			<jsp:useBean id ="servSvc"   class="com.serv.model.ServService"/>
																								
																			<c:forEach var="servVO" items="${servSvc.all}"  >					
																							
																					<div class="form-group col-sm-3">
																				<input type="checkbox" name="servItem" class="servItem" value="${servVO.servName}">${servVO.servName}
																					</div>
																								 
																			</c:forEach>																			
																		
																		
																		</div>
																	</div>
																	
																	</FORM>
																										
												
													<!----------------------房型陳列---------------------------------->
													
													
		
												<div id="context">
													

							
						
															<%
																Map<String,String[]> hotelMap = new HashMap<String,String[]>();
																RoomService roomSvc = new RoomService();
																List<RoomVO> upRoomList = roomSvc.getListBySQL("select * from room where roomforsell=1");
													if(upRoomList!=null && upRoomList.size()!=0){
														
																
																for(RoomVO roomVO:upRoomList){
																	String hotelId = roomVO.getRoomHotelId();
																	String[] roomBox = hotelMap.get(hotelId);
																	Map<String,Integer> roomMap = RoomServlet.OnData.get(roomVO.getRoomId());	//onData的MAP資料
																	
																	
																	String[] hotelData = new String[2];
																	
																	if(roomBox !=null){
																		if(roomMap.get("price")<new Integer(roomBox[1]))								
																		{	
																			hotelData[0] = roomVO.getRoomId();
																			hotelData[1] = roomMap.get("price")+"";
																			hotelMap.put(hotelId,hotelData);
																		}
																		
																	}else{
																		hotelData[0] = roomVO.getRoomId();
																		hotelData[1] = roomMap.get("price")+"";
																		hotelMap.put(hotelId,hotelData);
												
																	}
																		
																}
															
// 																System.out.println(hotelMap);
																
													
																
																Set<String> hotelIdBox = hotelMap.keySet();
																
															
															%>
															
													
															
															
															<% 
															
															
																
															for(String hotelId :hotelIdBox){
																
															%>
															<%  HotelService hotelSvc = new HotelService();
																HotelVO hotelVO = hotelSvc.getOne(hotelId);	
																RoomService roomSvc2 = new RoomService();
															
																RoomVO roomVO2 = roomSvc2.findByPrimaryKey(hotelMap.get(hotelId)[0]);
															
																%>														
															 	<div class="col-xs-12 col-sm-6">
															 	<div class="item">
															
															 		<a href="<%=request.getContextPath()%>/HotelRoomSearch?action=hotelPage&hotelId=<%=hotelId%>"  target="_blank">
															 		<img class="imgmap" src="<%=request.getContextPath()%>/HotelRoomSearch?action=showHotel&hotelId=<%=hotelId%>">
															 		</a>
															 		
															        <div class="col-xs-12 col-sm-6">
																		<h3><%=hotelVO.getHotelName()%></h3>
																		<div>		
																			<% 	for(int i=1;i<=hotelVO.getHotelRatingResult();i++){%>
																					<img src="mapImage/star.png"> 
																			
																			<%}%>
																		</div>
																	</div>
																	 	<% 
																	 	Integer onTime = 0;
																	 	if(MyEchoServer.onTimePeople!=null){ //取出本hotel正在觀看人數
																			try{
																			onTime = (Integer) MyEchoServer.onTimePeople.get(hotelId);
																			
																			}catch(Exception e){
																			onTime = 0;
																			}
																		}%>
																	<div class="col-xs-12 col-sm-3">
																		<div style="font-size:15px;margin-top:15px;color:red;"><%=roomVO2.getRoomName()%></div>
																		<div style="font-size:15px;margin-top:15px;color:blue;" id="<%=hotelId%>"><%if(onTime!=0){ %><%="正在瀏覽"+onTime+"人"%><%}%></div>	
																	</div>
																	<div class="col-xs-12 col-sm-3" id="<%=hotelMap.get(hotelId)[0]%>"><div class="price" style="font-size:30px;margin-top:10px;margin-bottom:-20px;"><%=hotelMap.get(hotelId)[1]%></div>
																	</div>
															 	</div>
															 	</div>
															
															<%} %><!--for -->
														
														<%} %><!--if -->
												
													<!-----------------------下一頁--------------------->
												
												
											  </div>
											</div>
											
											
												<div class="col-xs-12 col-sm-6">
													<div class="itembottom" style="height:300px">
													   
													</div>
												</div>
											</div><!--scroll-->	
											
											
											
										</div>
										
										<div class="col-xs-12 col-sm-5 semi">
												
												<div class="item right" id="right">
												
												
												
												</div>
												
												
										</div>
										
										
										
										
										
									  </div>
									</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
   <!--  --------------------------------------------------------------------- -->





</body>

</html>	
<script>
var FirstRoomId = [<%for(RoomVO roomVO3:upRoomList){%> <%=roomVO3.getRoomId()%>, <%}%>  <%=upRoomList.size()==0?"":upRoomList.get(0).getRoomId()%> ];
// 									roomMap.set(hotel.roomBottomId,itemRightDiv);
var FirstHotelId = [<%for(RoomVO roomVO3:upRoomList){%> <%=roomVO3.getRoomHotelId()%>, <%}%>  <%=upRoomList.size()==0?"":upRoomList.get(0).getRoomHotelId()%> ];

window.addEventListener('load',function(){
	roomMap = new Map;
	for(var i=0 ;i<FirstRoomId.length;i++){
		var item = document.getElementById(FirstRoomId[i]);
		if(item!=null){
		roomMap.set(""+FirstRoomId[i],item);
		}
	}
	
	hotelMap = new Map;
	for(var i=0 ;i<FirstHotelId.length;i++){
		var item = document.getElementById(FirstHotelId[i]);
		if(item!=null){
		hotelMap.set(""+FirstHotelId[i],item);
		}
	}
	
},false);


</script>

<script type="text/javascript">

	
 	var Taoyuan = ["桃園區","中壢區","平鎮區","龍潭區","楊梅區","新屋區","觀音區","龜山區","八德區","大溪區","大園區","蘆竹區","桃園區","復興區"];
	
 	var Taipie = ["中正區","大同區","中山區","松山區","大安區","萬華區","信義區","士林區","北投區","內湖區","南港區","文山區"];

 	var Xinbei = ["八里區","三芝區","三重區","三峽區","土城區","中和區","五股區","平溪區","永和區","石門區","石碇區","汐止區","坪林區","林口區","板橋區","金山區","泰山區","烏來區","貢寮區","淡水區","深坑區","新店區","新莊區","瑞芳區","萬里區","樹林區","雙溪區","蘆洲區","鶯歌區"];

 	var zone;	
	
	function doFirst(){

			var city = document.getElementById("city");
			city.addEventListener('change',showZone,false);	

			zone = document.getElementById("zone");	

	}	
	function showZone(){
			
			var city = document.getElementById("city").value;

		
			var num = 0;
			if (city == '桃園市'){
				
				zone.innerHTML = null;	
				        for(var i =0;i<Taoyuan.length;i++){
				        	var option = document.createElement("option");	
				        	option.value = Taoyuan[i];
				        	option.innerHTML = Taoyuan[i];
				        	zone.appendChild(option);			
				        }		
			}
			if (city == '台北市'){
				
				 zone.innerHTML = null;	
			        for(var i =0;i<Taipie.length;i++){
			        	var option = document.createElement("option");	
			        	option.value = Taipie[i];
			        	option.innerHTML = Taipie[i];
			        	zone.appendChild(option);			       
			        }	
			}
			if (city == '新北市'){			
				 zone.innerHTML = null;	
			        for(var i =0;i<Xinbei.length;i++){
			        	var option = document.createElement("option");	
			        	option.value = Xinbei[i];
			        	option.innerHTML = Xinbei[i];
			        	zone.appendChild(option);		       
			        }		
			}
						
	}	
 	window.addEventListener('load',doFirst,false);	



</script>

