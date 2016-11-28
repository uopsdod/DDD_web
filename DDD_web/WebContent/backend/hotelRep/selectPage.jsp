<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>IBM HotelRep: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>IBM HotelRep: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for IBM HotelRep: Home</p>

<h3>資料查詢:</h3>
<%-- 錯誤表列 --%>
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
  <li><a href='<%=request.getContextPath()%>/backend/hotelRep/listAllHotelRep.jsp'>List</a> all HotelReps. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/hotelRep/hotelRep.do" >
        <b>輸入廠商檢舉單編號 (如100000001):</b>
        <input type="text" name="hotelRepId">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOneForDisplay">
    </FORM>
  </li>

  <jsp:useBean id="hotelRepSvc" scope="page" class="com.hotelrep.model.HotelRepService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/hotelRep/hotelRep.do" >
       <b>選擇廠商檢舉單編號:</b>
       <select size="1" name="hotelRepId">
         <c:forEach var="hotelRepVO" items="${hotelRepSvc.all}" > 
          <option value="${hotelRepVO.hotelRepId}">${hotelRepVO.hotelRepId}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOneForDisplay">
    </FORM>
  </li>


  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/hotelRep/hotelRep.do" >
       <b>選擇廠商檢舉單處理狀態名稱:</b>
		<select size="1" name="hotelRepStatus">
			<option value="0">未審核</option>
			<option value="1">已審核未通過</option>
			<option value="2">已審核已通過</option>
		</select> 
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getAllByHotelRepStatus">
    </FORM>
  </li>

  
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="emp.do" > -->
<!--        <b>選擇員工姓名:</b> -->
<!--        <select size="1" name="empno"> -->
<%--          <c:forEach var="empVO" items="${empSvc.all}" >  --%>
<%--           <option value="${empVO.empno}">${empVO.ename} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="submit" value="送出"> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--      </FORM> -->
<!--   </li> -->
</ul>


<h3>廠商檢舉單管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/backend/hotelRep/addHotelRep.jsp'>Add</a> a new HotelRep.</li>
</ul>

</body>

</html>
