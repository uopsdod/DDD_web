<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.room.model.*"%>
<%@ page import="com.hotel.model.*"%>


	<%@ include file="../indexHeader.jsp"%>
	<%@ include file="City.file" %>
	
	
</head>	
	 <script src="<%=request.getContextPath()%>/frontend_mem/map/js/map.js"></script>
	
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend_mem/map/css/map.css"> 
  <!--價格條-->	
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <!--googleMap.api-->
   <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAlipz45QaidejTobGNnAZTXw3-sx-Bay0&callback=myMap"></script>
	





</head>

   <!--  ------------------------------------------------------------------- -->
   
   	<% RoomVO roomVO = (RoomVO)session.getAttribute("RoomVO"); %>
	<% HotelVO hotelVO = (HotelVO)session.getAttribute("HotelVO"); %>
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
													<div class="col-xs-12 col-sm-12 searchBar">
														
														
													
											
														
														<div style="height:50px" class="col-xs-12 col-sm-12"></div>
														
														
														
														<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/HotelRoomSearch" name="form1"  method="post" >
														
														
														<div class="form-group">
															<label class="col-sm-1 control-label">縣市</label>
															
																<select  name="city" id="city" class="form-control" style="width:100px" id="city">
			
														  			<% for(int i =0;i<city.length;i++){%>
																		<option value="<%=city[i]%>"  <%=item==null?"":((item.get("city")==city[i])?"selected":"")%>><%=city[i]%>	
																	<%}%>				
																</select>
															
														</div>
														
														
														
														
														<div class="form-group">
															<label class="col-sm-1 control-label">區域</label>
															
																<select  name="zone" id="zone" class="form-control" style="width:100px">
			
														  			<% for(int i =0;i<Taoyuan.length;i++){%>
																		<option value="<%=Taoyuan[i]%>" <%=hotelVO==null?"":((hotelVO.getHotelCounty()==Taoyuan[i])?"selected":"")%>  ><%=Taoyuan[i]%>	
																	<%}%>				
																</select>
															
														</div>
														
											
														
														<div class="form-group">
														<label class="col-sm-1 control-label">幾人房</label>
															
																	<% int[]roomCap ={1,2,4,6,8};%>
														 		 	<% String[] roomCapName ={"單人房","雙人房","四人房","六人房","八人房"};%>
															
																
																	<select  name="roomCapacity" class="form-control" style="width:100px">	
														  			<% for(int i =0;i<5;i++){%>
																		<option value="<%=roomCap[i]%>"	<%=roomVO==null?"":((roomVO.getRoomCapacity()==roomCap[i])?"selected":"")%>	><%=roomCapName[i]%>	
																	<%}%>	  			
																	</select>
															
														</div>
														
														
														<input type="submit"  value="搜尋">
														</FORM>
<!-- 														<div class="col-xs-12 col-sm-6" id="searchArea" > -->
<!-- 															 <p> -->
<!-- 															  <label for="amount">區域:</label> -->
<!-- 															 </p>  -->
<!-- 														</div> -->
														
														
														
														
														
														
<!-- 														<div class="col-xs-12 col-sm-12" id="searchRoomType" > -->
<!-- 															 <p> -->
<!-- 															  <label for="amount">房型:</label> -->
<!-- 																 <select> -->
<!-- 																  <option>單人房</option> -->
<!-- 																  <option selected="selected">雙人房</option> -->
<!-- 																  <option >四人房</option> -->
<!-- 																  <option>八人房</option> -->
<!-- 																</select> -->
<!-- 															 </p>  -->
<!-- 														</div> -->
														
														
<!-- 														<div class="col-xs-12 col-sm-12" id="searchPrice" > -->
<!-- 															<p> -->
<!-- 															  <label for="amount">價格範圍:</label> -->
<!-- 															  <input type="text" id="amount" readonly style="border:0; color:#f6931f; font-weight:bold;"> -->
<!-- 															</p> -->
<!-- 															<div id="slider-range"></div> -->
<!-- 														</div> -->
								
													
													</div>
													<!----------------------房型陳列---------------------------------->
													<div class="col-xs-12 col-sm-6">
														<div class="item">
															<img class="imgmap" src="mapImage/room1.jpg">
															<h3>新竹豐邑喜來登大飯店</h3>
															<p>Sheraton Hsinchu Hotel</p>
														</div>
													</div>
													<div class="col-xs-12 col-sm-6">
														<div class="item">
															<img class="imgmap" src="mapImage/room2.jpg">
															<h3>新竹豐邑喜來登大飯店</h3>
															<p>Sheraton Hsinchu Hotel</p></div>
													</div>
													<div class="col-xs-12 col-sm-6">
														<div class="item">
															<img class="imgmap" src="mapImage/room3.jpg">
															<h3>新竹豐邑喜來登大飯店</h3>
															<p>Sheraton Hsinchu Hotel</p></div>
													</div>
													<div class="col-xs-12 col-sm-6">
														<div class="item">
															<img class="imgmap" src="mapImage/room4.jpg">
															<h3>新竹豐邑喜來登大飯店</h3>
															<p>Sheraton Hsinchu Hotel</p></div><!--item-->
													</div>
													<div class="col-xs-12 col-sm-6">
														<div class="item">
															<img class="imgmap" src="mapImage/room5.jpg">
															<h3>新竹豐邑喜來登大飯店</h3>
															<p>Sheraton Hsinchu Hotel</p></div><!--item-->
													</div>
													<div class="col-xs-12 col-sm-6">
														<div class="item">
															<img class="imgmap" src="mapImage/room7.jpg">
															<h3>新竹豐邑喜來登大飯店</h3>
															<p>Sheraton Hsinchu Hotel</p></div><!--item-->
													</div>
													<!-----------------------下一頁--------------------->
													<div class="col-xs-12 col-sm-6">
														<div class="itembottom" style="height:300px">
														   
														</div><!--item-->
													</div>
												</div>
											</div><!--scroll-->
											</div>
											<div class="col-xs-12 col-sm-5 semi">
												
												<div class="item right" id="right">
													<img src="https://api.fnkr.net/testimg/500x200/">
													<h3>title</h3>
													<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
													tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
													quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
													consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
													cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
													proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
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
