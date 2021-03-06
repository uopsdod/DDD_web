<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>

<%
session.getAttribute("account_mem");
MemVO memVO =(MemVO)session.getAttribute("memVO");
session.setAttribute("memVO", memVO);
%>

<!-- 判斷是否登入過了 -->
<c:choose>
<c:when test="${account_mem!=null}">
	<%response.sendRedirect(request.getContextPath()+"/frontend_mem/index.jsp"); %>         
</c:when>		  
</c:choose> 

<!DOCTYPE html>
<html style="height: 100%;">

<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend_mem/css/loginOfmember.css">
    <script src="<%=request.getContextPath()%>/frontend_mem/js/loginOfmember.js"></script>
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
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/ord/listAllOrdByMemId.jsp">我的預訂</a></li>
                	<li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/ord/listAllOrdByMemId_history.jsp">歷史訂單</a></li>
                </ul>
            </div>
            <div class="dropdown" style='display: inline-block;'>
                <button class="btn text-muted" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
                    共住
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/chat/MapAndChat.jsp" target="_blank">聊天地圖</a></li>
                </ul>
            </div>
            <div class="dropdown" style='display: inline-block;'>
                <button class="btn text-muted" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
                    合作夥伴
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_hotel/hotel/addhotel.jsp" target="_blank">成為夥伴</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_hotel/hotel/loginhotel.jsp" target="_blank">夥伴登入</a></li>
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
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/bookingProcess/bookingProcess.jsp">訂房需知</a></li>
                </ul>
            </div>
            <hr>
        </div>
        <div>
            <h2>會員登入</h2>
            <h4>Welcome Back</h4>
            <div class="col-sm-12 text-center">
			<c:if test="${not empty errorMsgs}">
				<font color='red' style="font-family: Tahoma, Verdana, 微軟正黑體;">									
						<c:forEach var="message" items="${errorMsgs}">
								${message}
						</c:forEach>										
				</font>
			</c:if> 
			</div>           
            <form action="<%=request.getContextPath()%>/mem/mem.do" method="post">
            <div align="center">
                <input type="text" name="UserName" value="" placeholder="UserName" id="UserName">
                <input type="password" name="Password" value=""  placeholder="Password" id="Password">
            </div>
            <br>
            <div id="forgotPaw"><a href="#">Forgot Password?</a>
            <input type="hidden" name="action" value="login"><input type="button" id ="magic"> 
                <button id="LoginButton">Login</button>
            </div>
            </form>
            <div id="footerOfImg" class=""> 
                <img src="<%=request.getContextPath()%>/frontend_mem/images/facebook 2.png" class="iconOfFooter" >
                <img src="<%=request.getContextPath()%>/frontend_mem/images/instagram 2.png" class="iconOfFooter">
                <img src="<%=request.getContextPath()%>/frontend_mem/images/twitter2.png" class="iconOfFooter">
                <img src="<%=request.getContextPath()%>/frontend_mem/images/google-plus 2.png" class="iconOfFooter">
            </div>
            <div id="Copyright">Copyright © Dua Dee Dou 2016</div>
        </div>
    </section>
    <script>
	    function load() {
	    	document.getElementById("magic").onclick=magic;
	    	
	    }
	    window.onload = load;
		function magic(){
			$("[name~='UserName']").val("ck001583219@gmail.com");	
			$("[name~='Password']").val("a123456");	
			
		}
	</script>
</body>
</html>
