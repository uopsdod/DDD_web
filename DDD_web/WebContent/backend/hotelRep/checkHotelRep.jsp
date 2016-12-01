<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hotelrep.model.*"%>
<%@ page import="com.memrep.model.*"%>

<%-- 用EL練習寫 --%>

<%
    HotelRepService hotelRepSvc = new HotelRepService();
    List<HotelRepVO> hotelReplist = hotelRepSvc.getAll();
    List<HotelRepVO> hotelRepTodolist = new ArrayList<HotelRepVO>();
    
    /* 篩選出未處理的 */
    for(HotelRepVO aHotelRepVO : hotelReplist){
    	if( "0".equals( aHotelRepVO.getHotelRepStatus() )  ){
    		hotelRepTodolist.add(aHotelRepVO);
    	}
    }
 
    pageContext.setAttribute("hotelRepTodolist",hotelRepTodolist);
%>

<%
    MemRepService memRepSvc = new MemRepService();
    List<MemRepVO> memReplist = memRepSvc.getAll();
    List<MemRepVO> memRepTodolist = new ArrayList<MemRepVO>();
    
    /* 篩選出未處理的 */
    for(MemRepVO aMemRepVO : memReplist){
    	if( "0".equals( aMemRepVO.getMemRepStatus() )  ){
    		memRepTodolist.add(aMemRepVO);
    	}
    }    
    
    pageContext.setAttribute("memRepTodolist",memRepTodolist);
%>

<!DOCTYPE html>
<html lang="">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!-- 請輸入標題 -->
	<title>檢舉單審核作業 - checkHotelRep.jsp</title>
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/frontend_hotel/css/sweetalert.css">
	<!-- 自訂CSS -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/css/0_main.css">
	
	<script src="<%=request.getContextPath()%>/backend/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/backend/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/frontend_hotel/js/sweetalert.min.js"></script>
	<!-- 自訂JavaScript -->
	<script src=""></script>
</head>

<body>

<%@ include file="/backend/backendBody.jsp"%>

<%
if(!authorityList.contains("104")){
	response.sendRedirect(request.getContextPath()+"/backend/emp_index.jsp");
}
%>	

			<!-- 右邊的主要區塊 -->
			<div class="col-xs-12 col-sm-10 bb"
				style="background-color: #FFFAF0;">

				<!-- 麵包屑(當前路徑) -->
				<ol class="breadcrumb">
					<li>檢舉</li>
					<li class="active">檢舉單審核作業</li>
				</ol>

				<!-- 主要的table -->
				<h2 align="left">廠商檢舉單審核</h2>
<c:choose>
<c:when test="${hotelRepTodolist.size()!=0}">				
				<table class="table table-hover" border="1">
					<!-- table標題 -->
					<thead>
						<tr style="background-color: #B0C4DE;">
							<th class="text-center">廠商檢舉單編號</th>
							<th class="text-center">(原告)廠商名稱</th>
							<th class="text-center">(被告)旅客姓名</th>
							<th class="text-center">訂單編號</th>
							<th class="text-center">檢舉內容</th>
							<th class="text-center">檢舉時間</th>
							<th colspan='2' class="text-center">審核</th>
						</tr>
					</thead>

					<!-- table內容 -->
					<tbody>

						<c:forEach var="hotelRepVO" items="${hotelRepTodolist}">
							<tr align='center' valign='middle'>
								<td>${hotelRepVO.hotelRepId}</td>
								<td>${hotelRepVO.hotelRepHotelVO.hotelName}</td>
								<td>${hotelRepVO.hotelRepMemVO.memName}</td>
								<td>${hotelRepVO.hotelRepOrdVO.ordId}</td>
								<td><c:out value="${hotelRepVO.hotelRepContent}" default="無檢舉內容"/></td>
								<td>${hotelRepVO.hotelRepDate}</td>
								
								<td>
									<form method="post" action="<%=request.getContextPath()%>/hotelRep/hotelRep.do">
										<button type="submit" class="btn btn-warning js-sweetAlert-fail">
											<span class="glyphicon glyphicon-remove">不通過</span>
										</button>
										
										<input type="hidden" name="hotelRepId" value="${hotelRepVO.hotelRepId}">
										<input type="hidden" name="hotelRepStatus" value="1">
										<input type="hidden" name="memId" value="${hotelRepVO.hotelRepMemVO.memId}">
										<input type="hidden" name="hotelRepEmpId" value="${empvo.empId}">
										<input type="hidden" name="memBlackList" value="0">
									    <input type="hidden" name="action" value="setMemBlackList">
									    
									</form>
								</td>
								
								<td>
									<form method="post" action="<%=request.getContextPath()%>/hotelRep/hotelRep.do">
										<button type="submit" class="btn btn-success js-sweetAlert-succ">
											<span class="glyphicon glyphicon-ok">通過</span>
										</button>
										<input type="hidden" name="hotelRepId" value="${hotelRepVO.hotelRepId}">
										<input type="hidden" name="hotelRepStatus" value="2">
										<input type="hidden" name="memId" value="${hotelRepVO.hotelRepMemVO.memId}">
										<input type="hidden" name="hotelRepEmpId" value="${empvo.empId}">
										<input type="hidden" name="memBlackList" value="1">
										
										<input type="hidden" name="action" value="setMemBlackList">
									</form>
								</td>								
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
</c:when>
<c:otherwise>
<h3>目前無未處理的廠商檢舉單</h3>
</c:otherwise>				
</c:choose>				
	
				<!-- 主要的table -->
				<h2 align="left">旅客檢舉單審核</h2>

