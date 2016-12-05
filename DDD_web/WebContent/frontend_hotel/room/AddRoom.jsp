<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.room.model.*"%>
<%
RoomVO roomVO = (RoomVO) request.getAttribute("roomVO");
%>

<html>
<head>
<title>房型資料新增- addRoom.jsp</title>
<script src="<%=request.getContextPath()%>/frontend_hotel/room/js/6_new.js"></script>
<script>
	function magic(){
		var roomTwoBed = document.getElementById("roomTwoBed");		//雙人床
	    for (var i = 0; i < roomTwoBed.options.length; i++) {        
	        if (roomTwoBed.options[i].value == '2') {        
	        	roomTwoBed.options[i].selected = true;              
	            break;        
	        }        
	    }
		
	    var roomOneBed = document.getElementById("roomOneBed");		//單人床
	    for (var i = 0; i < roomOneBed.options.length; i++) {        
	        if (roomOneBed.options[i].value == '0') {        
	        	roomOneBed.options[i].selected = true;              
	            break;        
	        }        
	    } 
		
	    var roomCapacity = document.getElementById("roomCapacity");		//單人床
	    for (var i = 0; i < roomCapacity.options.length; i++) {
	    	console.log(roomCapacity.options[i]);
	    	console.log(roomCapacity.options[i].value);
	        if (roomCapacity.options[i].value == "4") {        
	        	roomCapacity.options[i].selected = true;              
	            break;        
	        }        
	    }
	    
	    
	    var roomSweetFacility = document.getElementById("roomSweetFacility");
	    while (roomSweetFacility.firstChild) {
	    	roomSweetFacility.removeChild(roomSweetFacility.firstChild);
	    }
	    var roomSweetFacilityText = document.createTextNode("空調和每日客房清潔服務非吸煙客房");
	    roomSweetFacility.appendChild(roomSweetFacilityText);
	    
	    
	    var roomFacility = document.getElementById("roomFacility");
	    while (roomFacility.firstChild) {
	    	roomFacility.removeChild(roomFacility.firstChild);
	    }
	    var roomFacilityText = document.createTextNode("保險箱、書桌和電話，可要求提供 免費搖籃/嬰兒床");
	    roomFacility.appendChild(roomFacilityText);
	    
	    var roomSleep = document.getElementById("roomSleep");
	    while (roomSleep.firstChild) {
	    	roomSleep.removeChild(roomSleep.firstChild);
	    }
	    var roomSleepText = document.createTextNode("高級寢具");
	    roomSleep.appendChild(roomSleepText);
	    
	    var roomMeal = document.getElementById("roomMeal");
	    while (roomMeal.firstChild) {
	    	roomMeal.removeChild(roomSleep.firstChild);
	    }
	    var roomMealText = document.createTextNode("冰箱和免費瓶裝水");
	    roomMeal.appendChild(roomMealText);
	    
	    var roomFun = document.getElementById("roomFun");
	    while (roomFun.firstChild) {
	    	roomFun.removeChild(roomFun.firstChild);
	    }
	    var roomFunText = document.createTextNode("免費無線上網、平面電視和有線電視頻道");
	    roomFun.appendChild(roomFunText);
	    
	    var roomPrice = document.getElementById("roomPrice");
	    roomPrice.value="7000";
	    
	    var roomTotalNo = document.getElementById("roomTotalNo");
	    roomTotalNo.value="8";
	    
	    var roomName = document.getElementById("roomName");
	    roomName.value="雅致家庭房";
	    
	    
	}
</script>
</head>


<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<!-- <table border='1' cellpadding='5' cellspacing='0' width='400'> -->
<!-- 	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'> -->
<!-- 		<td> -->
<!-- 		<h3>房型資料新增 - addRoom.jsp</h3> -->
<!-- 		</td> -->
<!-- 		<td> -->
<%-- 		   <a href="<%=request.getContextPath()%>/room/select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">回首頁</a> --%>
<!-- 	    </td> -->
<!-- 	</tr> -->
<!-- </table> -->

<%@ include file="../head.jsp"%>



<div style="height:200px">
<%--錯誤表列--%>

