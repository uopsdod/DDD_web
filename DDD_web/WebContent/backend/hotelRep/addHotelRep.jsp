<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.hotelrep.model.*"%>


<html>
<head>
		<!-- 請輸入標題 -->
		<title>廠商檢舉單新增 - addHotelRep.jsp</title>

		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
	
		
		<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/css/bootstrap.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/frontend_hotel/css/sweetalert.css">
		
		<!-- 自訂CSS -->
		<link rel="stylesheet" href="">		
		
		<script src="<%=request.getContextPath()%>/backend/js/jquery.js"></script>
		<script src="<%=request.getContextPath()%>/backend/js/bootstrap.js"></script>
		<script src="<%=request.getContextPath()%>/frontend_hotel/js/sweetalert.min.js"></script>
		<!-- 自訂JavaScript --> 	
		<script src=""></script>

</head>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>廠商檢舉單新增  - addHotelRep.jsp</h3>
		</td>
		<td>
		   <a href="<%=request.getContextPath()%>/backend/hotelRep/selectPage.jsp"><img src="<%=request.getContextPath()%>/backend/hotelRep/images/back1.gif" width="100" height="32" border="0">回首頁</a>
	    </td>
	</tr>
</table>

<h3>資料廠商檢舉單:</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/hotelRep/hotelRep.do" name="form1">
<table border="0">

	<jsp:useBean id="hotelSvc" scope="page" class="com.hotel.model.HotelService" />
	<tr>
		<td>廠商名稱:<font color=red><b>*</b></font></td>
		<td><select size="1" name="hotelRepHotelId">
			<c:forEach var="hotelVO" items="${hotelSvc.all}">
				<option value="${hotelVO.hotelId}" ${(hotelRepVO.hotelRepHotelVO.hotelId==hotelVO.hotelId)? 'selected':'' } >${hotelVO.hotelName}</option>
			</c:forEach>
		</select></td>
	</tr>
	
	<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
	<tr>
		<td>一般會員名稱:<font color=red><b>*</b></font></td>
		<td><select size="1" name="hotelRepMemId">
			<c:forEach var="memVO" items="${memSvc.all}">
				<option value="${memVO.memId}" ${(hotelRepVO.hotelRepMemVO.memId==memVO.memId)? 'selected':'' } >${memVO.memName}</option>
			</c:forEach>
		</select></td>
	</tr>	
	
	<jsp:useBean id="ordSvc" scope="page" class="com.ord.model.OrdService" />
	<tr>
		<td>訂單編號:<font color=red><b>*</b></font></td>
		<td><select size="1" name="hotelRepOrdId">
			<c:forEach var="ordVO" items="${ordSvc.all}">
				<option value="${ordVO.ordId}" ${(hotelRepVO.hotelRepOrdVO.ordId==ordVO.ordId)? 'selected':'' } >${ordVO.ordId}</option>
			</c:forEach>
		</select></td>
	</tr>	
	
	
	<jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmpService" />
	<tr>
		<td>員工姓名:<font color=red></font></td>
		<td><select size="1" name="hotelRepEmpId">
				<option value="">請選擇</option>
			<c:forEach var="empVO" items="${empSvc.all}">
				<option value="${empVO.empId}" ${(hotelRepVO.hotelRepEmpVO.empId==empVO.empId)? 'selected':'' } >${empVO.empName}</option>
			</c:forEach>
		</select></td>
	</tr>	
		
	<tr>
		<td>檢舉內容:</td>
	
		<td>						
			<textarea name="hotelRepContent">${hotelRepVO.hotelRepContent}</textarea>
		</td>
	</tr>
	
	<tr>
		<td>檢舉單處理狀態名稱:</td>
		<td>				
			<select name="hotelRepStatus">
 					<option value="0" ${('0' == hotelRepVO.hotelRepStatus)? 'selected':'' } >未審核</option>
  					<option value="1" ${('1' == hotelRepVO.hotelRepStatus)? 'selected':'' } >已審核未通過</option>
  					<option value="2" ${('2' == hotelRepVO.hotelRepStatus)? 'selected':'' } >已審核已通過</option>
			</select>
		</td>
	</tr>	
		
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增" class="btn-submit"></FORM>

<button type="button" id="magicButton" >神奇小按鈕</button>

</body>

</html>

<script>

	var magicData = {
			hotelRepHotelId: '10003',
			hotelRepMemId: '10000004',
			hotelRepOrdId: '2016111012',
			hotelRepEmpId: '',
			hotelRepContent: '沒有問題',
			hotelRepStatus: '0',
	};
	
	var magicButton = document.getElementById("magicButton"); 

	magicButton.addEventListener('click',function(e){
// 		var inputList = document.querySelectorAll('input');

// 		for(var i=0 ; i<inputList.length ; i++){
// 			var input = inputList[i];
// 			if(magicData.hasOwnProperty(input.name)){
// 				input.value = magicData[input.name];
// 			}		
// 		}

		$("select[name=hotelRepHotelId]").val('10003');
			
		$("select[name=hotelRepMemId]").val('10000004');
			
		$("select[name=hotelRepOrdId]").val('2016111012');
			
				
		$("textarea[name=hotelRepContent]").val('沒有問題');
				
		$("select[name=hotelRepStatus]").val('0');
	
	});
	
	$('.btn-submit').on('click',function(e){
	    e.preventDefault();
	    var form = $(this).parents('form');
	    swal({
	        title: "Are you sure?",
	        text: "You will not be able to recover this imaginary file!",
	        type: "warning",
	        showCancelButton: true,
	        confirmButtonColor: "#DD6B55",
	        confirmButtonText: "Yes, delete it!",
	        closeOnConfirm: false
	    }, function(isConfirm){
	        if (isConfirm) form.submit();
	    });
	})
	

</script>
