<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.emp.model.*"%>

<%
	EmpService dao = new EmpService();
	List<EmpVO> list = dao.getAll();
	pageContext.setAttribute("list", list);
	String authIdlistNo =(String)request.getAttribute("authIdlistNo");
	pageContext.setAttribute("authIdlistNo", authIdlistNo);
%>

<!-- 如果權限沒有人事轉到首頁怕他偷吃步-->



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/emp/css/listAllEmp.css">
<script src="<%=request.getContextPath()%>/backend/emp/js/listAllEmp.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>管理後端</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/emp/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/emp/css/0_main.css">

<script src="<%=request.getContextPath()%>/backend/emp/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/backend/emp/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/backend/emp/js/0_new.js "></script>
<style type="text/css">
	.aaa{
		height:1600px;
	}
</style>
</head>
<body>
	<%@ include file="/backend/backendBody.jsp"%>
	<%
	if(!authorityList.contains("101")){
		response.sendRedirect(request.getContextPath()+"/backend/emp_index.jsp");
	}
	%>
	
			
			<div class="col-xs-12 col-sm-10 tablediv text-center" > 
			<h1>員工資料列表<img  src=""></h1>
			<hr  size="10" width="95%"  align="center" style="border-top: 3px solid #000000">
			
			<table cellspacing="0" cellpadding="1"
				width='800' class="table table-hover text-center" id="emptableth" >	
				<tr class="text-center">
					<th class="text-center">員工照片</th>
					<th class="text-center">員工編號</th>
					<th class="text-center">員工姓名</th>
					<th class="text-center">員工帳號</th>
					<th class="text-center">員工生日</th>
					<th class="text-center">員工電話</th>
<!-- 					<th>empStatus</th> -->
					<th class="text-center">身分證字號</th>
					<th class="text-center">地址</th>
<!-- 					<th>empHireDate</th> -->
<!-- 					<th>empFireDate</th> -->
					<th class="text-center">修改資料</th>
					<th class="text-center">修改權限</th>
				</tr>
				<%@ include file="page1.file"%>
				<c:forEach var="EmpVO" items="${list}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">			
					<tr align='center' valign='middle'
						${(EmpVO.empId==param.empId) ? 'bgcolor=lightblue':''}
						
						${(EmpVO.empId==authIdlistNo) ? 'bgcolor=lightblue':''}>
						<!--將修改的那一筆加入對比色而已-->
						<td ><img src='data:image/jpeg;base64,${EmpVO.bs64}'				
							width="120" height="120" /></td>
						<td >${EmpVO.empId}</td>
						<td >${EmpVO.empName}</td>
						<td >${EmpVO.empAccount}</td>
						<td >${EmpVO.empBirthDate}</td>
						<td >${EmpVO.empPhone}</td>
<%-- 						<td>${EmpVO.empStatus}</td> --%>
						<td >${EmpVO.empAddress}</td>
						<td >${EmpVO.empROCId}</td>
<%-- 						<td>${EmpVO.empHireDate}</td> --%>
<%-- 						<td>${EmpVO.empFireDate}</td> --%>
						<td>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do">
								<input type="submit" value="修改" id="LoginButtonx"> <input
									type="hidden" name="empId" value="${EmpVO.empId}"> <input
									type="hidden" name="requestURL"
									value="<%=request.getServletPath()%>">
								<!--送出本網頁的路徑給Controller-->
								<input type="hidden" name="whichPage" value="<%=whichPage%>">
								<!--送出當前是第幾頁給Controller-->
								<input type="hidden" name="action" value="getOne_For_Update">
							</FORM>
						</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/auth/auth.do">
								<input type="submit" value="查詢權限" id="LoginButtonxy"> <input
									type="hidden" name="empId" value="${EmpVO.empId}"> <input
									type="hidden" name="requestURL"
									value="<%=request.getServletPath()%>">
								<!--送出本網頁的路徑給Controller-->
								<input type="hidden" name="whichPage" value="<%=whichPage%>">
								<!--送出當前是第幾頁給Controller-->
								
								<input type="hidden" name="action" value="getOne_For_Auth">
							</FORM>
						</td>
					</tr>
				</c:forEach>
			</table>
			</div>
			<%
				if (request.getAttribute("authList") != null) {
			%>
			<jsp:include page="/backend/auth/update_auth.jsp" />
			<%
				}
			%>
			<%@ include file="page2.file"%>
</body>
</html>
			