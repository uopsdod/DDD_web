<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ord.model.*, java.text.SimpleDateFormat" %>	
		<%@ include file="../head.jsp"%>

<style type="text/css">
		
	#hotelRepWindow textarea {
    	width: 700px;
    	height: 300px;
    	resize: both;
    	overflow: auto;
	}
	
	#hotelRepWindow th {
		font-family: Tahoma, Verdana, 微軟正黑體;
		font-size: 18px;
	}	
	
	#hotelRepWindow td {
		font-family: Tahoma, Verdana, 微軟正黑體;
		font-size: 18px;
	}
	

</style>	
		
		
		<div class="col-xs-12 col-sm-10 bb" style="background-color:#FFFAF0;postion:relative;top:220px">
					<ol class="breadcrumb">
						<li>
							訂單
						</li>
						<li class="active">所有訂單查詢</li>
					</ol>

				  <table class="table table-hover" border="1">
				    <thead>
				      <tr style="background-color:#B0C4DE;">
						<th>訂單編號</th>
						<th>房型名稱</th>
						<th>一般會員名稱</th>
						<th>訂單金額</th>
						<th>入住日期</th>
						<th>下訂日期</th>
						<th>訂單狀態名稱</th>
						<th>操作</th>
				      </tr>
				    </thead>
					
					<tbody>					
					<%
						String hotelId = (String)session.getAttribute("hotelId");
					%>
					
					
					<%
						OrdService ordSvc = new OrdService();
						List<OrdVO> list = ordSvc.getAllByOrdHotelId(hotelId);
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
					<c:forEach var="ordVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					

						<tr>
							<td>${ordVO.ordId}</td>
							<td>${ordVO.ordRoomVO.roomName}</td>
							<td>${ordVO.ordMemVO.memName}</td>

							<td>${ordVO.ordPrice}</td>

							<% OrdVO ordVO = ((OrdVO)(pageContext.getAttribute("ordVO"))); %>
							<% if(ordVO.getOrdLiveDate() != null  ) {%>
								<td><%=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(ordVO.getOrdLiveDate())%></td>
							<% } else { %>
								<td>尚未入住</td>	
							<% } %>


							<td><%=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(ordVO.getOrdDate())%></td>		
							<td>${ordStatusTrans.get(ordVO.ordStatus)}</td>
							
							<c:choose>
								<c:when test="${ordVO.ordHotelReps.isEmpty()}">	
									<td>
							 			<button type="button" class="btn btn-danger btn-lg" data-toggle="modal" data-target="#${ordVO.ordId}"><span class="glyphicon glyphicon-thumbs-down">檢舉</span></button>
									</td>
								</c:when>		  
			       				<c:otherwise>
			       					<td>
							 			<button type="button" class="btn btn-warning btn-lg"><span class="glyphicon glyphicon-hourglass">已檢舉</span></button>
									</td>
								</c:otherwise>
							 </c:choose>  
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
		
		
		  <!-- 彈出視窗 -->							
<c:forEach var="ordVO" items="${list}">
		
		  <!-- Modal -->
	<div id="hotelRepWindow">  
		  <div class="modal fade" id="${ordVO.ordId}" role="dialog">
		    <div class="modal-dialog modal-lg">
		      <div class="modal-content">
		        <div class="modal-header">
		          <h3 class="modal-title">建立廠商檢舉單</h3>
		        </div> <!-- modal-header -->
		        
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/hotelRep/hotelRep.do" name="form1">      
			        <div class="modal-body">
	
						<!-- (開始)檢舉單內容 -->
						<table border="0">
							<tr>
								<th>廠商名稱:</th>
								<td>
									${ordVO.ordHotelVO.hotelName}
								</td>
							</tr>
							
						
							<tr>
								<th>旅客名稱:</th>
								<td>
									${ordVO.ordMemVO.memName}
								</td>
							</tr>	
							
						
							<tr>
								<th>訂單編號:</th>
								<td>
									${ordVO.ordId}
								</td>
							</tr>	
							
							<tr>
								<th>檢舉內容:</th>
							
								<td>						
									<textarea name="hotelRepContent"></textarea>
								</td>
							</tr>	
						</table>
						
						<!-- (結束)檢舉單內容 -->
	
			        </div> <!-- modal-body -->
			        <div class="modal-footer">
			        
			          <input type="hidden" name="hotelRepHotelId" value="${ordVO.ordHotelVO.hotelId}">	
			          <input type="hidden" name="hotelRepMemId" value="${ordVO.ordMemVO.memId}">	
			          <input type="hidden" name="hotelRepOrdId" value="${ordVO.ordId}">
			          <input type="hidden" name="hotelRepStatus" value="0">		        
			          <input type="hidden" name="action" value="insert">
	
	
			          <button type="button" class="btn btn-warning" data-dismiss="modal">
					  	<span class="glyphicon glyphicon-remove">取消</span>
					  </button>
	
			        
			          <button type="submit" class="btn btn-primary">
					  	<span class="glyphicon glyphicon-ok">提交</span>
					  </button>
					  
			        </div> <!-- modal-footer -->
		       </FORM>
		       
		      </div>
		    </div>
		  </div>					
	</div>
</c:forEach>	
	
		
		<%@ include file="../footer.jsp" %>