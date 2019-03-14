<%@ page contentType="text/html; charset=utf-8"%>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>登陆</title>
<meta name="keywords" content="index">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--导入BootStrap的css-->
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" />
<!--导入jquery的js(1.8+)-->
<script src="${pageContext.request.contextPath }/js/jquery-1.11.0.min.js"></script>
<!--导入BootStrap的js-->
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>

<style>
.input-group {
	margin: 10px 0px;
	//
	输入框上下外边距为10px
	,左右为0px
}

h3 {
	padding: 5px;
	border-bottom: 1px solid #ddd;
	//
	h3字体下边框
}

li {
	list-style-type: square; //
	列表项图标为小正方形 margin: 10px 0;
	//
	上下外边距是10px
}

em { //
	强调的样式 color: #c7254e;
	font-style: inherit;
	background-color: #f9f2f4;
}
</style>
</head>

<body>
	<div class="row" style="margin-top: 150px;">

		<div class="col-md-6" style="border-right: 1px solid #ddd;">
			<div style="margin-left: 50px;" class="well col-md-10">
				<h3>用户登录</h3>
				<form id="loginForm" action="loginAction_login" method="post" >
					<div class="input-group input-group-md">
						<span class="input-group-addon" id="sizing-addon1"><i
							class="glyphicon glyphicon-user" aria-hidden="true"></i></span> <input
							type="text" class="form-control" placeholder="用户名" name="username"
							aria-describedby="sizing-addon1">
					</div>
					<div class="input-group input-group-md">
						<span class="input-group-addon" id="sizing-addon1"><i
							class="glyphicon glyphicon-lock"></i></span> <input type="password"
							class="form-control" placeholder="密码" name="password"
							aria-describedby="sizing-addon1">
					</div>
				</form>
				
				<div style="margin-top: 30px;"></div>
				<button type="submit" class="btn btn-success btn-block" onclick="formSubmit()">登录
				</button>
				
				<script type="text/javascript">
					function formSubmit(){
						document.getElementById("loginForm").submit();
					}
				</script>
				
				<div class="mirro">
					<logic:notEmpty name="loginFailed">
						<c:if test="${loginFailed==1}">
							<c:set var="errorInfo" value="用户名或密码错误, 请重新输入!" />
						</c:if>
						<c:if test="${loginFailed==2}">
							<c:set var="errorInfo" value="用户名不存在, 请重新输入!" />
						</c:if>
						<div class="erro" id="erro">
							<div class="erro_intro">${errorInfo}</div>
						</div>
					</logic:notEmpty>
				</div>
			</div>
		</div>
	
	<div class="col-md-6" style="padding-right: 70px;">
		<h3>欢迎使用教学评价管理系统</h3>
		<ul>
			<div style="margin-top: 10px;"></div>
			<li>学生请使用<em>学号</em>登录，初始密码为<em>6个1</em>，登录后请及时修改密码
			</li>
			<div style="margin-top: 10px;"></div>
			<li>老师请使用<em>联系方式</em>登录，初始密码为<em>6个1</em>，登录后请及时修改密码
			</li>
		</ul>
	</div>

	</div>


</body>

</html>