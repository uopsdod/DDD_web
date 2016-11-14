<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>
<%
	MemVO memVO = (MemVO)request.getAttribute("memVO"); 
%>
<%
session.getAttribute("account_mem");

%>
<c:choose>
<c:when test="${account_mem!=null}">
	<%response.sendRedirect(request.getContextPath()+"/frontend_mem/index.jsp"); %>         
</c:when>		  
</c:choose> 
<!DOCTYPE html>
<html style="height: 100%;">

<head>
   <meta charset="utf-8">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend_mem/css/registerOfmember.css">
    <script src="<%=request.getContextPath()%>/frontend_mem/js/registerOfmember.js"></script>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Dua Dee Dou:晚鳥有優惠</title>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/frontend_mem/images/index.jpg">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/bs/bootstrap.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/bs/bootstrap-theme.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/bs/bootstap_min_teacher.css">
     <script src="<%=request.getContextPath()%>/jq/jquery-3.1.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/jq/jquery-ui.js"></script>
    <script src="<%=request.getContextPath()%>/jq/jquery-cycle-all.js"></script>
    <script src="<%=request.getContextPath()%>/bs/bootstrap.min.js"></script>
</head>

<body style="height: 100%;">
    <section id="fade" class="col-xs-12 col-md-6 hidden-xs">
        <img src="<%=request.getContextPath()%>/frontend_mem/images/bg3.jpg" id="bgImg">
        <img src="<%=request.getContextPath()%>/frontend_mem/images/back6.jpg" id="bgImg">
        <img src="<%=request.getContextPath()%>/frontend_mem/images/back7.jpg" id="bgImg">
    </section>
    <a href="<%=request.getContextPath()%>/frontend_mem/index.jsp"><img src="<%=request.getContextPath()%>/frontend_mem/images/4.png" id="LogoImg"></a>
    <section id="section_right" class="col-xs-12 col-md-6 ">
        <div id="Navigationbar">
            <div class="dropdown" style='display: inline-block;'>
                <button class="btn text-muted" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
                    訂單專區
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="indexPage.html">我的訂單</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">QRCODE</a></li>
                </ul>
            </div>
            <div class="dropdown" style='display: inline-block;'>
                <button class="btn text-muted" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
                    共住
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">地圖模式</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">列表模式</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">對話記錄</a></li>
                </ul>
            </div>
            <div class="dropdown" style='display: inline-block;'>
                <button class="btn text-muted" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
                    合作夥伴
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">成為夥伴</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">夥伴登入</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">問題回報</a></li>
                </ul>
            </div>
            <div class="dropdown" style='display: inline-block;'>
                <button class="btn text-muted" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
                    會員中心
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/mem/registerOfmember.jsp">註冊</a></li>
                    	<li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/mem/loginOfmember.jsp">登入</a></li>
	                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/mem/member.jsp">會員中心</a></li>
	                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/wish/wishList.jsp">查看願望清單</a></li>
                </ul>
            </div>
            <div class="dropdown" style='display: inline-block;'>
                <button class="btn text-muted" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
                    幫助
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="https://www.agoda.com/zh-tw/">FAQ</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">連絡我們</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">訂房需知</a></li>
                </ul>
            </div>
            <hr>
        </div>
        <div>
        <%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font color='red'>
					<c:forEach var="message" items="${errorMsgs}">
						${message}
					</c:forEach>	
			</font>	
		</c:if>     
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/mem.do">  
	            <h2>註冊帳戶</h2>
	            <h4>加入我們讓您的旅程住得開心玩得開心</h4>
	            <div align="center">            
	            <input type="text" name="memName" id ="register" placeholder="UserName" value="<%=(memVO == null) ? "" : memVO.getMemName()%>"><br>
	            <input type="text" name="memAccount" id ="register" placeholder="Account" value="<%=(memVO == null) ? "" : memVO.getMemAccount()%>"><br>
	            <input type="password" name="memPsw" id ="register" placeholder="PassWord" value="<%=(memVO == null) ? "" : memVO.getMemPsw()%>"><br>
	            <input type="text" name="memTwId" id ="register" placeholder="Identifier" value="<%=(memVO == null) ? "" : memVO.getMemTwId()%>"><br>
	            <input type="text" name="memBirthDate" id ="register" placeholder="Birthday" value="<%=(memVO == null) ? "" : memVO.getMemBirthDate()%>"><br>
	            <input type="text" name="memPhone" id ="register" placeholder="Phone" value="<%=(memVO == null) ? "" : memVO.getMemPhone()%>"><br>
	                <div class="abgne-menu-20140101-1">
	                    <input type="radio" id="male" name="memGender"  value="M" checked>
	                    <label for="male">male</label>        
	                    <input type="radio" id="female" name="memGender" value="F">
	                    <label for="female">female</label>
	                </div>
	            </div>
<!-- 	            黑名單 -->
				<input type="hidden" name="memBlackList" value="0"> 
				<input type="hidden" name="action" value="insert"> 
	            <div align="center">
	                <button id="LoginButton">註冊</button>
	            </div>
			</FORM>
            <div id="forgotPaw" align="center">
                <a href="#">註冊帳號即表示我同意Dua Dee Dou的服務條款</a>
            </div>

            <div id="Copyright">Copyright © Dua Dee Dou 2016</div>
            <div class="footerOfImg"> 
                <img src="<%=request.getContextPath()%>/frontend_mem/images/facebook 2.png" class="iconOfFooter" >
                <img src="<%=request.getContextPath()%>/frontend_mem/images/instagram 2.png" class="iconOfFooter">
                <img src="<%=request.getContextPath()%>/frontend_mem/images/twitter2.png" class="iconOfFooter">
                <img src="<%=request.getContextPath()%>/frontend_mem/images/google-plus 2.png" class="iconOfFooter">
            </div>
        </div>
    </section>
</body>

</html>
