<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>

<%
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String formattedDate = df.format(new java.util.Date());
%>

<%
	EmpVO empVO = (EmpVO) request.getAttribute("empVO");
	
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/emp/css/addEmp.css">
<script src="<%=request.getContextPath()%>/backend/emp/js/addEmp.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>管理後端</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/emp/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/emp/css/0_main.css">

<script src="<%=request.getContextPath()%>/backend/emp/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/backend/emp/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/backend/emp/js/0_new.js "></script>
<style type="text/css">

</style>
</head>
<body>	
		<%@ include file="/backend/backendBody.jsp"%>
		<%
		if(!authorityList.contains("101")){
			response.sendRedirect(request.getContextPath()+"/backend/emp_index.jsp");
		}
		%>
		
		 
		<div class="col-xs-12 col-sm-10 tablediv" align="center"> 
		<h1 align="center">新進員工<img src="<%=request.getContextPath()%>/backend/emp/img/usercolor.png" width="100px" height="100px" ></h1><br>
		<hr  size="10" width="55%"  align="center" style="border-top: 3px solid #000000"><br>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font color='red'>
					<c:forEach var="message" items="${errorMsgs}">
						${message}<br>
					</c:forEach>
			</font>
		</c:if>

		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" name="form1"
			enctype="multipart/form-data">
			<table border="0" align="center">
				<tr>
					<td class="titletext">員工姓名： </td>
					<td ><input type="TEXT" name="empName" size="45"
						class="UserName" placeholder="請輸入員工名字" 
						value="<%=(empVO == null) ? "" : empVO.getEmpName()%>" /></td>
				</tr>

				<tr>
					<td class="titletext">信箱： </td>
					<td><input type="TEXT" name="empAccount" size="45"
						class="UserName" placeholder="請輸入信箱"
						value="<%=(empVO == null) ? "" : empVO.getEmpAccount()%>" /></td>
				</tr>

				<!-- 請輸入6~10字 -->
<!-- 				<tr> -->
<!-- 					<td>密碼:</td> -->
<!-- 					<td><input type="password" name="empPwd" size="45" -->
<!-- 						class="UserName" placeholder="請輸入密碼" -->
<%-- 						value="<%=(empVO == null) ? "" : empVO.getEmpPwd()%>" /></td> --%>
<!-- 				</tr> -->

				<tr>
					<td class="titletext">電話： </td>
					<td><input type="TEXT" name="empPhone" size="45"
						class="UserName" placeholder="請輸入員工電話"
						value="<%=(empVO == null) ? "" : empVO.getEmpPhone()%>" /></td>
				</tr>

			

				<tr>
					<td class="titletext">身分證字號： </td>
					<td><input type="TEXT" name="empROCId" size="45"
						class="UserName" placeholder="H123000000"
						value="<%=(empVO == null) ? "" : empVO.getEmpROCId()%>" /></td>
				</tr>

				<tr>
					<td class="titletext">員工住址： </td>
					<td><input type="TEXT" name="empAddress" size="45"
						class="UserName" placeholder="桃園市中壢區中大路10號"
						value="<%=(empVO == null) ? "" : empVO.getEmpAddress()%>" /></td>
				</tr>
				
				<tr>
					<td class="titletext">生日： </td>
					<td><input type="date" name="empBirthDate" size="45"
						class="UserName" placeholder="yyyy-mm-dd"
						value="<%=(empVO == null) ? "" : empVO.getEmpBirthDate()%>" /></td>
				</tr>
				
				<tr>
					<td class="titletext">大頭照： </td>
					<td>
						<div class="monkeyb-cust-file" >
	                       <img />
	                       <span>Select File</span>
	                       <input type="file" id="myFile" name="upfile1">  
	                	</div>  
						<p>
							<output id="image_output"></output>
						</p>
						</td>
				</tr>
			
				<tr>
					<%--到職日--%>
					<td><input type="hidden" name="empHireDate" size="45"
						value="<%=(empVO == null) ? formattedDate : empVO.getEmpHireDate()%>" /></td>
				</tr>

				<tr>
					<%--離職日--%>
					<td><input type="hidden" name="empFireDate" size="45"
						value="<%=(empVO == null) ? formattedDate : empVO.getEmpFireDate()%>" /></td>
				</tr>

				<tr>
					<%--狀態碼--%>
					<td><input type="hidden" name="empStatus" size="45"
						value="<%=(empVO == null) ? "0" : empVO.getEmpStatus()%>" /></td>
				</tr>

				<br>



			</table>
			<input type="button" align="center" class="LoginButton" id ="magic" value="神奇小按鈕">
			<input type="hidden" name="action" value="insert"> 
			<input type="submit" value="送出新增" align="center" class="LoginButton">
		</FORM>
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
</script>

<script>
	$(document).ready(function(){
		$(".container-fluid .row #eee").addClass("in");
	});
</script>