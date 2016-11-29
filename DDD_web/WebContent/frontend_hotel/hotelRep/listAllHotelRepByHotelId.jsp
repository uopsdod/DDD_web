<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.hotelrep.model.*" %>	
		<%@ include file="../head.jsp"%>

		
		
		<div class="col-xs-12 col-sm-10 bb" style="background-color:#FFFAF0;postion:relative;top:220px">
					<ol class="breadcrumb">
						<li>
							檢舉
						</li>
						<li class="active">廠商檢舉單查詢</li>
					</ol>

				  <table class="table table-hover" border="1">
				    <thead>
				      <tr style="background-color:#B0C4DE;">
						<th>廠商檢舉單編號</th>
						<th>(原告)廠商名稱</th>
						<th>(被告)一般會員姓名</th>
						<th>訂單編號</th>
						<th>處理的員工姓名</th>
						<th>檢舉內容</th>
						<th>處理狀態</th>
						<th>檢舉時間</th>
						<th>處理時間</th>
				      </tr>
				    </thead>
					
					<tbody>					
					<%
						String hotelId = (String)session.getAttribute("hotelId");
					%>
					
					<%
					    HotelRepService hotelRepSvc = new HotelRepService();
					    List<HotelRepVO> AllList = hotelRepSvc.getAll();
					    List<HotelRepVO> list = new ArrayList<HotelRepVO>();

					    /* 篩選出該廠商的 */
					    for(HotelRepVO aHotelRepVO : AllList){
					    	if( hotelId.equals( aHotelRepVO.getHotelRepHotelVO().getHotelId() )  ){
					    		list.add(aHotelRepVO);
					    	}
					    }
					 					    
					    pageContext.setAttribute("list",list);
					       
					%>
					
		<!-- 					*****************************換頁*************************************** -->			
					
					<%  int rowsPerPage = 5; 
					    int rowNumber=0;      
					    int pageNumber=0;    
					    int whichPage=1;     
					    int pageIndexArray[]=null;
					    int pageIndex=0; 
					%>
					
					<%  
					    rowNumber = list.size();
					    if (rowNumber%rowsPerPage !=0)
					     pageNumber=rowNumber/rowsPerPage +1;
					    else pageNumber=rowNumber/rowsPerPage;    
					
					    pageIndexArray=new int[pageNumber]; 
					    for (int i=1 ; i<=pageIndexArray.length ; i++)
					    pageIndexArray[i-1]=i*rowsPerPage-rowsPerPage;
					%>
					
					<%  try {
					      whichPage = Integer.parseInt(request.getParameter("whichPage"));
					      pageIndex=pageIndexArray[whichPage-1];
					    } catch (NumberFormatException e) { 
					       whichPage=1;
					       pageIndex=0;
					    } catch (ArrayIndexOutOfBoundsException e) {
					         if (pageNumber>0){
					              whichPage=pageNumber;
					              pageIndex=pageIndexArray[pageNumber-1];
					         }
					    } 
					%>
	<!-- 					*****************************換頁*************************************** -->				
					<c:forEach var="hotelRepVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					

						<tr>
							<td>${hotelRepVO.hotelRepId}</td>
							<td>${hotelRepVO.hotelRepHotelVO.hotelName}</td>
							<td>${hotelRepVO.hotelRepMemVO.memName}</td>
							<td>${hotelRepVO.hotelRepOrdVO.ordId}</td>
							<td><c:out value="${hotelRepVO.hotelRepEmpVO.empName}" default="尚無員工處理"/></td>
							
							<td><c:out value="${hotelRepVO.hotelRepContent}" default="無檢舉內容"/></td>
							
							<td>${hotelRepStatusTrans.get(hotelRepVO.hotelRepStatus)}</td>
				
							
							<td>${hotelRepVO.hotelRepDate}</td>
							
							<td><c:out value="${hotelRepVO.hotelRepReviewDate}" default="尚未處理"/></td>
						</tr>
							</tbody>
					</c:forEach>
				  </table>
<!-- 					*****************************換頁*************************************** -->
						<table border="0" > 
							 <tr>
							  <%if (rowsPerPage<rowNumber) {%>
							    <%if(pageIndex>=rowsPerPage){%>
							        <td><A href="<%=request.getRequestURI()%>?whichPage=1">至第一頁</A>&nbsp;</td>
							        <td><A href="<%=request.getRequestURI()%>?whichPage=<%=whichPage-1%>">上一頁</A>&nbsp;</td>
							    <%}%>
							  
							    <%if(pageIndex<pageIndexArray[pageNumber-1]){%>
							        <td><A href="<%=request.getRequestURI()%>?whichPage=<%=whichPage+1%>">下一頁</A>&nbsp;</td>
							        <td><A href="<%=request.getRequestURI()%>?whichPage=<%=pageNumber%>">至最後一頁</A>&nbsp;</td>
							    <%}%>
							  <%}%>  
							
							   
									<%if ( pageNumber > 1) {%>      
									   <FORM METHOD="post" ACTION="<%=request.getRequestURI()%>">   
									        <td>
									           <select size="1" name="whichPage">
									        <%for (int i=1; i<=pageNumber; i++){%>
									           <option value="<%=i%>">跳至第<%=i%>頁
									        <%}%> 
									           </select>
									           <input type="submit" value="確定" >  
									        </td>
									   </FORM>
									<%}%>
							 </tr>
							</table>  
						
						<%if (pageNumber>0){%>
						<b><font color= red>第<%=whichPage%>/<%=pageNumber%>頁</font></b>
						<%}%>
						<b>●符 合 查 詢 條 件 如 下 所 示: 共<font color=red><%=rowNumber%></font>筆</b>
				
<!-- 					*****************************換頁*************************************** -->




		</div>	
		
		
		
		
		<%@ include file="../footer.jsp" %>