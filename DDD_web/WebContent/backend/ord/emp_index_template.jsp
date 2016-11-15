<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!-- 請輸入標題 -->
	<title>管理後端</title>
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/css/bootstrap.css">
	<!-- 自訂CSS -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/css/0_main.css">
	
	<script src="<%=request.getContextPath()%>/backend/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/backend/js/bootstrap.js"></script>
	<!-- 自訂JavaScript -->
	<script src="<%=request.getContextPath()%>/backend/css/0_new.js"></script>
</head>

<body>
	<!-- 這是頂端那條Bar -->
	<div id="top-bar">
		<nav class="navbar navbar-inverse" role="navigation">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-ex1-collapse">
					<span class="sr-only">選單切換</span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"><span
					class="glyphicon glyphicon-heart-empty"></span> DDD後端管理介面</a>
			</div>

			<!-- 手機隱藏選單區 -->
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<!-- 右選單 -->
				<ul class="nav navbar-nav navbar-right">
					<!-- 抓使用者的名字 -->
					<li><a href="#">大城 您好 !</a></li>
					<li><a href="#"><span class="glyphicon glyphicon-bell"></span>
							通知</a></li>
					<li><a href="#"><span class="glyphicon glyphicon-log-out"></span>
							登出</a></li>
				</ul>
			</div>
			<!-- 手機隱藏選單區結束 -->
		</nav>
	</div>

	<div class="container-fluid">
		<div class="row">
			<!-- 左邊的手風琴 -->
			<div class="col-xs-12 col-sm-2 aa" style="background-color: #DCDCDC;">
				<div class="panel-group" id="accordion2" role="tablist"
					aria-multiselectable="true">
					<!-- 區塊A(訂單) -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="tab1"
							style="background-color: #B0C4DE;">
							<h4 class="panel-title">
								<a role="button" data-toggle="collapse"
									data-parent="#accordion2" href="#aaa" aria-expanded="true"
									aria-controls="aaa"> <span
									class="glyphicon glyphicon-list-alt"></span> 訂單
								</a>
							</h4>
						</div>
						<div id="aaa" class="panel-collapse collapse in" role="tabpanel"
							aria-labelledby="tab1">
							<div class="panel-body">
								<ul style="list-style-type: none">
									<li>今日訂單維護</li>
									<li>歷史訂單維護</li>
								</ul>
							</div>
						</div>
					</div>
					<!-- 區塊B(Banner) -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="tab4"
							style="background-color: #B0C4DE;">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion2" href="#ddd" aria-expanded="false"
									aria-controls="ddd"> <span
									class="glyphicon glyphicon-bullhorn"></span> Banner
								</a>
							</h4>
						</div>
						<div id="ddd" class="panel-collapse collapse" role="tabpanel"
							aria-labelledby="tab4">
							<div class="panel-body">
								<ul style="list-style-type: none">
									<li>Banner方案管理</li>
									<li>Banner審核作業</li>
									<li>Banner管理作業</li>
								</ul>
							</div>
						</div>
					</div>
					<!-- 區塊C(上架月租費) -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="tab7"
							style="background-color: #B0C4DE;">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion2" href="#ggg" aria-expanded="false"
									aria-controls="fff"> <span
									class="glyphicon glyphicon-piggy-bank"></span> 上架月租費
								</a>
							</h4>
						</div>
						<div id="ggg" class="panel-collapse collapse" role="tabpanel"
							aria-labelledby="tab7">
							<div class="panel-body">
								<ul style="list-style-type: none">
									<li>月租管理作業</li>
								</ul>
							</div>
						</div>
					</div>
					<!-- 區塊D(一般會員) -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="tab2"
							style="background-color: #B0C4DE;">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion2" href="#bbb" aria-expanded="false"
									aria-controls="bbb"> <span class="glyphicon glyphicon-king"></span>
									一般會員
								</a>
							</h4>
						</div>
						<div id="bbb" class="panel-collapse collapse" role="tabpanel"
							aria-labelledby="tab2">
							<div class="panel-body">
								<ul style="list-style-type: none">
									<li>一般會員維護</li>
									<li>對話紀錄維護</li>
								</ul>
							</div>
						</div>
					</div>
					<!-- 區塊E(廠商會員) -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="tab3"
							style="background-color: #B0C4DE;">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion2" href="#ccc" aria-expanded="false"
									aria-controls="ccc"> <span
									class="glyphicon glyphicon-queen"></span> 廠商會員
								</a>
							</h4>
						</div>
						<div id="ccc" class="panel-collapse collapse" role="tabpanel"
							aria-labelledby="tab3">
							<div class="panel-body">
								<ul style="list-style-type: none">
									<li>廠商審核作業</li>
									<li>廠商會員維護</li>
								</ul>
							</div>
						</div>
					</div>
					<!-- 區塊F(員工) -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="tab5"
							style="background-color: #B0C4DE;">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion2" href="#eee" aria-expanded="false"
									aria-controls="eee"> <span class="glyphicon glyphicon-pawn"></span>
									員工
								</a>
							</h4>
						</div>
						<div id="eee" class="panel-collapse collapse" role="tabpanel"
							aria-labelledby="tab5">
							<div class="panel-body">
								<ul style="list-style-type: none">
									<li>新增員工資料</li>
									<li>員工資料維護</li>
								</ul>
							</div>
						</div>
					</div>
					<!-- 區塊G(檢舉) -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="tab6"
							style="background-color: #B0C4DE;">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion2" href="#fff" aria-expanded="false"
									aria-controls="fff"> <span
									class="glyphicon glyphicon-thumbs-down"></span> 檢舉
								</a>
							</h4>
						</div>
						<div id="fff" class="panel-collapse collapse" role="tabpanel"
							aria-labelledby="tab6">
							<div class="panel-body">
								<ul style="list-style-type: none">
									<li>一般會員檢舉</li>
									<li>廠商會員檢舉</li>
								</ul>
							</div>
						</div>
					</div>
					<!-- 區塊H(評價留言) -->
					<div class="panel panel-default">
						<div class="panel-heading" role="tab" id="tab8"
							style="background-color: #B0C4DE;">
							<h4 class="panel-title">
								<a class="collapsed" role="button" data-toggle="collapse"
									data-parent="#accordion2" href="#hhh" aria-expanded="false"
									aria-controls="hhh"> <span
									class="glyphicon glyphicon-thumbs-up"></span> 留言評價
								</a>
							</h4>
						</div>
						<div id="hhh" class="panel-collapse collapse" role="tabpanel"
							aria-labelledby="tab8">
							<div class="panel-body">
								<ul style="list-style-type: none">
									<li>留言評價維護</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- 右邊的主要區塊 -->
			<div class="col-xs-12 col-sm-10 bb" style="background-color: #FFFAF0;">
			
				<!-- 麵包屑(當前路徑) -->
				<ol class="breadcrumb">
					<li><a href="#">訂單</a></li>
					<li class="active">今日訂單維護</li>
				</ol>

				<!-- 主要的table -->
				<table class="table table-hover" border="1">
					<!-- table標題 -->
					<thead>
						<tr style="background-color: #B0C4DE;">
							<th>編號</th>
							<th>標題</th>
							<th>發佈日期</th>
							<th>操作</th>
						</tr>
					</thead>

					<!-- table內容 -->
					<tbody>
						<tr>
							<td>helloworld</td>
							<td>helloworld</td>
							<td>helloworld</td>
							<td>
								<button type="button" class="btn btn-primary">修改</button>
								<button type="button" class="btn btn-danger">刪除</button>
							</td>
						</tr>
						<tr>
							<td>helloworld</td>
							<td>helloworld</td>
							<td>helloworld</td>
							<td>
								<button type="button" class="btn btn-primary">修改</button>
								<button type="button" class="btn btn-danger">刪除</button>
							</td>
						</tr>
						<tr>
							<td>helloworld</td>
							<td>helloworld</td>
							<td>helloworld</td>
							<td>
								<button type="button" class="btn btn-primary">修改</button>
								<button type="button" class="btn btn-danger">刪除</button>
							</td>
						</tr>
						<tr>
							<td>helloworld</td>
							<td>helloworld</td>
							<td>helloworld</td>
							<td>
								<button type="button" class="btn btn-primary">修改</button>
								<button type="button" class="btn btn-danger">刪除</button>
							</td>
						</tr>
						<tr>
							<td>helloworld</td>
							<td>helloworld</td>
							<td>helloworld</td>
							<td>
								<button type="button" class="btn btn-primary">修改</button>
								<button type="button" class="btn btn-danger">刪除</button>
							</td>
						</tr>
						<tr>
							<td>helloworld</td>
							<td>helloworld</td>
							<td>helloworld</td>
							<td>
								<button type="button" class="btn btn-primary">修改</button>
								<button type="button" class="btn btn-danger">刪除</button>
							</td>
						</tr>
						<tr>
							<td>helloworld</td>
							<td>helloworld</td>
							<td>helloworld</td>
							<td>
								<button type="button" class="btn btn-primary">修改</button>
								<button type="button" class="btn btn-danger">刪除</button>
							</td>
						</tr>
						<tr>
							<td>helloworld</td>
							<td>helloworld</td>
							<td>helloworld</td>
							<td>
								<button type="button" class="btn btn-primary">修改</button>
								<button type="button" class="btn btn-danger">刪除</button>
							</td>
						</tr>
						<tr>
							<td>helloworld</td>
							<td>helloworld</td>
							<td>helloworld</td>
							<td>
								<button type="button" class="btn btn-primary">修改</button>
								<button type="button" class="btn btn-danger">刪除</button>
							</td>
						</tr>
						<tr>
							<td>helloworld</td>
							<td>helloworld</td>
							<td>helloworld</td>
							<td>
								<button type="button" class="btn btn-primary">修改</button>
								<button type="button" class="btn btn-danger">刪除</button>
							</td>
						</tr>
					</tbody>
				</table>


				<!-- 下面的表格分頁操作 -->
				<div class="col-xs-12 col-sm-12">
					<div class="col-xs-12 col-sm-3">每頁10筆, 共87筆紀錄</div>

					<div class="col-xs-12 col-sm-6 text-center">
						<ul class="pagination" style="margin: 0px">
							<li><a href="#">&laquo;</a></li>
							<li><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li class="active"><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li><a href="#">5</a></li>
							<li><a href="#">&raquo;</a></li>
						</ul>
					</div>

					<div class="col-xs-12 col-sm-3"></div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>