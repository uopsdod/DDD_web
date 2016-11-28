<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ad.model.*"%>
<%
	AdVO adVO = (AdVO) request.getAttribute("adVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
<title>員工資料修改 - update_emp_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>員工資料修改 - update_emp_input.jsp</h3>
		<a href="<%=request.getContextPath()%>/backend/Ad/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></td>
	</tr>
</table>

<h3>資料修改:</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<FORM METHOD="post" ACTION="ad.do" name="form1"	enctype="multipart/form-data">
		<table border="0">
			<%-- 	<tr> 自動產生
				<td>Banner編號:</td>
				<td><input type="text" name="adid" size="45"
					value="<%=(adVO == null) ? "10000001" : adVO.getAdId()%>" /></td>
			</tr> --%>

			<jsp:useBean id="adPlanSvc" scope="page"
				class="com.adplan.model.AdPlanService" />
			<%-- <tr>
					<td>Banner方案編號:</td>
					<td>
						<select name="adAdPlanId">
							<c:forEach var="adPlanVO" items="${adPlanSvc.all}"><!-- 要對到ID --> <!-- 自動放在pagescope裡 -->
								<option value="${adPlanVO.adPlanId}" ${(adVO.adAdPlanId==adPlanVO.adPlanId)? 'selected':''}>${adPlanVO.adPlanId}
							</c:forEach>
						</select>
					</td>
				</tr> --%>
				<tr><td>Banner編號</td><td><%=adVO.getAdId() %></td></tr>
				<tr><td>Banner方案編號</td><td><%=adVO.getAdAdPlanId() %></td></tr>
				
			<tr>
				<td>廠商會員編號:</td>
				<td><input type="text" name="adHotelId" size="45"
					value="<%=(adVO == null) ? "10001" : adVO.getAdHotelId()%>" /></td>
			</tr>
			<tr>
				<td>處理狀態:</td>
				<td><select size="1" name="adStatus">

						<option value='0'>未處理
						<option value='1'>圖片未通過
						<option value='2'>未繳費
						<option value='3'>已繳費
						<option value='4'>已上架
						<option value='5'>已下架
				</select></td>
			</tr>

			<tr>
				<%
					java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());
				%>
				<td>雇用日期:</td>
				<td bgcolor="#CCCCFF"><input class="cal-TextBox"
					onFocus="this.blur()" size="9" readonly type="text"
					name="adPayDate"
					value="<%=(adVO == null) ? date_SQL : adVO.getAdPayDate()%>">
					<a class="so-BtnLink" href="javascript:calClick();return false;"
					onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
					onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
					onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','adPayDate','BTN_date');return false;">
						<img align="middle" border="0" name="BTN_date"
						src="images/btn_date_up.gif" width="22" height="17" alt="開始日期">
				</a></td>
			</tr>
			<tr>
				
				<td>圖片:</td><td>
				<input type="file" name="adPic">
				</td> 
			</tr>
			<tr>
				<td>圖片內容說明:</td>
				<td><input type="text" name="adPicContent" size="100"
					value="<%=(adVO == null) ? "漂亮嗎" : adVO.getAdPicContent()%>" /></td>
			</tr>
			<tr>
				<td>點擊數:</td>
				<td><input type="number" name="adHit" size="45"
					value="<%=(adVO == null) ? "10" : adVO.getAdHit()%>" /></td>
			</tr>


		</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name=" adId" value="<%=adVO.getAdId()%>">
<input type="hidden" name=" adAdPlanId" value="<%=adVO.getAdAdPlanId()%>">

<input type="submit" value="送出修改"></FORM>

</body>
</html>
