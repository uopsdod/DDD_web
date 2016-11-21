<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Title Page</title>
<!-- 		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> -->
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<style type="text/css">
			.item{
				
				background: grey;
				padding:10px;
				margin-bottom: 30px;
			}
			.aa{margin-bottom: 0px;
				background: transparent;		
			}
			.jumbotron{
				margin-bottom: 20px;
			}
			#c-bigscreen{
				 background-size:100vw;
 				 background-image:url(image/hotel1.jpg);	<!--進版大圖示-->
 				 
 				 background-repeat:inherit;
 				 background-repeat: no-repeat;
 				 height:709px;
				} 
				
			#c-bigscreenAnchor{position:absolute;top:650px;left:30px;}
			.c-breadcrumb{
					
				height:20vh;
				background:lightblue; 	
			}
			.c-mainHotel{
				height:12vh;
				position:relative;
				z-index:0;
			}
			.c-partHotel{
				height:8vh;
			}
			.c-room{
				margin-bottom:20px; 
			}	
			.c-roomImg{
				width:250px ;

			}
			.abody{
				font-size:30px;
			}
			#C-roomContent{
				position:relative;
				z-index:0; 
				margin-top:25%;
			}
		</style>

	</head>
	<body>
	
	<!--==主導覽列=====================================================================================================-->	
	
			<%@ include file="/frontend_mem/indexHeader.jsp"%>
<!--==大螢幕=====================================================================================================-->
			
	
	<div class="jumbotron" id="c-bigscreen">
	  <div class="container">
	   
		 
	  </div>
	</div>

			

