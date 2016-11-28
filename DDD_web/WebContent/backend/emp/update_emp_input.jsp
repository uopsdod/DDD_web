<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<%
	EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/emp/css/update_emp_input.css">
<script src="<%=request.getContextPath()%>/backend/emp/js/update_emp_input.js"></script>
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
	<!-- 如果權限沒有人事轉到首頁怕他偷吃步-->
	<%
	if(!authorityList.contains("101")){
		response.sendRedirect(request.getContextPath()+"/backend/emp_index.jsp");
	}
	%>
	
	

<div class="col-xs-12 col-sm-10 tablediv" align="center"> 
	<FORM METHOD="post" ACTION="emp.do" name="form1" enctype="multipart/form-data">
	<h2 align="center">修改員工資料<a href="<%=request.getContextPath()%>/backend/emp/listAllEmp.jsp"><img src="<%=request.getContextPath()%>/backend/emp/img/update.png" width="80px" height="80px" ></h2></a><br>
		<hr  size="10" width="55%"  align="center" style="border-top: 3px solid #000000"><br>
		<c:if test="${not empty errorMsgs}">
		<font color='red'>			
				<c:forEach var="message" items="${errorMsgs}">
					${message}<br>
				</c:forEach>			
		</font>
	</c:if>
		<table border="0" align="center" id="">
			<tr>
				<td class="titletext">員工編號:<font color=red><b>*</b></font></td>
				<td class="titletext"><%=empVO.getEmpId()%></td>
			</tr>
			
			<tr>
				<td class="titletext">員工姓名:</td>
				<td><input type="TEXT" name="empName" size="45" class="UserName"
					value="<%=empVO.getEmpName()%>" /></td>
			</tr>
			<tr>
				<td class="titletext">信箱:</td>
				<td><input type="TEXT" name="empAccount" size="45" class="UserName"
					value="<%=empVO.getEmpAccount()%>" /></td>
			</tr>

<!-- 			<tr> -->
<!-- 				<td>密碼:</td> -->
<!-- 				<td><input type="TEXT" name="empPwd" size="45" class="UserName" -->
<%-- 					value="<%=empVO.getEmpPwd()%>" /></td> --%>
<!-- 			</tr> -->

			<tr >
				<td class="titletext">連絡電話:</td>
				<td><input type="TEXT" name="empPhone" size="45" class="UserName"
					value="<%=empVO.getEmpPhone()%>" /></td>
			</tr>			
				<td class="titletext">到職日:</td>
				<td><input type="TEXT" name="empHireDate" size="45" class="UserName"
					value="<%=empVO.getEmpHireDate()%>" /></td>
			</tr>
		

			<tr>
				<td class="titletext">離職日:</td>
				<td><input type="TEXT" name="empFireDate" size="45" class="UserName"
					value="<%=empVO.getEmpFireDate()%>" /></td>
			</tr>

			<tr>
				<td class="titletext">狀態碼:</td>
				<td><input type="TEXT" name="empStatus" size="45" class="UserName"
					value="<%=empVO.getEmpStatus()%>" /></td>
			</tr>



			<tr>
				<td class="titletext">生日:</td>
				<td><input type="TEXT" name="empBirthDate" size="45" class="UserName"
					value="<%=empVO.getEmpBirthDate()%>" /></td>
			</tr>

			<tr>
				<td class="titletext">身分證字號:</td>
				<td><input type="TEXT" name="empROCId" size="45" class="UserName"
					value="<%=empVO.getEmpROCId()%>" /></td>
			</tr>

			<tr>
				<td class="titletext">住址:</td>
				<td><input type="TEXT" name="empAddress" size="45" class="UserName"
					value="<%=empVO.getEmpAddress()%>" /></td>
			</tr>
			
			<tr>
				<td class="titletext">大頭照:</td>
				<td>
					<div class="monkeyb-cust-file" >
                       <img />
                       <span>Select File</span>
                       <input type="file" id="myFile" name="upfile1">  
		           </div>  
				
					<output id="image_output"></output>
			
						 <input type="hidden" name=empProfile value="<%=empVO.getEmpProfile()%>">
						 
				</td>
			</tr>
			

		</table>
		<br> <input type="hidden" name="action" value="update"> 
		<input type="hidden" name="empId" value="<%=empVO.getEmpId()%>">
		<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
		<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">  <!--只用於:istAllEmp.jsp--> 
		<input type="submit" value="確認修改" class="LoginButton">
		
	</FORM>
	</div>
</body>
</html>
<script>
if ( window.FileReader ) { //測試瀏覽器
	document.getElementById("myFile").onchange = function(){

		for (var i=0, file; file=this.files[i]; i++) { //var file;
			var reader = new FileReader(); 
			reader.onloadend = (function(file){ 
				return function(){ 
				document.getElementById('image_output').innerHTML 
				+= '<img src="'+this.result +'" height="50"/> <br/>';
			} 
		})(file);  //自己CALL自己
		reader.readAsDataURL( file ); 
		} 
} 
}else{
	alert("瀏覽器不支援 HTML 5");
}
</script>