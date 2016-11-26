<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.ord.model.*" %>
<%@ include file="../head.jsp"%>

		<%
			String hotelId = (String)session.getAttribute("hotelId");
		%>
		
		
		<%
			OrdService ordSvc = new OrdService();
			List<OrdVO> list = ordSvc.getAllByOrdHotelId(hotelId);
			pageContext.setAttribute("list",list);
		%>



		<!-- 自訂CSS -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/frontend_hotel/ord/css/select2.css">		
		<!-- 自訂JavaScript --> 	
		<script src="<%=request.getContextPath()%>/frontend_hotel/ord/js/select2.js"></script>

		<div class="col-xs-12 col-sm-10 bb" style="background-color:#FFFAF0;postion:relative;top:220px">
		
			<ol class="breadcrumb">
				<li>
					訂單
				</li>
				<li class="active">旅客入住驗證</li>
			</ol>

			<h1>訂單驗證:</h1>
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				請修正以下錯誤:
				<ul>
					<c:forEach var="message" items="${errorMsgs}">	
						<li>${message}</li>	
					</c:forEach>
				</ul>
			</c:if>
	
			<form method="post" action="<%=request.getContextPath()%>/OrdCheckAndCancel">
				<h2>輸入旅客姓名:</h2>
				

				
				<select id="ordId" name="ordId">
					<c:forEach var="ordVO" items="${list}">
						<c:if test="${ordVO.ordStatus == '0'}">
							<option value="${ordVO.ordId}">${ordVO.ordMemVO.memName} [${ordVO.ordHotelVO.hotelName}]</option>
						</c:if>
					</c:forEach>
				</select>
				<br>
				
				<h2>輸入簡訊驗證碼:</h2>
				<input type="text" name="ordMsgNo">
				<br>
				<br>
				<input type="submit" value="送出">
				<input type="hidden" name="action" value="confirm">
			</form>

		</div>	

<%@ include file="../footer.jsp" %>


<script>
$(document).ready(function() {

   $("#ordId").select2({
     width:'300',
     placeholder: "輸入會員姓名",
     placeholderOption:"first",
     allowClear: true 
   });

});
</script>