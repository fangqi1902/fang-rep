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

  </head>
  <body>
  		<div class="easyui-panel" style="margin-bottom: 2px;text-align: center;padding: 5px"
  		data-options="title:'查询条件',iconCls:'icon-search',width:'100%'">
  		<form action="#" method="post" id="serachFrm">
  			<div align="left">
			<input class="easyui-textbox" name="identity" labelAlign="center" label="身份证号：" labelPosition="left" style="width:33%;">  		
			<input class="easyui-textbox" name="custname" labelAlign="center" label="客户姓名：" labelPosition="left" style="width:33%;">  		
			<input class="easyui-textbox" name="address" labelAlign="centerleft"  label="客户地址：" labelPosition="left" style="width:33%;">  		
			<div style="height: 5px"></div>
			<input class="easyui-textbox" name="phone" labelAlign="center" 	 label="客户电话：" 	labelPosition="left" style="width:33%;">  		
			<input class="easyui-textbox" name="career" labelAlign="center"  label="客户职位：" 	labelPosition="left" style="width:33%;">  		
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
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addCustomer()">添加客户</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateCustomer()">修改客户</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteCustomer()">删除客户</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-page-excel" plain="true" onclick="exportCustomer()">导出客户</a>
   		 </div>
    	</div>
    	<div id="dlg" class="easyui-dialog" style="width:400px" data-options="closed:true,modal:true,border:'thin',buttons:'#dlg-buttons'">
        <form id="fm" method="post" novalidate style="margin:0;padding:10px 30px">
            <h3 align="center">客户信息</h3>
            <div style="margin-bottom:10px">
                <input name="identity" id="identity" class="easyui-textbox" required="true" label="身份证号:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="custname" class="easyui-textbox" required="true" label="客户姓名:" style="width:100%">
            </div>
            <div style="margin-bottom:10px;margin-left: 17px">
               		 性别：	<input name="sex" type="radio"  value="1" style="margin-left: 80px">男
                			<input name="sex" type="radio" value="0"style="margin-left: 20px">女
            </div>
            <div style="margin-bottom:10px">
                <input name="address" class="easyui-textbox" required="true" label="客户地址:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="phone" class="easyui-textbox" required="true" label="客户电话:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="career" class="easyui-textbox" required="true"  label="客户职位:" style="width:100%">
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</a>
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
  		url:'${ctx}/customer/queryAllCustomer.action',//获取数据的地址
  		toolbar:'#toolbar',//打开工具栏
  		pageSize:10,//默认每页显示的记录数
  		pageNumber:1,//加载时的页码
  		columns:[[
  		          {field:'identity',title:'身份证号',width:100,align:'center'},
  		          {field:'custname',title:'客户姓名',width:100,align:'center'},
  		          {field:'sex',title:'客户性别',width:100,align:'center',formatter: function(sex){
  		     				return sex==1?'男':'女';
  		        		}},
  		          {field:'address',title:'客户地址',width:100,align:'center'},
  		          {field:'phone',title:'客户电话',width:100,align:'center'},
  		          {field:'career',title:'客户职位',width:100,align:'center'},
  		          ]]
  	});  
  	//查询
  	function doSearch(){
  		//序列化表格数据
  		var data=$("#serachFrm").serialize();
  		$("#dg").datagrid({
			url:'${ctx}/customer/queryAllCustomer.action?'+data
		});
  	}
  	
  	//全局变量
  	var url;
  	//打开添加弹出层
  	function addCustomer(){
  		 $('#dlg').dialog('open').dialog('center').dialog('setTitle','添加客户');
         $('#fm').form('clear');
       //取消禁用身份证号的编辑功能
         $('#identity').textbox('readonly',false);
         url = '${ctx}/customer/addCustomer.action';
  	}
  	//打开修改弹出层
  	function updateCustomer(){
  		 var row = $('#dg').datagrid('getSelected');
         if (row){
             $('#dlg').dialog('open').dialog('center').dialog('setTitle','修改客户');
             $('#fm').form('load',row);
           //禁用身份证号的编辑功能
             $('#identity').textbox('readonly',true);
             url = '${ctx}/customer/updateCustomer.action';
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
  	function deleteCustomer(){
  		var row=$('#dg').datagrid('getSelected');
  		if(row){
  		  $.messager.confirm('提示','你确定删除【'+row.custname+'】这个客户吗',function(r){
  				if(r){
  					$.post('${ctx}/customer/deleteCustomer.action',{identity:row.identity},function(result){
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
  	
  	function exportCustomer(){
  		var params=$("#serachFrm").serialize();
		//alert(params);
		//把数据传到后台并刷新datagrid
		var path="${ctx }/customer/exportCustomer.action?"+params;
		window.location.href=path;
  	}
  	
  </script>
  
  </body>
</html>
