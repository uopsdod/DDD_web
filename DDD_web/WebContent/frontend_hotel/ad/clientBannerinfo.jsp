<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hotel.model.*"%>
<%@ include file="../head.jsp"%>
<%@ page import="com.ad.model.*"%>
<%@ page import="com.adplan.model.*"%>
<!-- <script src="/frontend_hotel/js/sweetalert.js"></script> -->
<jsp:useBean id="hotelSvc" scope="page"
	class="com.hotel.model.HotelService" /> <!-- 跟25行一樣的意思 -->
<script src="/DDD_web/frontend_hotel/js/sweetalert.min.js"></script> <link rel="stylesheet" type="text/css" href="/DDD_web/frontend_hotel/css/sweetalert.css">


<%
	//AdPlanVO adPlanVO = (AdPlanVO) request.getAttribute("adPlanVO");/* 購買頁面傳過來的 */
%>

<%
	AdVO adVO = (AdVO) request.getAttribute("adVO");/* get修正過後的資料 java 寫法要取值就要加這個or從上一個頁面取值*/
%>


<%
	HotelService dao = new HotelService(); /* 拿到service物件 */

	HotelVO hotel = (HotelVO) dao.getOne(hotelVO.getHotelId());/* 用service物件去拿資料 */

	pageContext.setAttribute("hotel", hotel);/*放在這網頁才看得到  */
%>
<style>
 .tablediv{
 margin-top:10%;
 }
</style>

<script src="https://code.jquery.com/jquery.js"></script>
<script type="text/javascript">

	function doFirst() {
		
		document.getElementById('adPic').onchange = fileChange;
		/* 
		 $('.h_alert').click(function() {
			sweetAlert("submit", "提交成功", "success"); // 第3個參數換了
		});  */
	}
	function fileChange(e) {
		var file = document.getElementById('adPic').files[0];
		var readFile = new FileReader();
		readFile.readAsDataURL(file);
		readFile.onload = function() {
			var image = document.getElementById('image');
			image.src = readFile.result;
			image.width = 350;

		}

	}
	var isCheckImageType = true; 
	function checkFile() { 
		var f = document.form1; 
		var re = /\.(jpg|gif)$/i;  //允許的圖片副檔名 
		
		if (isCheckImageType && !re.test(f.adPic.value)) { 
			alert("需有圖片且圖片必須為JPG或GIF影像檔"); 
		} else { 
			console.log(f);
			
			/* f.submit(); */
			setTimeout(function(){
				f.submit();
			},1000);
			sweetAlert("submit", "提交成功", "success");
		} 
	} 

	window.addEventListener('load', doFirst, false);

</script>






