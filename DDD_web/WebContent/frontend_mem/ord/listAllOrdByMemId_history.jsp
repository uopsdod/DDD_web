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
    
    /* 篩選出已入住的 */
    for(OrdVO aOrdVO : allList){
    	if( "2".equals( aOrdVO.getOrdStatus() ) ){
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
	                <h1 id="WishH2">- 謝謝您的支持          <img src="<%=request.getContextPath()%>/frontend_mem/images/like.png"> - </h1>
	                
	                <div>${OrdMessage}</div>	                
					<table class="table table-hover " >
						<thead>
							<tr style="background-color: #B0C4DE;">
								<th>訂單編號</th>
								<th>廠商會員名稱</th>
								<th>房型名稱</th>
								
								<th>訂單金額</th>
	
								<th>入住日期</th>
								<th>訂單狀態名稱</th>
	
								<th colspan='2'>操作</th>
	
							</tr>
						</thead>
						<tbody>
						<c:forEach var="ordVO" items="${list}">

								<tr>
									<td>${ordVO.ordId}</td>
									<td>${ordVO.ordHotelVO.hotelName}</td>
									<td>${ordVO.ordRoomVO.roomName}</td>
			
									<td>${ordVO.ordPrice}</td>
																	
									<td><%=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(((OrdVO)(pageContext.getAttribute("ordVO"))).getOrdLiveDate())%></td>		
									<td>${ordStatusTrans.get(ordVO.ordStatus)}</td>

									<td>
										<input type="submit" value="給個分數" id="buttnOnimg" data-toggle="modal" data-target="#${ordVO.ordId}-score">
									</td>
									
									<td>
										<input type="submit" value="檢舉廠商" id="buttnOnimg1" data-toggle="modal" data-target="#${ordVO.ordId}-report">
									</td>									
									
								</tr>

						</c:forEach>
						<tbody>
					</table>
					
	                
<c:forEach var="ordVO" items="${list}">
	                
<!-- 評論 -->	                

<!-- Modal -->
<div id="memRepWindow">  
<div class="modal fade" id="${ordVO.ordId}-score" role="dialog">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">建立廠商檢舉單</h4>
      </div> <!-- modal-header -->
      
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/hotelRep/hotelRep.do" name="form1">      
	        <div class="modal-body">

				<!-- (開始)檢舉單內容 -->
				<table border="0">
					<tr>
						<th>廠商名稱:</th>
						<td>
							${ordVO.ordHotelVO.hotelName}
						</td>
					</tr>
					
				
					<tr>
						<th>旅客名稱:</th>
						<td>
							${ordVO.ordMemVO.memName}
						</td>
					</tr>	
					
				
					<tr>
						<th>訂單編號:</th>
						<td>
							${ordVO.ordId}
						</td>
					</tr>	
					
					<tr>
						<th>檢舉內容:</th>
					
						<td>						
							<textarea name="hotelRepContent"></textarea>
						</td>
					</tr>	
				</table>
				
				<!-- (結束)檢舉單內容 -->

	        </div> <!-- modal-body -->
	        <div class="modal-footer">
	        
	          <input type="hidden" name="hotelRepHotelId" value="${ordVO.ordHotelVO.hotelId}">	
	          <input type="hidden" name="hotelRepMemId" value="${ordVO.ordMemVO.memId}">	
	          <input type="hidden" name="hotelRepOrdId" value="${ordVO.ordId}">
	          <input type="hidden" name="hotelRepStatus" value="0">		        
	          <input type="hidden" name="action" value="insert">

	          <button type="button" class="btn btn-warning" data-dismiss="modal">
			  	取消
			  </button>

	        
	          <button type="submit" class="btn btn-primary">
			  	提交
			  </button>
			  
	        </div> <!-- modal-footer -->
     </FORM>
     
    </div>
  </div>
</div>					
</div>	                
	                
<!-- 旅客檢舉單 -->	                

<!-- Modal -->
<div id="memRepWindow">  
<div class="modal fade" id="${ordVO.ordId}-report" role="dialog">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">建立廠商檢舉單</h4>
      </div> <!-- modal-header -->
      
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/android/memRep/memRep.do" name="form1">      
	        <div class="modal-body">

				<!-- (開始)檢舉單內容 -->
				<table border="0">
					<tr>
						<th>旅客名稱:</th>
						<td>
							${ordVO.ordMemVO.memName}
						</td>
					</tr>
						
					<tr>
						<th>廠商名稱:</th>
						<td>
							${ordVO.ordHotelVO.hotelName}
						</td>
					</tr>
					
					<tr>
						<th>訂單編號:</th>
						<td>
							${ordVO.ordId}
						</td>
					</tr>	
					
					<tr>
						<th>檢舉內容:</th>
					
						<td>						
							<textarea name="hotelRepContent"></textarea>
						</td>
					</tr>	
				</table>
				
				<!-- (結束)檢舉單內容 -->

	        </div> <!-- modal-body -->
	        <div class="modal-footer">
	        
	          <input type="hidden" name="memRepHotelId" value="${ordVO.ordHotelVO.hotelId}">	
	          <input type="hidden" name="memRepMemId" value="${ordVO.ordMemVO.memId}">	
	          <input type="hidden" name="memRepOrdId" value="${ordVO.ordId}">
	          <input type="hidden" name="hotelRepStatus" value="0">		        
	          <input type="hidden" name="action" value="insert">


	          <button type="button" class="btn btn-warning" data-dismiss="modal">
			  	取消
			  </button>

	        
	          <button type="button" class="btn btn-primary">
			  	提交
			  </button>
			  
	        </div> <!-- modal-footer -->
     </FORM>
     
    </div>
  </div>
</div>					
</div>	                
	                
</c:forEach>

	                
	           	   </c:when>		  
			       <c:otherwise>
			       	    <h1 id="WishH2">- 謝謝您的支持          <img src="<%=request.getContextPath()%>/frontend_mem/images/like.png"> - </h1>
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
<%@ include file="../indexFooter.jsp" %>
