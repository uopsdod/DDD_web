
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
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->

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

<style type="text/css">
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
</head>
<body>

<%@ include file="/backend/backendBody.jsp"%>

<%
if(!authorityList.contains("103")){
	response.sendRedirect(request.getContextPath()+"/backend/emp_index.jsp");
}
%>	

	
			<div class="col-xs-12 col-sm-10 bb"
				style="background-color: #FFFAF0;">
				錯誤表列
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
								<h3>Banner方案修改</h3>
								<FORM METHOD="post" ACTION="adPlan.do" name="form1">
									<table class="table    table-striped  table-bordered hupdate"
										id="hhwid" style="border: 2px solid #cccccc;">
										<jsp:useBean id="adPlanSvc" scope="page"
											class="com.adplan.model.AdPlanService" />


										<tr>
											<th>Banner方案編號:</th>
											<td id="htd"><%=adPlanVO.getAdPlanId()%></td>
										</tr>
										<tr>
											<th>Banner方案名稱</th>
											<td id="htd"><input type="text" name="adPlanName"
												size="20" value="<%=adPlanVO.getAdPlanName()%>" /></td>
										</tr>
										<tr>
											<th>刊登開始日期:</th>
											<td id="htd"><input type="date" name="adPlanStartDate"
												value="<%=adPlanVO.getAdPlanStartDate()%>" required>




											</td>
										</tr>
										<tr>
											<th>刊登結束日期:</th>
											<td id="htd"><input type="date" name="adPlanEndDate"
												value="<%=adPlanVO.getAdPlanEndDate()%>" required></td>
										</tr>
										<tr>
											<th>方案費用</th>
											<td id="htd">5000元</td>
										</tr>


										<tr>
											<th>剩餘可購買數量:</th>
											<td id="htd"><input type="number" name="adPlanRemainNo"
												size="5"
												value="<%=(adPlanVO == null) ? "3" : adPlanVO.getAdPlanRemainNo()%>" /></td>
										</tr>


									</table>

									<input type="hidden" name="adPlanPrice" value="5000"> <input
										type="hidden" name="adPlanId"
										value="<%=adPlanVO.getAdPlanId()%>">
									<!-- 如果要讓使用者不能選但是要讓他看到就要加這個  -->
									<input type="hidden" name="action" value="update">
									<button type="submit" class="btn btn-primary ">
										<span class="glyphicon glyphicon-pencil">送出修改</span>
									</button>
								</form>
							</center>












						</div>

					</div>
				</div>


				<script src="https://code.jquery.com/jquery.js"></script>
				<script
					src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
			</div>

		</div>
	</div>
	</div>
</body>
</html>