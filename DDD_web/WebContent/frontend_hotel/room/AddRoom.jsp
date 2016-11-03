<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.room.model.*"%>
<%
RoomVO roomVO = (RoomVO) request.getAttribute("roomVO");
%>

<html>
<head>
<title>房型資料新增- addRoom.jsp</title></head>


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



<h3>房型資料:</h3>
<%--錯誤表列--%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/room/room.do" name="form1">

						<div class="container">
			<div class="row">
				<div class="col-sm-6 col-sm-offset-3">
					<form>

						
						<div class="form-horizontal">
							<div class="form-group">
								<label for="user_name" class="col-sm-3 control-label">hotel編號</label>
								<div class="col-sm-9">

									<input type="TEXT" name="roomHotelId" size="45" id="user_name" class="form-control"
											value="<%= (roomVO==null)? "10001" : roomVO.getRoomHotelId()%>" />
								</div>
							</div>
						



							<div class="form-group">
								<label class="col-sm-3 control-label">房型名稱</label>
								<div class="col-sm-9">
										<input type="TEXT" name="roomName" size="45" class="form-control"
												value="<%= (roomVO==null)? "大雅房" : roomVO.getRoomName()%>" />
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label">房間總數</label>
								<div class="col-sm-9">
										<input type="TEXT" name="roomTotalNo" size="10" class="form-control
											value="<%= (roomVO==null)? "5" : roomVO.getRoomTotalNo()%>" />
								</div>
							</div>
							
							
							<div class="form-group">
								<label class="col-sm-3 control-label">今日上架房數</label>
								<div class="col-sm-9">
										<input type="TEXT" name="roomRemainNo" size="45" class="form-control
												value="<%= (roomVO==null)? "4" : roomVO.getRoomRemainNo()%>" />
								</div>
							</div>
							
							
							<div class="form-group">
								<label class="col-sm-3 control-label">房間定價</label>
								<div class="col-sm-9">
										<input type="TEXT" name="roomPrice" size="45" class="form-control
											value="<%= (roomVO==null)? "5000" : roomVO.getRoomPrice()%>" />
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label">是否上架</label>
								<div class="col-sm-9">
									<div class="col-xs-12 col-sm-3">
										<input type="radio" name="roomForSell" size="10" class="form-control
											value="true" <%=(roomVO==null)?"":(roomVO.getRoomForSell()==true?"checked":"") %> />
									
									</div>
									<div class="col-xs-12 col-sm-3">
										<div class="row">
										<h3>yes</h3>
										</div>
									</div>
									<div class="col-xs-12 col-sm-3">
										<input type="radio" name="roomForSell" size="10" class="form-control
											value="false" <%=(roomVO==null)?"":(roomVO.getRoomForSell()==false?"checked":"") %> />
								
									</div>
									<div class="col-xs-12 col-sm-3">
										<div class="row">
										<h3>no</h3>
										</div>
									</div>
									
								</div>
							</div>
							
							
							<div class="form-group">
								<label class="col-sm-3 control-label">是否自動上架</label>
								<div class="col-sm-9">
									<div class="col-xs-12 col-sm-3">
										<input type="radio" name="roomForSellAuto"  class="form-control
											value="true" <%=(roomVO==null)?"":(roomVO.getRoomForSellAuto()==true?"checked":"") %> />
									</div>
									<div class="col-xs-12 col-sm-3">
										<div class="row">
										<h3>yes</h3>
										</div>
									</div>
									<div class="col-xs-12 col-sm-3">
										<input type="radio" name="roomForSellAuto" class="form-control 
											value="false"<%=(roomVO==null)?"":(roomVO.getRoomForSellAuto()==false?"checked":"") %> />
									</div>
									<div class="col-xs-12 col-sm-3">
										<div class="row">
										<h3>no</h3>
										</div>
									</div>
									
								</div>
							</div>
							
							<div class="form-group">
							
								<label class="col-sm-3 control-label">優惠開始時間</label>
									<div class="col-sm-9">
									<table>
											<tr>	
												<td>時<select class="form-control" size="1" name="roomDiscountStartDatehour">
													<% for(int i =0;i<24;i++){%>
														<option value="<%=i%>"    <%=roomVO==null?"":((roomVO.getRoomDiscountStartDate())/(60*60*1000)==i)?"selected":""%>	><%=i%>	
													<% }%>	
												</select>
												</td>
											
												<td>分<select class="form-control" size="1" name="roomDiscountStartDateminute">
													<% for(int i =0;i<60;i++){%>
														<option value="<%=i%>"	  <%=roomVO==null?"":(((roomVO.getRoomDiscountStartDate())%(60*60*1000))/(60*1000)==i)?"selected":""%>	><%=i%>	
													<% }%>	
												</select>
												</td>
											
												<td>秒<select class="form-control" size="1" name="roomDiscountStartDateSecond">
													<% for(int i =0;i<60;i++){%>
														<option value="<%=i%>" 	  <%=roomVO==null?"":(((roomVO.getRoomDiscountStartDate())%(60*60*1000))%(60*1000)/1000==i)?"selected":""%>	><%=i%>	
													<%}%>	
												</select>
												</td>
											</tr>
										</table>
								
									</div>
							</div>

							
							<div class="form-group">
							
								<label class="col-sm-3 control-label">優惠結束時間</label>
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
											
												<td>秒<select class="form-control" size="1" name="roomDiscountEndDateSecond">
													<% for(int i =0;i<60;i++){%>
														<option value="<%=i%>" 	  <%=roomVO==null?"":(((roomVO.getRoomDiscountEndDate())%(60*60*1000))%(60*1000)/1000==i)?"selected":""%>	><%=i%>	
													<%}%>	
												</select>
												</td>
											</tr>
										</table>
								
									</div>
							</div>
							












						</div>



					</form>
				</div>
			</div>
		</div>
		
		
		
		
		
		
		
		
		
		
		
		hotel編號:
		<input type="TEXT" name="roomHotelId" size="45" 
		value="<%= (roomVO==null)? "10001" : roomVO.getRoomHotelId()%>" />

	<tr>
		<td>房型名稱:</td>
		<td><input type="TEXT" name="roomName" size="45" 
			value="<%= (roomVO==null)? "大雅房" : roomVO.getRoomName()%>" /></td>
	</tr>
	<tr>
		<td>房間總數:</td>
		<td><input type="TEXT" name="roomTotalNo" size="45"
			value="<%= (roomVO==null)? "5" : roomVO.getRoomTotalNo()%>" /></td>
	</tr>
	<tr>
		<td>房間定價:</td>
		<td><input type="TEXT" name="roomPrice" size="45"
			value="<%= (roomVO==null)? "5000" : roomVO.getRoomPrice()%>" /></td>
	</tr>
	<tr>
		<td>是否上架:</td>
		<td>
			<input type="radio" name="roomForSell" size="45"
			value="true" <%=(roomVO==null)?"":(roomVO.getRoomForSell()==true?"checked":"") %> />yes
			<input type="radio" name="roomForSell" size="45"
			value="false" <%=(roomVO==null)?"":(roomVO.getRoomForSell()==false?"checked":"") %> />no
		</td>
	</tr>
	<tr>
		<td>是否自動上架:</td>
		<td>
			<input type="radio" name="roomForSellAuto" 
			value="true" <%=(roomVO==null)?"":(roomVO.getRoomForSellAuto()==true?"checked":"") %> />yes
			<input type="radio" name="roomForSellAuto" 
			value="false"<%=(roomVO==null)?"":(roomVO.getRoomForSellAuto()==false?"checked":"") %> />no
		</td>
	</tr>
	<tr>
		<td>今日剩餘房數:</td>
		<td>
			<input type="TEXT" name="roomRemainNo" size="45"
			value="<%= (roomVO==null)? "4" : roomVO.getRoomRemainNo()%>" />
		</td>
	</tr>
	

	<tr><td>優惠開始時間:</td>
		<td>
		<table>
				<tr>	
					<td>時<select size="1" name="roomDiscountStartDatehour">
						<% for(int i =0;i<24;i++){%>
							<option value="<%=i%>"    <%=roomVO==null?"":((roomVO.getRoomDiscountStartDate())/(60*60*1000)==i)?"selected":""%>	><%=i%>	
						<% }%>	
					</select>
					</td>
				
					<td>分<select size="1" name="roomDiscountStartDateminute">
						<% for(int i =0;i<60;i++){%>
							<option value="<%=i%>"	  <%=roomVO==null?"":(((roomVO.getRoomDiscountStartDate())%(60*60*1000))/(60*1000)==i)?"selected":""%>	><%=i%>	
						<% }%>	
					</select>
					</td>
				
					<td>秒<select size="1" name="roomDiscountStartDateSecond">
						<% for(int i =0;i<60;i++){%>
							<option value="<%=i%>" 	  <%=roomVO==null?"":(((roomVO.getRoomDiscountStartDate())%(60*60*1000))%(60*1000)/1000==i)?"selected":""%>	><%=i%>	
						<%}%>	
					</select>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	
	<tr><td>優惠結束時間:</td>
	<td>
	<table><tr>	
		<td>時<select size="1" name="roomDiscountEndDatehour">
			<% for(int i =0;i<24;i++){%>
				<option value="<%=i%>"  <%=roomVO==null?"":((roomVO.getRoomDiscountEndDate())/(60*60*1000)==i)?"selected":""%>     ><%=i%>	
			<% }%>	
		</select>
		</td>
	
		<td>分<select size="1" name="roomDiscountEndDateminute">
			<% for(int i =0;i<60;i++){%>
				<option value="<%=i%>"   <%=roomVO==null?"":(((roomVO.getRoomDiscountEndDate())%(60*60*1000))/(60*1000)==i)?"selected":""%>	   ><%=i%>	
			<% }%>	
		</select>
		</td>

		<td>秒<select size="1" name="roomDiscountEndDateSecond">
			<% for(int i =0;i<60;i++){%>
				<option value="<%=i%>"   <%=roomVO==null?"":(((roomVO.getRoomDiscountEndDate())%(60*60*1000))%(60*1000)/1000==i)?"selected":""%>   ><%=i%>	
			<%}%>	
		</select>
		</td>
		</tr></table>
		</td>
	</tr>
	
	<tr>
		<td>單位時間折扣:</td>
		<td>
			<select size="1" name="roomDisccountPercent">
				<% for(double i =0.05;i<0.61;i+=0.05){%>
					<option value="<%=i%>"	<%=roomVO==null?"":((roomVO.getRoomDisccountPercent()==i)?"selected":"")%>	><%=(new Double(i*100)).intValue()+"%"%>	
				<%}%>	
			</select>
		</td>
	</tr>
	
	<tr><td>折扣單位時間:</td>
		<td>小時<select size="1" name="roomDiscountHr">
				<% for(int i =1;i<5;i+=1){%>
					<option value="<%=i%>"  <%=roomVO==null?"":((roomVO.getRoomDiscountHr()==i)?"selected":"")%>	>	<%=i%>
				<%}%>
			   </select>
		</td>
	</tr>
	<tr>
		<td>是否一價到底:</td>
		<td>
			<input type="radio" name="roomOnePrice" 
			value="true" <%=(roomVO==null)?"":(roomVO.getRoomOnePrice()==true?"checked":"") %> />yes
			<input type="radio" name="roomOnePrice" 
			value="false" <%=(roomVO==null)?"":(roomVO.getRoomOnePrice()==false?"checked":"") %> />no
		</td>
	</tr>
	
	<tr>
		<td>娛樂:</td>
		<td>
			<textarea rows="3" cols="50" name="roomFun" maxlength="50"><%= (roomVO==null)? "" : roomVO.getRoomFun()%></textarea>
		</td>
	</tr>
	
	<tr>
		<td>餐飲:</td>
		<td>
			<textarea rows="3" cols="50" name="roomMeal" maxlength="50"><%= (roomVO==null)? "" : roomVO.getRoomMeal()%></textarea>
		</td>
	</tr>
	
	<tr>
		<td>舒適睡眠:</td>
		<td>
			<textarea rows="3" cols="50" name="roomSleep" maxlength="50"><%= (roomVO==null)? "" : roomVO.getRoomSleep()%></textarea>
		</td>
	</tr>
	
	<tr>
		<td>設施:</td>
		<td>
			<textarea rows="3" cols="50" name="roomFacility" maxlength="50"><%= (roomVO==null)? "" : roomVO.getRoomFacility()%></textarea>
		</td>
	</tr>
	<tr>
		<td>貼心服務:</td>
		<td>
			<textarea rows="3" cols="50" name="roomSweetFacility" maxlength="50"><%= (roomVO==null)? "" : roomVO.getRoomSweetFacility()%></textarea>
		</td>
	</tr>

	<tr>	
		<td>幾人房</td>
	  	<td>
	  	<% int[]roomCap ={1,2,4,6,8};%>
	  	<% String[] roomCapName ={"單人房","雙人房","四人房","六人房","八人房"};%>
	  	<select  name="roomCapacity">	
	  			<% for(int i =0;i<5;i++){%>
					<option value="<%=roomCap[i]%>"	<%=roomVO==null?"":((roomVO.getRoomCapacity()==roomCap[i])?"selected":"")%>	><%=roomCapName[i]%>	
				<%}%>	  			
		</select>
		</td>	
	</tr>
	
	<tr>	
		<td>單人床數</td>
	  	<td>
	  	<select  name="roomOneBed">			
				<% for(int i =0;i<9;i++){%>
					<option value="<%=i%>"	<%=roomVO==null?"":((roomVO.getRoomOneBed()==i)?"selected":"")%>	><%=i%>	
				<%}%>
		</select>
		</td>	
	</tr>
	
	<tr>	
		<td>雙人床房</td>
	  	<td>
	  	<select  name="roomTwoBed">
	  	
	  			<% for(int i =0;i<5;i++){%>
					<option value="<%=i%>"	<%=roomVO==null?"":(roomVO.getRoomTwoBed()==i?"selected":"")%>	><%=i%>	
				<%}%>				
		</select>
		</td>	
	</tr>
	</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">


</FORM>





	
		<%@ include file="../footer.jsp" %>