<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link
	href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet">
<!-- 可选的Bootstrap主题文件（一般不使用） -->
<script
	src="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap-theme.min.css"></script>
<#assign base=request.contextPath />

<!DOCTYPE html>
<html>
<head>
<title>秒杀商品列表</title>

</head>
<body>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading text-center">
				<h2>秒杀列表</h2>
			</div>
			<div class="panel-body">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>名称</th>
							<th>库存</th>
							<th>开始时间</th>
							<th>结束时间</th>
							<th>创建时间</th>
							<th>详情</th>
						</tr>
					</thead>
					<tbody>
						<#list list as sk> 
							<tr>
							<td>${sk.name}</td>
							<td>${sk.number}</td>
							<td>${sk.startTime?string("yyyy-MM-dd HH:mm:ss")}</td>
							<td>${sk.endTime?string("yyyy-MM-dd HH:mm:ss")}</td>
							<td>${sk.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
							<#if sk.isBuy==1>
								<td><a class="btn btn-warning" href="###">已购买</a></td>
							<#else>
								<td><a class="btn btn-info"
											href="${base}/seckill/${sk.seckillId}/detail"
											target="_blank">未购买</a></td>
							</#if>
							</tr>
						 </#list>
						 
					</tbody>
				</table>

			</div>
		</div>
	</div>



	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>

	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script
		src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</body>
</html>