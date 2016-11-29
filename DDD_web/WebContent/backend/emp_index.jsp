<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hotelrep.model.*"%>
<%@ page import="com.memrep.model.*"%>
<%@ page import="com.ad.model.*"%>
<%@ page import="com.hotel.model.*"%>
<%@ page import="util.adStatusTransform"%>

<%
	HotelService dao = new HotelService();
	List<HotelVO> hotellist = dao.getAll_NEED_CHECK();
	pageContext.setAttribute("hotellist", hotellist);
%>

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
#emptableth th{
	text-align:center;
	background-color:#b0c4de;
	
}
#emptableth th h3{
	
	font-weight:bold;
}
#list th{
	text-align:center;
	background-color:#b0c4de;
	font-size:24px;
}
#list td{
	
	font-size:18px;
}
#up{
	font-family:Tahoma,Verdana,Arial,微軟正黑體;
	font-size:18px;	
	font-weight:bold; 
}
</style>
</head>

<%@ include file="/backend/backendBody.jsp"%>

<div class="col-xs-12 col-sm-10 bb text-center" style="background-color:#FFFFFF;">

	<h1>後端首頁管理系統</h1>
	<hr  size="10" align="center" style="border-top: 3px solid #000000">
	<img src="<%=request.getContextPath()%>/frontend_mem/images/DDD_NEW_LOGO.png" id="register" width="400" height="230">	
	<div id="up">熱情服務、轉變觀念、增強知識、迎接挑戰。</div>
	<h2 align="left"><img src="<%=request.getContextPath()%>/backend/images/promotion.png" width="30px" height="30px">公告</h2>
	<table class="table table-hover text-center" id="list">
		<tr>
			<th>公告日期</th>
			<th>主題</th>
			<th>類型</th>
			<th>實施日期</th>
		</tr>
		
		<tr>			
			<td><a href="">2016-11-29</a></td>
			<td><a href="">家庭日(清境兩日遊)</a></td>
			<td><a href="">活動</a></td>
			<td><a href="">2016-12-24</a></td>			
		</tr>
		
		<tr>			
			<td><a href="">2016-11-29</a></td>
			<td><a href="">例行開會於四樓大會議室(請準時與會)</a></td>
			<td><a href="">公告</a></td>
			<td><a href="">2016-12-09</a></td>			
		</tr>
		
		<tr>			
			<td><a href="">2016-11-22</a></td>
			<td><a href="">員工獎金申請辦法</a></td>
			<td><a href="">公告</a></td>
			<td><a href="">2016-12-01</a></td>			
		</tr>
		
		<tr>
			<td><a href="">2016-10-22</a></td>
			<td><a href="">審核廠商規範(相關人員請詳讀)</a></td>
			<td><a href="">公告</a></td>
			<td><a href="">2016-10-22</a></td>
		</tr>
		<tr>
			<td><a href="">2016-10-22</a></td>
			<td><a href="">BANNER作業流程(相關人員請詳讀)</a></td>
			<td><a href="">公告</a></td>
			<td><a href="">2016-10-22</a></td>
		</tr>
	</table>
	
	<h2 align="left"><img src="<%=request.getContextPath()%>/backend/images/list.png" width="30px" height="30px">待辦事項</h2>
	<table  class="table table-hover text-center" id="emptableth">
		<tr>
			<th><c:if test="${fn:contains(authorityList,'104')}"> <%-- 客服管理 --%><a href="#" style="color:black;"></c:if><h3>廠商檢舉單 <img src="<%=request.getContextPath()%>/backend/images/click.png" width="25px" height="25px"></h3></a></th>
			<th><c:if test="${fn:contains(authorityList,'104')}"> <%-- 客服管理 --%><a href="#" style="color:black;"></c:if><h3>旅客檢舉單 <img src="<%=request.getContextPath()%>/backend/images/click.png" width="25px" height="25px"></h3></a></th>
			<th><c:if test="${fn:contains(authorityList,'103')}"> <%-- 首頁管理 --%><a href="<%=request.getContextPath()%>/backend/Ad/listAllAdPage.jsp" style="color:black;"></c:if><h3>Banner<img src="<%=request.getContextPath()%>/backend/images/click.png" width="25px" height="25px"></h3></a></th>
			<th><c:if test="${fn:contains(authorityList,'101')}"> <%-- 人事管理 --%><a href="<%=request.getContextPath()%>/backend/hotel/get_need_check.jsp"  style="color:black;"></c:if><h3>廠商審核<img src="<%=request.getContextPath()%>/backend/images/click.png" width="25px" height="25px"></h3></a></th>
		</tr>
		<tr>
			<td><h4>目前有 <%=hotelRepTodolist.size()%>筆未處理 </h4></td>
			<td><h4>目前有 <%=memRepTodolist.size()%>筆未處理</h4></td>
			<td><h4>目前有 <%=adTodolist.size()%>筆未處理</h4></td>
			<td><h4>目前有 <%=hotellist.size()%>筆未處理</h4></td>
		</tr>
	</table>
</div>	
		
</body>
</html>