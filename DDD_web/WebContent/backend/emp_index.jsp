<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hotelrep.model.*"%>
<%@ page import="com.memrep.model.*"%>
<%@ page import="com.ad.model.*"%>
<%@ page import="util.adStatusTransform"%>

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

<%
    AdService adSvc = new AdService();
    List<AdVO> adlist = adSvc.getAll();
    List<AdVO> adTodolist = new ArrayList<AdVO>();
    
    /* 篩選出未處理的 */
    for(AdVO aAdVO : adlist){
    	if( "0".equals( aAdVO.getAdStatus() )  ){
    		adTodolist.add(aAdVO);
    	}
    }      
    
    pageContext.setAttribute("adTodolist",adTodolist);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>管理後端</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/emp/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/emp/css/0_main.css">
<script src="<%=request.getContextPath()%>/backend/emp/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/backend/emp/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/backend/emp/js/0_new.js "></script>
<style type="text/css">
.vcenter {
    display: inline-block;
    vertical-align: middle;
    float: none;
}
#empName{
	color:white;
}

</style>
</head>

<%@ include file="/backend/backendBody.jsp"%>

				<div class="col-xs-12 col-sm-10 bb" style="background-color:#FFFAF0;">

			<h2>待辦事項</h2>
			<hr  size="10" align="center" style="border-top: 3px solid #000000">


<a href="#"><h3>廠商檢舉單 </h3></a>
<h4>有 <%=hotelRepTodolist.size()%>筆未處理 </h4>
<!-- 廠商檢舉單 -->
<c:choose>
<c:when test="${hotelRepTodolist.size()!=0}">	
<table border='1' width='800'>
	<tr>
		<th>廠商檢舉單編號</th>
		<th>(檢舉人) 廠商名稱</th>
		<th>(被檢舉人) 旅客姓名</th>
		<th>訂單編號</th>
		<th>處理的員工姓名</th>
		<th>檢舉內容</th>
		<th>處理狀態</th>
		<th>檢舉時間</th>
		<th>處理時間</th>
	</tr>
		

	<c:forEach var="hotelRepVO" items="${hotelRepTodolist}">
		<tr align='center' valign='middle'>
			<td>${hotelRepVO.hotelRepId}</td>
			<td>${hotelRepVO.hotelRepHotelVO.hotelName}</td>
			<td>${hotelRepVO.hotelRepMemVO.memName}</td>
			<td>${hotelRepVO.hotelRepOrdVO.ordId}</td>
			<td><c:out value="${hotelRepVO.hotelRepEmpVO.empName}" default="尚無員工處理"/></td>
			
			<td><c:out value="${hotelRepVO.hotelRepContent}" default="無檢舉內容"/></td>
			
			<td>${hotelRepStatusTrans.get(hotelRepVO.hotelRepStatus)}</td>

			
			<td>${hotelRepVO.hotelRepDate}</td>
			
			<td><c:out value="${hotelRepVO.hotelRepReviewDate}" default="尚未處理"/></td>
		</tr>
	</c:forEach>
</table>
</c:when>
</c:choose> 

<a href="#"><h3>旅客檢舉單 </h3></a>
<h4>有 <%=memRepTodolist.size()%>筆未處理</h4>
<!-- 旅客檢舉單 -->
<c:choose>
<c:when test="${memRepTodolist.size()!=0}">
<table border='1' width='800'>
	<tr>
		<th>旅客檢舉單編號</th>
		<th>(檢舉人) 旅客姓名</th>
		<th>(被檢舉人) 廠商名稱</th>
		<th>訂單編號</th>
		<th>處理的員工姓名</th>
		<th>檢舉內容</th>
		<th>處理狀態</th>
		<th>檢舉時間</th>
		<th>處理時間</th>
	</tr>
		

	<c:forEach var="memRepVO" items="${memRepTodolist}">
		<tr align='center' valign='middle'>
			<td>${memRepVO.memRepId}</td>
			<td>${memRepVO.memRepMemVO.memName}</td>
			<td>${memRepVO.memRepHotelVO.hotelName}</td>
			<td>${memRepVO.memRepOrdVO.ordId}</td>
			<td><c:out value="${memRepVO.memRepEmpVO.empName}" default="尚無員工處理"/></td>
			
			<td><c:out value="${memRepVO.memRepContent}" default="無檢舉內容"/></td>
			
			<td>${hotelRepStatusTrans.get(memRepVO.memRepStatus)}</td>

			
			<td>${memRepVO.memRepDate}</td>
			
			<td><c:out value="${memRepVO.memRepReviewDate}" default="尚未處理"/></td>
		</tr>
	</c:forEach>
</table>
</c:when>
</c:choose> 

<a href="#"><h3>Banner</h3></a>
<h4>有 <%=adTodolist.size()%>筆未處理</h4>
<!-- Banner-->
<c:choose>
<c:when test="${adTodolist.size()!=0}">
<table border='1' width='800'>
	<tr>
		<th>Banner編號</th>
		<th>Banner方案名稱</th>
		<th>廠商名稱</th>
		<th>處理狀態</th>

	</tr>
		

	<c:forEach var="adVO" items="${adTodolist}">
		<tr align='center' valign='middle'>
			<td>${adVO.adId}</td>
			
			<jsp:useBean id="adPlanSvc" scope="page" class="com.adplan.model.AdPlanService" />
			
			<c:forEach var="adPlanVO" items="${adPlanSvc.all}">						
				<c:if test="${adVO.adAdPlanId == adPlanVO.adPlanId}">	
					<td>${adPlanVO.adPlanName}</td>
				</c:if>
			</c:forEach>
			
			<jsp:useBean id="hotelSvc" scope="page" class="com.hotel.model.HotelService" />
			
			<c:forEach var="hotelVO" items="${hotelSvc.all}">
				<c:if test="${adVO.adHotelId == hotelVO.hotelId}">	
					<td>${hotelVO.hotelName}</td>
				</c:if>
			</c:forEach>


			<td><%=adStatusTransform.status( ((AdVO)(pageContext.getAttribute("adVO"))).getAdStatus()  ) %></td>

		</tr>
	</c:forEach>
</table>
</c:when>
</c:choose> 



				</div>	
			</div>
		</div>	
	</body>
</html>