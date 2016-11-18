<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="<%=request.getContextPath()%>/room/room.do">
	
	<input type="hidden" name="action" value="AllSell">
	<input type="submit" value="上架所有房型">
	
	</form>
</body>
</html>