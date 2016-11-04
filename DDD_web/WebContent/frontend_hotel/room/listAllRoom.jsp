<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.room.model.*"%>		
		
		<%@ include file="../head.jsp"%>

		
		
		<div class="col-xs-12 col-sm-10 bb" style="background-color:#FFFAF0;postion:relative;top:220px">
					<ol class="breadcrumb">
						<li>
							房型資料
						</li>
						<li class="active">房型資料維護</li>
					</ol>
					
					
					
					<div class="panel " style="margin:0px;background:#dff0d8;padding:10px;height:50px;border-color:pink"  >							
							<a href="<%=request.getContextPath()%>/frontend_hotel/room/AddRoom.jsp"><img src="<%=request.getContextPath()%>/frontend_hotel/room/images/add.png" width="30"  border="0"></a> 			  
					</div>
					
					
				  <table class="table table-hover" border="1">
				    <thead>
				      <tr style="background-color:#B0C4DE;">
						<th>房間名稱</th>
						<th>總房間數</th>
						<th>房間定價</th>
						<th >娛樂</th>
						<th >餐飲</th>
						<th >舒適睡眠</th>
						<th >房間設施</th>
						<th >貼心服務</th>
						<th>幾人房</th>
						<th>單人床數</th>
						<th>雙人床數</th>
						<th>
						
						
						</th>
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
					<c:forEach var="roomVO" items="${roomSet}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					

						<tr>
							<td><a href=""  id="${roomVO.roomId}"  onclick="show()">${roomVO.roomName}</a></td>
							<td>${roomVO.roomTotalNo}</td>
							<td>${roomVO.roomPrice}</td>
							<td>${roomVO.roomFun}</td> 
							<td>${roomVO.roomMeal}</td> 
							<td>${roomVO.roomSleep}</td> 
							<td>${roomVO.roomFacility}</td>
							<td>${roomVO.roomSweetFacility}</td> 
							<td>${roomVO.roomCapacity}</td> 
							<td>${roomVO.roomOneBed}</td> 
							<td>${roomVO.roomTwoBed}</td>
							<td>
								<button type="button" class="btn btn-primary">修改</button>
								<button type="button" class="btn btn-danger">刪除</button>
							</td>
						</tr>
							</tbody>
					</c:forEach>
				  </table>
					
						<table border="0">    
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
				



<!-- 				<div class="col-xs-12 col-sm-12"> -->
<!-- 					<div class="col-xs-12 col-sm-3"> -->
<!-- 						每頁10筆, 共87筆紀錄 -->
<!-- 					</div> -->

<!-- 					<div class="col-xs-12 col-sm-6 text-center"> -->
<!-- 						<ul class="pagination" style="margin:0px"> -->
<!-- 						  <li><a href="#">&laquo;</a></li> -->
<!-- 						  <li><a href="#">1</a></li> -->
<!-- 						  <li><a href="#">2</a></li> -->
<!-- 						  <li class="active"><a href="#">3</a></li> -->
<!-- 						  <li><a href="#">4</a></li> -->
<!-- 						  <li><a href="#">5</a></li> -->
<!-- 						  <li><a href="#">&raquo;</a></li> -->
<!-- 						</ul> -->
<!-- 					</div>		 -->

<!-- 					<div class="col-xs-12 col-sm-3"> -->

<!-- 					</div> -->
<!-- 				</div> -->





		</div>	
		
		
		
		
		<%@ include file="../footer.jsp" %>