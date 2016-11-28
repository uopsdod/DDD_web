<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
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
.vcenter {
    display: inline-block;
    vertical-align: middle;
    float: none;
}
#empName{
	color:white;
}

</style>
</head>

<%@ include file="/backend/backendBody.jsp"%>

				<div class="col-xs-12 col-sm-10 bb" style="background-color:#FFFAF0;">
					<ol class="breadcrumb">
						<li>
							<a href="#">訂單</a>
						</li>
						<li class="active">今日訂單維護</li>
					</ol>

				  <table class="table table-hover" border="1">
				    <thead>
				      <tr style="background-color:#B0C4DE;">
				        <th>編號</th>
				        <th>標題</th>
				        <th>發佈日期</th>
				        <th>操作</th>
				      </tr>
				    </thead>

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


				<div class="col-xs-12 col-sm-12">
					<div class="col-xs-12 col-sm-3">
						每頁10筆, 共87筆紀錄
					</div>

					<div class="col-xs-12 col-sm-6 text-center">
						<ul class="pagination" style="margin:0px">
						  <li><a href="#">&laquo;</a></li>
						  <li><a href="#">1</a></li>
						  <li><a href="#">2</a></li>
						  <li class="active"><a href="#">3</a></li>
						  <li><a href="#">4</a></li>
						  <li><a href="#">5</a></li>
						  <li><a href="#">&raquo;</a></li>
						</ul>
					</div>		

					<div class="col-xs-12 col-sm-3">

					</div>
				</div>






				</div>	
			</div>
		</div>	
	</body>
</html>