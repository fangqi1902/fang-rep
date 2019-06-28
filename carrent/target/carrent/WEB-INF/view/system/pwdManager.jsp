<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>修改密码</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/easyui/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/wu.css" />
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/icon.css" />
	<script type="text/javascript" src="${ctx }/resources/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${ctx }/resources/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx }/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<div class="easyui-panel" style="height: 500px">
           <div style="height: 50px"></div>
		<h2 align="center">修改密码</h2>
		<form action="" method="post" id="fm" novalidate>
			 <div style="margin-bottom:10px" align="center">
	 		     <input type="hidden" name="userid" value="${user.userid }"/>
           		 <input name="loginname" class="easyui-textbox" required="true" label="用户名：" style="width:40%" value="${user.loginname}" readonly="readonly"/>
           		 <div style="height: 50px"></div>
                          <input name="pwd1" id="pwd1" class="easyui-passwordbox"  label="旧密码："   required="true" style="width:40%"  />
           		 <div style="height: 50px">
           		 <div style="height: 10px"></div>
           		   <span id="msg1" style="color:red"></span> 
           		 </div>
                          <input name="pwd2" id="pwd2" class="easyui-passwordbox" label="新密码："  required="true" style="width:40%" />
           		 <div style="height: 50px"></div>
                          <input name="pwd" id="pwd" class="easyui-passwordbox" label="确认新密码："   required="true" style="width:40%"  />
           		 <div style="height: 50px">
           		 <div style="height: 10px"></div>
           		 <span id="msg2" style="color:red"></span> 
           		 </div>
         		 <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveNewPwd()" style="width:90px">保存</a>
          		</div>	
		</form>
	</div>
	<script type="text/javascript">
	$(function(){
		$("input",$("#pwd1").next("span")).blur(function(){
			if($("#pwd1").val()==${user.pwd}){
				  $("#msg1").html("");
			  }else{
				  $("#msg1").html("原始密码输入错误，请重新输入");
			  }
			});
		$("input",$("#pwd").next("span")).blur(function(){
			  var pwd2=$("#pwd2").val();				  
			  var pwd=$("#pwd").val();
			  if(pwd2==pwd){
				  $("#msg2").html("");
			  }else{
					  $("#msg2").html("输入密码和第一次不一致,请重新输入");
			}
		});
	});

	function saveNewPwd(){
		var data=$("#fm").serialize();
		var url='${ctx}/user/updatePwd.action?'+data;
		$.post(url,{},function(result){
			$.messager.show({
					title:'提示',
					msg:result.msg
				});
			//跳转到登陆界面
			window.parent.location.href="${ctx}/index.jsp";
		},"json");
    }
</script>
</body>
</html>