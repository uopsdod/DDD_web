<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.room.model.*"%>		
		
		<%@ include file="../head.jsp"%>

		
		
		<div class="col-xs-12 col-sm-10 bb" style="background-color:#FFFAF0;postion:relative;top:220px">
					<ol class="breadcrumb">
						<li>
							房型資料
						</li>
						<li class="active">房型資料維護</li>
					</ol>
					
					
					
					<div class="panel " style="margin:0px;background:#dff0d8;padding:10px;height:50px;border-color:pink"  >							
							<a href="<%=request.getContextPath()%>/frontend_hotel/room/AddRoom.jsp"><img src="<%=request.getContextPath()%>/frontend_hotel/room/images/add.png" width="30"  border="0"></a> 			  
					</div>
					
					
				  <table class="table table-hover" border="1">
				    <thead>
				      <tr style="background-color:#B0C4DE;">
						<th>房間名稱</th>
						<th>總房間數</th>
						<th>房間定價</th>
						<th >娛樂</th>
						<th >餐飲</th>
						<th >舒適睡眠</th>
						<th >房間設施</th>
						<th >貼心服務</th>
						<th>幾人房</th>
						<th>單人床數</th>
						<th>雙人床數</th>
						<th>
						
						
						</th>
				      </tr>
				    </thead>
					
					<tbody>
<%-- 					<%Set<RoomVO> roomSet =(Set<RoomVO>)request.getAttribute("RoomSet");%> --%>
					
					<%
					String hotelId = (String)session.getAttribute("hotelId");
					System.out.println(hotelId);
					%>
					
					
					<%
						RoomService roomSvc = new RoomService();
	        			Set<RoomVO> roomSet = roomSvc.getOneHotelAllRoom(hotelId); 
					    pageContext.setAttribute("roomSet",roomSet);
					%>
					
					
					
					<%  int rowsPerPage = 5; 
					    int rowNumber=0;      
					    int pageNumber=0;    
					    int whichPage=1;     
					    int pageIndexArray[]=null;
					    int pageIndex=0; 
					%>
					
					<%  
					    rowNumber=roomSet.size();
					    if (rowNumber%rowsPerPage !=0)
					     pageNumber=rowNumber/rowsPerPage +1;
					    else pageNumber=rowNumber/rowsPerPage;    
					
					    pageIndexArray=new int[pageNumber]; 
					    for (int i=1 ; i<=pageIndexArray.length ; i++)
					    pageIndexArray[i-1]=i*rowsPerPage-rowsPerPage;
					%>
					
					<%  try {
					      whichPage = Integer.parseInt(request.getParameter("whichPage"));
					      pageIndex=pageIndexArray[whichPage-1];
					    } catch (NumberFormatException e) { 
					       whichPage=1;
					       pageIndex=0;
					    } catch (ArrayIndexOutOfBoundsException e) {
					         if (pageNumber>0){
					              whichPage=pageNumber;
					              pageIndex=pageIndexArray[pageNumber-1];
					         }
					    } 
					%>
					
					<c:forEach var="roomVO" items="${roomSet}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					
				
						<tr ${(roomVO.roomId==param.roomId) ? 'bgcolor=#CCCCFF':''}>
							<td><span>${roomVO.roomName}</span></td>
							<td>${roomVO.roomTotalNo}</td>
							<td>${roomVO.roomPrice}</td>
							<td>${roomVO.roomFun}</td> 
							<td>${roomVO.roomMeal}</td> 
							<td>${roomVO.roomSleep}</td> 
							<td>${roomVO.roomFacility}</td>
							<td>${roomVO.roomSweetFacility}</td> 
							<td>${roomVO.roomCapacity}</td> 
							<td>${roomVO.roomOneBed}</td> 
							<td>${roomVO.roomTwoBed}</td>
							<td>
								
									  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/room/room.do">
									     <input type="submit" class="btn btn-primary" value="修改"> 
									     <input type="hidden" name="roomId" value="${roomVO.roomId}">
									     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
									     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
									     <input type="hidden" name="action"	value="getOne_For_Update">
									   </FORM>
								
								
								<button type="button" class="btn btn-danger" id="${roomVO.roomId}" onclick="showImg(this)" >圖示</button>
							</td>
						</tr>
						</c:forEach>
							</tbody>
					
				  </table>
					
						<table border="0"   >    
							 <tr >
							  <%if (rowsPerPage<rowNumber) {%>
							    <%if(pageIndex>=rowsPerPage){%>
							        <td><A href="<%=request.getRequestURI()%>?whichPage=1">至第一頁</A>&nbsp;</td>
							        <td><A href="<%=request.getRequestURI()%>?whichPage=<%=whichPage-1%>">上一頁</A>&nbsp;</td>
							    <%}%>
							  
							    <%if(pageIndex<pageIndexArray[pageNumber-1]){%>
							        <td><A href="<%=request.getRequestURI()%>?whichPage=<%=whichPage+1%>">下一頁</A>&nbsp;</td>
							        <td><A href="<%=request.getRequestURI()%>?whichPage=<%=pageNumber%>">至最後一頁</A>&nbsp;</td>
							    <%}%>
							  <%}%>  
							
							   
									<%if ( pageNumber > 1) {%>      
									   <FORM METHOD="post" ACTION="<%=request.getRequestURI()%>">   
									        <td>
									           <select size="1" name="whichPage">
									        <%for (int i=1; i<=pageNumber; i++){%>
									           <option value="<%=i%>">跳至第<%=i%>頁
									        <%}%> 
									           </select>
									           <input type="submit" value="確定" >  
									        </td>
									   </FORM>
									<%}%>
							 </tr>
							</table>  
						
						<%if (pageNumber>0){%>
						<b><font color= red>第<%=whichPage%>/<%=pageNumber%>頁</font></b>
						<%}%>
						<b>●符 合 查 詢 條 件 如 下 所 示: 共<font color=red><%=rowNumber%></font>筆</b>
				



