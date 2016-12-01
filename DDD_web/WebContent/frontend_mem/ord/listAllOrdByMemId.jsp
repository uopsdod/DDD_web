<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ include file="../indexHeader.jsp"%>
<%@ page import="com.ord.model.*, java.text.SimpleDateFormat" %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/frontend_mem/css/wishList.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/css/0_main.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/auth/css/sweet-alert.css">
<script src="<%=request.getContextPath()%>/backend/auth/js/sweet-alert.js"></script>
<%
	OrdService ordSvc = new OrdService();
	List<OrdVO> allList = ordSvc.getAllByOrdMemId(memVO.getMemId());
    List<OrdVO> list = new ArrayList<OrdVO>();
    
    /* 篩選出已下單的 */
    for(OrdVO aOrdVO : allList){
    	if( "0".equals( aOrdVO.getOrdStatus() ) ){
    		list.add(aOrdVO);
    	}
    }
	
	pageContext.setAttribute("list",list);
%>



<style>
	#listinfor{
		font-family:Tahoma, Verdana, 微軟正黑體;
	}
	
	#view th{
		font-family:Tahoma, Verdana, 微軟正黑體;
		font-size:18px;
		
	}
	#view tr{
		font-family:Tahoma, Verdana, 微軟正黑體;
		font-size:20px;
		
	}
</style>

    <section>
       <div class="col-xs-12 col-sm-12 ">
           <div class="col-xs-12 col-sm-1">
               
           </div>          
           <div class="col-xs-12 col-sm-10" id="view">
	           <c:choose>
				    <c:when test="${list.size()!=0}">				     
	                <h1 id="WishH2">- 管理你的預訂          <img src="<%=request.getContextPath()%>/frontend_mem/images/like.png"> - </h1>
	                
	                <div>${OrdMessage}</div>	                
					<table class="table table-hover " >
						<thead>
							<tr style="background-color: #B0C4DE;">
								<th>訂單編號</th>
								<th>廠商會員名稱</th>
								<th>房型名稱</th>
								
								<th>訂單金額</th>
	
								<th>下訂日期</th>
								<th>訂單狀態名稱</th>
	
								<th>簡訊驗證碼</th>
								<th>操作</th>
	
							</tr>
						</thead>
						<tbody>
						<c:forEach var="ordVO" items="${list}">

								<tr>
									<td>${ordVO.ordId}</td>
									<td>${ordVO.ordHotelVO.hotelName}</td>
									<td>${ordVO.ordRoomVO.roomName}</td>
			
									<td>${ordVO.ordPrice}</td>
																	
									<td><%=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(((OrdVO)(pageContext.getAttribute("ordVO"))).getOrdDate())%></td>		
									<td>${ordStatusTrans.get(ordVO.ordStatus)}</td>
	
									<td>${ordVO.ordMsgNo}</td>
									<td>
										<form method="post" action="<%=request.getContextPath()%>/OrdCheckAndCancel" name="theForm">
												<input type="button" value="取消訂單" id="buttnOnimg1" onClick="alert()">
												<input type="hidden" name="ordId" value="${ordVO.ordId}">
												<input type="hidden" name="ordMsgNo" value="${ordVO.ordMsgNo}">
												<input type="hidden" name="action" value="cancel">
												<input type="hidden" name="location" value="fromMan">
										</form>
									</td>
								</tr>

						</c:forEach>
						<tbody>
					</table>
	                <script>
						    function alert() {     			
							    setTimeout('document.theForm.submit();', 1500);
							    //swal("你已成功取消此訂單", "謝謝您", "success");
							}

					</script>
	                
	           	   </c:when>		  
			       <c:otherwise>
			       	    <h1 id="WishH2">- 管理你的預定          <img src="<%=request.getContextPath()%>/frontend_mem/images/like.png"> - </h1>
			        	<hr style="border-top:3px solid lightgray">				
			        	<h4 id="listinfor">您目前沒有任何一筆清單資料<img src="<%=request.getContextPath()%>/frontend_mem/images/listwish.png"></h4>
			        	<br><br><br><br><br><br><br>
			        	
			       </c:otherwise>
		       </c:choose>   
           </div>
           <div class="col-xs-12 col-sm-1">
               
           </div>
       </div>
    </section>
   <!--  --------------------------------------------------------------------- -->
<%-- <%@ include file="../indexFooter.jsp" %> --%>
