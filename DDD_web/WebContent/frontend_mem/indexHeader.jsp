<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend_mem/css/0_css.css">
    <script src="<%=request.getContextPath()%>/frontend_mem/js/0_new.js"></script>
    <title>Dua Dee Dou:晚鳥有優惠</title>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/images/index.jpg">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/bs/bootstrap.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/bs/bootstrap-theme.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/jq/jquery-ui-date.js" />
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
    <script src="<%=request.getContextPath()%>/jq/jquery-3.1.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/jq/jquery-ui.js"></script>
    <script src="<%=request.getContextPath()%>/jq/jquery-cycle-all.js"></script>
    <script src="<%=request.getContextPath()%>/bs/bootstrap.min.js"></script>
</head>

<body>
    <header id="top_header">
        <div class="col-md-6 col-md-offset-1 ">
            <a href="index.html"><img src="images/4.png" id="LogoImg"></a>
        </div>
        <div class="col-md-4 col-md-offset-1 ">
            <div class="dropdown" style='display: inline-block;'>
                <button class="btn text-muted" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
                    訂單專區
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">我的訂單</a></li>
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
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="registerOfmember.html">註冊</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="loginOfmember.html">登入</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">忘記密碼</a></li>
                    <li role="presentation"><a role="menuitem" tabindex="-1" href="#">查看願望清單</a></li>
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
            
        </div>
        <!-- 浮動列 -->
        <div id="top_header1">
            <div class="col-md-6 col-md-offset-1 ">
                <a href="file:///D:/usee/usee/Desktop/%E5%B0%88%E9%A1%8C%E9%A6%96%E9%A0%81%E7%B7%B4%E7%BF%92/index.html"><img src="images/4.png" id="LogoImg">
                </a>
            </div>
            <div class="col-md-4 col-md-offset-1 ">
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
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">註冊</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">登入</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">忘記密碼</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">查看願望清單</a></li>
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
            </div>
        </div>
    </header>
   
</body>

</html>
