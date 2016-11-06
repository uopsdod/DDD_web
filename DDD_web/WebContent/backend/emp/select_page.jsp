<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	body{
		font-family:Tahoma, Verdana, 微軟正黑體;
		
	}
</style>
</head>
<body>
<div>
	<h3>資料查詢:</h3>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:
		<ul>
			<c:forEach var="message" items="${errorMsgs}"> <%-- var是把值存到裡面 ,item集合物件--%>
				<li>${message}</li>
			</c:forEach>
		</ul>
		</font>
	</c:if>

	<ul>
		<li><a href='listAllEmp.jsp'>List</a> all Emps. </li> <br><br>
	</ul>
	
	<h3>員工新增</h3>
	<ul>
	  <li><a href='addEmp.jsp'>Add</a> a new Emp.</li>
	</ul>
</div>	
	
	
</body>
</html>