<c:choose>
<c:when test="${memRepTodolist.size()!=0}">				
				<table class="table table-hover" border="1">
					<!-- table標題 -->
					<thead>
						<tr style="background-color: #B0C4DE;">
							<th class="text-center">旅客檢舉單編號</th>
							<th class="text-center">(原告)旅客姓名</th>
							<th class="text-center">(被告)廠商名稱</th>
							<th class="text-center">訂單編號</th>
							<th class="text-center">檢舉內容</th>							
							<th class="text-center">檢舉時間</th>
							<th colspan='2' class="text-center">審核</th>
						</tr>
					</thead>

					<!-- table內容 -->
					<tbody>

						<c:forEach var="memRepVO" items="${memRepTodolist}">
							<tr align='center' valign='middle'>
								<td>${memRepVO.memRepId}</td>
								<td>${memRepVO.memRepMemVO.memName}</td>
								<td>${memRepVO.memRepHotelVO.hotelName}</td>
								<td>${memRepVO.memRepOrdVO.ordId}</td>
								<td><c:out value="${memRepVO.memRepContent}" default="無檢舉內容"/></td>
								<td>${memRepVO.memRepDate}</td>
								
								<td>
									<form method="post" action="<%=request.getContextPath()%>/memRep/memRep.do">
										<button type="submit" class="btn btn-warning ">
											<span class="glyphicon glyphicon-remove js-sweetAlert-fail">不通過</span>
										</button>
										
										<input type="hidden" name="memRepId" value="${memRepVO.memRepId}">
										<input type="hidden" name="memRepStatus" value="1">
										<input type="hidden" name="hotelId" value="${memRepVO.memRepHotelVO.hotelId}">
										<input type="hidden" name="memRepEmpId" value="${empvo.empId}">
										<input type="hidden" name="hotelBlackList" value="0">
									    <input type="hidden" name="action" value="setHotelBlackList">
									    
									</form>
								</td>
								
								<td>
									<form method="post" action="<%=request.getContextPath()%>/memRep/memRep.do">
										<button type="submit" class="btn btn-success ">
											<span class="glyphicon glyphicon-ok js-sweetAlert-succ">通過</span>
										</button>
										<input type="hidden" name="memRepId" value="${memRepVO.memRepId}">
										<input type="hidden" name="memRepStatus" value="2">
										<input type="hidden" name="hotelId" value="${memRepVO.memRepHotelVO.hotelId}">
										<input type="hidden" name="memRepEmpId" value="${empvo.empId}">
										<input type="hidden" name="hotelBlackList" value="1">
										
										<input type="hidden" name="action" value="setHotelBlackList">
									</form>
								</td>							
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
</c:when>
<c:otherwise>
<h3>目前無未處理的旅客檢舉單</h3>
</c:otherwise>									
</c:choose>					
				
			</div>
		</div>
	</div>
</body>
</html>

<script>

/* SweetAlert */
$(document).ready(function(){
		
	$(".js-sweetAlert-succ").on('click',function(e){
	    e.preventDefault();
	    var form = $(this).parents('form');
	    sweetAlert({title: "APPROVED",
	    			text: "審核通過",
	    			type: "success",
	    			closeOnConfirm: true}, 
	    function(isConfirm){
	    	if (isConfirm) form.submit();
	    });

	});
 	
	$(".js-sweetAlert-fail").on('click',function(e){
	    e.preventDefault();
	    var form = $(this).parents('form');
	    sweetAlert({title: "REJECTED",
	    			text: "審核不通過",
	    			type: "error",
	    			closeOnConfirm: true}, 
	    function(isConfirm){
	    	if (isConfirm) form.submit();
	    });

	});
});


</script>