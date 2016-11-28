<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.emp.model.*"%>

<%
	MemVO memvo =  (MemVO)request.getAttribute("memid");
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
	font-size: 12px;
}

.vcenter {
	display: inline-block;
	vertical-align: middle;
	float: none;
}
#empName{
	color:white;
}
#tablehotel{
	font-family: Tahoma, Verdana, 微軟正黑體;
	font-size:20px;
	
}
#tablehotel th{
	text-align:center;
}
.LoginButtonBlack{
	opacity: 0.7;
	z-index: 1;
	background: rgb(212, 74, 151);
	color: #ffffff;
	position: relative;
	font-weight: bold;
	font-family: Tahoma, Verdana, 微軟正黑體;
	border: 0px;
	border-radius: 10px;
	padding: 8px;
	font-size: 20px;

}
.LoginButtonBlack1{
	opacity: 0.7;
	z-index: 1;
	background:#0283df;
	color: #ffffff;
	position: relative;
	font-weight: bold;
	font-family: Tahoma, Verdana, 微軟正黑體;
	border: 0px;
	border-radius: 10px;
	padding: 8px;
	font-size: 20px;

}
.aaa{
	height: 1250px;
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
				<table border='0' bordercolor='black' cellspacing="0" cellpadding="5"
				width='800' class="table table-hover" id="tablehotel" >	
				<tr>
					<td>
					<H1>大頭照</H1>
					<img src='data:image/jpeg;base64,${memvo.bs64}'
							width="250" height="180" />
					</td>					
				</tr>
				<tr>
					<th>一般會員編號</th>
					<th>個人簡介</th>
				</tr>
				<tr>
					<td>${memvo.memId}</td>
					<td>${memvo.memIntro}</td>
				</tr>
				<tr>
					<th>帳號</th>
					<th>密碼</th>
				</tr>
				<tr>
					<td>${memvo.memAccount}</td>
					<td>${memvo.memPsw}</td>
				</tr>
				<tr>
					<th>姓名</th>
					<th>性別</th>
					
				</tr>
				<tr>
					<td>${memvo.memName}</td>
				<c:choose>
		            <c:when test="${memvo.memGender=='f'}">
		                <td>女生</td>
		            </c:when>
		            <c:when test="${memvo.memGender=='m'}">
		                <td>男生</td>
		            </c:when>		       
		       	</c:choose>										
				</tr>
				<tr>
					<th>身份證字號</th>
					<th>出生年月日</th>
				</tr>
				<tr>
					<td>${memvo.memTwId}</td>
					<td>${memvo.memBirthDate}</td>
				</tr>	
				<tr>
					<th>電話</th>
					<th>共住預算</th>
				</tr>
				<tr>
					<td>${memvo.memPhone}</td>
					<td>${memvo.memLiveBudget}</td>		
				</tr>
				<tr>
					<th>信用卡有效日期</th>
					<th>黑名單</th>
				</tr>
				<tr>
					<td>${memvo.memCreditDueDate}</td>
					
						<c:choose>
		            <c:when test="${memvo.memBlackList==0}">
		                <td>非黑名單</td>
		            </c:when>	        
		            <c:otherwise>
		                <td>黑名單</td>
		            </c:otherwise>
		        </c:choose>
						
				</tr>
				
				<tr>
					<th>信用卡卡號</th>
					<th>信用卡驗證碼</th>
				</tr>
				<tr>
					<td>${memvo.memCreditCardNo}</td>
					<td>${memvo.memCreditCheckNo}</td>		
				</tr>											
			
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/mem/mem.do">
							<input type="submit" value="將此會員加入黑名單" class="LoginButtonBlack"> 
							<input type="hidden" name="memId" value="${memvo.memId}">
							<input type="hidden" name="memBlackList" value="${memvo.memBlackList}">
							<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
							<input type="hidden" name="action" value="update_blacklist">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/mem/mem.do">
							<input type="submit" value="取消黑名單" class="LoginButtonBlack1"> 
							<input type="hidden" name="memId" value="${memvo.memId}">
							<input type="hidden" name="memBlackList" value="${memvo.memBlackList}">
							<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
							<input type="hidden" name="action" value="update_blacklist1">
						</FORM>
					</td>
				</tr>
				
			</table>
			<div align="center"><a href="<%=request.getContextPath()%>/backend/mem/listAllMem.jsp">回會員首頁</a></div>
			</div>
			

</body>
</html>