<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<title>登录</title>
<meta charset="utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript"
	src="./static/js/jquery/jquery-1.11.3.min.js" charset="utf-8"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				// 更换验证码
				$("#captchaImage").click(
						function() {
							$("#captchaImage").attr("src","./common/captcha.html?captchaId=${captchaId}&timestamp=" + (new Date()).valueOf());
						});

				$("#submit").click(
						function() {
							$.ajax({
								url : "./login/submit.html",
								data : $("#loginForm").serialize(),
								type : "post",
								dataType : "text",
								cache : false,
								success : function(data) {
									if (data == "成功") {
										//alert("登录成功！");
										window.location.href = "./";
									} else {
										$("#message").text(data);
										$("#captchaImage").attr("src","./common/captcha.html?captchaId=${captchaId}&timestamp="+ (new Date()).valueOf());
									}
								},
								error : function(data) {
								}
							});
						});
				/**回车**/
				$("#captcha").keydown(function(e) {
					if (e.keyCode == 13) {
						$("#submit").click();
					}
				});

			});
</script>
<style type="text/css">
	body {
	    margin:0px;
	    padding:0px;
	    font-size: 14px;
	}
	.main{
		margin: 0 auto;
		width:400px;
	}
	.main div{
		padding:5px 0;
	}
	#captcha{
		width:60px;
	}
	img{
		vertical-align: middle;
	}
	label{
		width:100px;
	}
</style>
</head>

<body>
	<div class="main">
		<form id="loginForm" action="../login/submit.html" method="post">
			<input type="hidden" name="captchaId" id="captchaId" value="${captchaId }"> 
			<div>
				<label for="username">用户名称：</label><input type="text" name="username" id="username" value=""> 
			</div>
			<div>
				<label for="password">用户密码：</label><input type="password" name="password" id="password" value="">
			</div>
			<div>
				<label for="captcha">验证码：</label><input type="text" name="captcha" id="captcha">&nbsp;&nbsp;
				<img id="captchaImage" class="captchaImage" src="./common/captcha.html?captchaId=${captchaId}" title="点击切换验证码" />
			</div>
			<div>
				<input type="button" value="登录" id="submit"> <input type="button" value="退出" onclick="javascript:window.location.href='../login/logout.html'">
			</div>
		</form>
		<label id="message" style="color:red"></label>
	</div>
</body>
</html>
