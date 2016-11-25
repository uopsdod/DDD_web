<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.wish.model.*"%>
<%@ include file="../indexHeader.jsp"%>
<%@ page import="com.room.model.*"%>
<%@ page import="com.room.controler.*"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/frontend_mem/css/wishList.css">
<script src="<%=request.getContextPath()%>/frontend_mem/js/wishList.js"></script>
<script src="<%=request.getContextPath()%>/frontend_mem/wish/socket.js"></script>
<%
	
	WishService wishsvc = new WishService();
	List<Map> list =wishsvc.getOneWishOfmemNO(memVO.getMemId());
	pageContext.setAttribute("list", list);
%>
<script>
<%if(list.size()!=0){%>
var a = [<c:forEach var="wish" items="${list}">"${wish.roomid}",</c:forEach> "${list.get(0).get("roomid")}"];
<%}else{%>
	
<%}%>
	
	
	var roomMap;
	
	window.onunload = disconnect;
	window.addEventListener('load',function(){ 
		roomMap = new Map;
		for(var i =0;i<a.length;i++){
			var node = document.getElementById(a[i]);
			roomMap.set(a[i],node);	
			}
		console.log(roomMap);
		},false);
	window.addEventListener('load',connect,false);

</script>


<!-- //查看詳情還沒做 -->
<style>
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
			                <div class="roomName"><b>${wish.hotelName}/${wish.roomName}</b></div>
			                <div class="house">旅館剩餘房數-${wish.roomRemainNo}</div>
			                <div class="star">
			                	<c:choose>
			                	<c:when test='${wish.hotelRatingResult==0}'>
			                		目前沒有評價
							     </c:when>
						         <c:when test='${wish.hotelRatingResult==1}'>
						         	<img src='<%=request.getContextPath()%>/frontend_mem/img/star.png' width='16px' height="16px">
							     </c:when>
							     <c:when test='${wish.hotelRatingResult==2}'>
						         	<img src='<%=request.getContextPath()%>/frontend_mem/img/star.png' width='16px' height="16px">
						         	<img src='<%=request.getContextPath()%>/frontend_mem/img/star.png' width='16px' height="16px">
							     </c:when>
							     <c:when test='${wish.hotelRatingResult==3}'>
						         	<img src='<%=request.getContextPath()%>/frontend_mem/img/star.png' width='16px' height="16px">
						         	<img src='<%=request.getContextPath()%>/frontend_mem/img/star.png' width='16px' height="16px">
						         	<img src='<%=request.getContextPath()%>/frontend_mem/img/star.png' width='16px' height="16px">
							     </c:when>
							     <c:when test='${wish.hotelRatingResult==4}'>
						         	<img src='<%=request.getContextPath()%>/frontend_mem/img/star.png' width='16px' height="16px">
						         	<img src='<%=request.getContextPath()%>/frontend_mem/img/star.png' width='16px' height="16px">
						         	<img src='<%=request.getContextPath()%>/frontend_mem/img/star.png' width='16px' height="16px">
						         	<img src='<%=request.getContextPath()%>/frontend_mem/img/star.png' width='16px' height="16px">
							     </c:when>		  
							     <c:otherwise>
							     	<img src='<%=request.getContextPath()%>/frontend_mem/img/star.png' width='16px' height="16px">
							     	<img src='<%=request.getContextPath()%>/frontend_mem/img/star.png' width='16px' height="16px">
							     	<img src='<%=request.getContextPath()%>/frontend_mem/img/star.png' width='16px' height="16px">
							     	<img src='<%=request.getContextPath()%>/frontend_mem/img/star.png' width='16px' height="16px">
							     	<img src='<%=request.getContextPath()%>/frontend_mem/img/star.png' width='16px' height="16px">
							     </c:otherwise>
								</c:choose> 
			                </div>
			                <div class="toNight1">原價</div>
			               <del> <div class="price1">TWD ${wish.roomPrice}</div></del>
			                <br><br><br>
			                <div class="toNight">今晚價</div>
			                <div class="price">
			                <u>
			                	<span id="${wish.roomid}"><span>     
								<%  
									String roomId = (String)(((Map)pageContext.getAttribute("wish")).get("roomid"));
									Map oneRoom = RoomServlet.OnData.get(roomId);
									if(oneRoom!=null)
									{	
										int onPrice = (Integer)oneRoom.get("price");
									%>
									   <%=onPrice %>
									<%
									
										System.out.println(onPrice);
									}else{
										out.write("未上架");
									}
								
								%>
								</span>
							</span>
			                </u></div>
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