<!-- 				<div class="col-xs-12 col-sm-12"> -->
<!-- 					<div class="col-xs-12 col-sm-3"> -->
<!-- 						每頁10筆, 共87筆紀錄 -->
<!-- 					</div> -->

<!-- 					<div class="col-xs-12 col-sm-6 text-center"> -->
<!-- 						<ul class="pagination" style="margin:0px"> -->
<!-- 						  <li><a href="#">&laquo;</a></li> -->
<!-- 						  <li><a href="#">1</a></li> -->
<!-- 						  <li><a href="#">2</a></li> -->
<!-- 						  <li class="active"><a href="#">3</a></li> -->
<!-- 						  <li><a href="#">4</a></li> -->
<!-- 						  <li><a href="#">5</a></li> -->
<!-- 						  <li><a href="#">&raquo;</a></li> -->
<!-- 						</ul> -->
<!-- 					</div>		 -->

<!-- 					<div class="col-xs-12 col-sm-3"> -->

<!-- 					</div> -->
<!-- 				</div> -->
		
		<div id="showBarItem" class="panel " style="margin:0px;background:#dff0d8;padding:10px;height:50px;border-color:pink"  >		
			
			
			
		
			
			<span id="showBar">
			
			
			</span>  	
										
		</div>
		
		
		<div id="showPanel" >

		</div>
		
		<script>
		var xhr = null;
		

		
		
		
				
		function deleteImg(){
			
 		this.parentElement.remove();	
		
	
			var xhr = new XMLHttpRequest();
			  //設定好回呼函數 
			  xhr.onreadystatechange = function (){
			    if( xhr.readyState == 4 ){
			      if(xhr.status == 200){
//	 		    	  alert(xhr.responseText);
					
			    	
			      }else{
			        alert( xhr.status);
			      }//xhr.status == 200
			    }//xhr.readyState == 4
			  }//onreadystatechange
			  
		
			  
			  //建立好Post連接
			  var url = "<%=request.getContextPath()%>/RoomPhotoServlet";
			  var data_info = "action=DeleteOneRoomPhoto&RoomPhotoId="+this.id;
			  xhr.open("Post",url,true);
			  xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			  xhr.send(data_info);
			  //送出請求
		
		}
		
		//建立顯示圖片區之dom元件	
		function showRoom(jsonStr){
		  //剖析json字串,將其轉成jsob物件
			var roomImgArray = JSON.parse(jsonStr); 
			
	
			var showPanel = document.querySelector('#showPanel');
			showPanel.innerHTML=null;
			
			for(var a=0;a<roomImgArray.length;a++){
				
				
				var Debutton =document.createElement('button');
				Debutton.innerHTML="刪除";
				Debutton.onclick=deleteImg;
				Debutton.style="position:absolute;top:50px:right:50px;z-index:4";	
				Debutton.id=roomImgArray[a];
				
				
				var img = document.createElement('img');
				img.src="<%=request.getContextPath()%>/RoomPhotoServlet?action=getOne_For_Display&roomPhotoId="+roomImgArray[a];
				img.style="width:350px;position:absolute;z-index:1";
				
				var span = document.createElement('span');
				span.style="position:absolute;"
				
				var spanOut = document.createElement('span');
				span.style="margin-right:375px;"
				
				span.appendChild(Debutton);
				
				span.appendChild(img);

				spanOut.appendChild(span);
				
				if((a+1)%3==0){
					for(var i =0;i<11;i++){
					var br = document.createElement('br');
					showPanel.appendChild(br);
					}
				}
				
				showPanel.appendChild(spanOut);
 				
			}
			
			
			
			

		}


		function showImg(obj){
			
			
			
			
		  var xhr = new XMLHttpRequest();
		  //設定好回呼函數 
		  xhr.onreadystatechange = function (){
		    if( xhr.readyState == 4 ){
		      if(xhr.status == 200){
// 		    	  alert(xhr.responseText);  
		        showRoom(xhr.responseText);
		        showBar();
		      }else{
		        alert( xhr.status);
		      }//xhr.status == 200
		    }//xhr.readyState == 4
		  }//onreadystatechange
		  
	
		  
		  //建立好Post連接
		  var url = "<%=request.getContextPath()%>/RoomPhotoServlet";
		  var data_info = "action=getAllId_For_OneRoom&RoomId="+obj.id;
		  xhr.open("Post",url,true);
		  xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		  xhr.send(data_info);
		  //送出請求
		}
		
		
		function showBar(){
			 
			
			var showBarLine = document.querySelector('#showBar');
			showBarLine.innerHTML = null;
			
		
			
			var hide =document.createElement('button');
			hide.innerHTML="全部隱藏";
			hide.onclick=hidezoon;
			
		
			
			showBarLine.appendChild(hide);
	
		}
		
		function hidezoon(){
			
			var showPanel = document.querySelector('#showPanel');
			showPanel.innerHTML = null; 
			var showBarLine = document.querySelector('#showBar');
			showBarLine.innerHTML = null;
		}
		
		
		
		</script>
			
	</div>	
		
		
		
		<%@ include file="../footer.jsp" %>
		
		
		
		
		