<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>

<%
	 MemService memsvc = new MemService();
	 List<MemVO> memvo = 	memsvc.getAll();
	 pageContext.setAttribute("memvo", memvo);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>管理後端</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/emp/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/emp/css/0_main.css">
<link href="<%=request.getContextPath()%>/backend/mem/datatables/css/jquery.dataTables.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/backend/emp/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/backend/emp/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/backend/emp/js/0_new.js "></script>

<style type="text/css">
body {
	font-family: Tahoma, Verdana, 微軟正黑體;
	font-size: 13px;
}

#LoginButton {
	opacity: 0.7;
	z-index: 1;
	background: #0283df;
	color: #ffffff;
	position: relative;
	font-weight: bold;
	font-family: Tahoma, Verdana, 微軟正黑體;
	border: 0px;
	border-radius: 10px;
	padding: 4px;
	font-size: 20px;
	width:120px;
}

.vcenter {
	display: inline-block;
	vertical-align: middle;
	float: none;
}
#empName{
	color:white;
}
.tablediv{
	
	font-family: Tahoma, Verdana, 微軟正黑體;
	font-size: 19px;
	font-weight: bold;
	
}
#emptableth th{
	font-family: Tahoma, Verdana, 微軟正黑體;
	font-size: 20px;
	 line-height:50px;
}
#emptableth td{
	line-height:70px;
	}
	.aaa{
	height: 1450px;
	background-color: #ccc;
	margin-top: 53px;
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
			
			<h1>會員資料列表</h1>
			<hr  size="10" width="95%"  align="center" style="border-top: 3px solid #000000">
			<div class="panel-body">
				<table  cellspacing="0" cellpadding="5"				
				width='100%' class="table table-striped table-bordered table-hover" id="dataTables-example">	
				<thead>
				<tr>
					<th  class="text-center">會員編號</th>
					<th  class="text-center">姓名</th>
					<th  class="text-center">性別</th>
					<th  class="text-center">身份證字號</th>
					<th  class="text-center">出生年月日</th>
					<th  class="text-center">電話</th>
					
<!-- 					<th>信用卡卡號</th> -->
<!-- 					<th>信用卡驗證碼</th> -->
<!-- 					<th>信用卡有效日期</th> -->
					<th  class="text-center">查看詳情</th>					
				</tr>
				</thead>
				
				<tbody>
				<c:forEach var="memvo" items="${memvo}">			
					<tr align='center' valign='middle' class="odd gradeX"
						${(memvo.memId==param.memId) ? 'bgcolor=lightblue':''}
						>
						<!--將修改的那一筆加入對比色而已-->
<%-- 						<td><img src='data:image/jpeg;base64,${HotelVO.bs64}' --%>
<!-- 							width="120" height="80" /></td> -->
						<td>${memvo.memId}</td>
						<td>${memvo.memName}</td>
						
						<c:choose>
				            <c:when test="${memvo.memGender=='f'}">
				                <td>女</td>
				            </c:when>
				            <c:when test="${memvo.memGender=='m'}">
				                <td>男</td>
				            </c:when>				            
				        </c:choose>			
				        		
						<td>${memvo.memTwId}</td>
						<td>${memvo.memBirthDate}</td>
						<td>${memvo.memPhone}</td>
<%-- 						<td>${memvo.memCreditCardNo}</td> --%>
<%-- 						<td>${memvo.memCreditCheckNo}</td> --%>
<%-- 						<td>${memvo.memCreditDueDate}</td> --%>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/mem/mem.do">
								<input type="submit" value="查看" id="LoginButton"> <input
									type="hidden" name="memId" value="${memvo.memId}"> <input
									type="hidden" name="requestURL"
									value="<%=request.getServletPath()%>">
<!-- 							送出本網頁的路徑給Controller  -->

								<input type="hidden" name="action" value="getOne">
							</FORM>
						</td>
<!-- 						<td> -->
<!-- 							<FORM METHOD="post" -->
<%-- 								ACTION="<%=request.getContextPath()%>/auth/auth.do"> --%>
<!-- 								<input type="submit" value="查詢權限" id="LoginButton"> <input -->
<%-- 									type="hidden" name="empId" value="${EmpVO.empId}"> <input --%>
<!-- 									type="hidden" name="requestURL" -->
<%-- 									value="<%=request.getServletPath()%>"> --%>
<!-- 								送出本網頁的路徑給Controller -->
<%-- 								<input type="hidden" name="whichPage" value="<%=whichPage%>"> --%>
<!-- 								送出當前是第幾頁給Controller -->
<!-- 								<input type="hidden" name="action" value="getOne_For_Auth"> -->
<!-- 							</FORM> -->
<!-- 						</td> -->
					</tr>
				</c:forEach>
				</tbody>
			</table>
			</div>
			</div>
			<script src="<%=request.getContextPath()%>/backend/mem/jquery.min.js"></script>
		    <script src="<%=request.getContextPath()%>/backend/mem/datatables/js/jquery.dataTables.min.js"></script>
		    <script src="<%=request.getContextPath()%>/backend/mem/datatables-plugins/dataTables.bootstrap.min.js"></script>
		    <script src="<%=request.getContextPath()%>/backend/mem/datatables-responsive/dataTables.responsive.js"></script>

    <script>
    $(document).ready(function() {
        $('#dataTables-example').DataTable({
            responsive: true
        });
        
        $(".container-fluid .row #bbb").addClass("in");
    });
    </script>
</body>
</html>