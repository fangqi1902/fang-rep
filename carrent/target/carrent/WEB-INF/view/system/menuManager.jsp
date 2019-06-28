<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="copyright" content="All Rights Reserved, Copyright (C) 2013, Wuyeguo, Ltd." />
<title>尚学堂汽车出租系统</title>
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
<div class="easyui-layout" style="width: 100%;height:500px;">
	<div class="easyui-panel" data-options="region:'west',split:true,border:true,title:'菜单树',width:'18%',height:'100%'"> 
  			<div class="ztree" id="treeMenus" data-options="border:false,fit:true">  </div>
	</div>
	<div class="easyui-panel" data-options="region:'center',split:true,title:'菜单列表',width:'82%',height:'100%'">
			<table id="dg" class="easyui-datagrid"></table>
			 <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addMenu()">添加菜单</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateMenu()">修改菜单</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteMenu()">删除菜单</a>
    </div>
	</div>
</div>
    	<div id="dlg" class="easyui-dialog" style="width:500px" data-options="closed:true,modal:true,border:'thin',buttons:'#dlg-buttons'">
        <form id="fm" method="post" novalidate style="margin:0;padding:10px 50px;">
            <div style="margin-bottom:10px">
		        	<input type="hidden" name="id">
                   <input name="pId" class="easyui-combotree" url="${ctx}/menu/loadMenuTreeForNormalJosn.action?available=1" method="get" labelAlign="center"  required="true" label="父节点:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="name" class="easyui-textbox" required="true" label="菜单名称:" labelAlign="right"  style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="href" class="easyui-textbox"  label="菜单地址:" labelAlign="right"  style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="target" class="easyui-numberbox"  label="TARGET:" labelAlign="right"  style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="icon" class="easyui-numberbox" required="true" label="图标:" labelAlign="right"  style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="tabicon" class="easyui-numberbox" required="true"  label="TAB图标:" labelAlign="right"  style="width:100%">
            </div>
            <div style="margin-bottom:10px;">
               	<label class="textbox-label" style="text-align: center;text-align: right;">是否展开:</label>
				<input type="radio" name="open" value="1" style="margin-left: 30px">是
				<input type="radio" name="open" value="0" style="margin-left: 30px">否
            </div>
            <div style="margin-bottom:10px;" >
               	<label class="textbox-label" style="text-align: center;text-align: right;">是否父节点:</label>
				<input type="radio" name="parent" value="1" style="margin-left: 30px">是
				<input type="radio" name="parent" value="0" style="margin-left: 30px">否
            </div>
            <div style="margin-bottom:10px;">
               	<label class="textbox-label" style="text-align: center;text-align: right;">是否可用:</label>
				<input type="radio" name="available" value="1" style="margin-left: 30px">是
				<input type="radio" name="available" value="0" style="margin-left: 30px">否
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveMenu()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</a>
    </div>
<script type="text/javascript">
	//加载菜单树
	function zTreeOnClick(event, treeId, treeNode) {
		$("#dg").datagrid({
			url:'${ctx}/menu/queryAllMenus.action?id='+treeNode.id
		});
	};
	var setting = {
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onClick: zTreeOnClick
		}
	};
	
	function iniLeftTree(){
		var url="${ctx}/menu/loadLeftTree.action";
		$.post(url,function(json){
			console.log(json);
			$.fn.zTree.init($("#treeMenus"), setting, json);
		});
	}
	
	
	$(document).ready(function(){
		iniLeftTree();
	});
	
	//加载才单列表数据表格数据
	$("#dg").datagrid({
		//title:'菜单列表',//标题
		width:'100%',//宽度
		height:'100%',//高度
		border:false,
		//iconCls:'icon-save',//图标
		pagination:true,//是否打开分页
		fitColumns:true,// 是否动分配列宽
		singleSelect:true,// 是否支持单行选中
		rownumbers:true,// 是否显示行号
		url:'${ctx}/menu/queryAllMenus.action',//json数据地址
		toolbar:'#toolbar',//工具栏
	    columns:[[//对url传回的json数据的解析
		           {field:'id',title:'ID',width:100,align:'center'},
		           {field:'pid',title:'父ID',width:100,align:'center'},
		           {field:'name',title:'菜单名称',width:100,align:'center'},
		           {field:'href',title:'菜单地址',width:100,align:'center'},
		           {field:'open',title:'是否展开',width:100,align:'center',formatter:function(value){
		        	   return value==1?'<font color=blue>展开</font>':'<font color=red>不展开</font>';
		           }},
		           {field:'parent',title:'是否父节点',width:100,align:'center',formatter:function(value){
		        	   return value==1?'<font color=blue>是</font>':'<font color=red>否</font>';
		           }},
		           {field:'target',title:'TARGET',width:100,align:'center'},
		           {field:'icon',title:'菜单图标',width:100,align:'center',formatter:function(value){
		        	   var html="<img src='"+value+"'/>"+value;
		        	   return html;
		           }},
		           {field:'available',title:'是否可用',width:100,align:'center',formatter:function(value){
		        	   return value==1?'<font color=blue>可用</font>':'<font color=red>不可用</font>';
		           }},
		       ]]
	});
	
	
	//全局变量
  	var url;
  	//打开添加弹出层
  	function addMenu(){
  		 $('#dlg').dialog('open').dialog('center').dialog('setTitle','添加菜单');
         $('#fm').form('clear');
  		//解决单选按钮默认选中 的问题
  		 $('#fm').form("load",{open:0,parent:0,available:1});
         url = '${ctx}/menu/addMenu.action.action';
  	}
  	
  	
  	//打开修改弹出层
  	function updateMenu(){
  		 var row = $('#dg').datagrid('getSelected');
         if (row){
             $('#dlg').dialog('open').dialog('center').dialog('setTitle','修改菜单');
             $('#fm').form('load',row);
             url = '${ctx}/menu/updateMenu.action';
         }else{
        	 $.messager.show({
					title:'提示',
					msg:'请选中行',
					showType:'show'
				});
         }
    }
  	
  	//提交表单数据，并刷新列表
  	function saveMenu(){
        $('#fm').form('submit',{
            url: url,
            onSubmit: function(){
                return $(this).form('validate');
            },
            success: function(result){
            		var result = eval('('+result+')');
                    $.messager.show({
                        title: '提示',
                        msg:result.msg
                    });
                    $('#dlg').dialog('close'); //关闭弹出层
                    $('#dg').datagrid('reload');//刷新表格    
                    iniLeftTree();//刷新左侧菜单树
                }     
        });
    }
  	
  	

  	//删除信息
  	function deleteMenu(){
  		var row=$('#dg').datagrid('getSelected');
  		if(row){
  			$.messager.confirm('提示','你确定要删除【'+row.name+'】这个菜单吗？',function(r){
  				if(r){
  					$.post('${ctx}/menu/deleteMenu.action',{id:row.id},function(result){
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
