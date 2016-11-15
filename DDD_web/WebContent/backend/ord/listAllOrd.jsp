<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*, com.ord.model.*, java.text.SimpleDateFormat"%>

<%-- 用EL練習寫 --%>

<%
	OrdService ordSvc = new OrdService();
	List<OrdVO> list = ordSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!-- 請輸入標題 -->
	<title>所有訂單查詢 - listAllOrd.jsp</title>
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/css/bootstrap.css">
	<!-- 自訂CSS -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/css/0_main.css">
	
	<script src="<%=request.getContextPath()%>/backend/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/backend/js/bootstrap.js"></script>
	<!-- 自訂JavaScript -->
	<script src="<%=request.getContextPath()%>/backend/css/0_new.js"></script>
</head>

<body>
	<!-- 這是頂端那條Bar -->
	<div id="top-bar">
		<nav class="navbar navbar-inverse" role="navigation">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
					<span class="sr-only">選單切換</span> 
					<span class="icon-bar"></span>
					<span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"><span class="glyphicon glyphicon-heart-empty"></span> DDD後端管理介面</a>
			</div>

			<!-- 手機隱藏選單區 -->
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<!-- 右選單 -->
				<ul class="nav navbar-nav navbar-right">
					<!-- 抓使用者的名字 -->
					<li><a href="#">大城 您好 !</a></li>
					<li><a href="#"><span class="glyphicon glyphicon-bell"></span>通知</a></li>
					<li><a href="#"><span class="glyphicon glyphicon-log-out"></span>登出</a></li>
				</ul>
			</div>
			<!-- 手機隱藏選單區結束 -->
		</nav>
	</div>

	<div class="container-fluid">
		<div class="row">
			<!-- 左邊的手風琴 -->
			<div class="col-xs-12 col-sm-2 aa" style="background-color: #DCDCDC;">
				<div class="panel-group" id="accordion2" role="tablist" aria-multiselectable="true">
					<!-- 區塊A(訂單) -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="tab1" style="background-color: #B0C4DE;">
							<h4 class="panel-title">
								<a role="button" data-toggle="collapse" data-parent="#accordion2" href="#aaa" aria-expanded="true" aria-controls="aaa">
									<span class="glyphicon glyphicon-list-alt"></span> 訂單
								</a>
							</h4>
						</div>
						<div id="aaa" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="tab1">
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
						<div class="panel-heading" role="tab" id="tab4" style="background-color: #B0C4DE;">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion2" href="#ddd" aria-expanded="false" aria-controls="ddd">
									<span class="glyphicon glyphicon-bullhorn"></span> Banner
								</a>
							</h4>
						</div>
						<div id="ddd" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab4">
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
						<div class="panel-heading" role="tab" id="tab7" style="background-color: #B0C4DE;">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion2" href="#ggg" aria-expanded="false" aria-controls="fff">
									<span class="glyphicon glyphicon-piggy-bank"></span> 上架月租費
								</a>
							</h4>
						</div>
						<div id="ggg" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab7">
							<div class="panel-body">
								<ul style="list-style-type: none">
									<li>月租管理作業</li>
								</ul>
							</div>
						</div>
					</div>
					<!-- 區塊D(一般會員) -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="tab2" style="background-color: #B0C4DE;">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion2" href="#bbb" aria-expanded="false" aria-controls="bbb">
									<span class="glyphicon glyphicon-king"></span> 一般會員
								</a>
							</h4>
						</div>
						<div id="bbb" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab2">
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
						<div class="panel-heading" role="tab" id="tab3" style="background-color: #B0C4DE;">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion2" href="#ccc" aria-expanded="false" aria-controls="ccc">
									<span class="glyphicon glyphicon-queen"></span> 廠商會員
								</a>
							</h4>
						</div>
						<div id="ccc" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab3">
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
						<div class="panel-heading" role="tab" id="tab5" style="background-color: #B0C4DE;">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion2" href="#eee" aria-expanded="false" aria-controls="eee">
									<span class="glyphicon glyphicon-pawn"></span> 員工
								</a>
							</h4>
						</div>
						<div id="eee" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab5">
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
						<div class="panel-heading" role="tab" id="tab6" style="background-color: #B0C4DE;">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion2" href="#fff" aria-expanded="false" aria-controls="fff">
									<span class="glyphicon glyphicon-thumbs-down"></span> 檢舉
								</a>
							</h4>
						</div>
						<div id="fff" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab6">
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
						<div class="panel-heading" role="tab" id="tab8" style="background-color: #B0C4DE;">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion2" href="#hhh" aria-expanded="false" aria-controls="hhh">
									<span class="glyphicon glyphicon-thumbs-up"></span> 留言評價
								</a>
							</h4>
						</div>
						<div id="hhh" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab8">
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

				<!-- 麵包屑(當前路徑) -->
				<ol class="breadcrumb">
					<li>訂單</li>
					<li class="active">所有訂單查詢</li>
				</ol>

				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
				請修正以下錯誤:
				<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li>${message}</li>
						</c:forEach>
					</ul>
				</c:if>

				<!-- 主要的table -->
				<table class="table table-hover" border="1">
					<!-- table標題 -->
					<thead>
						<tr style="background-color: #B0C4DE;">
							<th class="text-center">訂單編號</th>
							<th class="text-center">房型名稱</th>
							<th class="text-center">一般會員名稱</th>
							<th class="text-center">廠商名稱名稱</th>
							<th class="text-center">訂單金額</th>
							<!-- 		<th>入住日期</th> -->
							<th class="text-center">下訂日期</th>
							<th class="text-center">訂單狀態名稱</th>
							<!-- 		<th>評價內容</th> -->
							<!-- 		<th>評價星星數</th> -->
							<!-- 		<th>簡訊驗證碼</th> -->
							<!-- 		<th>QR Code圖片</th> -->
							<th colspan='2' class="text-center">操作</th>
						</tr>
					</thead>

					<!-- table內容 -->
					<tbody>
						<%@ include file="pages/page1.file"%>
						<c:forEach var="ordVO" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">
							<tr align="center" valign='middle' ${(ordVO.ordId==param.ordId)? 'style="background-color:#CCCCFF"' :''}>
								<!--將修改的那頁換底色-->
								<td><a href="<%=request.getContextPath()%>/ord/ord.do?ordId=${ordVO.ordId}&action=getOneFrom04&requestURL=<%=request.getServletPath()%>&whichPage=<%=whichPage%>">${ordVO.ordId}</a></td>

								<td>${ordVO.ordRoomVO.roomName}</td>
								<td>${ordVO.ordMemVO.memName}</td>
								<td>${ordVO.ordHotelVO.hotelName}</td>

								<td>${ordVO.ordPrice}</td>
								<%-- 			<td><%=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(((OrdVO)(pageContext.getAttribute("ordVO"))).getOrdLiveDate())%></td> --%>
								<td><%=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(((OrdVO) (pageContext.getAttribute("ordVO"))).getOrdDate())%></td>

								<%-- 			<td>${ordVO.ordStatus}</td> --%>
								<%-- 			<td><%=ordStatusTrans.get( ((OrdVO)(pageContext.getAttribute("ordVO"))).getOrdStatus() )%></td> --%>

								<td>${ordStatusTrans.get(ordVO.ordStatus)}</td>

								<%-- 			<td>${ordVO.ordRatingContent}</td> --%>
								<%-- 			<td>${ordVO.ordRatingStarNo}</td> --%>
								<%-- 			<td>${ordVO.ordMsgNo}</td> --%>
								<%-- 			<td><img src="DBGifReader4?ordId=${ordVO.ordId}"></td> --%>
								<td>
									<form method="post" action="<%=request.getContextPath()%>/ord/ord.do">
										<input type="submit" value="修改" class="btn btn-primary">
										<input type="hidden" name="ordId" value="${ordVO.ordId}">
										<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
										<input type="hidden" name="whichPage" value="<%=whichPage%>">
										<input type="hidden" name="action" value="getOneForUpdate">
									</form>
								</td>
<!-- 								<td> -->
<%-- 									<form method="post" action="<%=request.getContextPath()%>/ord/ord.do"> --%>
<!-- 										<input type="submit" value="刪除" class="btn btn-danger"> <input type="hidden" -->
<%-- 											name="ordId" value="${ordVO.ordId}"> <input --%>
<!-- 											type="hidden" name="requestURL" -->
<%-- 											value="<%=request.getServletPath()%>"> <input --%>
<%-- 											type="hidden" name="whichPage" value="<%=whichPage%>"> --%>
<!-- 										<input type="hidden" name="action" value="delete"> -->
<!-- 									</form> -->
<!-- 								</td> -->
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<div class="col-xs-12 col-sm-12">
					<%@ include file="pages/page2.file"%>
				</div>
				
					
				<div id="simpleTable" class="col-xs-12 col-sm-12">			
					<% if (request.getAttribute("ordVO") != null) { %>
							<div class="col-xs-12 col-sm-3"></div>
						
							<div class="col-xs-6 col-sm-6">	
								<jsp:include page="listOneOrdSimple.jsp" />
							</div>
							<div class="col-xs-12 col-sm-3"></div>	
					<% } %>
				<div>

			</div>
		</div>
	</div>
</body>
</html>