</div>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/room/room.do" name="form1"   enctype="multipart/form-data">

						<div class="container">
			<div class="row">
				<div class="col-sm-6 col-sm-offset-3">
					

						
						<div class="form-horizontal">
						
							<div>
								<c:if test="${not empty errorMsgs}">
									<font color='red'>請修正以下錯誤:
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<li>${message}</li>
										</c:forEach>
									</ul>
									</font>
								</c:if>
							</div>	
							
							<!------------------------頁簽---------------------------->
							<div class="col-xs-12 col-sm-8">
								<div role="tabpanel">
							    <!-- 標籤面板：標籤區 -->
									    <ul class="nav nav-tabs" role="tablist">
									        <li role="presentation" class="active">
									            <a href="#xx1" aria-controls="xx1" role="tab" data-toggle="tab">房型資料設定</a>
									        </li>
									       
									        <li role="presentation">
									            <a href="#dd3" aria-controls="dd3" role="tab" data-toggle="tab">圖檔設定</a>
									        </li>
									    </ul>
							
							    <!-- 標籤面板：內容區 -->
								    <div class="tab-content">
								        <div role="tabpanel" class="tab-pane active" id="xx1">
													
													
													<input type="hidden" name="roomHotelId" size="45" 
															value="<%= (roomVO==null)? hotelVO.getHotelId() : roomVO.getRoomHotelId()%>" />
														
													<div class="form-group">
														<label class="col-sm-3 control-label">房型名稱</label>
														<div class="col-sm-9">
																<input type="TEXT" id="roomName" name="roomName" size="45" class="form-control"
																		value="<%= (roomVO==null)? "" : roomVO.getRoomName()%>" />
														</div>
													</div>
							
													<div class="form-group">
														<label class="col-sm-3 control-label">房間總數</label>
														<div class="col-sm-9">
																<input type="TEXT" id="roomTotalNo" name="roomTotalNo" size="10" class="form-control"
																	value="<%= (roomVO==null)? "" : (roomVO.getRoomTotalNo()==null?"":roomVO.getRoomTotalNo())%>" />
														</div>
													</div>
							
													<div class="form-group">
														<label class="col-sm-3 control-label">房間定價</label>
														<div class="col-sm-9">
																<input type="TEXT" id="roomPrice" name="roomPrice" size="45" class="form-control"
																	value="<%= (roomVO==null)? "" : (roomVO.getRoomPrice()==null?"":roomVO.getRoomPrice())%>" />
														</div>
													</div>
							
													<div class="form-group">
														<label class="col-sm-3 control-label">娛樂</label>
														<div class="col-sm-9">
																
																<textarea rows="3" cols="50" id="roomFun" name="roomFun" maxlength="50" class="form-control"><%= (roomVO==null)? "" : roomVO.getRoomFun()%></textarea>
														</div>
													</div>	
													
													<div class="form-group">
														<label class="col-sm-3 control-label">餐飲</label>
														<div class="col-sm-9">
																<textarea rows="3" cols="50" id="roomMeal" name="roomMeal" maxlength="50" class="form-control"><%= (roomVO==null)? "" : roomVO.getRoomMeal()%></textarea>
																
														</div>
													</div>
													
													<div class="form-group">
														<label class="col-sm-3 control-label">舒適睡眠</label>
														<div class="col-sm-9">
																<textarea rows="3" cols="50" id="roomSleep" name="roomSleep" maxlength="50" class="form-control"><%= (roomVO==null)? "" : roomVO.getRoomSleep()%></textarea>
																
														</div>
													</div>
													
													<div class="form-group">
														<label class="col-sm-3 control-label">設施</label>
														<div class="col-sm-9">
																
																<textarea rows="3" cols="50" id="roomFacility" name="roomFacility" maxlength="50" class="form-control"><%= (roomVO==null)? "" : roomVO.getRoomFacility()%></textarea>
														</div>
													</div>
													
													<div class="form-group">
														<label class="col-sm-3 control-label">貼心服務</label>
														<div class="col-sm-9">
																
																<textarea rows="3" cols="50" id="roomSweetFacility" name="roomSweetFacility" maxlength="50" class="form-control"><%= (roomVO==null)? "" : roomVO.getRoomSweetFacility()%></textarea>
														</div>
													</div>	
								
													<div class="form-group">
														<label class="col-sm-3 control-label">幾人房</label>
														<div class="col-sm-9">
																<% int[]roomCap ={1,2,4,6,8};%>
													 		 	<% String[] roomCapName ={"單人房","雙人房","四人房","六人房","八人房"};%>
														
															
																<select  name="roomCapacity" id="roomCapacity" class="form-control" style="width:100px">	
													  			<% for(int i =0;i<5;i++){%>
																	<option value="<%=roomCap[i]%>"	<%=roomVO==null?"":((roomVO.getRoomCapacity()==roomCap[i])?"selected":"")%>	><%=roomCapName[i]%>	
																<%}%>	  			
																</select>
														</div>
													</div>
													
													
													<div class="form-group">
														<label class="col-sm-3 control-label">單人床數</label>
														<div class="col-sm-9">
																<select  id="roomOneBed" name="roomOneBed" class="form-control" style="width:100px">			
																<% for(int i =0;i<9;i++){%>
																	<option value="<%=i%>"	<%=roomVO==null?"":((roomVO.getRoomOneBed()==i)?"selected":"")%>	><%=i%>	
																<%}%>
																</select>
														</div>
													</div>
													
													<div class="form-group">
														<label class="col-sm-3 control-label">雙人床數</label>
														<div class="col-sm-9">
																<select  name="roomTwoBed" id="roomTwoBed" class="form-control" style="width:100px">
													  	
													  			<% for(int i =0;i<5;i++){%>
																	<option value="<%=i%>"	<%=roomVO==null?"":(roomVO.getRoomTwoBed()==i?"selected":"")%>	><%=i%>	
																<%}%>				
																</select>
														</div>
													</div>
						
													<input type="button" class="btn btn-success" value="神奇小按鈕" onclick="magic()" >
										 </div>
									 
										 
										 <div role="tabpanel" class="tab-pane" id="dd3">
										        
										        
										               房型照片<input type="file" name="upfile1" id='upfile1'><br>
            							               房型照片<input type="file" name="upfile1" id='upfile2'><br> 
										        
										    
										    <img width="400" id="image1">
										    <img width="400" id="image2">
										    
										        
										        <input type="hidden" name="action" value="RoomInsert">
												<input type="submit" value="送出新增">	
										        
										        
										        
										        
										 </div>  <!-- 第三頁籤內容結束 -->
								    </div>
								</div>
							</div>
								
						

						</div>
	
				</div>
			</div>
		</div>	
<br>

</FORM>





	
		<%@ include file="../footer.jsp" %>