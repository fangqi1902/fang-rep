<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录日志</title>
    
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

  </head>
  <body>
  		<div class="easyui-panel" style="margin-bottom: 2px;text-align: center;padding: 5px"
  		data-options="title:'查询条件',iconCls:'icon-search',width:'100%'">
  		<form action="#" method="post" id="serachFrm">
			<input class="easyui-textbox" name="loginname" labelAlign="center" label="登录名称：" labelPosition="left" style="width:40%;">  		
			<input class="easyui-textbox" name="loginip" labelAlign="center" label="登录地址：" labelPosition="left" style="width:40%;">  		
			<div style="height: 5px"></div>
			<input class="easyui-datebox" name="startDate" labelAlign="center" editable="false" label="开始时间：" labelPosition="left" style="width:40%;">  		
			<input class="easyui-datebox" name="endDate" labelAlign="center" editable="false" label="结束时间：" labelPosition="left" style="width:40%;">  		
			<div style="height: 5px"></div>
			<div style="text-align: center;">
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="doSearch()" iconCls="icon-search">查询</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#serachFrm').form('clear')" iconCls="icon-reload">重置</a>
			</div>  		
  		</form>
  		</div>
  
  		<table id="dg" class="easyui-datagrid"></table>
  		<div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyLogInfo()">删除信息</a>
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
  		url:'${ctx}/logInfo/queryAllLogInfo.action',//获取数据的地址
  		toolbar:'#toolbar',//打开工具栏
  		pageSize:10,//默认每页显示的记录数
  		pageNumber:1,//加载时的页码
  		columns:[[
  		          {field:'id',title:'编号',width:100,align:'center'},
  		          {field:'loginname',title:'登录人',width:100,align:'center'},
  		          {field:'loginip',title:'登录ip地址',width:100,align:'center'},
  		          {field:'logintime',title:'登录时间',width:100,align:'center'},
  		          ]]
  	});  
  	//查询
  	function doSearch(){
  		//序列化表格数据
  		var data=$("#serachFrm").serialize();
  		$("#dg").datagrid({
			url:'${ctx}/logInfo/queryAllLogInfo.action?'+data
		});
  	}
  	//删除信息
  	function destroyLogInfo(){
  		var row=$('#dg').datagrid('getSelected');
  		if(row){
  			$.messager.confirm('提示','你确定要删除这条日志吗？',function(r){
  				if(r){
  					$.post('${ctx}/logInfo/deleteLogInfo.action',{id:row.id},function(result){
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
  	
  </script>
  
  </body>
</html>