<div class="col-xs-12 col-sm-10 bb" style="background-color:#FFFAF0;postion:relative;top:220px" >
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3">
				<center>
					<h3>填寫購買資料</h3>
				</center>




           
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Ad/ad.do"
					name="form1" enctype="multipart/form-data">

					<div class="form-horizontal">
						<div class="form-group">
							<label for="user_name" class="col-sm-3 control-label">Banner方案名稱</label>
							<div class="col-sm-9">


								<div>${adPlanVO.adPlanName}</div>

							</div>
						</div>
						<div class="form-horizontal">
							<div class="form-group">
								<label for="user_name" class="col-sm-3 control-label">廠商名稱</label>
								<div class="col-sm-9">
									<%-- <input type="text" name=" hotelName" id="user_name"
										class="form-control" value="${hotel.hotelName}"> --%>

									<div>${hotel.hotelName}</div>
								</div>
							</div>

							<div class="form-group">
								<label for="user_name" class="col-sm-3 control-label">帳號(Email)</label>
								<div class="col-sm-9">

									<div>${hotel.hotelAccount}</div>
								</div>
							</div>

							<div class="form-group">
								<label for="user_name" class="col-sm-3 control-label">圖片內容說明</label>
								<div class="col-sm-9">
									<input type="text" name=" adPicContent" id="user_name"
										class="form-control" size="100"
										value="<%=(adVO == null) ? " " : adVO.getAdPicContent()%>" required/>
								</div>
							</div>

							<div class="form-group">
								<label for="user_name" class="col-sm-3 control-label">圖片</label>
								<div class="col-sm-9">
									<input type="file" name="adPic" id="adPic" class="form-control"
										style="border: 0px;"> <img id="image"
										style="margin-top: 10px;"> <input type="hidden"
										name="action" value="insert">
									<button type="button" class="btn btn-success" onclick="checkFile()">購買</button>
									<input type="hidden" name="adId" value=${adVO.adId}> <br>
									<input type="hidden" name="adHotelId" value=${hotel.hotelId}>
									<br> <input type="hidden" name="adAdPlanId"
										value=${adPlanVO.adPlanId}> <br> <input
										type="hidden" name="adStatus" value="0"> <br> <input
										type="hidden" name="adPayDate" value=""> <input
										type="hidden" name="adHit" value="0">
									
				</form>


				<c:if test="${adVO.adSatus==2}">
					<table>
						<tr>
							<td>123</td>
						</tr>
					</table>
				</c:if>

				<%-- <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Ad/ad.do"
					name="form1" enctype="multipart/form-data">
					<table border="0">
							<tr> 自動產生
				<td>Banner編號:</td>
				<td><input type="text" name="adid" size="45"
					value="<%=(adVO == null) ? "10000001" : adVO.getAdId()%>" /></td>
			</tr>


						<tr>
							<td>Banner方案編號:</td>
							<td>${adPlanVO.adPlanName}</td>
						</tr>
						<tr>
							<td>廠商會員編號:</td>
							<td><input type="text" name="adHotelId" size="45"
								value="<%=(adVO == null) ? "" : adVO.getAdHotelId()%>" /></td>
						</tr>
						<tr>
							<td>處理狀態:</td>
							<td><select size="1" name="adStatus">

									<option value='0'>未處理
									<option value='1'>圖片未通過
									<option value='2'>未繳費
									<option value='3'>已繳費
									<option value='4'>已上架
									<option value='5'>已下架
							</select></td>
						</tr>

						<tr>
							<%
								java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());
							%>
							<td>繳費日期:</td>
							<td bgcolor="#CCCCFF"><input class="cal-TextBox"
								onFocus="this.blur()" size="9" readonly type="text"
								name="adPayDate"
								value="<%=(adVO == null) ? date_SQL : adVO.getAdPayDate()%>">
								<a class="so-BtnLink" href="javascript:calClick();return false;"
								onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
								onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
								onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','adPayDate','BTN_date');return false;">
									<img align="middle" border="0" name="BTN_date"
									src="images/btn_date_up.gif" width="22" height="17" alt="開始日期">
							</a></td>
						</tr>
						<tr>

							<td>圖片:</td>
							<td><input type="file" name="adPic"></td>
						</tr>
						<tr>
							<td>圖片內容說明:</td>
							<td><input type="text" name="adPicContent" size="100"
								value="<%=(adVO == null) ? "漂亮嗎" : adVO.getAdPicContent()%>" /></td>
						</tr>
						<tr>
							<td>點擊數:</td>
							<td><input type="number" name="adHit" size="45"
								value="<%=(adVO == null) ? "10" : adVO.getAdHit()%>" /></td>
						</tr>


					</table>
					<input type="submit" value="送出新增"> <br> <input
						type="hidden" name="action" value="insert"> <br> <input
						type="hidden" name="adId" value=${adVO.adId}> <br>
					<input type="hidden" name="adHotelId" value=${hotel.hotelId}>
					<br> <input type="hidden" name="adAdPlanId"
						value=${adPlanVO.adPlanId}> <br> <input type="hidden"
						name="adStatus" value="0"> <br> <input type="hidden"
						name="adPayDate" value="2016-12-11"> <input type="hidden"
						name="adHit" value="0">
				</form>

 --%>
			</div>
		</div>
	</div>
</div>

<%@ include file="../footer.jsp"%>