<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hotel.model.*"%>


<%
HotelVO hotelVO = (HotelVO) request.getAttribute("hotelVO");
%>

<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/frontend_hotel/hotel/css/addhotel.css">
		<script src="<%=request.getContextPath()%>/frontend_hotel/hotel/js/addhotel.js"></script>
		
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
						<li><a href='#modal-id' data-toggle="modal" ><span class="glyphicon glyphicon-bell"></span> 註冊</a></li>
						<li><a href="<%=request.getContextPath()%>/frontend_hotel/hotel/loginhotel.jsp"><span class="glyphicon glyphicon-log-out"></span> 登入</a></li>
					</ul>
				</div>
				<!-- 手機隱藏選單區結束 -->
			</nav>
		</div> 
					
<div class="col-xs-12 col-sm-12 tablediv" align="center" width="102%"> 
<!-- -------------------------------背景圖-------------------------------------------- -->
		<img src="<%=request.getContextPath()%>/frontend_hotel/hotel/img/light-property-min-2.jpg" width="1924px" height="500px" class="bgimgOfjoin">
		<a href='#modal-id' data-toggle="modal" ><input type="button" value="加入我們" class="joinus"></a>
<!-- 		-----------------------------展示特色----------------------------------------------- -->
		<h1>為什麼選擇DDD.com?</h1>
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12" >
					<div class="container">
						<div class="row" id="row">
							<div class="borderOfBanner col-xs-12 col-sm-3">
								<div class="item" >
									<img src="<%=request.getContextPath()%>/frontend_hotel/hotel/img/image009.jpg" class="img-responsive">
									<br>
									<div align="left" style="font-size:20px"><img src="<%=request.getContextPath()%>/frontend_hotel/hotel/img/checked.png" width="25px" height="25px"> 註冊完全免費</div>
									<div align="left" style="font-size:20px;margin-top:5px;"><img src="<%=request.getContextPath()%>/frontend_hotel/hotel/img/checked.png" width="25px" height="25px"> 刊登價格最優惠</div>
								</div>
							</div>
							<div class="borderOfBanner col-xs-12 col-sm-3">
								<div class="item" >
									<img src="<%=request.getContextPath()%>/frontend_hotel/hotel/img/service.png" class="img-responsive">
									<br>
									<div align="left" style="font-size:20px"><img src="<%=request.getContextPath()%>/frontend_hotel/hotel/img/checked.png" width="25px" height="25px"> 全天24小時多語種服務</div>
									<div align="left" style="font-size:20px;margin-top:5px;"><img src="<%=request.getContextPath()%>/frontend_hotel/hotel/img/checked.png" width="25px" height="25px"> 當地團隊隨時提供協助</div>
								</div>
							</div>
							<div class="borderOfBanner col-xs-12 col-sm-3">
								<div class="item" >
									<img src="<%=request.getContextPath()%>/frontend_hotel/hotel/img/0316.jpg" class="img-responsive">
									<br>
									<div align="left" style="font-size:20px"><img src="<%=request.getContextPath()%>/frontend_hotel/hotel/img/checked.png" width="25px" height="25px"> 遍布全球的廣泛客源</div>
									<div align="left" style="font-size:20px;margin-top:5px;"><img src="<%=request.getContextPath()%>/frontend_hotel/hotel/img/checked.png" width="25px" height="25px"> 向世界各地展示貴酒店</div>
								</div>
							</div>
							<div class="borderOfBanner col-xs-12 col-sm-3" >
								<div class="item" >
									<img src="<%=request.getContextPath()%>/frontend_hotel/hotel/img/banner4.jpg" class="img-responsive">
									<br>
									<div align="left" style="font-size:20px"><img src="<%=request.getContextPath()%>/frontend_hotel/hotel/img/checked.png" width="25px" height="25px"> 高曝光率廣告刊登</div>
									<div align="left" style="font-size:20px;margin-top:5px;"><img src="<%=request.getContextPath()%>/frontend_hotel/hotel/img/checked.png" width="25px" height="25px"> 增加您出售的速度</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	
		
<!-- 		------------------------------註冊------------------------------------------- -->
		<%-- 錯誤表列 --%>
		
		<div class="modal fade" id="modal-id">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title"><img src="<%=request.getContextPath()%>/frontend_hotel/hotel/img/bed.png">註冊DDD快速出售您剩餘的空房</h4>
					</div>
