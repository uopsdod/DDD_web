<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ord.model.*"%>
<%@ page import="java.util.Random"%>
<%@ page import="java.sql.Timestamp"%>
<%
OrdVO ordVO = (OrdVO) request.getAttribute("ordVO");
/* OrdServlet有放 下面可以省 */
pageContext.setAttribute("ordVO", ordVO);
%>

<!DOCTYPE html>
<html>
	<head>
		<title>訂單資料新增 - addOrd.jsp</title>
		<link rel="stylesheet" type="text/css">
		<script type="text/javascript"></script>
	</head>	
		
	<body>		
		<table border='1'>
			<tr>
				<td>
					<h3>訂單資料新增 - addOrd.jsp</h3>
				</td>
				<td>
					<a href="<%=request.getContextPath()%>/backend/selectPage.jsp"><img src="<%=request.getContextPath()%>/backend/ord/images/tomcat.gif"> 回首頁</a>
				</td>
			</tr>
		</table>

		<h3>資料訂單:</h3>
		<%-- 錯誤表列 --%>
		<c:if test="{not empty errorMsgs}">
			請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="{errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</c:if>

		<form method="post" action="<%=request.getContextPath()%>/ord/ord.do" name="form1" enctype="multipart/form-data">
			<table>
				<jsp:useBean id="roomSvc" scope="page" class="com.room.model.RoomService"/>
				<tr>
					<td>房型編號:</td>
					<td>
						<select name="ordRoomId">
							<c:forEach var="roomVO" items="${roomSvc.all}">
								<option value="${roomVO.roomId}" ${(ordVO.ordRoomVO.roomId==roomVO.roomId)?'selected':''}>${roomVO.roomName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				
				<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService"/>
				<tr>
					<td>一般會員編號:</td>
					<td>
						<select name="ordMemId">
							<c:forEach var="memVO" items="${memSvc.all}">
								<option value="${memVO.memId}" ${(ordVO.ordMemVO.memId==memVO.memId)? 'selected':''}>${memVO.memName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				
				<jsp:useBean id="hotelSvc" scope="page" class="com.hotel.model.HotelService"/>
				<tr>
					<td>廠商會員編號:</td>
					<td>
						<select name="ordHotelId">
							<c:forEach var="hotelVO" items="${hotelSvc.all}">
								<option value="${hotelVO.hotelId}" ${(ordVO.ordHotelVO.hotelId==hotelVO.hotelId)?'selected':''}>${hotelVO.hotelName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>

				<tr>
					<td>訂單金額:</td>
					<td>
						<input type="number" name="ordPrice" 
						value="<%= (ordVO==null)? 100 : ordVO.getOrdPrice()%>" min='100' max='1000000' step='100'/>		
					</td>
				</tr>

				<tr>
<%-- 					<%java.sql.Date dateSQL = new java.sql.Date(System.currentTimeMillis());%> --%>
					<td>入住日期:</td>
					<td>
						<input type="date" name="ordLiveDate" value='<%= (ordVO == null || ordVO.getOrdLiveDate() == null)? "" : ordVO.getOrdLiveDate() %>'>
					</td>
				</tr>
				
				<tr>
					<td>訂單狀態名稱:</td>
					<td>				
						<select name="ordStatus">
							<% if(ordVO == null || ordVO.getOrdStatus() == null ) { %>
			  					<option value="0">已下單</option>
			  					<option value="1">主動取消</option>
			  					<option value="2">已入住</option>
			  					<option value="3">已繳費</option>
			  					<option value="4">逾時取消</option>
		  					<% } else { %>
		  						<option value="0" <%= ("0".equals(ordVO.getOrdStatus()))? "selected" : "" %> >已下單</option>
			  					<option value="1" <%= ("1".equals(ordVO.getOrdStatus()))? "selected" : "" %> >主動取消</option>
			  					<option value="2" <%= ("2".equals(ordVO.getOrdStatus()))? "selected" : "" %> >已入住</option>
			  					<option value="3" <%= ("3".equals(ordVO.getOrdStatus()))? "selected" : "" %> >已繳費</option>
			  					<option value="4" <%= ("4".equals(ordVO.getOrdStatus()))? "selected" : "" %> >逾時取消</option>
		  					<% } %>
						</select>
					</td>
				</tr>

				<tr>
					<td>評價內容:</td>
					<td>						
						<textarea name="ordRatingContent"> <%=(ordVO == null || ordVO.getOrdRatingContent() == null )? "" : ordVO.getOrdRatingContent()%> </textarea>
					</td>
				</tr>

				<tr>
					<td>評價星星數:</td>
					<td>
						<select name="ordRatingStarNo">
							<% if(ordVO == null || ordVO.getOrdRatingStarNo() == null) { %>
								<option value="">請選擇</option>
			  					<option value="0">0顆星</option>
			  					<option value="1">1顆星</option>
			  					<option value="2">2顆星</option>
			  					<option value="3">3顆星</option>
			  					<option value="4">4顆星</option>
			    				<option value="5">5顆星</option>	
			    			<% } else { %>
			    				<option value="0" <%= ("0".equals(Integer.toString(ordVO.getOrdRatingStarNo()))) ? "selected" : "" %> >0顆星</option>
			  					<option value="1" <%= ("1".equals(Integer.toString(ordVO.getOrdRatingStarNo()))) ? "selected" : "" %> >1顆星</option>
			  					<option value="2" <%= ("2".equals(Integer.toString(ordVO.getOrdRatingStarNo()))) ? "selected" : "" %> >2顆星</option>
			  					<option value="3" <%= ("3".equals(Integer.toString(ordVO.getOrdRatingStarNo()))) ? "selected" : "" %> >3顆星</option>
			  					<option value="4" <%= ("4".equals(Integer.toString(ordVO.getOrdRatingStarNo()))) ? "selected" : "" %> >4顆星</option>
			    				<option value="5" <%= ("5".equals(Integer.toString(ordVO.getOrdRatingStarNo()))) ? "selected" : "" %> >5顆星</option>		
		    				<% } %>			
						</select>			
					</td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td>QR Code圖片:</td> -->
<!-- 					<td> -->
<!-- 						<input type="file" name="ordQrPic"/> -->
<!-- 					</td> -->
<!-- 				</tr> -->
				<tr>
					<td>簡訊驗證碼:</td>
					<td>
						<% 
							char[] randomCharArray = new char[4];
						
							for(int i= 0 ; i < randomCharArray.length ;i++){
								Random r = new Random();
								randomCharArray[i] = (char) (r.nextInt(26) + 'A');
							}
						
							String ordMsgNo = new StringBuilder().append(randomCharArray).toString();
							
						%>
					
						<%=ordMsgNo%>
						
					</td>
				</tr>
			</table>

			<br>
			<input type="hidden" name="action" value="insert">
			<input type="hidden" name="ordMsgNo" value="<%= (ordVO==null)? ordMsgNo : ordVO.getOrdMsgNo()%>"/>
			<input type="submit" value="送出新增">
		</form>
			
	</body>
</html>