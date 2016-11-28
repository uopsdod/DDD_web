<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hotel.model.*"%>
<%@ page import="com.emp.model.*"%>
<%
HotelVO hotelvo =  (HotelVO)request.getAttribute("hotelvo");
pageContext.setAttribute("hotelvo", hotelvo);
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
	height: 1450px;
	background-color: #ccc;
	margin-top: 53px;
}

</style>
</head>
<body>
	<%@ include file="/backend/backendBody.jsp"%>
	<!-- 如果權限沒有人事轉到首頁怕他偷吃步-->

	<%
	if(!authorityList.contains("101")){
		response.sendRedirect(request.getContextPath()+"/backend/emp_index.jsp");
	}
	%>
	
			<div class="col-xs-12 col-sm-10 tablediv text-center" > 
				<table border='0' bordercolor='black' cellspacing="0" cellpadding="5"
				width='800' class="table table-hover " id="tablehotel" >	
				<tr>
					<td>
					<H1>旅館照片</H1>
					<img src='data:image/jpeg;base64,${hotelvo.bs64}'
							width="250" height="180" />
					</td>
					
					<td><H2>旅館登記證</H2><img src='data:image/jpeg;base64,${hotelvo.bs64_2}'
							width="250" height="180" />
					</td>
				</tr>
				<tr>
					<th>廠商會員編號</th>
					<th>廠商種類名稱</th>
				</tr>
				<tr>
					<td>${hotelvo.hotelId}</td>
					<td>${hotelvo.hotelType}</td>
				</tr>
				<tr>
					<th>廠商名稱</th>
					<th>統一編號</th>
				</tr>
				<tr>
					<td>${hotelvo.hotelName}</td>
					<td>${hotelvo.hotelTaxId}</td>
				</tr>
				<tr>
					<th>縣市</th>
					<th>鄉鎮區路名牌號</th>
					
				</tr>
				<tr>
					<td>${hotelvo.hotelCity}</td>
					<td>${hotelvo.hotelCounty}${hotelvo.hotelRoad}</td>						
				</tr>
				<tr>
					<th>負責人姓名</th>
					<th>電話</th>
				</tr>
				<tr>
					<td>${hotelvo.hotelOwner}</td>
					<td>${hotelvo.hotelPhone}</td>
				</tr>	
				<tr>
					<th>帳號</th>
					<th>飯店連結</th>
				</tr>
				<tr>
					<td>${hotelvo.hotelAccount}</td>
					<td>${hotelvo.hotelLink}</td>		
				</tr>
				<tr>
					<th>審核狀態</th>
					<th>黑名單</th>	
				</tr>
				<tr>				
				<c:choose>
		            <c:when test="${hotelvo.hotelStatus==0}">
		                <td>未審核</td>
		            </c:when>
		            <c:when test="${hotelvo.hotelStatus==1}">
		                <td>已審核未通過</td>
		            </c:when>
		            <c:otherwise>
		                <td>通過</td>
		            </c:otherwise>
		        </c:choose>
			    
			    
			    <c:choose>
		            <c:when test="${hotelvo.hotelBlackList==0}">
		                <td>非黑名單</td>
		            </c:when>
		            <c:otherwise>
		                <td>黑名單</td>
		            </c:otherwise>
		        </c:choose>
					
				</tr>
				<tr>
					<th>評價總人數</th>
					<th>評價統計結果</th>
				</tr>
				<tr>
					<td>${hotelvo.hotelRatingTotal}</td>
					<td>${hotelvo.hotelRatingResult}</td>
				</tr>
				<tr>
					<th>信用卡卡號</th>
					<th>信用卡驗證碼</th>
				</tr>
				<tr>
					<td>${hotelvo.hotelCreditCardNo}</td>
					<td>${hotelvo.hotelCreditCheckNo}</td>
				</tr>
				<tr>
					<th>信用卡有效日期</th>				
					<th>廠商簡介</th>
				</tr>
				<tr>
					<td>${hotelvo.hotelCreditDueDate}</td>
					<td>${hotelvo.hotelIntro}</td>							
				</tr>	
				<tr >
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/hotel/hotel.do">
							<input type="submit" value="將此會員加入黑名單" class="LoginButtonBlack"> 
							<input type="hidden" name="hotelId" value="${hotelvo.hotelId}">
							<input type="hidden" name="hotelBlackList" value="${hotelvo.hotelBlackList}">
							<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
							<input type="hidden" name="action" value="update_blacklist">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/hotel/hotel.do">
							<input type="submit" value="取消黑名單" class="LoginButtonBlack1"> 
							<input type="hidden" name="hotelId" value="${hotelvo.hotelId}">
							<input type="hidden" name="hotelBlackList" value="${hotelvo.hotelBlackList}">
							<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
							<input type="hidden" name="action" value="update_blacklist1">
						</FORM>
					</td>
				</tr>
				
			</table>
			<div align="center"><a href="<%=request.getContextPath()%>/backend/hotel/listAllHotel.jsp">回廠商首頁</a></div>
			</div>
</body>
</html>