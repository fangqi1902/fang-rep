<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'logInfoManager.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="${ctx }/resources/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/easyui/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/wu.css" />
	<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/icon.css" />
	<script type="text/javascript" src="${ctx }/resources/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${ctx }/resources/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx }/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${ctx }/resources/zTree/js/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="${ctx }/resources/zTree/js/jquery.ztree.excheck.js"></script>
  </head>
  <body>
  		<div class="easyui-panel" style="margin-bottom: 2px;text-align: center;padding: 5px"
  		data-options="title:'查询条件',iconCls:'icon-search',width:'100%'">
  		<form action="#" method="post" id="serachFrm">
  			<div align="left">
			<input class="easyui-textbox" name="identity" labelAlign="center" label="身份证号：" labelPosition="left" style="width:33%;">  		
			<input class="easyui-textbox" name="realname" labelAlign="center" label="用户姓名：" labelPosition="left" style="width:33%;">  		
			<input class="easyui-textbox" name="loginname" labelAlign="center"  label="登录名称：" labelPosition="left" style="width:33%;">  		
			<div style="height: 5px"></div>
			<input class="easyui-textbox" name="phone" edittable="false" labelAlign="center" 	 label="用户电话：" 	labelPosition="left" style="width:33%;">  		
			<input class="easyui-textbox" name="position" edittable="false" labelAlign="center" 	 label="用户职位：" 	labelPosition="left" style="width:33%;">  		
			<input class="easyui-textbox" name="address" edittable="false" labelAlign="center" 	 label="用户地址：" 	labelPosition="left" style="width:33%;">  		
			<div style="height: 5px"></div>
			<div style="height: 5px"></div>
			</div>
			<div style="text-align: center;">
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="doSearch()" iconCls="icon-search">查询</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#serachFrm').form('clear')" iconCls="icon-reload">重置</a>
			</div>  		
  		</form>
  		</div>
  
  		<table id="dg" class="easyui-datagrid"></table>
  		<div id="toolbar">
       <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addUser()">添加用户</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateUser()">修改用户</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteUser()">删除用户</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="resetUserPwd()">重置密码</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="toSelectRole()">分配角色</a>
   		 </div>
    	</div>
    	<div id="dlg" class="easyui-dialog" style="width:400px" data-options="closed:true,modal:true,border:'thin',buttons:'#dlg-buttons'">
        <form id="fm" method="post" novalidate style="margin:0;padding:10px 30px">
            <h3 align="center">用户信息</h3>
            <input type="hidden" name="userid">
            <input type="hidden" name="pwd">
            <input type="hidden" name="type">
            <div style="margin-bottom:10px">
                <input name="loginname" class="easyui-textbox" required="true" label="登录名称:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="identity" id="identity" class="easyui-textbox" required="true" label="身份证号:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="realname" id="identity" class="easyui-textbox" required="true" label="真实姓名:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="address" class="easyui-textbox" required="true" label="用户地址:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="phone" class="easyui-textbox" required="true" label="用户电话:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="position" class="easyui-textbox" required="true"  label="用户职位:" style="width:100%">
            </div>
            <div style="margin-bottom:10px;margin-left: 17px">
               		 性别：	<input name="sex" type="radio"  value="1" style="margin-left: 80px">男
                			<input name="sex" type="radio" value="0"style="margin-left: 20px">女
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</a>
    </div>
    <!--分配角色弹出层  -->
    <div id="dlg_role" class="easyui-dialog" style="width:400px" data-options="closed:true,modal:true,border:'thin',buttons:'#dlg_role-buttons'">
        <div style="margin-bottom:10px" class="ztree" id="roleTree"></div>
    </div>
    <div id="dlg_role-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUserRole()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_role').dialog('close')" style="width:90px">关闭</a>
    </div>
  
  <script type="text/javascript">
  	$("#dg").datagrid({
  		title:'日志列表',//标题
  		width:'100%',//宽度
  		height:'340px',//高度
  		iconCls:'icon-save',//图标
  		pagination:true,//是否打开分页
  		fitColumns:true,//是否自动分配列宽
  		singleSelect:true,//是否支持单行选中
  		rownumbers:true,//是否显示行号
  		url:'${ctx}/user/queryAllUser.action',//获取数据的地址
  		toolbar:'#toolbar',//打开工具栏
  		pageSize:10,//默认每页显示的记录数
  		pageNumber:1,//加载时的页码
  		columns:[[
  		          {field:'userid',title:'用户编号',width:100,align:'center'},
  		          {field:'identity',title:'身份证号',width:100,align:'center'},
  		          {field:'loginname',title:'登录名称',width:100,align:'center'},
  		          {field:'realname',title:'用户姓名',width:100,align:'center'},
  		          {field:'sex',title:'用户性别',width:100,align:'center',formatter: function(sex){
  		     				return sex==1?'男':'女';
  		        		}},
  		          {field:'address',title:'用户地址',width:100,align:'center'},
  		          {field:'phone',title:'用户电话',width:100,align:'center'},
  		       	   {field:'pwd',title:'密码',width:100,align:'center',formatter: function(value){
	     				return '*******';
	        		}},
  		          {field:'position',title:'用户职位',width:100,align:'center'},
  		          ]]
  	});  
  	//查询
  	function doSearch(){
  		//序列化表格数据
  		var data=$("#serachFrm").serialize();
  		$("#dg").datagrid({
			url:'${ctx}/user/queryAllUser.action?'+data
		});
  	}
  	
  	//全局变量
  	var url;
  	//打开添加弹出层
  	function addUser(){
  		 $('#dlg').dialog('open').dialog('center').dialog('setTitle','添加用户');
         $('#fm').form('clear');
         url = '${ctx}/user/addUser.action';
  	}
  	//打开修改弹出层
  	function updateUser(){
  		 var row = $('#dg').datagrid('getSelected');
         if (row){
             $('#dlg').dialog('open').dialog('center').dialog('setTitle','修改用户');
             $('#fm').form('load',row);
             url = '${ctx}/user/updateUser.action';
         }else{
        	 $.messager.show({
					title:'提示',
					msg:'请选中行',
					showType:'show'
				});
         }
    }
  	
  	//提交表单数据，并刷新列表
  	function saveUser(){
        $('#fm').form('submit',{
            url: url,
            onSubmit: function(){
                return $(this).form('validate');
            },
            success: function(result){
            	var result = eval('('+result+')');
                $.messager.show({
                    title: '提示',
                    msg: result.msg
                });
                $('#dlg').dialog('close');        //关闭弹出层
                $('#dg').datagrid('reload');    // 刷新数据表格
                }     
        });
    }
  	
  	
  	//删除信息
  	function deleteUser(){
  		var row=$('#dg').datagrid('getSelected');
  		if(row){
  		  $.messager.confirm('提示','你确定删除【'+row.realname+'】这个用户吗',function(r){
  				if(r){
  					$.post('${ctx}/user/deleteUser.action',{userid:row.userid},function(result){
  						$.messager.show({
  							title:'提示',
  							msg:result.msg
  						});
						 $('#dg').datagrid('reload');//刷新界面 						
  					},'json');
  				}
  			});
  		}else{
  			$.messager.show({
					title:'提示',
					msg:'请选中行',
					showType:'show'
				});
  		}
  	}
  	//删除信息
  	function resetUserPwd(){
  		var row=$('#dg').datagrid('getSelected');
  		if(row){
  		  $.messager.confirm('提示','你确定重置【'+row.realname+'】这个用户的密码吗',function(r){
  				if(r){
  					$.post('${ctx}/user/resetUserPwd.action',{userid:row.userid},function(result){
  						$.messager.show({
  							title:'提示',
  							msg:result.msg
  						});
						 $('#dg').datagrid('reload');//刷新界面 						
  					},'json');
  				}
  			});
  		}else{
  			$.messager.show({
					title:'提示',
					msg:'请选中行',
					showType:'show'
				});
  		}
  	}
  	
  //多选树的加载
  	var setting = {
  			data: {
  				simpleData: {
  					enable: true
  				}
  			},
  			check:{
  				enable:true
  			}
   	
  		};
  
    //打开分配角色的弹出层
  	function toSelectRole(){
    	//得到选中行对象
  		var row=$('#dg').datagrid('getSelected');
  		if (row){
  			//得到对象的id
  			var userid = row.userid;
            $('#dlg_role').dialog('open').dialog('center').dialog('setTitle','分配角色');
            var url="${ctx}/user/initUserRoleTree.action?userid="+userid;
            //初始化角色树形结构
			$.post(url,function(json){
				$.fn.zTree.init($("#roleTree"), setting, json);
			});
        }else{
       	 $.messager.show({
					title:'提示',
					msg:'请选中行',
					showType:'show'
				});
        }
  }
	//保存用户拥有的角色
	function saveUserRole(){
		var row=$('#dg').datagrid('getSelected');
		var userid = row.userid;
		var params="?userid="+userid;
		//得到树对象$.fn.zTree.getZTreeObj("roleTree");
		var treeObj=$.fn.zTree.getZTreeObj("roleTree");
		//得到树中被选中得节点
		var nodes = treeObj.getCheckedNodes(true);
		for(var i=0;i<nodes.length;i++){
			params+="&ids="+nodes[i].id;
		} 
		$.post("${ctx}/user/saveUserRole.action"+params,function(obj){
        	$.messager.show({
                title: '提示',
                msg: obj.msg
            });
        },"json");
    }
		
    
    
  	
  	
  </script>
  
  </body>
</html>
