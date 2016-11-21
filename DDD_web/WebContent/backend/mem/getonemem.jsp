<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.emp.model.*"%>

<%
	MemVO memvo =  (MemVO)request.getAttribute("memid");
	pageContext.setAttribute("memvo", memvo);
%>
<%
session.getAttribute("account");
EmpVO empvo =(EmpVO)session.getAttribute("empVO");
List<String> authorityList =(List<String>)session.getAttribute("authorityList");
session.setAttribute("empvo", empvo);
session.setAttribute("authorityList",authorityList);
%>
<%

if(!authorityList.contains("101")){
	response.sendRedirect(request.getContextPath()+"/backend/emp_index.jsp");
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
body {
	font-family: Tahoma, Verdana, 微軟正黑體;
	font-size: 13px;
}

#LoginButton {
	opacity: 0.7;
	z-index: 1;
	background: #0283df;
	color: #ffffff;
	position: relative;
	font-weight: bold;
	font-family: Tahoma, Verdana, 微軟正黑體;
	border: 0px;
	border-radius: 10px;
	padding: 4px;
	font-size: 12px;
}

.vcenter {
	display: inline-block;
	vertical-align: middle;
	float: none;
}
#empName{
	color:white;
}
#tablehotel{
	font-family: Tahoma, Verdana, 微軟正黑體;
	font-size:20px;
	
}
#tablehotel th{
	text-align:center;
}
.LoginButtonBlack{
	opacity: 0.7;
	z-index: 1;
	background: rgb(212, 74, 151);
	color: #ffffff;
	position: relative;
	font-weight: bold;
	font-family: Tahoma, Verdana, 微軟正黑體;
	border: 0px;
	border-radius: 10px;
	padding: 8px;
	font-size: 20px;

}
.LoginButtonBlack1{
	opacity: 0.7;
	z-index: 1;
	background:#0283df;
	color: #ffffff;
	position: relative;
	font-weight: bold;
	font-family: Tahoma, Verdana, 微軟正黑體;
	border: 0px;
	border-radius: 10px;
	padding: 8px;
	font-size: 20px;

}
.aaa{
	height: 1250px;
	background-color: #ccc;
	margin-top: 53px;
}

