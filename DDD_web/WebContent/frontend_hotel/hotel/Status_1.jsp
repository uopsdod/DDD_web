<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hotel.model.*"%>
<%
session.getAttribute("account_hotel");
HotelVO hotelVO =(HotelVO)session.getAttribute("hotelVO");
session.setAttribute("hotelVO", hotelVO);
%>

<%
	HotelService dao = new HotelService();
	HotelVO hotel = (HotelVO)dao.getOne(hotelVO.getHotelId());
	pageContext.setAttribute("hotel", hotel);
%>
<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>廠商前端</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/frontend_hotel/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/frontend_hotel/css/0_main.css">
		<script src="<%=request.getContextPath()%>/frontend_hotel/js/jquery.js"></script>
		<script src="<%=request.getContextPath()%>/frontend_hotel/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath()%>/frontend_hotel/js/0_new.js "></script>
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<style type="text/css">
				
			.vcenter {
			    display: inline-block;
			    vertical-align: middle;
			    float: none;
			}
			.tablediv{
				margin-top:5%;
			}
			#tablcbasic td{
		background-color:#fffaf0;
	}
	.UserName {
	font-size: 18px;
	margin-top: 12px;
	margin-right: 20px;
	padding: 4px 18px;
	background: white;
	border: 1 none;
	cursor: pointer;
	-webkit-border-radius: 5px;
	border-radius: 5px;
}
.titletext{
	margin-top:5px;
	font-size: 18px;
	font-family: Tahoma, Verdana, 微軟正黑體;
}
.downlist{
	font-size: 20px;
	width:200px;
}
.LoginButton {
	z-index: 1;
	background: #dc6eab;
	color: #ffffff;
	position: relative;
	font-weight: bold;
	font-family: Tahoma, Verdana, 微軟正黑體;
	padding: 10px;
	font-size: 20px;
	border: 0px;
	border-radius: 5px;
	width: 125px;
	margin-top:10px;
}
#files {
	text-align: center;
}

.monkeyb-cust-file {
	margin-top: 3%;
	overflow: hidden;
	position: relative;
	display: inline-block;
	background-color: #0283df;
	color: #fff;
	text-align: center;
	-web-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
	padding: 10px 10px;
	font-size: 16px;
	font-family: Arial, Microsoft JhengHei;
}

	.monkeyb-cust-file input {
		position: absolute;
		opacity: 0;
		filter: alpha(opacity = 0);
		top: 0;
		right: 0;
		width: 100%;
		height: 100%;
	}
	
		</style>
		</head>
<body style="background:#FFFAF0">		
		<div id="top-bar" >
			<nav class="navbar navbar-inverse" role="navigation">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
						<span class="sr-only">選單切換</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#"><span class="glyphicon glyphicon-heart-empty" style="font-size:30px">DDD廠商</span> </a>
				</div>
			
				<!-- 手機隱藏選單區 -->
				<div class="collapse navbar-collapse navbar-ex1-collapse">			
					<!-- 右選單 -->
					<ul class="nav navbar-nav navbar-right">
						<li><a href="<%=request.getContextPath()%>/frontend_hotel/hotel/addhotel.jsp"><span class="glyphicon glyphicon-bell"></span> 註冊</a></li>
						<li><a href="<%=request.getContextPath()%>/frontend_hotel/hotel/loginhotel.jsp"><span class="glyphicon glyphicon-log-out"></span> 登入</a></li>
					</ul>
				</div>
				<!-- 手機隱藏選單區結束 -->
			</nav>
		</div> 
		
<div class="col-xs-12 col-sm-12 tablediv" align="center"> 
	<h1>修改資料後請耐心再等待我們審核</h1>	
	<h1>修改廠商基本資料</h1>	
	<c:if test="${not empty errorMsgs}">
			<font color='red'>
					<c:forEach var="message" items="${errorMsgs}">
						${message}<br>
					</c:forEach>
			</font>
		</c:if>
			
			<form METHOD="POST" ACTION="<%=request.getContextPath()%>/hotel/hotel.do" name="form1" enctype="multipart/form-data" id="table">
				<table border="0" align="center" id ="tablcbasic">
			
				<%
					List<String> list= new ArrayList<String>();
					list.add("飯店");
					list.add("汽車旅館");
					list.add("民宿");
					list.add("渡假村");
					pageContext.setAttribute("list", list);
				%>
				<tr>
					<td class="titletext">種類:<font color=red><b>*</b></font></td>
					<td><select size="1" name="hotelType" class="downlist">

							<c:forEach var="hotellist" items="${list}">
							<option value="${hotellist}" <c:if test="${fn:contains(hotellist,hotel.hotelType)}">selected</c:if>>${hotellist}
							 </c:forEach>

					</select></td>
				</tr>
				
				
				<tr>
					<td>廠商名稱:</td>
					<td><input type="TEXT" name="hotelName" size="45" 
						class="UserName" placeholder="請輸入貴旅館的名字"
						value="${hotel.hotelName}" /></td>
				</tr>

				<!-- 請輸入6~10字 -->
