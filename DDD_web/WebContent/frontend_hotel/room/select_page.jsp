<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>AA104G4 Room: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>AA104G4 Room: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for AA104G4 Room: Home</p>

<h3>資料查詢:</h3>
<%--錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<ul>
  <li><a href='listAllEmp.jsp'>List</a> all Rooms. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/room/room.do" >
        <b>輸入房間編號(如1000003):</b>
        <input type="text" name="roomId">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="roomSvc" scope="page" class="com.room.model.RoomService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/room/room.do" >
       <b>選擇房間編號:</b>
       <select size="1" name="roomId">
         <c:forEach var="roomVO" items="${roomSvc.all}" ><!-- 呼叫RoomService物件的getALL()方法 -->
          <option value="${roomVO.roomId}">${roomVO.roomId}
         </c:forEach>
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/room/room.do" >
       <b>選擇房間名稱:</b>
       <select size="1" name="roomId">
         <c:forEach var="roomVO" items="${roomSvc.all}" > 
          <option value="${roomVO.roomId}">${roomVO.roomName}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
</ul>


<h3>房型管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/room/addEmp.jsp'>Add</a> a new Room.</li>
</ul>

</body>

</html>
