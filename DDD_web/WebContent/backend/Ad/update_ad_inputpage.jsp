<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ad.model.*"%>
<%
	AdVO adVO = (AdVO) request.getAttribute("adVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<!DOCTYPE html>
<html lang="">
<head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>
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

<style type="text/css">
.hupdate tr:nth-of-type(even) {
	background: #CCC
}

.hupdate tr:nth-of-type(odd) {
	background: #FFF
}

#htdad {
	border: 1px #b5f1b2;
}

#hhwidad {
	width: 50%;
}
</style>
</head>



<body bgcolor='white'>
<!-- 這是頂端那條Bar -->
	<div id="top-bar">
		<nav class="navbar navbar-inverse" role="navigation">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-ex1-collapse">
					<span class="sr-only">選單切換</span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"><span
					class="glyphicon glyphicon-heart-empty"></span> DDD後端管理介面</a>
			</div>

			<!-- 手機隱藏選單區 -->
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<!-- 右選單 -->
				<ul class="nav navbar-nav navbar-right">
					<!-- 抓使用者的名字 -->
					<li><a href="#">大城 您好 !</a></li>
					<li><a href="#"><span class="glyphicon glyphicon-bell"></span>
							通知</a></li>
					<li><a href="#"><span class="glyphicon glyphicon-log-out"></span>
							登出</a></li>
				</ul>
			</div>
			<!-- 手機隱藏選單區結束 -->
		</nav>
	</div>

	<div class="container-fluid">
		<div class="row">
			<!-- 左邊的手風琴 -->
			<div class="col-xs-12 col-sm-2 aa" style="background-color: #DCDCDC;">
				<div class="panel-group" id="accordion2" role="tablist"
					aria-multiselectable="true">
					<!-- 區塊A(訂單) -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="tab1"
							style="background-color: #B0C4DE;">
							<h4 class="panel-title">
								<a role="button" data-toggle="collapse"
									data-parent="#accordion2" href="#aaa" aria-expanded="true"
									aria-controls="aaa"> <span
									class="glyphicon glyphicon-list-alt"></span> 訂單
								</a>
							</h4>
						</div>
						<div id="aaa" class="panel-collapse collapse in" role="tabpanel"
							aria-labelledby="tab1">
							<div class="panel-body">
								<ul style="list-style-type: none">
									<li><a href="<%=request.getContextPath()%>/backend/ord/listAllOrd.jsp">所有訂單查詢</a></li>
									<li><a href="<%=request.getContextPath()%>/backend/selectPage.jsp">訂單條件搜尋</a></li>
								</ul>
							</div>
						</div>
					</div>
					<!-- 區塊B(Banner) -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="tab4"
							style="background-color: #B0C4DE;">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion2" href="#ddd" aria-expanded="false"
									aria-controls="ddd"> <span
									class="glyphicon glyphicon-bullhorn"></span> Banner
								</a>
							</h4>
						</div>
						<div id="ddd" class="panel-collapse collapse" role="tabpanel"
							aria-labelledby="tab4">
							<div class="panel-body">
								<ul style="list-style-type: none">
									<li>Banner方案管理</li>
									<li>Banner審核作業</li>
									<li>Banner管理作業</li>
								</ul>
							</div>
						</div>
					</div>
					<!-- 區塊C(上架月租費) -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="tab7"
							style="background-color: #B0C4DE;">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion2" href="#ggg" aria-expanded="false"
									aria-controls="fff"> <span
									class="glyphicon glyphicon-piggy-bank"></span> 上架月租費
								</a>
							</h4>
						</div>
						<div id="ggg" class="panel-collapse collapse" role="tabpanel"
							aria-labelledby="tab7">
							<div class="panel-body">
								<ul style="list-style-type: none">
									<li>月租管理作業</li>
								</ul>
							</div>
						</div>
					</div>
					<!-- 區塊D(一般會員) -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="tab2"
							style="background-color: #B0C4DE;">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion2" href="#bbb" aria-expanded="false"
									aria-controls="bbb"> <span class="glyphicon glyphicon-king"></span>
									一般會員
								</a>
							</h4>
						</div>
						<div id="bbb" class="panel-collapse collapse" role="tabpanel"
							aria-labelledby="tab2">
							<div class="panel-body">
								<ul style="list-style-type: none">
									<li>一般會員維護</li>
									<li>對話紀錄維護</li>
								</ul>
							</div>
						</div>
					</div>
					<!-- 區塊E(廠商會員) -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="tab3"
							style="background-color: #B0C4DE;">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion2" href="#ccc" aria-expanded="false"
									aria-controls="ccc"> <span
									class="glyphicon glyphicon-queen"></span> 廠商會員
								</a>
							</h4>
						</div>
						<div id="ccc" class="panel-collapse collapse" role="tabpanel"
							aria-labelledby="tab3">
							<div class="panel-body">
								<ul style="list-style-type: none">
									<li>廠商審核作業</li>
									<li>廠商會員維護</li>
								</ul>
							</div>
						</div>
					</div>
					<!-- 區塊F(員工) -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="tab5"
							style="background-color: #B0C4DE;">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion2" href="#eee" aria-expanded="false"
									aria-controls="eee"> <span class="glyphicon glyphicon-pawn"></span>
									員工
								</a>
							</h4>
						</div>
						<div id="eee" class="panel-collapse collapse" role="tabpanel"
							aria-labelledby="tab5">
							<div class="panel-body">
								<ul style="list-style-type: none">
									<li>新增員工資料</li>
									<li>員工資料維護</li>
								</ul>
							</div>
						</div>
					</div>
					<!-- 區塊G(檢舉) -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="tab6"
							style="background-color: #B0C4DE;">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion2" href="#fff" aria-expanded="false"
									aria-controls="fff"> <span
									class="glyphicon glyphicon-thumbs-down"></span> 檢舉
								</a>
							</h4>
						</div>
						<div id="fff" class="panel-collapse collapse" role="tabpanel"
							aria-labelledby="tab6">
							<div class="panel-body">
								<ul style="list-style-type: none">
									<li>一般會員檢舉</li>
									<li>廠商會員檢舉</li>
								</ul>
							</div>
						</div>
					</div>
					<!-- 區塊H(評價留言) -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="tab8"
							style="background-color: #B0C4DE;">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion2" href="#hhh" aria-expanded="false"
									aria-controls="hhh"> <span
									class="glyphicon glyphicon-thumbs-up"></span> 留言評價
								</a>
							</h4>
						</div>
						<div id="hhh" class="panel-collapse collapse" role="tabpanel"
							aria-labelledby="tab8">
							<div class="panel-body">
								<ul style="list-style-type: none">
									<li>留言評價維護</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- 右邊的主要區塊 -->
			<div class="col-xs-12 col-sm-10 bb"
				style="background-color: #FFFAF0;">


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
								<h3>Banner修改</h3>

								<FORM METHOD="post" ACTION="ad.do" name="form1"
									enctype="multipart/form-data">
									<table border="0">
										<table class="table    table-striped  table-bordered hupdate"
											id="hhwidad" style="border: 2px solid #cccccc;">
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
											<tr>
												<th>Banner編號</th>
												<td id="htdad"><%=adVO.getAdId()%></td>
											</tr>


											<tr>
												<th>Banner方案編號</th>
												<td id="htdad"><%=adVO.getAdAdPlanId()%></td>
											</tr>
											<tr>
												<th>廠商會員編號:</th>
												<td id="htdad"><%=adVO.getAdHotelId()%></td>
											</tr>
											<tr>
												<th>處理狀態:</th>
												<td id="htdad"><select size="1" name="adStatus">

														<option value='0'>未處理
														<option value='1'>圖片未通過
														<option value='2'>未繳費
														<option value='3'>已繳費
														<option value='4'>已上架
														<option value='5'>已下架
												</select></td>
											</tr>
											<tr>
												<%-- <%
										java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());
									%> --%>
												<th>繳費日期:</th>
												<td id="htdad"><%=(adVO == null) ? "未繳費" : adVO.getAdPayDate()%>
													<%-- <%=("0".equals(((AdVO)(pageContext.getAttribute("adVO"))).getAdStatus())
						|| "1".equals(((AdVO) (pageContext.getAttribute("adVO"))).getAdStatus())
						|| "2".equals(((AdVO) (pageContext.getAttribute("adVO"))).getAdStatus())) ? "未繳費"
								: ((AdVO) (pageContext.getAttribute("adVO"))).getAdPayDate()%> --%>
												</td>
											<tr>
												<th>圖片:</th>
												<td id="htdad">
													<!-- <input type="file" name="adPic"> --> <img
													src="<%=request.getContextPath()%>/Ad/AdPic?adId=${adVO.adId}" />
												</td>
											</tr>
											<tr>
												<th>圖片內容說明:</th>
												<td id="htdad"><%=adVO.getAdPicContent()%></td>
											</tr>

											<tr>
												<th>點擊數:</th>
												<td id="htdad"><%=adVO.getAdHit()%></td>
											</tr>











										</table>
										<br>
										<input type="hidden" name="action" value="update">
										<input type="hidden" name=" adId" value="<%=adVO.getAdId()%>">
										<input type="hidden" name=" adAdPlanId"
											value="<%=adVO.getAdAdPlanId()%>">
										<input type="hidden" name="adHotelId"
											value="<%=adVO.getAdHotelId()%>">
										<input type="hidden" name="adStatus"
											value="<%=adVO.getAdStatus()%>">
										<input type="hidden" name="adPayDate"
											value="<%=adVO.getAdPayDate()%>">
										<input type="hidden" name="adPic" value="<%=adVO.getAdPic()%>">
										<input type="hidden" name="adPicContent"
											value="<%=adVO.getAdPicContent()%>">
										<input type="hidden" name="adHit" value="<%=adVO.getAdHit()%>">

										<button type="submit" class="btn btn-primary ">
											<span class="glyphicon glyphicon-pencil">送出修改</span>
										</button>
										</FORM>
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
