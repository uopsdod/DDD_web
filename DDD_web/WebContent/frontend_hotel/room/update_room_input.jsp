
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.room.model.*"%>
<%
RoomVO roomVO = (RoomVO) request.getAttribute("roomVO");
pageContext.setAttribute("roomVO",roomVO);
%>

<html>
<head>
<title>房型資料新增- addRoom.jsp</title>
<script src="<%=request.getContextPath()%>/frontend_hotel/room/js/6_new.js"></script>
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
									            <a href="#xx1" aria-controls="xx1" role="tab" data-toggle="tab">房型資料修改</a>
									        </li>
									   		
									        <li role="presentation">
									            <a href="#dd3" aria-controls="dd3" role="tab" data-toggle="tab">圖檔新增</a>
									        </li>
									    </ul>
							
							    <!-- 標籤面板：內容區 -->
								    <div class="tab-content">
								    
								        <div role="tabpanel" class="tab-pane active" id="xx1">
													
												<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/room/room.do" name="form1"  >	
													<input type="hidden" name="roomHotelId" size="45" 
															value="<%= (roomVO==null)? "10001" : roomVO.getRoomHotelId()%>" />
														
													<div class="form-group">
														<label class="col-sm-3 control-label">房型名稱</label>
														<div class="col-sm-9">
																<input type="TEXT" name="roomName" size="45" class="form-control"
																		value="<%= (roomVO==null)? "" : roomVO.getRoomName()%>" />
														</div>
													</div>
							
													<div class="form-group">
														<label class="col-sm-3 control-label">房間總數</label>
														<div class="col-sm-9">
																<input type="TEXT" name="roomTotalNo" size="10" class="form-control"
																	value="<%= (roomVO==null)? "" : (roomVO.getRoomTotalNo()==null?"":roomVO.getRoomTotalNo())%>" />
														</div>
													</div>
							
													<div class="form-group">
														<label class="col-sm-3 control-label">房間定價</label>
														<div class="col-sm-9">
																<input type="TEXT" name="roomPrice" size="45" class="form-control"
																	value="<%= (roomVO==null)? "" : (roomVO.getRoomPrice()==null?"":roomVO.getRoomPrice())%>" />
														</div>
													</div>
							
													<div class="form-group">
														<label class="col-sm-3 control-label">娛樂</label>
														<div class="col-sm-9">
																
																<textarea rows="3" cols="50" name="roomFun" maxlength="50" class="form-control"><%= (roomVO==null)? "" : roomVO.getRoomFun()%></textarea>
														</div>
													</div>	
													
													<div class="form-group">
														<label class="col-sm-3 control-label">餐飲</label>
														<div class="col-sm-9">
																<textarea rows="3" cols="50" name="roomMeal" maxlength="50" class="form-control"><%= (roomVO==null)? "" : roomVO.getRoomMeal()%></textarea>
																
														</div>
													</div>
													
													<div class="form-group">
														<label class="col-sm-3 control-label">舒適睡眠</label>
														<div class="col-sm-9">
																<textarea rows="3" cols="50" name="roomSleep" maxlength="50" class="form-control"><%= (roomVO==null)? "" : roomVO.getRoomSleep()%></textarea>
																
														</div>
													</div>
													
													<div class="form-group">
														<label class="col-sm-3 control-label">設施</label>
														<div class="col-sm-9">
																
																<textarea rows="3" cols="50" name="roomFacility" maxlength="50" class="form-control"><%= (roomVO==null)? "" : roomVO.getRoomFacility()%></textarea>
														</div>
													</div>
													
													<div class="form-group">
														<label class="col-sm-3 control-label">貼心服務</label>
														<div class="col-sm-9">
																
																<textarea rows="3" cols="50" name="roomSweetFacility" maxlength="50" class="form-control"><%= (roomVO==null)? "" : roomVO.getRoomSweetFacility()%></textarea>
														</div>
													</div>	
								
													<div class="form-group">
														<label class="col-sm-3 control-label">幾人房</label>
														<div class="col-sm-9">
																<% int[]roomCap ={1,2,4,6,8};%>
													 		 	<% String[] roomCapName ={"單人房","雙人房","四人房","六人房","八人房"};%>
														
															
																<select  name="roomCapacity" class="form-control" style="width:100px">	
													  			<% for(int i =0;i<5;i++){%>
																	<option value="<%=roomCap[i]%>"	<%=roomVO==null?"":((roomVO.getRoomCapacity()==roomCap[i])?"selected":"")%>	><%=roomCapName[i]%>	
																<%}%>	  			
																</select>
														</div>
													</div>
													
													
													<div class="form-group">
														<label class="col-sm-3 control-label">單人床數</label>
														<div class="col-sm-9">
																<select  name="roomOneBed" class="form-control" style="width:100px">			
																<% for(int i =0;i<9;i++){%>
																	<option value="<%=i%>"	<%=roomVO==null?"":((roomVO.getRoomOneBed()==i)?"selected":"")%>	><%=i%>	
																<%}%>
																</select>
														</div>
													</div>
													
													<div class="form-group">
														<label class="col-sm-3 control-label">雙人床數</label>
														<div class="col-sm-9">
																<select  name="roomTwoBed" class="form-control" style="width:100px">
													  	
													  			<% for(int i =0;i<5;i++){%>
																	<option value="<%=i%>"	<%=roomVO==null?"":(roomVO.getRoomTwoBed()==i?"selected":"")%>	><%=i%>	
																<%}%>				
																</select>
														</div>
													</div>
													
											<input type="hidden" name="roomId" value="<%=roomVO.getRoomId()%>">
											<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
											<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>"> 	
											<input type="hidden" name="action" value="RoomUpdate">
											<input type="submit" value="送出修改">	
										
										  </form> 
										 </div>

										
										 
										 <div role="tabpanel" class="tab-pane" id="dd3">
										 
										 <form METHOD="post" ACTION="<%=request.getContextPath()%>/RoomPhotoServlet" name="form2"  enctype="multipart/form-data">        
										        
										               房型照片<input type="file" name="upfile1" id='upfile1'><br>
            							               房型照片<input type="file" name="upfile1" id='upfile2'><br> 
										        
										    
										    <img width="400" id="image1">
										    <img width="400" id="image2">
										    
										        <input type="hidden" name="roomId" value="<%=roomVO.getRoomId()%>">
												<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
												<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
										        <input type="hidden" name="hotelId" value="${roomVO.roomHotelId}">
										        <input type="hidden" name="action" value="PhotoUpdate">
												<input type="submit" value="送出新增">	
										        
										        
										        
										  </form>      
										 </div>  <!-- 第三頁籤內容結束 -->
								    </div>
								</div>
							</div>
								
						

						</div>
	
				</div>
			</div>
		</div>	
<br>







	
		<%@ include file="../footer.jsp" %>
