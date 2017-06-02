<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link
	href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet">
<!-- 可选的Bootstrap主题文件（一般不使用） -->
<script
	src="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap-theme.min.css"></script>

<!DOCTYPE html>
<html>
<head>
<title>秒杀详情页</title>


</head>
<body>
	<div class="container">
		<div class="panel panel-default text-center">
			<div class="pannel-heading">
				<h1>${seckill.name}</h1>
			</div>

			<div class="panel-body">
				<h2 class="text-danger">
					<!--显示time图标-->
					<span class="glyphicon glyphicon-time"></span>
					<!--展示倒计时-->
					<span class="glyphicon" id="seckill-box"></span>
				</h2>
			</div>
		</div>
	</div>
	<!--登录弹出层 输入电话-->
	<div id="killPhoneModal" class="modal fade">

		<div class="modal-dialog">

			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title text-center">
						<span class="glyphicon glyphicon-phone"> </span>秒杀电话:
					</h3>
				</div>

				<div class="modal-body">
					<div class="row">
						<div class="col-xs-8 col-xs-offset-2">
							<input type="text" name="killPhone" id="killPhoneKey"
								placeholder="填写手机号^o^" class="form-control">
						</div>
					</div>
				</div>

				<div class="modal-footer">
					<!--验证信息-->
					<span id="killPhoneMessage" class="glyphicon"> </span>
					<button type="button" id="killPhoneBtn" class="btn btn-success">
						<span class="glyphicon glyphicon-phone"></span> Submit
					</button>
				</div>

			</div>
		</div>

	</div>

</body>
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<script
	src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script
	src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script src="https://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>
<script src="${request.contextPath}/js/seckill.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
    //使用EL表达式传入参数
	alert("进来了");
    seckill.detail.init({
        seckillId: "${seckill.seckillId}",
        startTime: "${seckill.startTime?datetime?long}",
        endTime: "${seckill.endTime?datetime?long}"
       });
    });

</script>


</html>