<!-- 					開始				 -->
					<form METHOD="POST" ACTION="<%=request.getContextPath()%>/hotel/hotel.do" name="form1" enctype="multipart/form-data" id="table">
						<c:if test="${not empty errorMsgs}">
							<font color='red' >				
									<c:forEach var="message" items="${errorMsgs}">
										${message}<br>
									</c:forEach>				
							</font>
						</c:if>
						<table  align="center" class="bgtable" >
							<tr>
								<td class="td">廠商種類名稱</td>
								<td class="td"><select size="1" name="hotelType" class="col-xs-6 col-sm-6"  style="color:black" >						
										<option value="汽車旅館" style="color:black">汽車旅館</option>
										<option value="渡假村"  style="color:black">渡假村</option>
										<option value="民宿"  style="color:black">民宿</option>
										<option value="飯店"  style="color:black">飯店</option>
								</select>
								</td>
							</tr>
							
			
							<tr>
								<td class="td">廠商名稱:</td>
								<td ><input type="TEXT" name="hotelName" size="45" 
									class="UserName" placeholder="請輸入貴旅館的名字"
									value="<%=(hotelVO == null) ? "" : hotelVO.getHotelName()%>" /></td>
							</tr>
			
							<tr>
								<td class="td">統一編號:</td>
								<td><input type="TEXT" name="hotelTaxId" size="45" maxlength="8"
									class="UserName" placeholder="請輸入統一編號"
									value="<%=(hotelVO == null) ? "" : hotelVO.getHotelTaxId()%>" /></td>
							</tr>
			
							<tr>
								<td class="td">縣市:</td>
								<td><input type="TEXT" name="hotelCity" size="45" maxlength="4"
									class="UserName" placeholder="輸入你所在的縣市"
									value="<%=(hotelVO == null) ? "" : hotelVO.getHotelCity()%>" /></td>
							</tr>
			
							<tr>
								<td class="td">鄉鎮區:</td>
								<td><input type="TEXT" name="hotelCounty" size="45" maxlength="4"
									class="UserName" placeholder="輸入鄉鎮區"
									value="<%=(hotelVO == null) ? "" : hotelVO.getHotelCounty()%>" /></td>
							</tr>
			
							<tr>
								<td class="td">路名牌號:</td>
								<td><input type="TEXT" name="hotelRoad" size="45" maxlength="15"
									class="UserName" placeholder="輸入路名牌號"
									value="<%=(hotelVO == null) ? "" : hotelVO.getHotelRoad()%>" /></td>
							</tr>
							
							<tr>
								<td class="td">負責人姓名:</td>
								<td><input type="TEXT" name="hotelOwner" size="45" maxlength="5"
									class="UserName" placeholder="輸入負責人"
									value="<%=(hotelVO == null) ? "" : hotelVO.getHotelOwner()%>" /></td>
							</tr>
							
							<tr>
								<td class="td">信箱:</td>
								<td><input type="TEXT" name="hotelAccount" size="45" 
									class="UserName" placeholder="輸入信箱"
									value="<%=(hotelVO == null) ? "" : hotelVO.getHotelAccount()%>" /></td>
							</tr>
							
							<tr>
								<td class="td">密碼:</td>
								<td><input type="password" name="hotelPwd" size="45" maxlength="10"
									class="UserName" placeholder="輸入密碼"
									value="<%=(hotelVO == null) ? "" : hotelVO.getHotelPwd()%>" /></td>
							</tr>
			
							<tr>
								<td class="td">網站連結:</td>
								<td><input type="TEXT" name="hotelLink" size="45" maxlength="60"
									class="UserName" placeholder="輸入網站連結"
									value="<%=(hotelVO == null) ? "" : hotelVO.getHotelLink()%>" /></td>
							</tr>
							
							<tr>
								<td class="td">聯絡電話:</td>
								<td><input type="TEXT" name="hotelPhone" size="45" maxlength="10"
									class="UserName" placeholder="輸入聯絡電話"
									value="<%=(hotelVO == null) ? "" : hotelVO.getHotelPhone()%>" /></td>
							</tr>
							
							<tr>
								<td class="td">信用卡卡號:</td>
								<td><input type="TEXT" name="hotelCreditCardNo" size="45" maxlength="16"
									class="UserName" placeholder="輸入信用卡卡號"
									value="<%=(hotelVO == null) ? "" : hotelVO.getHotelCreditCardNo()%>" /></td>
							</tr>
							
							<tr>
								<td class="td">信用卡驗證碼:</td>
								<td><input type="TEXT" name="hotelCreditCheckNo" size="45" maxlength="3"
									class="UserName" placeholder="輸入信用卡驗證碼"
									value="<%=(hotelVO == null) ? "" : hotelVO.getHotelCreditCheckNo()%>" /></td>
							</tr>
							
							<tr>
								<td class="td">信用卡有效日期:</td>
								<td><input type="TEXT" name="hotelCreditDueDate" size="45" maxlength=""
									class="UserName" placeholder="YYYY-MM"
									value="<%=(hotelVO == null) ? "" : hotelVO.getHotelCreditDueDate()%>" /></td>
							</tr>
							
							<tr>
								<td class="td">業者登記証:</td>
								<td>
									<div class="monkeyb-cust-file" >
					                <img />
					                <span>Select File</span>
									<input type="file" name="upfile1" id="myFile" /></div>
									<p>
										<output id="image_output"></output>
									</p>
								</td>
							</tr>
							
							<tr>
								<td class="td">封面照片:</td>
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
								<td class="td">廠商簡介:</td>
								<td>
									<textarea name="hotelIntro" rows="5" cols="40" style='resize : none'
									onkeyup="this.value = this.value.slice(0, 400)"></textarea>
								</td>
							</tr>
							
							<tr>
								<%--審核狀態--%>
								<td><input type="hidden" name="hotelStatus" size="45"
									value="0" /></td>
							</tr>
			
							<tr>
								<%--黑名單--%>
								<td><input type="hidden" name="hotelBlackList" size="45"
									value="0" /></td>
							</tr>
			
							<tr>
								<%--評價總人數--%>
								<td><input type="hidden" name="hotelRatingTotal" size="45"
									value="0" /></td>
							</tr>
							
							<tr>
								<%--評價統計結果--%>
								<td><input type="hidden" name="hotelRatingResult" size="45"
									value="0" /></td>
							</tr>		
							
							<br>
						</table>
						
						<input type="hidden" name="action" value="inserthotel"> 
						<input type="submit" value="送出新增" align="center" class="LoginButton"><br>
						<input type="button" align="center" class="LoginButton" id ="magic" value="神奇小按鈕" class="ui">
								
					</form>		
<!-- 				結束	 -->
					
				</div>
			</div>
		</div>		
</div>

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
	
	
    $(function(){
        $('[data-toggle="tooltip"]').tooltip();
        $('[data-toggle="popover"]').popover();
    })
	




</script>