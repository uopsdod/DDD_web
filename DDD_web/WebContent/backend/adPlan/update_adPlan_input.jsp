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
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Title Page</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">


</head>

<body>
<div id="popupcalendar" class="text"></div>
	<table id="had" border='1' cellpadding='5' cellspacing='0' width='400' >
		<tr  align='center' valign='middle' height='20'>
			<td>
				<h3>廣告方案資料修改 - update_adPlan_input.jsp</h3>
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

	

	

	<FORM METHOD="post" ACTION="adPlan.do" name="form1"	>
		<table  style="border:1px solid #808080;" >
		
			<jsp:useBean id="adPlanSvc" scope="page"
				class="com.adplan.model.AdPlanService" />
		 <tr>
					<td>Banner方案編號:</td>
					<td>
		              <%=adPlanVO.getAdPlanId()%>
					</td>
				</tr> 
			<tr>
				<td>Banner方案名稱</td>
				<td><input type="text" name="adPlanName" size="45"
					value= <%=adPlanVO.getAdPlanName()%> />
				</td>
			</tr>
			

			<tr>
				<td>刊登開始日期:</td>
				<td><select size='1' name="adPlanStartDate">

						<option value='2016-11-01'>2016-11-01
						<option value='2016-12-01'>2016-12-01
						<option value='2016-01-01'>2017-01-01
						<option value='2016-02-01'>2017-02-01
						
				</select></td>
			</tr>
			
			<tr>
				<td>刊登結束日期:</td>
				<td><select size="1" name="adPlanEndDate">

						<option value='2016-11-30'>2016-11-30
						<option value='2016-12-31'>2016-12-31
						<option value='2016-01-31'>2016-01-31
						<option value='2016-02-28'>2016-02-28
						
				</select></td>
			</tr>
			<tr>
				<td>方案費用:</td>
				<td>
					5000</td>
			</tr>
			<tr>
				<td>剩餘可購買數量:</td>
				<td><input type="number" name="adPlanRemainNo" size="45"
					value="<%=(adPlanVO == null) ? "3" : adPlanVO.getAdPlanRemainNo()%>" /></td>
			</tr>


		</table>
		<br>
		<input type="hidden" name="adPlanPrice" value="5000">
		<input type="hidden" name="adPlanId" value="<%=adPlanVO.getAdPlanId()%>"><!-- 如果要讓使用者不能選但是要讓他看到就要加這個  -->
		<input type="hidden" name="action" value="update"> 
		<button type="submit" class="btn btn-primary " >
		<span class="glyphicon glyphicon-pencil">修改</span>
										</button>
	</form>
</body>
</html>