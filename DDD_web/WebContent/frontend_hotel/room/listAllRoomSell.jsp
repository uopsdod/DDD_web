<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.room.model.*"%>		
<%@ page import="com.room.model.RoomVO"%>		
		<%@ include file="../head.jsp"%>

		
		
		<div class="col-xs-12 col-sm-10 bb" style="background-color:#FFFAF0;postion:relative;top:220px">
					<ol class="breadcrumb">
						<li> 
							房型資料
						</li>
						<li class="active">上架管理</li>
					</ol>

				  <table class="table table-hover" border="1" style="font-size:20px;vertical-align: middle;text-align: center;">
				    <thead>
				      <tr style="background-color:#B0C4DE;">
					<th>房間名稱</th>
					<th>總房間數</th>
					<th>剩餘房數</th>
					<th>每日預訂上架房數</th>		
					<th>房間定價</th>
					<th>上架狀態</th>
					<th>開啟定時系統</th>	
					<th>定時上架時間</th>
					<th>下架時間</th>
					<th>單位時間折扣百分比</th>
					<th>折扣單位時間(30min*n)</th>
					<th>是否一價到底</th>
					<th></th>	
				      </tr>
				    </thead>
					
					<tbody>
<%-- 					<%Set<RoomVO> roomSet =(Set<RoomVO>)request.getAttribute("RoomSet");%> --%>
					
					<%
					String hotelId = (String)session.getAttribute("hotelId");
					%>
					
					
					<%
						RoomService roomSvc = new RoomService();
	        			Set<RoomVO> roomSet = roomSvc.getOneHotelAllRoom(hotelId); 
					  
	        			pageContext.setAttribute("roomSet",roomSet);
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
					    rowNumber=roomSet.size();
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
					<c:forEach var="roomVO" items="${roomSet}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					

						<tr>
							<td><a href=""  id="${roomVO.roomId}"  onclick="show()">${roomVO.roomName}</a></td>
							<td>${roomVO.roomTotalNo}</td>
							<td>${roomVO.roomRemainNo}</td>
							<td>${roomVO.roomDefaultNo}</td>
							
							<td>${roomVO.roomPrice}</td>
							<td>${roomVO.roomForSell==true?'上架中':'未上架'}</td>
							<td>${roomVO.roomForSellAuto==true?'開啟':'關閉'}</td>
						
					
						
							
							<td>${(roomVO.roomDiscountStartDate/3600000).intValue()}時 ${((roomVO.roomDiscountStartDate%3600000)/60000).intValue()}分</td>
							<td>${(roomVO.roomDiscountEndDate/3600000).intValue()}時 ${((roomVO.roomDiscountEndDate%3600000)/60000).intValue()}分</td>															
							
							<td>${roomVO.roomDisccountPercent}%</td>
							<td>${roomVO.roomDiscountHr}</td>
							<td>${roomVO.roomOnePrice==true?'是':'否'}</td>
							<td>
								
								
								 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/room/room.do">
									     <input type="submit" class="btn btn-primary" value="上架設定"> 
									     <input type="hidden" name="roomId" value="${roomVO.roomId}">
									     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
									     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
									     <input type="hidden" name="action"	value="setSell">
								 </FORM>
								  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/room/room.do">
									     <input type="submit" class="btn btn-danger" value="下架房型"> 
									     <input type="hidden" name="roomId" value="${roomVO.roomId}">
									     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
									     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
									     <input type="hidden" name="action"	value="DownSell">
								 </FORM>
								  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/room/room.do">
									     <input type="submit" class="btn btn-success" value="取消定時"> 
									     <input type="hidden" name="roomId" value="${roomVO.roomId}">
									     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
									     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
									     <input type="hidden" name="action"	value="RegularCancel">
								 </FORM>						
							</td>
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