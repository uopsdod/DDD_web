<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hotel.model.*"%>
<%@ include file="../head.jsp"%>
<%
	
%>
<%
	HotelService dao = new HotelService();
	HotelVO hotel = (HotelVO)dao.getOne(hotelVO.getHotelId());
	pageContext.setAttribute("hotel", hotel);
	
%>
<style>
	.tablediv{
		margin-top:10%;
	}
</style>

<div class="col-xs-12 col-sm-10 tablediv" align="center"> 
	<h1>修改廠商基本資料</h1>	
	<c:if test="${not empty errorMsgs}">
			<font color='red'>請修正以下錯誤:
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li>${message}</li>
					</c:forEach>
				</ul>
			</font>
		</c:if>
			
			<form METHOD="POST" ACTION="<%=request.getContextPath()%>/hotel/hotel.do" name="form1" enctype="multipart/form-data" id="table">
				<table border="0" align="center">
			
				<jsp:useBean id="hotelSvc" scope="page" class="com.hotel.model.HotelService" />
				<tr>
					<td>種類:<font color=red><b>*</b></font></td>
					<td><select size="1" name="hotelType">
						<c:forEach var="hotelVO" items="${hotelSvc.all}">
							<option value="${hotelVO.hotelType}" ${(hotelVO.hotelType==hotel.hotelType)?'selected':'' } >${hotelVO.hotelType}
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
					<td>旅館業者登記証:</td>
					<td><input type="file" name="upfile1" id="myFile" />
						<p>
							<output id="image_output"></output>
						</p>
					</td>
				</tr>
				
				<tr>
					<td>封面照片:</td>
					<td><input type="file" name="upfile2" id="myFile1" />
						<p>
							<output id="image_output1"></output>
						</p>
					</td>
				</tr>
				
				<tr>
					<td>廠商簡介:</td>
					<td>
						<textarea name="hotelIntro" rows="5" cols="40" style='resize : none'
						onkeyup="this.value = this.value.slice(0, 200)">${hotel.hotelIntro}</textarea>
					</td>
				</tr>
				
				
				<br>
			</table>
			<input type="hidden" name="hotelId" value="${hotel.hotelId}"> 
			<input type="hidden" name="action" value="updatehotel"> 
			<input type="submit" value="送出新增" align="center">
		</form>		
		<br><br><br>
		<h1>修改密碼</h1>
		<form METHOD="POST" ACTION="<%=request.getContextPath()%>/hotel/hotel.do" name="form1" enctype="multipart/form-data" id="table">
				<c:if test="${not empty errorMsgss}">
					<font color='red'>請修正以下錯誤:
						<ul>
							<c:forEach var="message" items="${errorMsgss}">
								<li>${message}</li>
							</c:forEach>
						</ul>
					</font>
				</c:if>
				
				<tr>
					<td>請輸入新密碼:</td>
					<td><input type="password" name="hotelPwd" size="45" maxlength="10"
						class="UserName" placeholder="輸入舊的密碼"
						value="" /></td>
				</tr>
				<p>
				<br>
				<tr>
					<td>再輸入新密碼:</td>
					<td><input type="password" name="hotelPwd_0" size="45" maxlength="10"
						class="UserName" placeholder="輸入舊的密碼"
						value="" /></td>
				</tr>
				<br><br>
				<input type="hidden" name="hotelId" value="${hotel.hotelId}"> 
				<input type="hidden" name="action" value="updatepsw"> 
				<input type="submit" value="送出新增" align="center">
       </form>
</div>
		
</div>
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