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


<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend_mem/css/0_css.css">
    <script src="<%=request.getContextPath()%>/frontend_mem/js/0_new.js"></script>
    <title>Dua Dee Dou:晚鳥有優惠</title>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/frontend_mem/images/index.jpg">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/bs/bootstrap.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/bs/bootstrap-theme.min.css">
   
    <script src="<%=request.getContextPath()%>/jq/jquery-3.1.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/jq/jquery-ui.js"></script>
    <script src="<%=request.getContextPath()%>/jq/jquery-cycle-all.js"></script>
    <script src="<%=request.getContextPath()%>/bs/bootstrap.min.js"></script>
</head>

<body>
    <header id="top_header">
        <div class="col-md-5 col-md-offset-1 ">
            <a href="<%=request.getContextPath()%>/frontend_mem/index.jsp"><img src="<%=request.getContextPath()%>/frontend_mem/images/4.png" id="LogoImg"></a>
        </div>
        <div class="col-md-5 col-md-offset-1 ">
            <div class="dropdown" style='display: inline-block;'>
                <button class="btn text-muted" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
                    訂單專區
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/ord/listAllOrdByMemId.jsp">我的預訂</a></li>
                   
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
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_hotel/hotel/addhotel.jsp">成為夥伴</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_hotel/hotel/loginhotel.jsp">夥伴登入</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">問題回報</a></li>
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
            <div class="dropdown" style='display: inline-block;'>
                <c:choose>
            <c:when test="${memVO.memName==null}">
                <button class="btn text-muted" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
                    會員中心
                    <span class="caret"></span>
                </button>      		                
		     </c:when>		  
		     <c:otherwise>
		        <button class="btn text-muted" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
                   ${memVO.memName}您好!
                    <span class="caret"></span>
                </button>  
		      </c:otherwise>
		    </c:choose> 
		    <c:choose>
		     	<c:when test="${memVO.memName==null}">
                	<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
	                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/mem/registerOfmember.jsp">註冊</a></li>
                    	<li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/mem/loginOfmember.jsp">登入</a></li>
	                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/mem/member.jsp">會員中心</a></li>
	                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/wish/wishList.jsp">查看願望清單</a></li>
                	</ul>
           		</c:when>
           		<c:otherwise>
           			<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">	                
	                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/mem/mem.do">登出</a></li>
	                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/mem/member.jsp">會員中心</a></li>
	                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/wish/wishList.jsp">查看願望清單</a></li>
                	</ul>
           		</c:otherwise>
           </c:choose>
            </div>
            
        </div>
        <!-- 浮動列 -->
        <div id="top_header1">
            <div class="col-md-5 col-md-offset-1 ">
            <a href="<%=request.getContextPath()%>/frontend_mem/index.jsp"><img src="<%=request.getContextPath()%>/frontend_mem/images/4.png" id="LogoImg"></a>
                
            </div>
            <div class="col-md-5 col-md-offset-1 ">
                <div class="dropdown" style='display: inline-block;'>
                    <button class="btn text-muted" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
                        訂單專區
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/ord/listAllOrdByMemId.jsp">我的預訂</a></li>
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
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_hotel/hotel/addhotel.jsp">成為夥伴</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_hotel/hotel/loginhotel.jsp">夥伴登入</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">問題回報</a></li>
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
                <div class="dropdown" style='display: inline-block;'>
                    <c:choose>
            <c:when test="${memVO.memName==null}">
                <button class="btn text-muted" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
                    會員中心
                    <span class="caret"></span>
                </button>      		                
		     </c:when>		  
		     <c:otherwise>
		        <button class="btn text-muted" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
                   ${memVO.memName}您好!
                    <span class="caret"></span>
                </button>  
		      </c:otherwise>
		    </c:choose> 
		    <c:choose>
		     	<c:when test="${memVO.memName==null}">
                	<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
	                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/mem/registerOfmember.jsp">註冊</a></li>
                    	<li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/mem/loginOfmember.jsp">登入</a></li>
	                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/mem/member.jsp">會員中心</a></li>
	                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/wish/wishList.jsp">查看願望清單</a></li>
                	</ul>
           		</c:when>
           		<c:otherwise>
           			<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">	                
	                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/mem/mem.do">登出</a></li>
	                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/mem/member.jsp">會員中心</a></li>
	                    <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/frontend_mem/wish/wishList.jsp">查看願望清單</a></li>
                	</ul>
           		</c:otherwise>
           </c:choose>
                </div>
            </div>
        </div>
    </header>
   

