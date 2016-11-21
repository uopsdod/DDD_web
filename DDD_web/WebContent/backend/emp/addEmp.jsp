<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>

<%
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String formattedDate = df.format(new java.util.Date());
%>

<%
	EmpVO empVO = (EmpVO) request.getAttribute("empVO");
	session.getAttribute("account");
	EmpVO empvo =(EmpVO)session.getAttribute("empVO");
	List<String> authorityList =(List<String>)session.getAttribute("authorityList");
	session.setAttribute("empvo", empvo);
	session.setAttribute("authorityList",authorityList);
%>
<!-- 如果權限沒有人事轉到首頁怕他偷吃步-->
<%
if(!authorityList.contains("101")){
	response.sendRedirect(request.getContextPath()+"/backend/emp_index.jsp");
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/emp/css/addEmp.css">
<script src="<%=request.getContextPath()%>/backend/emp/js/addEmp.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>管理後端</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/emp/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/emp/css/0_main.css">

<script src="<%=request.getContextPath()%>/backend/emp/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/backend/emp/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/backend/emp/js/0_new.js "></script>
<style type="text/css">

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
				<div class="col-xs-12 col-sm-2 aa" style="background-color: #DCDCDC;">
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
<!-- 		bar end -->
		 
		<div class="col-xs-12 col-sm-10 tablediv" align="center"> 
		<h1 align="center">新進員工<img src="<%=request.getContextPath()%>/backend/emp/img/usercolor.png" width="100px" height="100px" ></h1><br>
		<hr  size="10" width="55%"  align="center" style="border-top: 3px solid #000000"><br>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font color='red'>
					<c:forEach var="message" items="${errorMsgs}">
						${message}<br>
					</c:forEach>
			</font>
		</c:if>

		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" name="form1"
			enctype="multipart/form-data">
			<table border="0" align="center">
				<tr>
					<td class="titletext">員工姓名： </td>
					<td ><input type="TEXT" name="empName" size="45"
						class="UserName" placeholder="請輸入員工名字" 
						value="<%=(empVO == null) ? "" : empVO.getEmpName()%>" /></td>
				</tr>

				<tr>
					<td class="titletext">信箱： </td>
					<td><input type="TEXT" name="empAccount" size="45"
						class="UserName" placeholder="請輸入信箱"
						value="<%=(empVO == null) ? "" : empVO.getEmpAccount()%>" /></td>
				</tr>

				<!-- 請輸入6~10字 -->
<!-- 				<tr> -->
<!-- 					<td>密碼:</td> -->
<!-- 					<td><input type="password" name="empPwd" size="45" -->
<!-- 						class="UserName" placeholder="請輸入密碼" -->
<%-- 						value="<%=(empVO == null) ? "" : empVO.getEmpPwd()%>" /></td> --%>
<!-- 				</tr> -->

				<tr>
					<td class="titletext">電話： </td>
					<td><input type="TEXT" name="empPhone" size="45"
						class="UserName" placeholder="請輸入員工電話"
						value="<%=(empVO == null) ? "" : empVO.getEmpPhone()%>" /></td>
				</tr>

			

				<tr>
					<td class="titletext">身分證字號： </td>
					<td><input type="TEXT" name="empROCId" size="45"
						class="UserName" placeholder="H123000000"
						value="<%=(empVO == null) ? "" : empVO.getEmpROCId()%>" /></td>
				</tr>

				<tr>
					<td class="titletext">員工住址： </td>
					<td><input type="TEXT" name="empAddress" size="45"
						class="UserName" placeholder="桃園市中壢區中大路10號"
						value="<%=(empVO == null) ? "" : empVO.getEmpAddress()%>" /></td>
				</tr>
				
				<tr>
					<td class="titletext">生日： </td>
					<td><input type="date" name="empBirthDate" size="45"
						class="UserName" placeholder="yyyy-mm-dd"
						value="<%=(empVO == null) ? "" : empVO.getEmpBirthDate()%>" /></td>
				</tr>
				
				<tr>
					<td class="titletext">大頭照： </td>
					<td>
						<div class="monkeyb-cust-file" >
	                       <img />
	                       <span>Select File</span>
	                       <input type="file" id="myFile" name="upfile1">  
	                	</div>  
						<p>
							<output id="image_output"></output>
						</p>
						</td>
				</tr>
			
				<tr>
					<%--到職日--%>
					<td><input type="hidden" name="empHireDate" size="45"
						value="<%=(empVO == null) ? formattedDate : empVO.getEmpHireDate()%>" /></td>
				</tr>

				<tr>
					<%--離職日--%>
					<td><input type="hidden" name="empFireDate" size="45"
						value="<%=(empVO == null) ? formattedDate : empVO.getEmpFireDate()%>" /></td>
				</tr>

				<tr>
					<%--狀態碼--%>
					<td><input type="hidden" name="empStatus" size="45"
						value="<%=(empVO == null) ? "0" : empVO.getEmpStatus()%>" /></td>
				</tr>

				<br>



			</table>
			<input type="button" align="center" class="LoginButton" id ="magic" value="神奇小按鈕">
			<input type="hidden" name="action" value="insert"> 
			<input type="submit" value="送出新增" align="center" class="LoginButton">
		</FORM>
		</div>
</body>
</html>
<script>
	if (window.FileReader) { //測試瀏覽器
		document.getElementById("myFile").onchange = function() {

			for (var i = 0, file; file = this.files[i]; i++) { //var file;
				var reader = new FileReader();
				reader.onloadend = (function(file) {
					return function() {
						document.getElementById('image_output').innerHTML += '<img src="'+this.result +'" height="50"/> <br/>';
					}
				})(file); //自己CALL自己
				reader.readAsDataURL(file);
			}
		}
	} else {
		alert("瀏覽器不支援 HTML 5");
	}
</script>