</style>
</head>
<body>
	<div id="top-bar">
			<nav class="navbar navbar-inverse" role="navigation">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
						<span class="sr-only">選單切換</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#"><span class="glyphicon glyphicon-heart-empty"></span> DDD後端管理介面</a>
				</div>
			
				<!-- 手機隱藏選單區 -->
				<div class="collapse navbar-collapse navbar-ex1-collapse">			
					<!-- 右選單 -->
					<ul class="nav navbar-nav navbar-right">
						<li id="empName"><a href="#">${empvo.empName }</a></li>
						<li><a href="#"><span class="glyphicon glyphicon-bell"></span> 通知</a></li>
						<li><a href="<%=request.getContextPath()%>/emp/emp.do"><span class="glyphicon glyphicon-log-out" ></span> 登出</a></li>
					</ul>
				</div>
				<!-- 手機隱藏選單區結束 -->
			</nav>
		</div> 

		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12 col-sm-2 aaa" style="background-color: #DCDCDC;">
					<div class="panel-group" id="accordion2" role="tablist" aria-multiselectable="true">
					  <!-- 區塊1 -->
					  <c:if test="${fn:contains(authorityList,'102')}"> <%-- 行政業務管理 --%>
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="tab1" style="background-color:#B0C4DE;">
					      <h4 class="panel-title">
					        <a role="button" data-toggle="collapse" data-parent="#accordion2" href="#aaa" aria-expanded="true" aria-controls="aaa">
							 
							 <span class="glyphicon glyphicon-list-alt"></span> 訂單
					        </a>
					      </h4>
					    </div>
					    
					    <div id="aaa" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="tab1" >
					      <div class="panel-body">
					        	<ul style="list-style-type: none">
					        		<li>今日訂單維護</li>
					        		<li>歷史訂單維護</li>
					        	</ul>
					      </div>
					    </div>
					  </div>
					   </c:if>
					  <!-- 區塊4 -->
					  <c:if test="${fn:contains(authorityList,'103')}"> <%-- 首頁管理 --%>
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="tab4" style="background-color:#B0C4DE;">
					      <h4 class="panel-title">
					        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion2" href="#ddd" aria-expanded="false" aria-controls="ddd">
					          <span class="glyphicon glyphicon-bullhorn"></span> Banner					         
					        </a>
					      </h4>
					    </div>
					     
					    <div id="ddd" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab4">
					      <div class="panel-body">
					        	<ul style="list-style-type: none">
					        		<li>Banner方案管理</li>
					        		<li>Banner審核作業</li>
					        		<li>Banner管理作業</li>
					        	</ul>
					      </div>
					    </div>
					  </div>
					  </c:if>
					  <!-- 區塊7 -->
					  <c:if test="${fn:contains(authorityList,'102')}"> <%-- 行政業務管理 --%>
					  <div class="panel panel-default">					  
					    <div class="panel-heading" role="tab" id="tab7" style="background-color:#B0C4DE;">
					      <h4 class="panel-title">
					        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion2" href="#ggg" aria-expanded="false" aria-controls="fff">
					          <span class="glyphicon glyphicon-piggy-bank"></span> 上架月租費
					        </a>
					      </h4>
					    </div>
					    <div id="ggg" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab7">
					      <div class="panel-body">
					        	<ul style="list-style-type: none">
					        		<li>月租管理作業</li>
					        	</ul>
					      </div>
					    </div>
					  </div>		
					  </c:if>			  					  
					  <!-- 區塊2 -->
					  <c:if test="${fn:contains(authorityList,'101')}"> <%-- 人事管理 --%>
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="tab2" style="background-color:#B0C4DE;">
					      <h4 class="panel-title">
					        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion2" href="#bbb" aria-expanded="false" aria-controls="bbb">
					          <span class="glyphicon glyphicon-king"></span> 一般會員
					        </a>
					      </h4>
					    </div>
					    <div id="bbb" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab2">
					      <div class="panel-body">
					        	<ul style="list-style-type: none">
					        		<a href="<%=request.getContextPath()%>/backend/mem/listAllMem.jsp"><li>一般會員維護</li></a>
					        		<li>對話紀錄維護</li>
					        	</ul>
					      </div>
					    </div>
					  </div>
					  </c:if>	
					  <!-- 區塊3 -->
					  <c:if test="${fn:contains(authorityList,'101')}"> <%-- 人事管理 --%>
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="tab3" style="background-color:#B0C4DE;">
					      <h4 class="panel-title">
					        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion2" href="#ccc" aria-expanded="false" aria-controls="ccc">
					          <span class="glyphicon glyphicon-queen"></span> 廠商會員
					        </a>
					      </h4>
					    </div>
					    <div id="ccc" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab3">
					      <div class="panel-body">
					        	<ul style="list-style-type: none">
					        		<a href="<%=request.getContextPath()%>/backend/hotel/get_need_check.jsp"><li>廠商審核作業</li></a>
					        		<a href="<%=request.getContextPath()%>/backend/hotel/listAllHotel.jsp"><li>廠商會員維護</li></a>
					        	</ul>
					      </div>
					    </div>
					  </div>	
					  </c:if>				  
					  <!-- 區塊5 -->
					  <c:if test="${fn:contains(authorityList,'101')}"> <%-- 人事管理 --%>
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="tab5" style="background-color:#B0C4DE;">
					      <h4 class="panel-title">
					        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion2" href="#eee" aria-expanded="false" aria-controls="eee">
					          <span class="glyphicon glyphicon-pawn"></span> 員工
					        </a>
					      </h4>
					    </div>
					    <div id="eee" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab5">
					      <div class="panel-body">
					        	<ul style="list-style-type: none">
					        		<a href="<%=request.getContextPath()%>/backend/emp/addEmp.jsp"><li>新增員工資料</li></a>
									<a href="<%=request.getContextPath()%>/backend/emp/listAllEmp.jsp""><li>員工資料維護</li></a>
					        	</ul>
					      </div>
					    </div>
					  </div>		
					  </c:if>				  					  					  
					  <!-- 區塊6 -->
					  <c:if test="${fn:contains(authorityList,'104')}"> <%-- 人事管理 --%>
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="tab6" style="background-color:#B0C4DE;">
					      <h4 class="panel-title">
					        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion2" href="#fff" aria-expanded="false" aria-controls="fff">
					          <span class="glyphicon glyphicon-thumbs-down"></span> 檢舉
					        </a>
					      </h4>
					    </div>
					    <div id="fff" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab6">
					      <div class="panel-body">
					        	<ul style="list-style-type: none">
					        		<li>一般會員檢舉</li>
					        		<li>廠商會員檢舉</li>
					        	</ul>
					      </div>
					    </div>
					  </div>
					  </c:if>
					  <!-- 區塊8 -->
					  <c:if test="${fn:contains(authorityList,'102')}"> <%-- 行政業務管理 --%>
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="tab8" style="background-color:#B0C4DE;">
					      <h4 class="panel-title">
					        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion2" href="#hhh" aria-expanded="false" aria-controls="hhh">
					          <span class="glyphicon glyphicon-thumbs-up"></span> 留言評價
					        </a>
					      </h4>
					    </div>
					    <div id="hhh" class="panel-collapse collapse" role="tabpanel" aria-labelledby="tab8">
					      <div class="panel-body">
					        	<ul style="list-style-type: none">
					        		<li>留言評價維護</li>
					        	</ul>
					      </div>
					    </div>
					  </div>
					  </c:if>						  	
					</div>
				</div>
			<!-- 		hotelvo		bar結束 -->
			<div class="col-xs-12 col-sm-10 tablediv text-center" > 
				<table border='0' bordercolor='black' cellspacing="0" cellpadding="5"
				width='800' class="table table-hover" id="tablehotel" >	
				<tr>
					<td>
					<H1>大頭照</H1>
					<img src='data:image/jpeg;base64,${memvo.bs64}'
							width="250" height="180" />
					</td>					
				</tr>
				<tr>
					<th>一般會員編號</th>
					<th>個人簡介</th>
				</tr>
				<tr>
					<td>${memvo.memId}</td>
					<td>${memvo.memIntro}</td>
				</tr>
				<tr>
					<th>帳號</th>
					<th>密碼</th>
				</tr>
				<tr>
					<td>${memvo.memAccount}</td>
					<td>${memvo.memPsw}</td>
				</tr>
				<tr>
					<th>姓名</th>
					<th>性別</th>
					
				</tr>
				<tr>
					<td>${memvo.memName}</td>
					<c:choose>
			            <c:when test="${memvo.memGender=='f'}">
			                <td>女生</td>
			            </c:when>
			            <c:when test="${memvo.memGender=='m'}">
			                <td>男生</td>
			            </c:when>		       
			       	</c:choose>																			
				</tr>
				<tr>
					<th>身份證字號</th>
					<th>出生年月日</th>
				</tr>
				<tr>
					<td>${memvo.memTwId}</td>
					<td>${memvo.memBirthDate}</td>
				</tr>	
				<tr>
					<th>電話</th>
					<th>共住預算</th>
				</tr>
				<tr>
					<td>${memvo.memPhone}</td>
					<td>${memvo.memLiveBudget}</td>		
				</tr>
				<tr>
					<th>信用卡有效日期</th>
					<th>黑名單</th>
				</tr>
				<tr>
					<td>${memvo.memCreditDueDate}</td>
					
						<c:choose>
		            <c:when test="${memvo.memBlackList==0}">
		                <td>非黑名單</td>
		            </c:when>	        
		            <c:otherwise>
		                <td>黑名單</td>
		            </c:otherwise>
		        </c:choose>
						
				</tr>
				
				<tr>
					<th>信用卡卡號</th>
					<th>信用卡驗證碼</th>
				</tr>
				<tr>
					<td>${memvo.memCreditCardNo}</td>
					<td>${memvo.memCreditCheckNo}</td>		
				</tr>											
			
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/mem/mem.do">
							<input type="submit" value="將此會員加入黑名單" class="LoginButtonBlack"> 
							<input type="hidden" name="memId" value="${memvo.memId}">
							<input type="hidden" name="memBlackList" value="${memvo.memBlackList}">
							<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
							<input type="hidden" name="action" value="update_blacklist">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/mem/mem.do">
							<input type="submit" value="取消黑名單" class="LoginButtonBlack1"> 
							<input type="hidden" name="memId" value="${memvo.memId}">
							<input type="hidden" name="memBlackList" value="${memvo.memBlackList}">
							<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
							<input type="hidden" name="action" value="update_blacklist1">
						</FORM>
					</td>
				</tr>
				
			</table>
			<div align="center"><a href="<%=request.getContextPath()%>/backend/mem/listAllMem.jsp">回會員首頁</a></div>
			</div>
			

</body>
</html>