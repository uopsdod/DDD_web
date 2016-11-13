<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	


	<%@ include file="../indexHeader.jsp"%>
	
	
	
</head>	
	 <script src="<%=request.getContextPath()%>/frontend_mem/map/js/map.js"></script>
	
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend_mem/map/css/map.css"> 
  <!--價格條-->	
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <!--googleMap.api-->
   <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAlipz45QaidejTobGNnAZTXw3-sx-Bay0&callback=myMap"></script>
</head>

   <!--  ------------------------------------------------------------------- -->
    <section>
        <div>
            <div>
                <div>
                    <div>
                        <div>
                            <div>
                                <div>
									<div class="container-fluid">
										<div class="row">
											<div class="col-xs-12 col-sm-7 semi">
											<div id="myDIV"
												style="
												position:absolute;					
												height: 1000px;
												overflow: scroll;
												overflow-x: hidden;">	
												<div class="row">
												<!----------------------搜尋bar----------------------------------->
													<div class="col-xs-12 col-sm-12 searchBar">
														<div class="col-xs-12 col-sm-6" id="searchCity" >
															 <p>
															  <label for="amount">城市:</label>
															 </p> 
														</div>
														<div class="col-xs-12 col-sm-6" id="searchArea" >
															 <p>
															  <label for="amount">區域:</label>
															 </p> 
														</div>
														
														<div class="col-xs-12 col-sm-12" id="searchRoomType" >
															 <p>
															  <label for="amount">房型:</label>
																 <select>
																  <option>單人房</option>
																  <option selected="selected">雙人房</option>
																  <option >四人房</option>
																  <option>八人房</option>
																</select>
															 </p> 
														</div>
														
														
														<div class="col-xs-12 col-sm-12" id="searchPrice" >
															<p>
															  <label for="amount">價格範圍:</label>
															  <input type="text" id="amount" readonly style="border:0; color:#f6931f; font-weight:bold;">
															</p>
															<div id="slider-range"></div>
														</div>
								
													
													</div>
													<!----------------------房型陳列---------------------------------->
													<div class="col-xs-12 col-sm-6">
														<div class="item">
															<img class="imgmap" src="mapImage/room1.jpg">
															<h3>新竹豐邑喜來登大飯店</h3>
															<p>Sheraton Hsinchu Hotel</p>
														</div>
													</div>
													<div class="col-xs-12 col-sm-6">
														<div class="item">
															<img class="imgmap" src="mapImage/room2.jpg">
															<h3>新竹豐邑喜來登大飯店</h3>
															<p>Sheraton Hsinchu Hotel</p></div>
													</div>
													<div class="col-xs-12 col-sm-6">
														<div class="item">
															<img class="imgmap" src="mapImage/room3.jpg">
															<h3>新竹豐邑喜來登大飯店</h3>
															<p>Sheraton Hsinchu Hotel</p></div>
													</div>
													<div class="col-xs-12 col-sm-6">
														<div class="item">
															<img class="imgmap" src="mapImage/room4.jpg">
															<h3>新竹豐邑喜來登大飯店</h3>
															<p>Sheraton Hsinchu Hotel</p></div><!--item-->
													</div>
													<div class="col-xs-12 col-sm-6">
														<div class="item">
															<img class="imgmap" src="mapImage/room5.jpg">
															<h3>新竹豐邑喜來登大飯店</h3>
															<p>Sheraton Hsinchu Hotel</p></div><!--item-->
													</div>
													<div class="col-xs-12 col-sm-6">
														<div class="item">
															<img class="imgmap" src="mapImage/room7.jpg">
															<h3>新竹豐邑喜來登大飯店</h3>
															<p>Sheraton Hsinchu Hotel</p></div><!--item-->
													</div>
													<!-----------------------下一頁--------------------->
													<div class="col-xs-12 col-sm-6">
														<div class="itembottom" style="height:300px">
														   
														</div><!--item-->
													</div>
												</div>
											</div><!--scroll-->
											</div>
											<div class="col-xs-12 col-sm-5 semi">
												
												<div class="item right" id="right">
													<img src="https://api.fnkr.net/testimg/500x200/">
													<h3>title</h3>
													<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
													tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
													quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
													consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
													cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
													proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
												</div>
											</div>
										</div>
									</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
   <!--  --------------------------------------------------------------------- -->

</body>

</html>
