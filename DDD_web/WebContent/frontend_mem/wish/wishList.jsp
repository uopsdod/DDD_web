<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.wish.model.*"%>
<%@ include file="../indexHeader.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/frontend_mem/css/wishList.css">
<script src="<%=request.getContextPath()%>/frontend_mem/js/wishList.js"></script>
<%
	
	WishService wishsvc = new WishService();
	List<Map> list =wishsvc.getOneWishOfmemNO(memVO.getMemId());
	pageContext.setAttribute("list", list);
    	
%>

<<style>
	#listinfor{
		 font-family:Tahoma, Verdana, 微軟正黑體;
	}
</style>


    <section>
       <div class="col-xs-12 col-sm-12 ">
           <div class="col-xs-12 col-sm-3">
               
           </div>          
           <div class="col-xs-12 col-sm-6" id="view">
	           <c:choose>
				    <c:when test="${list.size()!=0}">				     
	                <h1 id="WishH2">- 我的願望清單          <img src="<%=request.getContextPath()%>/frontend_mem/images/like.png"> - </h1>
	             	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/wish/wish.do">
		                <c:forEach var="wish" items="${list}">		
			                <hr style="border-top:3px solid lightgray">			
			                <img src='data:image/jpeg;base64,${wish.roomPhotoPic}' width="500" height="300" class="wishImg">
			                <div class="roomName">${wish.hotelName}/${wish.roomName}</div>
			                <div class="star">評價${wish.hotelRatingResult} *****</div>
			                <div class="sale"><u>剩餘房數${wish.roomTotalNo}</u></div>
			                <div class="sale"><u>優惠期間8:00~9:00</u></div>
			                <br><br><br><br>
			                <div class="toNight">今晚價</div>
			                <div class="price"><u>TWD ${wish.roomPrice*wish.roomDisccountPercent}</u></div>
			                <br>
			                <button id="buttnOnimg" >查看詳情</button>
			                <input type="hidden" name="action" value="delect"> 
			                <input type="hidden" name="memid" value="${memVO.getMemId()}"> 
			                <input type="hidden" name="roomPhotoRoomId" value="${wish.roomPhotoRoomId}"> 
			                <button id="buttnOnimg1" >X刪除</button>
		            	</c:forEach>
	                </FORM>
	           	   </c:when>		  
			       <c:otherwise>
			       	    <h1 id="WishH2">- 我的願望清單          <img src="<%=request.getContextPath()%>/frontend_mem/images/like.png"> - </h1>
			        	<hr style="border-top:3px solid lightgray">				
			        	<h4 id="listinfor">您目前沒有任何一筆清單資料<img src="<%=request.getContextPath()%>/frontend_mem/images/listwish.png"></h4>
			        	<br><br><br><br><br><br><br>
			        	
			       </c:otherwise>
		       </c:choose>   
           </div>
           <div class="col-xs-12 col-sm-3">
               
           </div>
       </div>
    </section>
   <!--  --------------------------------------------------------------------- -->
<%@ include file="../indexFooter.jsp" %>
