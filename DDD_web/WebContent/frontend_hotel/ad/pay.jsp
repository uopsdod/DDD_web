<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hotel.model.*"%>
<%@ include file="../head.jsp"%>
<%@ page import="com.ad.model.*"%>
<%@ page import="com.adplan.model.*"%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li>${message}</li>
			</c:forEach>
		</ul>
	</font>
</c:if>
<%
	//AdVO adVO = (AdVO) request.getAttribute("adVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
	String adid = request.getParameter("adid");
	AdVO adVO = new AdService().getOneAd(adid);
	pageContext.setAttribute("adVO", adVO);//要寫這個EL才能取
%>



<%
	/* 在網頁使用java語法 */
	AdService adSvc = new AdService(); /* 建一個Service物件 */
	String hotelId = (String) session.getAttribute("hotelId");
	List<AdVO> list = adSvc.getAll(hotelId);/* 用Service物件取得資料庫資料 */
	pageContext.setAttribute("list", list); /* 把資料放在當前頁面 */
%>
<!-- <script src="/frontend_hotel/js/sweetalert.js"></script> -->
<jsp:useBean id="hotelSvc" scope="page"
	class="com.hotel.model.HotelService" />
<!-- 跟25行一樣的意思 -->
<script
	src="<%=request.getContextPath()%>/frontend_hotel/js/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/DDD_web/frontend_hotel/css/sweetalert.css">
<link rel="stylesheet" type="text/css" href="sweetalert.css">
<style type="text/css">
#h_title {
	background-color: #43b7ae;
	font-size: xx-large;
	text-align: center;
	color: white;
}

#h_border {
	border: 1px solid #43b7ae;
	font-size: xx-large;
}

#h_border tr {
	background-color: white;
	border: 1px solid #43b7ae;
}

.tablediv {
	margin-top: 5%;
}

.h_alert {
	margin: 5px;
	
}

#h_button {
	margin: 5px;
	
}
</style>
<script>
	$(document).ready(function() {
		$("#h_button").click(function() {
			$("input[name=credit01]").val("5614");
			$("input[name=credit02]").val("4586");
			$("input[name=credit03]").val("3654");
			$("input[name=credit04]").val("5614");
			$("input[name=credit05]").val("03");
			$("input[name=credit06]").val("2017");
			$("input[name=credit07]").val("336");
		});
	});
</script>
</head>

<body>

	<div class="col-xs-12 col-sm-10 tablediv" align="center">
		<table border="1" id="h_border">
			<caption id="h_title">
				<img src="images/abc.jpg" width="50" height="50">ABC銀行
			</caption>
			<tr>
				<td>訂單編號</td>
				<td>${param.adid}</td>
			</tr>
			<!-- 君毅 為什麼要remove? -->
			<%-- <c:remove var="adid" scope="session"/>	 --%>
			<tr>
				<td>信用卡號</td>
				<td><input type="text" name="credit01" size="3">-<input
					type="text" name="credit02" size="3">-<input type="text"
					name="credit03" size="3">-<input type="text"
					name="credit04" size="3"></td>
			</tr>
			<tr>
				<td>有效日期(Month/Year)</td>
				<td><input type="text" name="credit05" size="3">月<input
					type="text" name="credit06" size="3">年</td>
			</tr>
			<tr>
				<td>驗證碼(卡片背面末3碼)</td>
				<td><input type="text" name="credit07" size="3"></td>
			</tr>

			<tr>
				<td colspan="2" ><center>
						<input type="submit" class="h_alert" name="submit" value="確認付款" >
						<FORM METHOD="post" name="form2"
							ACTION="<%=request.getContextPath()%>/Ad/ad.do">
							<input type="hidden" name="action" value="updatesStatus">
							<input type="hidden" name="adId" value=${adVO.adId}> <input
								type="hidden" name="adAdPlanId" value=${adVO.adAdPlanId}>
							<input type="hidden" name="adHotelId" value=${adVO.adHotelId}>
							<input type="hidden" name="adStatus" value=${adVO.adStatus}>
							<input type="hidden" name="adPic" value=${adVO.adPic}> <input
								type="hidden" name="adPayDate" value=${adVO.adPayDate}>
							<input type="hidden" name="adPicContent"
								value=${adVO.adPicContent}> <input type="hidden"
								name="adHit" value=${adVO.adHit}>



						</FORM>

						<button type="submit" class="btn btn-info" id="h_button">
						<span class="glyphicon glyphicon-remove">神奇按鈕</span>
						</button>
					</center></td>
			</tr>


		</table>
	</div>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$('.h_alert').click(function() {
			var b = document.form2;
			setTimeout(function() {
				b.submit();
			}, 1000);
			sweetAlert("submit", "提交成功", "success");
		});
	</script>
</body>

</html>