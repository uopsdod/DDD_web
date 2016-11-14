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

<%
	MemService dao = new MemService();
	MemVO memvo = dao.getOneMem(memVO.getMemId());
	pageContext.setAttribute("memvo", memvo);
	String tagname = (String)request.getAttribute("tab");
	pageContext.setAttribute("tagname", tagname);
	System.out.println(tagname);
	
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend_mem/css/member.css">
    <script src="<%=request.getContextPath()%>/frontend_mem/js/member.js"></script>
    <title>Dua Dee Dou:晚鳥有優惠</title>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/frontend_mem/images/ndex.jpg">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/bs/bootstrap.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/bs/bootstrap-theme.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font-awesome.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/demo.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/component.css" />
     <script src="<%=request.getContextPath()%>/jq/jquery-3.1.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/jq/jquery-ui.js"></script>
  
    <script src="<%=request.getContextPath()%>/bs/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/jq/classie.js"></script>
</head>

<body background="lightgray">
    <header id="top_header">
        <div class="col-md-6 col-md-offset-1 ">
            <a href="<%=request.getContextPath()%>/frontend_mem/index.jsp"><img src="<%=request.getContextPath()%>/frontend_mem/images/4.png" id="LogoImg"></a>
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
            <div class="col-md-6 col-md-offset-1 ">
                <a href="file:///D:/usee/usee/Desktop/%E5%B0%88%E9%A1%8C%E9%A6%96%E9%A0%81%E7%B7%B4%E7%BF%92/index.html"><img src="<%=request.getContextPath()%>/frontend_mem/images/4.png" id="LogoImg">
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
   <!--  ------------------------------------------------------------------- -->
    <section>
       <div class="col-xs-12 col-sm-2">
           
       </div>
       <div class="col-xs-12 col-sm-8 ">
       <a href="#" class="memtext"></a>
           <div>
              <img src="<%=request.getContextPath()%>/frontend_mem/images/back7.jpg" height="370px" width="100%" id="membackimg">
              <c:choose>
					<c:when test="${memvo.bs64==''}">
						 <c:choose>
						 	<c:when test="${memvo.memGender=='f'}">
								<img src="<%=request.getContextPath()%>/frontend_mem/images/girlhead.png" width="180px" height="190px" id="myphoto" >           
						 	</c:when>	
						 	<c:otherwise>
						 		<img src="<%=request.getContextPath()%>/frontend_mem/images/man.png" width="180px" height="190px" id="myphoto" >           
						 	</c:otherwise>  
						 </c:choose> 
					</c:when>		
					<c:otherwise>
           					 <img src='data:image/jpeg;base64,${memvo.bs64}' width="180px" height="190px" id="myphoto" >     
           			</c:otherwise>  
			  </c:choose>                
           </div>
           
           <a href="#set">
           <button id="buttnOnimg">變更照片</button><br> 
           </a>
           <br><br><br><br><br><br><br>
           
           <div role="tabpanel">
               <!-- 標籤面板：標籤區 tagname -->
               <ul class="nav nav-tabs" role="tablist">
                   <li role="presentation" class="<c:if test="${tagname=='#tab1'||tagname==null}">active</c:if>">
                       <a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab" class="tab"><img src="<%=request.getContextPath()%>/frontend_mem/images/curriculum.png">  編輯個人簡介</a>
                   </li>
                   <li role="presentation" class="<c:if test="${tagname=='#tab2'}">active</c:if>">
                       <a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab" class="tab"><img src="<%=request.getContextPath()%>/frontend_mem/images/boy.png"><img src="<%=request.getContextPath()%>/frontend_mem/images/girl.png"> 編輯個人資料</a>
                   </li>
                   <li role="presentation" class="<c:if test="${tagname=='#tab3'}">active</c:if>">
                       <a href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab" class="tab"><img src="<%=request.getContextPath()%>/frontend_mem/images/credit-card.png "> 編輯信用卡資料</a>
                   </li>
                   <li role="presentation" class="<c:if test="${tagname=='#tab4'}">active</c:if>">
                       <a href="#tab4" aria-controls="tab3" role="tab" data-toggle="tab" class="tab"><img src="<%=request.getContextPath()%>/frontend_mem/images/key.png">修改密碼</a>
                   </li>
               </ul>
                <br>
                
               <!-- 標籤面板：內容區 -->
               <div class="tab-content">
               	
                   <div role="tabpanel" class="tab-pane <c:if test="${tagname=='#tab1'||tagname==null}">active</c:if>" id="tab1">
                        <div class="col-xs-12 col-sm-2">
     <!-- ---------------------------------------------------------------------- -->
                    <!-- 個人簡介 -->
                        </div>
                        <div class="col-xs-12 col-sm-8 text-center" width="100%">
