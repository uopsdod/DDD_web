<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ord.model.*, java.text.SimpleDateFormat"%>

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

<%@ include file="/backend/backendBody.jsp"%>

<%
if(!authorityList.contains("102")){
	response.sendRedirect(request.getContextPath()+"/backend/emp_index.jsp");
}
%>	

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
<!-- 							<th colspan='2' class="text-center">操作</th> -->
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
								
								<td><%=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(((OrdVO)(pageContext.getAttribute("ordVO"))).getOrdDate())%></td>		
								
								<%-- 			<td>${ordVO.ordStatus}</td> --%>
								<%-- 			<td><%=ordStatusTrans.get( ((OrdVO)(pageContext.getAttribute("ordVO"))).getOrdStatus() )%></td> --%>

								<td>${ordStatusTrans.get(ordVO.ordStatus)}</td>

								<%-- 			<td>${ordVO.ordRatingContent}</td> --%>
								<%-- 			<td>${ordVO.ordRatingStarNo}</td> --%>
								<%-- 			<td>${ordVO.ordMsgNo}</td> --%>
								<%-- 			<td><img src="DBGifReader4?ordId=${ordVO.ordId}"></td> --%>
<!-- 								<td> -->
<%-- 									<form method="post" action="<%=request.getContextPath()%>/ord/ord.do"> --%>
<!-- 										<button type="submit" class="btn btn-primary "> -->
<!-- 											<span class="glyphicon glyphicon-pencil">修改</span> -->
<!-- 										</button> -->
<%-- 										<input type="hidden" name="ordId" value="${ordVO.ordId}"> --%>
<%-- 										<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> --%>
<%-- 										<input type="hidden" name="whichPage" value="<%=whichPage%>"> --%>
<!-- 										<input type="hidden" name="action" value="getOneForUpdate"> -->
<!-- 									</form> -->
<!-- 								</td> -->
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