<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.adplan.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	AdPlanVO adPlanVO = (AdPlanVO) request.getAttribute("adPlanVO");/* controller錯誤修正的資料錯誤會放在這裡 */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>
</head>

<style>
.datag, .datetest {
	position: relative;
}

.ui-datepicker-trigger {
	position: absolute;
}
</style>

<script>
	$(function() {
		$("#datepicker")
				.datepicker(
						{
							//default為focus，可設定focus 、button、 both 。
							//focus 當 focus到 date picker 則 popup date box.
							//button 當按下按鈕時則 popup date box.
							//both 二則都可 popup date box.
							showOn : "both",
							//設定button 圖片url
							buttonImage : "https://jqueryui.com/resources/demos/datepicker/images/calendar.gif",
							//設定button image 如果只有圖則為true,
							//false則為會產出button
							buttonImageOnly : true,
						//設定button title and alt text

						});
	});

	$(function() {
		$("#datepicker2")
				.datepicker(
						{
							//default為focus，可設定focus 、button、 both 。
							//focus 當 focus到 date picker 則 popup date box.
							//button 當按下按鈕時則 popup date box.
							//both 二則都可 popup date box.
							showOn : "both",
							//設定button 圖片url
							buttonImage : "https://jqueryui.com/resources/demos/datepicker/images/calendar.gif",
							//設定button image 如果只有圖則為true,
							//false則為會產出button
							buttonImageOnly : true,
						//設定button title and alt text

						});
	});

	function myalert() {
		alert("確定提交?");
	}
</script>
<body>
	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>廣告方案資料新增 - addAdPlan.jsp</h3>
			</td>
			<td><a href="select_page.jsp"><img src="images/tomcat.gif"
					width="100" height="100" border="1">回首頁</a></td>
		</tr>
	</table>

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


	<FORM METHOD="post" ACTION="adPlan.do" name="form1">
		<table border="0">

			<jsp:useBean id="adPlanSvc" scope="page"
				class="com.adplan.model.AdPlanService" />
			<%--  <tr>
					<td>Banner方案編號:</td>
					<td>
						<select name="adAdPlanId">
							<c:forEach var="adPlanVO" items="${adPlanSvc.all}"><!-- 要對到ID --> <!-- 自動放在pagescope裡 -->
								<option value="${adPlanVO.adPlanId}" ${(adVO.adAdPlanId==adPlanVO.adPlanId)? 'selected':''}>${adPlanVO.adPlanId}
							</c:forEach>
						</select>
					</td>
				</tr>  --%>
			<tr>
				<td>Banner方案名稱</td>
				<td><input type="text" name="adPlanName" size="45"
					value="<%=(adPlanVO == null) ? "" : adPlanVO.getAdPlanName()%>" /></td>
				</td>
			</tr>



			<tr>
				<%
					java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());
				%>
				<td>刊登開始日期:</td>
				<td><input class="cal-TextBox" onFocus="this.blur()" size="9"
					readonly type="text" name="adPlanStartDate"
					value="<%=(adPlanVO == null) ? date_SQL : adPlanVO.getAdPlanStartDate()%>">
					<a class="so-BtnLink" href="javascript:calClick();return false;"
					onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
					onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
					onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','adPlanStartDate','BTN_date');return false;">
						<img align="middle" border="0" name="BTN_date"
						src="images/btn_date_up.jpg" width="22" height="17" alt="開始日期">
				</a></td>
			</tr>

			<%-- 				<td><input type="da" name="adPlanStartDate" size="45"  id="datepicker"
					value="<%=(adPlanVO == null) ? "" : adPlanVO.getAdPlanStartDate()%>" /></td>
					</td> --%>
			<tr>
				<td>刊登結束日期:</td>
				<td><input class="cal-TextBox" onFocus="this.blur()" size="9"
					readonly type="text" name="adPlanEndDate"
					value="<%=(adPlanVO == null) ? date_SQL : adPlanVO.getAdPlanEndDate()%>">
					<a class="so-BtnLink" href="javascript:calClick();return false;"
					onmouseover="calSwapImg('BTN_date1', 'img_Date_OVER',true);"
					onmouseout="calSwapImg('BTN_date1', 'img_Date_UP',true);"
					onclick="calSwapImg('BTN_date1', 'img_Date_DOWN');showCalendar('form1','adPlanEndDate','BTN_date1');return false;">
						<img align="middle" border="0" name="BTN_date1"
						src="images/btn_date_up.jpg" width="22" height="17" alt="開始日期">
				</a></td>
				<%-- <td><input type="text" name="adPlanEndDate" size="45"  id="datepicker2"
					value="<%=(adPlanVO == null) ? "" : adPlanVO.getAdPlanEndDate()%>" /></td>
					</td> --%>


			</tr>
			<tr>
				<td>方案費用:</td>
				<td>5000</td>
			</tr>
			<tr>
				<td>剩餘可購買數量:</td>
				<td><input type="number" name="adPlanRemainNo" size="45"
					value="<%=(adPlanVO == null) ? "3" : adPlanVO.getAdPlanRemainNo()%>" /></td>
			</tr>


		</table>
		<br> <input type="hidden" name="adPlanPrice" value="5000">
		<!-- 如果要讓使用者不能選但是要讓他看到就要加這個  -->
		<input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</form>
</body>
</html>