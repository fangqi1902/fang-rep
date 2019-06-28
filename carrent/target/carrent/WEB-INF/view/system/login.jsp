<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<title>登录-尚学堂汽车出租系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<script type="text/javascript" src="${ctx }/resources/js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="${ctx }/resources/js/login.js"></script>
		<link href="${ctx }/resources/css/login.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<h1>尚学堂汽车出租管理系统<sup>2018 </sup></h1>
		<div class="login" style="margin-top:50px;">
			<div class="header">
				<div class="switch" id="switch">
					<span class="switch_btn_focus" id="switch_qlogin">快速登录</span>
				</div>
			</div>
			<div class="web_qr_login" id="web_qr_login" style="display: block; height: 235px;">
				<!--登录-->
				<div class="web_login" id="web_login">
					<div class="login-box">
						<div class="login_form">
							<form action="${ctx }/login/login.action" name="loginform" accept-charset="utf-8" id="login_form" class="loginForm" method="post">
								<div class="uinArea" id="uinArea">
									<label class="input-tips" for="u">帐号：</label>
									<div class="inputOuter" id="uArea">
										<input type="text" id="u" name="loginname" class="inputstyle" />
									</div>
								</div>
								<div class="pwdArea" id="pwdArea">
									<label class="input-tips" for="p">密码：</label>
									<div class="inputOuter" id="pArea">
										<input type="password" id="p" name="pwd" class="inputstyle" />
									</div>
								</div>
								<div style="color: red" align="center">${error }</div>
								<div style="padding-left:50px;margin-top:20px;"><input type="submit" value="登 录" style="width:150px;" class="button_blue" /></div>
							</form>
						</div>
					</div>
				</div>
				<!--登录end-->
			</div>
		</div>
		<div class="jianyi">*推荐使用ie8或以上版本ie浏览器或Chrome内核浏览器访问本站</div>
	</body>
</html>