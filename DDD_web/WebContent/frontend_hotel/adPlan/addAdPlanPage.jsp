
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.adplan.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	AdPlanVO adPlanVO = (AdPlanVO) request.getAttribute("adPlanVO");/* controller錯誤修正的資料錯誤會放在這裡 */
%>
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Title Page</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="js/calendar.css">


<!-- 請輸入標題 -->
<title>管理後端</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/backend/css/bootstrap.css">
<!-- 自訂CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/backend/css/0_main.css">

<script src="<%=request.getContextPath()%>/backend/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/backend/js/bootstrap.js"></script>
	<!-- 自訂JavaScript -->
	<script src="<%=request.getContextPath()%>/backend/css/0_new.js"></script>


<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>
<style type="text/css">
.datag, .datetest {
	position: relative;
}

.ui-datepicker-trigger {
	position: absolute;
}

.hupdate tr:nth-of-type(even) {
	background: #CCC
}

.hupdate tr:nth-of-type(odd) {
	background: #FFF
}

#htd {
	border: 1px #b5f1b2;
}

#hhwid {
	width: 50%;
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
</head>
<body>
<%@ include file="/backend/backendBody.jsp"%>

<%
if(!authorityList.contains("102")){
	response.sendRedirect(request.getContextPath()+"/backend/emp_index.jsp");
}
%>	

			<div class="col-xs-12 col-sm-10 bb" style="background-color: #FFFAF0;">

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

	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12">



				<center>
					<h3>Banner方案新增</h3>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/adPlan/adPlan.do"
						name="form1">
						<table class="table    table-striped  table-bordered hupdate"
							id="hhwid" style="border: 2px solid #cccccc;">
							<jsp:useBean id="adPlanSvc" scope="page"
								class="com.adplan.model.AdPlanService" />



							<tr>
								<th>Banner方案名稱</th>
								<td id="htd"><input type="text" name="adPlanName" size="30"
									value="<%=(adPlanVO == null) ? "" : adPlanVO.getAdPlanName()%>" /></td>
							</tr>
							<tr>
								<th>刊登開始日期:</th>
								<td id="htd"><input type="date" name="adPlanStartDate"
									value="" required></td>
							</tr>
							<tr>
								<th>刊登結束日期:</th>
								<td id="htd"><input type="date" name="adPlanEndDate"
									value="" required></td>
							</tr>
							<tr>
								<th>方案費用</th>
								<td id="htd">5000元</td>
							</tr>


							<tr>
								<th>剩餘可購買數量:</th>
								<td id="htd"><input type="number" name="adPlanRemainNo"
									size="5"
									value="<%=(adPlanVO == null) ? "9" : adPlanVO.getAdPlanRemainNo()%>" /></td>
							</tr>


						</table>

						<input type="hidden" name="adPlanPrice" value="5000">
						<!-- 如果要讓使用者不能選但是要讓他看到就要加這個  -->
						<input type="hidden" name="action" value="insert">
						<button type="submit" class="btn btn-success">
							<span class="glyphicon glyphicon-plus">送出新增</span>
						</button>
					</form>
				</center>
				
				</div>

			</div>
		</div>
	</div>
</body>
</html>