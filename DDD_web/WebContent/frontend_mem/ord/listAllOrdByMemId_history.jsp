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
	
	.memRepWindow textarea,.memRatingWindow textarea {
    	width: 700px;
    	height: 300px;
    	resize: both;
    	overflow: auto;
	}
	
	.memRepWindow th, .memRatingWindow th {
		font-size: 18px;
	}	
	
	.memRepWindow td, .memRatingWindow td {
		font-size: 18px;
	}	
	

</style>

    <section>
       <div class="col-xs-12 col-sm-12 " style="z-index:-1;">
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
									
									<% OrdVO ordVO = (OrdVO)(pageContext.getAttribute("ordVO")); %>
									
									<% if(ordVO.getOrdLiveDate()!= null){ %>
																	
									<td><%= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format( ordVO.getOrdLiveDate() ) %></td>		
									<% } else { %>
									<td>入住日期讀取中</td>
									<% } %>
									<td>${ordStatusTrans.get(ordVO.ordStatus)}</td>

									<% if(ordVO.getOrdRatingStarNo() == null && ordVO.getOrdRatingContent() == null ){ %>
											<td id="td2-${ordVO.ordId}">
												<input type="submit" value="給個分數" id="buttnOnimg" data-toggle="modal" data-target="#rating-${ordVO.ordId}">
											</td>
									<% } else { %>
					       					<td id="td2-${ordVO.ordId}">
									 			已給分
											</td>
									<% } %>
							
									<c:choose>
										<c:when test="${ordVO.ordMemReps.isEmpty()}">	
											<td id="td-${ordVO.ordId}">
												<input type="submit" value="檢舉廠商" id="buttnOnimg1" data-toggle="modal" data-target="#report-${ordVO.ordId}">
											</td>	
										</c:when>		  
					       				<c:otherwise>
					       					<td id="td-${ordVO.ordId}">
									 			已檢舉
											</td>
										</c:otherwise>
									 </c:choose>  									
								</tr>

						</c:forEach>
						<tbody>
					</table>
					 
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
   
   
<c:forEach var="ordVO" items="${list}">

<!--------------------  旅客評分   -------------------->	

<!-- Modal -->
<div class="memRatingWindow">  
<div class="modal fade" role="dialog" id="rating-${ordVO.ordId}">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="modal-title">評分與意見</h3>
      </div> <!-- modal-header -->
      
		<FORM METHOD="post" id="jsonForm2-${ordVO.ordId}" name="form1">      
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
						<th>評論內容:</th>
					
						<td>						
							<textarea name="ordRatingContent"></textarea>
						</td>
					</tr>	
				</table>
				
				<!-- (結束)檢舉單內容 -->

	        </div> <!-- modal-body -->
	        <div class="modal-footer">
	          <input type="hidden" name="ordId" value="${ordVO.ordId}">	        
	          <input type="hidden" name="action" value="updateRating">

	          <button type="button" id="jsonClose2-${ordVO.ordId}" class="btn btn-warning" data-dismiss="modal">
			  	取消
			  </button>

	          <button type="button" id="jsonPost2-${ordVO.ordId}" class="btn btn-primary">
			  	提交
			  </button>
			 			   
	        </div> <!-- modal-footer -->
     </FORM>
     
    </div>
  </div>
</div>					
</div>	                


<script>
 
var ctx ="${pageContext.request.contextPath}";
 
var myRatingForm = {
		"action" : "",
		"ordId" : "",
		"ordRatingContent" : "",
		"ordRatingStarNo" : "5"
}; 
 
 
$(document).ready(function(){

	$("#jsonPost2-${ordVO.ordId}").click(function(e){
		
		myRatingForm.action = $("#jsonForm2-${ordVO.ordId} input[name='action']").val();
		myRatingForm.ordRatingContent = $("#jsonForm2-${ordVO.ordId} textarea").val();
		myRatingForm.ordId = $("#jsonForm2-${ordVO.ordId} input[name='ordId']").val();
		
		console.log(myRatingForm);
		
		var url = ctx + "/android/ord/ord.do";
		//console.log(url);
		$.post(url,JSON.stringify(myRatingForm));
		
		$("#jsonClose2-${ordVO.ordId}").click();
		
		$("#td2-${ordVO.ordId}").empty().append("已給分");
				
		
	});
	 	 
});
 
</script>  
	                           
<!--------------------  旅客檢舉單   -------------------->	                

<!-- Modal -->
<div class="memRepWindow">  
<div class="modal fade" role="dialog" id="report-${ordVO.ordId}">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="modal-title">建立旅客檢舉單</h3>
      </div> <!-- modal-header -->
      
		<FORM METHOD="post" id="jsonForm-${ordVO.ordId}" name="form1">      
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
							<textarea name="content"></textarea>
						</td>
					</tr>	
				</table>
				
				<!-- (結束)檢舉單內容 -->

	        </div> <!-- modal-body -->
	        <div class="modal-footer">
	          <input type="hidden" name="ordId" value="${ordVO.ordId}">	        
	          <input type="hidden" name="action" value="insert">

	          <button type="button" id="jsonClose-${ordVO.ordId}" class="btn btn-warning" data-dismiss="modal">
			  	取消
			  </button>

	          <button type="button" id="jsonPost-${ordVO.ordId}" class="btn btn-primary">
			  	提交
			  </button>
			 			   
	        </div> <!-- modal-footer -->
     </FORM>
     
    </div>
  </div>
</div>					
</div>	                


<script>
 
//var ctx ="${pageContext.request.contextPath}";
 
var myRepForm = {
		"action" : "",
		"ordId" : "",
		"content" : ""
}; 
 
 
$(document).ready(function(){

	$("#jsonPost-${ordVO.ordId}").click(function(e){
		
		myRepForm.action = $("#jsonForm-${ordVO.ordId} input[name='action']").val();
		myRepForm.content = $("#jsonForm-${ordVO.ordId} textarea").val();
		myRepForm.ordId = $("#jsonForm-${ordVO.ordId} input[name='ordId']").val();
		
		//console.log(myRepForm);
		
		var url = ctx + "/android/memRep/memRep.do";
		//console.log(url);
		$.post(url,JSON.stringify(myRepForm));
		
		$("#jsonClose-${ordVO.ordId}").click();
		
		$("#td-${ordVO.ordId}").empty().append("已檢舉");
		
	});
	 	 
});
 
</script>  

	                
</c:forEach>
   
   
   
<%@ include file="../indexFooter.jsp" %>