<!--                         送簡介 -->
                        <form METHOD="POST" ACTION="<%=request.getContextPath()%>/mem/mem.do" name="form1" enctype="multipart/form-data" >                
                            <section class="content">
                                <h1>設定簡介</h1>
							       <c:if test="${not empty errorMsgs}">
										<font color='red' align="center">									
												<c:forEach var="message" items="${errorMsgs}">
													${message}
												</c:forEach>										
										</font>
									</c:if>
									<br>
                                <span class="input input--hoshi">
                                    <input class="input__field input__field--hoshi" type="text" id="input-4" name="memIntro" value="${memvo.memIntro}" maxlength="50">
                                    <label class="input__label input__label--hoshi input__label--hoshi-color-1" for="input-4">
                                        <span class="input__label-content input__label-content--hoshi">簡介</span>
                                    </label>
                                </span>
                                <span class="input input--hoshi">
                                    <input class="input__field input__field--hoshi" type="text" id="input-5" name="memLiveBudget"  value="${memvo.memLiveBudget}" maxlength="7"/>
                                    <label class="input__label input__label--hoshi input__label--hoshi-color-2" for="input-5">
                                        <span class="input__label-content input__label-content--hoshi">預算</span>
                                    </label>
                                </span>
                                
                            </section>
                            <p>
                              <output id="image_output"></output>
                            </p>
                            <div class="monkeyb-cust-file" >
                                  <img />
                                  <span>Select File</span>
                                  <input type="file" id="myFile" name="upfile">  
                            </div>  
                            <br>
                            <input type="hidden" name="tab" value="#tab1"> 
                            <input type="hidden" name="action" value="updatememIntro"> 
                            <input type="hidden" name="memId" value="${memvo.memId}" >
                            <input type="submit" value="確認修改" id="buttnOnimg1">
						</form>
                        </div>
                       
                        <div class="col-xs-12 col-sm-2">
                            
                        </div>
                   </div>
                   <!-- ---------------------------------------------------------------------- -->
                   <!-- 編輯個人資料 -->
                   <div role="tabpanel" class="tab-pane <c:if test="${tagname=='#tab2'}">active</c:if>" id="tab2">
                       <div class="col-xs-12 col-sm-12 text-center">
                       <form METHOD="POST" ACTION="<%=request.getContextPath()%>/mem/mem.do" name="form1" enctype="multipart/form-data" > 
                           <section class="content">
                                <h1>編輯個人資料</h1>
                                <c:if test="${not empty errorMsgssss}">
										<font color='red' align="center">									
												<c:forEach var="message" items="${errorMsgssss}">
													${message}
												</c:forEach>										
										</font>
									</c:if>
									<br>
                                <span class="input input--hoshi">
                                    <input class="input__field input__field--hoshi" type="text" id="input-4" name="memName" value="${memvo.memName}" maxlength="4">
                                    <label class="input__label input__label--hoshi input__label--hoshi-color-1" for="input-4">
                                        <span class="input__label-content input__label-content--hoshi">姓名</span>
                                    </label>
                                </span>
                                <br>
                                <span class="input input--hoshi">
                                    <input class="input__field input__field--hoshi" type="text" id="input-5"  name="memTwId" value="${memvo.memTwId}" maxlength="10"/>
                                    <label class="input__label input__label--hoshi input__label--hoshi-color-2" for="input-5">
                                        <span class="input__label-content input__label-content--hoshi">身份證字號</span>
                                    </label>
                                </span>
                                <br>
                                <span class="input input--hoshi">
                                    <input class="input__field input__field--hoshi" type="text" id="input-6" name="memBirthDate" value="${memvo.memBirthDate}"/>
                                    <label class="input__label input__label--hoshi input__label--hoshi-color-3" for="input-6">
                                        <span class="input__label-content input__label-content--hoshi">出生年月日</span>
                                    </label>
                                </span>
                                <br>
                                <span class="input input--hoshi">
                                    <input class="input__field input__field--hoshi" type="text" id="input-4" name="memPhone" value="${memvo.memPhone}" maxlength="10">
                                    <label class="input__label input__label--hoshi input__label--hoshi-color-1" for="input-4">
                                        <span class="input__label-content input__label-content--hoshi">電話</span>
                                    </label>
                                </span>

                                <div class="abgne-menu-20140101-1">
                                    <input type="radio" id="male" name="memGender" value="m" ${(memvo.memGender == 'm')? 'checked':''}>
                                    <label for="male">male</label>        
                                    <input type="radio" id="female" name="memGender" value="f" ${(memvo.memGender == 'f')? 'checked':''}>
                                    <label for="female">female</label>
                                </div>
                                <br>
                                <input type="hidden" name="tab" value="#tab2"> 
                                <input type="hidden" name="action" value="memInformation"> 
                            	<input type="hidden" name="memId" value="${memvo.memId}" >                   
                                <input type="submit" value="確認修改" id="buttnOnimg2">
                        </section>
                        </form>
                       </div>
                   </div>
           <!-- ---------------------------------------------------------------------- -->
            <!-- 編輯信用卡資料信用卡 -->
                   <div role="tabpanel" class="tab-pane <c:if test="${tagname=='#tab3'}">active</c:if>" id="tab3">
                   <div class="col-xs-12 col-sm-12 text-center">
                   <form METHOD="POST" ACTION="<%=request.getContextPath()%>/mem/mem.do" name="form1" enctype="multipart/form-data" >                  
                       <section class="content">
                                <h1>設定信用卡資訊</h1>
                                <c:if test="${not empty errorMsgsss}">
										<font color='red' align="center">									
												<c:forEach var="message" items="${errorMsgsss}">
													${message}
												</c:forEach>										
										</font>
									</c:if>
									<br>
                                <span class="input input--hoshi">
                                    <input class="input__field input__field--hoshi" type="text" id="input-4" name ="memCreditCardNo" value="${memvo.memCreditCardNo}" maxlength="16">
                                    <label class="input__label input__label--hoshi input__label--hoshi-color-1" for="input-4">
                                        <span class="input__label-content input__label-content--hoshi">信用卡卡號</span>
                                    </label>
                                </span>
                                <br>
                                <span class="input input--hoshi">
                                    <input class="input__field input__field--hoshi" type="text" id="input-5" name ="memCreditCheckNo"  value="${memvo.memCreditCheckNo}"maxlength="3"/>
                                    <label class="input__label input__label--hoshi input__label--hoshi-color-2" for="input-5">
                                        <span class="input__label-content input__label-content--hoshi">驗證碼</span>
                                    </label>
                                </span>
                                <br>
                                <span class="input input--hoshi">
                                    <input class="input__field input__field--hoshi" type="text" id="input-6" name ="memCreditDueDate"  value="${memvo.memCreditDueDate}"/>
                                    <label class="input__label input__label--hoshi input__label--hoshi-color-3" for="input-5">
                                        <span class="input__label-content input__label-content--hoshi">到期日</span>
                                    </label>
                                </span>
                                <br>
                                <input type="hidden" name="tab" value="#tab3"> 
                                <input type="hidden" name="action" value="update_card"> 
                            	<input type="hidden" name="memId" value="${memvo.memId}" >  
                                <input type="submit" value="確認修改" id="buttnOnimg2">               
                    </section>
                    </form>
                   </div>

                   </div>
            <!-- ---------------------------------------------------------------------- -->
            <!-- 修改密碼 -->
                   <div role="tabpanel" class="tab-pane <c:if test="${tagname=='#tab4'}">active</c:if>" id="tab4">
                   <div class="col-xs-12 col-sm-12 text-center">
                    <form METHOD="POST" ACTION="<%=request.getContextPath()%>/mem/mem.do" name="form1" enctype="multipart/form-data" >                  
                       <section class="content">
                                <h1>修改密碼</h1>
                                <c:if test="${not empty errorMsgss}">
										<font color='red' align="center">									
												<c:forEach var="message" items="${errorMsgss}">
													${message}
												</c:forEach>										
										</font>
									</c:if>
									<br>
                                <span class="input input--hoshi">
                                    <input class="input__field input__field--hoshi" type="password" id="input-4" name="memPsw" >
                                    <label class="input__label input__label--hoshi input__label--hoshi-color-1" for="input-4">
                                        <span class="input__label-content input__label-content--hoshi">新密碼</span>
                                    </label>
                                </span>
                                
                                <span class="input input--hoshi">
                                    <input class="input__field input__field--hoshi" type="password" id="input-5" name="memPsw_1"/>
                                    <label class="input__label input__label--hoshi input__label--hoshi-color-2" for="input-5">
                                        <span class="input__label-content input__label-content--hoshi">確認新密碼</span>
                                    </label>
                                </span>
                                <br>
                                <input type="hidden" name="tab" value="#tab4"> 
                                <input type="hidden" name="action" value="update_psw"> 
                            	<input type="hidden" name="memId" value="${memvo.memId}" > 
                                <input type="submit" value="確認修改" id="buttnOnimg2">               
                    </section>
                    </form>
                   </div>
                   </div>
               </div>
           </div>       
       </div>
       <div class="col-xs-12 col-sm-2">
           
       </div>
    </section>
   <!--  --------------------------------------------------------------------- -->
    <footer id="footerPosition">
        <div id="the_footer" class="col-md-12">
            <div id="list" class="col-md-3 col-md-offset-3">
                <img src="<%=request.getContextPath()%>/frontend_mem/images/phone-call.png">
                <h3>連絡我們</h3>
                <br>訂購諮詢 412-8101
                <br>(手機加 02)
                <br>手機易遊通 55678
                <br>(中華電信、遠傳、台灣大哥大門號適用)
            </div>
            <div id="listHouse" class="col-md-2 ">
                <img src="<%=request.getContextPath()%>/frontend_mem/images/house.png">
                <h3>住宿類型</h3>
                <br><a href="#">度假村</a>
                <br><a href="#">露營地</a>
                <br><a href="#">青年旅館</a>
                <br><a href="#">家庭旅館</a>
                <br><a href="#">膠囊旅館</a>
                <br><a href="#">連鎖飯店</a>
                <br><a href="#">溫泉住宿</a>
                <br><a href="#">獨棟別墅 </a>        
            </div>
            <div id="list" class="col-md-4 ">
                <img src="<%=request.getContextPath()%>/frontend_mem/images/smartphone.png">
                <h3>手機下載APP</h3>
                <br>
                <button class="app"><img src="<%=request.getContextPath()%>/frontend_mem/images/apple.jpg"></button>
                <br>
                <br>
                <button class="app"><img src="<%=request.getContextPath()%>/frontend_mem/images/google.jpg"></button>
            </div>
        </div>
        <div id="the_footer1" class="col-md-12 text-center">
            <h4 align="center">加入我們</h4>
            <img src="<%=request.getContextPath()%>/frontend_mem/images/facebook.png">
            <img src="<%=request.getContextPath()%>/frontend_mem/images/twitter.png">
            <img src="<%=request.getContextPath()%>/frontend_mem/images/youtube.png">
            <img src="<%=request.getContextPath()%>/frontend_mem/images/instagram.png">
            <img src="<%=request.getContextPath()%>/frontend_mem/images/google-plus.png">
            <h3 align="center">Dua Dee Dou</h3>
            <h4 align="center">版權所有©2005 – 2016, Dua Dee Dou Company.保留所有權利
    					DuaDeeDou.com是線上旅遊業及相關服務的全球領導品牌。</h4>
        </div>
    </footer>
</body>

</html>
