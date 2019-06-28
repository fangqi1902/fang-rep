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
	  <link href="${ctx}/resources/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/easyui/themes/metro/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/icon.css" />
	<script type="text/javascript" src="${ctx}/resources/js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx}/resources/umeditor/umeditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx}/resources/umeditor/umeditor.min.js"></script>
    <script type="text/javascript" src="${ctx}/resources/umeditor/lang/zh-cn/zh-cn.js"></script>
  </head>
  <body>
  		<div class="easyui-panel" style="margin-bottom: 2px;text-align: center;padding: 5px"
  		data-options="title:'查询条件',iconCls:'icon-search',width:'100%'">
  		<form action="#" method="post" id="serachFrm">
			<input class="easyui-textbox" name="title" labelAlign="center" label="新闻标题：" labelPosition="left" style="width:40%;">  		
			<input class="easyui-textbox" name="content" labelAlign="center" label="新闻内容：" labelPosition="left" style="width:40%;">  		
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
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newNews()">添加新闻</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editNews()">修改新闻</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyNews()">删除新闻</a>
    	</div> 	
    	    <div id="dlg" class="easyui-dialog" style="width:1000px" data-options="closed:true,modal:true,border:'thin',buttons:'#dlg-buttons'">
        <form id="fm" method="post" novalidate style="margin:0;padding:20px 50px">
            <div style="margin-bottom:10px">
            	<input name="id" id="id" type="hidden">
            	<input name="opername"  type="hidden">
            	<input name="createtime" id="createtime" type="hidden">
                <input name="title" class="easyui-textbox" required="true" label="新闻标题:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
            <label class="easyui-label">新闻内容:</label>
	            <div id="content" name="content" style="width:100%;height:240px;">
				</div>
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveNews()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
    </div>
  
  <script type="text/javascript">

  	$("#dg").datagrid({
  		title:'公告列表',//标题
  		width:'100%',//宽度
  		height:'340px',//高度
  		iconCls:'icon-save',//图标
  		pagination:true,//是否打开分页
  		fitColumns:true,//是否自动分配列宽
  		singleSelect:true,//是否支持单行选中
  		rownumbers:true,//是否显示行号
  		url:'${ctx}/news/queryAllNews.action',//获取数据的地址
  		toolbar:'#toolbar',//打开工具栏
  		pageSize:10,//默认每页显示的记录数
  		pageNumber:1,//加载时的页码
  		columns:[[
  		          {field:'id',title:'编号',width:100,align:'center'},
  		          {field:'title',title:'新闻标题',width:100,align:'center'},
  		          {field:'content',title:'新闻内容',width:100,align:'center'},
  		          {field:'createtime',title:'发布时间',width:100,align:'center'},
  		          {field:'opername',title:'发布人',width:100,align:'center'},
  		          ]]
  	});  
  	//查询
  	function doSearch(){
  		//序列化表格数据
  		var data=$("#serachFrm").serialize();
  		$("#dg").datagrid({
			url:'${ctx}/news/queryAllNews.action?'+data
		});
  	}
  	
  	 var url;
     function newNews(){
         $('#dlg').dialog('open').dialog('center').dialog('setTitle','添加新闻');
         $('#fm').form('clear');
         url = '${ctx}/news/addNews.action';
     }
     function editNews(){
     	//得到当前用户的选中行的数据  
         var row = $('#dg').datagrid('getSelected');
         $('#fm').form('clear');
         if (row){
             $('#dlg').dialog('open').dialog('center').dialog('setTitle','修改新闻');
             $('#fm').form('load',row);
             $("#content").html("");//设置内容为空
             $('#content').html(row.content);//把新闻内容回显
             url = '${ctx}/news/updateNews.action';
         }else{
         	  $.messager.show({
                   title: '提示',
                   msg: '请选中操作行'
               });
         }
     }
     function saveNews(){
         $('#fm').form('submit',{
             url: url,
             onSubmit: function(){
                 return $(this).form('validate');
             },
             success: function(result){
             	var res=eval('('+result+')');
                 $('#dlg').dialog('close');        // close the dialog
                 $('#dg').datagrid('reload');    // reload the News data
                 $.messager.show({
                     title: '提示',
                     msg: res.msg
                 });
             }
         });
     }
  	
  	
  	
  	
  	//删除信息
  	function destroyNews(){
  		var row=$('#dg').datagrid('getSelected');
  		if(row){
  			$.messager.confirm('提示','你确定要删除这条公告吗？',function(r){
  				if(r){
  					$.post('${ctx}/news/deleteNews.action',{id:row.id},function(result){
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
  	
    //初始化富文本编辑器
	 //实例化编辑器
	var um = UM.getEditor('content');
  	
  </script>
  
  </body>
</html>