<!-- 				<tr> -->
<!-- 					<td>密碼:</td> -->
<!-- 					<td><input type="password" name="empPwd" size="45" -->
<!-- 						class="UserName" placeholder="請輸入密碼" -->
<%-- 						value="<%=(empVO == null) ? "" : empVO.getEmpPwd()%>" /></td> --%>
<!-- 				</tr> -->

				<tr>
					<td>統一編號:</td>
					<td><input type="TEXT" name="hotelTaxId" size="45" maxlength="8"
						class="UserName" placeholder="請輸入統一編號"
						value="${hotel.hotelTaxId}" /></td>
				</tr>

				<tr>
					<td>縣市:</td>
					<td><input type="TEXT" name="hotelCity" size="45" maxlength="4"
						class="UserName" placeholder="輸入你所在的縣市"
						value="${hotel.hotelCity}" /></td>
				</tr>

				<tr>
					<td>鄉鎮區:</td>
					<td><input type="TEXT" name="hotelCounty" size="45" maxlength="4"
						class="UserName" placeholder="輸入鄉鎮區"
						value="${hotel.hotelCounty}" /></td>
				</tr>

				<tr>
					<td>路名牌號:</td>
					<td><input type="TEXT" name="hotelRoad" size="45" maxlength="15"
						class="UserName" placeholder="輸入路名牌號"
						value="${hotel.hotelRoad}" /></td>
				</tr>
				
				<tr>
					<td>負責人姓名:</td>
					<td><input type="TEXT" name="hotelOwner" size="45" maxlength="5"
						class="UserName" placeholder="輸入負責人"
						value="${hotel.hotelOwner}" /></td>
				</tr>
				
				<tr>
					<td>信箱:</td>
					<td><input type="TEXT" name="hotelAccount" size="45" 
						class="UserName" placeholder="輸入信箱"
						value="${hotel.hotelAccount}" /></td>
				</tr>
				
<!-- 				<tr> -->
<!-- 					<td>密碼:</td> -->
<!-- 					<td><input type="password" name="hotelPwd" size="45" maxlength="10" -->
<!-- 						class="UserName" placeholder="輸入密碼" -->
<%-- 						value="${hotel.hotelPwd}" /></td> --%>
<!-- 				</tr> -->

				<tr>
					<td>網站連結:</td>
					<td><input type="TEXT" name="hotelLink" size="45" maxlength="60"
						class="UserName" placeholder="輸入網站連結"
						value="${hotel.hotelLink}" /></td>
				</tr>
				
				<tr>
					<td>聯絡電話:</td>
					<td><input type="TEXT" name="hotelPhone" size="45" maxlength="10"
						class="UserName" placeholder="輸入聯絡電話"
						value="${hotel.hotelPhone}" /></td>
				</tr>
				
				<tr>
					<td>信用卡卡號:</td>
					<td><input type="TEXT" name="hotelCreditCardNo" size="45" maxlength="16"
						class="UserName" placeholder="輸入信用卡卡號"
						value="${hotel.hotelCreditCardNo}" /></td>
				</tr>
				
				<tr>
					<td>信用卡驗證碼:</td>
					<td><input type="TEXT" name="hotelCreditCheckNo" size="45" maxlength="3"
						class="UserName" placeholder="輸入信用卡驗證碼"
						value="${hotel.hotelCreditCheckNo}" /></td>
				</tr>
				
				<tr>
					<td>信用卡有效日期:</td>
					<td><input type="TEXT" name="hotelCreditDueDate" size="45" maxlength=""
						class="UserName" placeholder="YYYY-MM"
						value="${hotel.hotelCreditDueDate}" /></td>
				</tr>
				
				<tr>
					<td class="titletext">旅館業者登記証:</td>
					<td>
					<div class="monkeyb-cust-file" >
	                    <img />
	                    <span>Select File</span>
					<input type="file" name="upfile1" id="myFile" />
					</div>
						<p>
							<output id="image_output"></output>
						</p>
					</td>
				</tr>
				
				<tr>
					<td class="titletext">封面照片: </td>
					<td>
					<div class="monkeyb-cust-file" >
	                    <img />
	                    <span>Select File</span>
					<input type="file" name="upfile2" id="myFile1" />
					</div>
						<p>
							<output id="image_output1"></output>
						</p>
					</td>
				</tr>
				
				<tr>
					<td>廠商簡介:</td>
					<td>
						<textarea name="hotelIntro" rows="5" cols="40" style='resize : none;width:470px'
						onkeyup="this.value = this.value.slice(0, 400)">${hotel.hotelIntro}</textarea>
					</td>
				</tr>
				
				<tr>
					<%--審核狀態--%>
					<td><input type="hidden" name="hotelStatus" size="45"
						value="0" /></td>
				</tr>
				
				<br>
			</table>
			<input type="hidden" name="hotelId" value="${hotel.hotelId}"> 
			<input type="hidden" name="action" value="updatehotel_st_1"> 
			<input type="submit" value="送出新增" align="center" class="LoginButton">
		</form>		
		

		
</div>
<%
session.removeAttribute("account_hotel");
session.removeAttribute("hotelVO");
%>
<%@ include file="../footer.jsp" %>
</body>
</html>
<script>
	if (window.FileReader) { //測試瀏覽器
		document.getElementById("myFile").onchange = function() {

			for (var i = 0, file; file = this.files[i]; i++) { //var file;
				var reader = new FileReader();
				reader.onloadend = (function(file) {
					return function() {
						document.getElementById('image_output').innerHTML += '<img src="'+this.result +'" height="50"/> <br/>';
					}
				})(file); //自己CALL自己
				reader.readAsDataURL(file);
			}
		}
	} else {
		alert("瀏覽器不支援 HTML 5");
	}
	
	if (window.FileReader) { //測試瀏覽器
		document.getElementById("myFile1").onchange = function() {

			for (var i = 0, file; file = this.files[i]; i++) { //var file;
				var reader = new FileReader();
				reader.onloadend = (function(file) {
					return function() {
						document.getElementById('image_output1').innerHTML += '<img src="'+this.result +'" height="50"/> <br/>';
					}
				})(file); //自己CALL自己
				reader.readAsDataURL(file);
			}
		}
	} else {
		alert("瀏覽器不支援 HTML 5");
	}
</script>