<!--==內容=====================================================================================================-->	

	<div class="container abody">
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				
				<!--旅館主資料-->

				<div class="row ">
					<div class="col-xs-12 col-sm-3 c-mainHotel text-center">
						<img src="https://api.fnkr.net/testimg/50x50/00CED1/FFF/?text=img+placeholder" style="height:18vh; border-radius: 60%;margin-top:25px">
					</div>
					
					<div class="col-xs-12 col-sm-9 c-mainHotel">
						<p><h2>Lovely Studio in the Heart of Paris</h2></p>
						<p><small>巴黎, 法蘭西島(Île-de-France), 法國 <small></p>					
					
				<!--旅館副資料-->


					<div class="row">
						<div class="col-xs-12 col-sm-3 c-partHotel text-center">
							<h2><img src="image/house.png"></h2>
							<span class"word">飯店<span>	
						</div>
						
						<div class="col-xs-12 col-sm-3 c-partHotel text-center">
							<h2><img src="image/man.png"></h2>
							<span class"word">4位房客<span>
						</div>
						
						<div class="col-xs-12 col-sm-3 c-partHotel text-center">
							<h2><img src="image/room.png"></h2>
							<span class"word">1間臥室<span>
						</div>
						
						<div class="col-xs-12 col-sm-3 c-partHotel text-center">
							<h2><img src="image/bed.png"></h2>
							<span class"word">2張單人床<span>
						</div>
					</div>
				</div>
			
		<!--麵包屑- - - - - -  - - - - - - - - - - - - - - - - - - - - - - - - - - - -->		
			<div id="C-roomContent">	
				<p>Charming small studio of 11 square meters in the heart of Paris (3rd district ) .
				Very quiet ( courtyard ), ideally located in the Marais district . Close to the Temple station, 5 subway lines are available.</p>

				<p>This charming studio is located on the 4th floor of an old building ( courtyard side ), in the Marais district in the heart of Paris .</p>

				<p>This studio of 11 sq.m. offers every comfort . The space is cleverly arranged, kitchen area, bathroom space .</p>
				<p>KitchenetteThe kitchen has two hot plates , a kettle and a fridge. There are of course all utensils for cooking (cutlery, knives , pans , strainer, dishes ...) . A high table and two chairs are provided.</p>
					
				<hr>

				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
				tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
				quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
				consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
				cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
				proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
				</p>

				<hr>

				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
				tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
				quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
				consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
				cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
				proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
				</p>
			</div>	
				<!--面板- - - - - - - - - - - -      - - - - - - - - - - - -  - - - - - -  -->
				<div class="panel panel-success">
				  <div class="panel-heading">
				    <h3 class="panel-title">標題</h3>
				  </div>
				  
				</div>
				<!--房型陳列- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
				
				<div class="row c-room">
					
						<div class="col-xs-12 col-sm-3">
							<img src="mapImage/room2.jpg"	class="c-roomImg">

						</div>
						<div class="col-xs-12 col-sm-9">
							Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cupiditate, voluptatem quaerat doloribus soluta beatae, minus, dicta, aliquam assumenda tempore ipsum id! Repellendus provident expedita earum a voluptates, voluptatum, nemo hic!
						</div>
					
				</div>

				<div class="row c-room">
					
						<div class="col-xs-12 col-sm-3">
							<img src="mapImage/room3.jpg"	class="c-roomImg">

						</div>
						<div class="col-xs-12 col-sm-9">
							Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cupiditate, voluptatem quaerat doloribus soluta beatae, minus, dicta, aliquam assumenda tempore ipsum id! Repellendus provident expedita earum a voluptates, voluptatum, nemo hic!
						</div>
					
				</div>

				<div class="row c-room">
					
						<div class="col-xs-12 col-sm-3">
							<img src="mapImage/room4.jpg"	class="c-roomImg">

						</div>
						<div class="col-xs-12 col-sm-9">
							Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cupiditate, voluptatem quaerat doloribus soluta beatae, minus, dicta, aliquam assumenda tempore ipsum id! Repellendus provident expedita earum a voluptates, voluptatum, nemo hic!
						</div>
					
				</div>

				<div class="row c-room">
					
						<div class="col-xs-12 col-sm-3">
							<img src="mapImage/room5.jpg"	class="c-roomImg">

						</div>
						<div class="col-xs-12 col-sm-9">
							Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cupiditate, voluptatem quaerat doloribus soluta beatae, minus, dicta, aliquam assumenda tempore ipsum id! Repellendus provident expedita earum a voluptates, voluptatum, nemo hic!
						</div>
					
				</div>

				<div class="row c-room">
					
						<div class="col-xs-12 col-sm-3">
							<img src="mapImage/room6.jpg"	class="c-roomImg">

						</div>
						<div class="col-xs-12 col-sm-9">
							Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cupiditate, voluptatem quaerat doloribus soluta beatae, minus, dicta, aliquam assumenda tempore ipsum id! Repellendus provident expedita earum a voluptates, voluptatum, nemo hic!
						</div>
					
				</div>





				<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - -->	
			</div>	
		</div>
	</div>

		

			
				
<!--==分頁=====================================================================================================-->			
			<div class="col-xs-12 col-sm-12 text-center">		<!--加上 text-center置中-->	
						<ul class="pagination" align>
						  <li><a href="#">&laquo;</a></li>
						  <li><a href="#">1</a></li>
						  <li><a href="#">2</a></li>
						  <li class="active"><a href="#">3</a></li>
						  <li><a href="#">4</a></li>
						  <li><a href="#">5</a></li>
						  <li><a href="#">&raquo;</a></li>
						</ul>
			</div>	
	

	<!--==上下頁=====================================================================================================-->	
				
			<ul class="pager">
					<li class="previous"><a href="#">&larr; 上一頁</a></li>
					<li class="next"><a href="#">下一頁 &rarr;</a></li>
			</ul>	

			<ul class="pager">
			  <li><a href="#">上一頁</a></li>
			  <li><a href="#">下一頁</a></li>
			</ul>

		</div>
	</div>
		


		



		
<!-- 		<script src="https://code.jquery.com/jquery.js"></script> -->
<!-- 		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->
	</body>
</html>