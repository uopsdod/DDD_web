
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.room.model.*"%>
<%
RoomVO roomVO = (RoomVO) request.getAttribute("roomVO");
pageContext.setAttribute("roomVO",roomVO);
%>

<html>
<head>
<title>上架設定- addRoom.jsp</title>
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
									    	
									    	<li role="presentation">
									            <a href="#dd3" aria-controls="dd3" role="tab" data-toggle="tab">即時上架</a>
									        </li>
									    	
									        <li role="presentation" class="active">
									            <a href="#xx1" aria-controls="xx1" role="tab" data-toggle="tab">定時上架</a>
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
							
													
													
											<input type="hidden" name="roomId" value="<%=roomVO.getRoomId()%>">
											<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
											<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>"> 	
											<input type="hidden" name="action" value="RoomUpdate">
											<input type="submit" value="定時上架">	
										
										  </form> 
										 </div>
												
										
										 
										 <div role="tabpanel" class="tab-pane" id="dd3">
										 
										 <form METHOD="post" ACTION="<%=request.getContextPath()%>/room/room.do" name="form2"  enctype="multipart/form-data">        
										        
										           
										           <div class="form-group">
														<label class="col-sm-3 control-label">房型名稱</label>
														<div class="col-sm-9">
																<input type="TEXT" name="roomName" size="45" class="form-control"
																		value="<%= (roomVO==null)? "" : roomVO.getRoomName()%>" />
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
														<label class="col-sm-3 control-label">房間底價</label>
														<div class="col-sm-9">
																<input type="TEXT" name="bottomPrice" size="45" class="form-control" />
														</div>
													</div>
													
													
										           
										           	<div class="form-group">
													
														<label class="col-sm-3 control-label">今日上架房數</label>
															<div class="col-sm-9">
																<table>
																	<tr>	
																		<td><select class="form-control" size="1" name="roomRemainNo">
																			<% for(int i =0;i<=roomVO.getRoomTotalNo();i++){%>
																				<option value="<%=i%>"    <%=roomVO==null?"":(roomVO.getRoomRemainNo()==i)?"selected":""%>	><%=i%>	
																			<% }%>	
																		</select>
																		</td>
																
																	</tr>
																</table>
														
															</div>
													</div>
													
													<div class="form-group">
													
														<label class="col-sm-3 control-label">下架時間</label>
															<div class="col-sm-9">
															<table>
																	<tr>	
																		<td>時<select class="form-control" size="1" name="roomDiscountEndDatehour">
																			<% for(int i =0;i<24;i++){%>
																				<option value="<%=i%>"    <%=roomVO==null?"":((roomVO.getRoomDiscountEndDate())/(60*60*1000)==i)?"selected":""%>	><%=i%>	
																			<% }%>	
																		</select>
																		</td>
																	
																		<td>分<select class="form-control" size="1" name="roomDiscountEndDateminute">
																			<% for(int i =0;i<60;i++){%>
																				<option value="<%=i%>"	  <%=roomVO==null?"":(((roomVO.getRoomDiscountEndDate())%(60*60*1000))/(60*1000)==i)?"selected":""%>	><%=i%>	
																			<% }%>	
																		</select>
																		</td>
																	
																	</tr>
																</table>
														
															</div>
													</div>											             
										    		
										    		
										    		
										 			<div class="form-group">
														<label class="col-sm-3 control-label">單位時間折扣</label>
														<div class="col-sm-9">
																<select size="1" name="roomDisccountPercent" class="form-control" style="width:100px">
																<% for(int i =1;i<=30;i++){%>
																	<option value="<%=i%>"	<%=roomVO==null?"":((roomVO.getRoomDisccountPercent()==i)?"selected":"")%>	><%=i+"%"%>	
																<%}%>	
															</select>
														</div>
													</div>
													
													<div class="form-group">
														<label class="col-sm-3 control-label">折扣每單位時間30*n(min)</label>
														<div class="col-sm-9">
																<select size="1" name="roomDiscountHr" class="form-control" style="width:100px">
																<% for(int i =1;i<7;i+=1){%>
																	<option value="<%=i%>"  <%=roomVO==null?"":((roomVO.getRoomDiscountHr()==i)?"selected":"")%>	>	<%=i%>
																<%}%>
															</select>
														</div>
													</div>
													
													
													<div class="form-group">
														<label class="col-sm-3 control-label">是否一價到底</label>
														<div class="col-sm-9">
															<div class="col-xs-12 col-sm-3">
																<input type="radio" name="roomOnePrice"  class="form-control"
															value="true" <%=(roomVO==null)?"":(roomVO.getRoomOnePrice()==true?"checked":"") %> />
															</div>
															<div class="col-xs-12 col-sm-3">
																<div class="row">
																<h3>yes</h3>
																</div>
															</div>
															<div class="col-xs-12 col-sm-3">
																<input type="radio" name="roomOnePrice" class="form-control"
															value="false" <%=(roomVO==null)?"":(roomVO.getRoomOnePrice()==false?"checked":"") %> />
															</div>
															<div class="col-xs-12 col-sm-3">
																<div class="row">
																<h3>no</h3>
																</div>
															</div>
															
														</div>
													</div>
										    		
										    		
										    		
										    		
										    		
										    		
										    		
										        <input type="hidden" name="roomId" value="<%=roomVO.getRoomId()%>">
												<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
												<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
										        <input type="hidden" name="hotelId" value="${roomVO.roomHotelId}">
										        <input type="hidden" name="action" value="OnTimeSell">
												<input type="submit" value="上架">	
										        
										        
										        
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