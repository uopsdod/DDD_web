<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adplan.model.*"%>

<%
	/* 在網頁使用java語法 */
	AdPlanService adPlanSvc = new AdPlanService(); /* 建一個Service物件 */
	List<AdPlanVO> list = adPlanSvc.getAll();/* 用Service物件取得資料庫資料 */
	pageContext.setAttribute("list", list); /* 把資料放在當前頁面 */
%>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Title Page</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
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

<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
<style type="text/css">
.table-hover>tbody>tr:hover {
	background-color: #dcdcdc;
}

#hmod {
	margin: 5px;
	float: left;
}

#hdel {
	margin: 5px;
	float: left;
}

.hwid {
	width: 200px;
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

			
	
			<div class="col-xs-12 col-sm-10 bb" style="background-color: #FFFAF0;">
			<!-- 麵包屑(當前路徑) -->
				<ol class="breadcrumb">
					<li class="active">Banner方案管理</li>
					<li >Banner審核作業</li>
				</ol>

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




				<table class="table table-hover table-bordered  table-striped">
					<thead>
						<tr style="background-color: #B0C4DE;">

							<th>Banner方案編號</th>
							<th>Banner方案名稱</th>
							<th>刊登開始日期</th>
							<th>刊登結束日期</th>
							<th>方案費用</th>
							<th>剩餘可購買數量</th>
							<th>管理</th>





						</tr>

					</thead>
					<%@ include file="page1.file"%>

					<c:forEach var="adPlanVO" items="${list}">
						<tbody>
							<!-- 秀出全部資料 -->
							<tr>

								<td>${adPlanVO.adPlanId}</td>
								<!-- EL語法 p148 -->
								<td>${adPlanVO.adPlanName}</td>
								<td>${adPlanVO.adPlanStartDate}</td>
								<td>${adPlanVO.adPlanEndDate}</td>
								<td>${adPlanVO.adPlanPrice}</td>
								<td>${adPlanVO.adPlanRemainNo}</td>

								<td class="hwid">
									<FORM METHOD="post" id="hmod"
										ACTION="<%=request.getContextPath()%>/adPlan/adPlan.do">
										<!--  <button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-pencil">修改</span></button>   -->
										<!-- <input type="submit"  class="btn btn-primary" value="修改"> -->
										<button type="submit" class="btn btn-primary ">
											<span class="glyphicon glyphicon-pencil">修改</span>
										</button>
										<input type="hidden" name="adPlanId"
											value="${adPlanVO.adPlanId}">
										<!-- 隱藏欄位 幫想秀到畫面上 可是值也要送到servlet才知道要改的值在哪 -->
										<input type="hidden" name="action" value="getOne_For_Update">
									</FORM>




									<FORM METHOD="post" id="hdel"
										ACTION="<%=request.getContextPath()%>/adPlan/adPlan.do">
										<!--   <button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-remove">刪除</span></button> -->
										<button type="submit" class="btn btn-danger ">
											<span class="glyphicon glyphicon-remove">刪除</span>
										</button>
										<input type="hidden" name="adPlanId"
											value="${adPlanVO.adPlanId}">
										<!-- 隱藏欄位 幫想秀到畫面上 可是值也要送到servlet才知道要改的值在哪 -->
										<input type="hidden" name="action" value="delete">
									</FORM>
								</td>
					</c:forEach>

					</tr>


					</tbody>
				</table>

				<%@ include file="page2.file"%>
				<center>
					<a
						href='<%=request.getContextPath()%>/backend/adPlan/addAdPlanPage.jsp'><button
							type="button" class="btn btn-success">
							<span class="glyphicon glyphicon-plus">新增</span>
						</button></a>
				</center>











			</div>

		</div>
	</div>
		
				</div>

			</div>
		</div>
	</div>


	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>