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
			<input class="easyui-textbox" name="rolename" labelAlign="center" label="角色名称：" labelPosition="left" style="width:33%;">  		
			<input class="easyui-textbox" name="roledesc" labelAlign="center"  label="角色描述：" labelPosition="left" style="width:33%;">  		
			<div style="height: 5px"></div>
			<div style="text-align: center;">
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="doSearch()" iconCls="icon-search">查询</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#serachFrm').form('clear')" iconCls="icon-reload">重置</a>
			</div>  		
  		</form>
  		</div>
  
  		<table id="dg" class="easyui-datagrid"></table>
  		<div id="toolbar">
       <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addRole()">添加角色</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateRole()">修改角色</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteRole()">删除角色</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="toSelectMenu()">分配菜单</a>
   		 </div>
    	</div>
    	<div id="dlg" class="easyui-dialog" style="width:400px" data-options="closed:true,modal:true,border:'thin',buttons:'#dlg-buttons'">
        <form id="fm" method="post" novalidate style="margin:0;padding:10px 30px">
            <h3 align="center">角色信息</h3>
            <div style="margin-bottom:10px">
                <input name="rolename"  class="easyui-textbox" required="true" label="角色名称:" style="width:100%">
				<input type="hidden" name="roleid">
	        </div>
            <div style="margin-bottom:10px">
                <input name="roledesc" class="easyui-textbox" required="true" multiline="true"  label="角色描述:" style="width:100%;height:60px;">
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveRole()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</a>
    </div>
  	    <!-- 弹出层结束  -->
     <!-- 分配菜单弹出层开始 -->
     <div id="dlg_menu" class="easyui-dialog" style="width:400px;height: 400px" data-options="closed:true,modal:true,border:'thin',buttons:'#dlg_menu-buttons'">
            <div id="menuTree" class="ztree"> </div>
    </div>
    <div id="dlg_menu-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveRoleMenu()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_menu').dialog('close')" style="width:90px">取消</a>
    </div>
    <!-- 分配菜单弹出层结束  -->
  <script type="text/javascript">
  	$("#dg").datagrid({
  		title:'角色列表',//标题
  		width:'100%',//宽度
  		height:'340px',//高度
  		iconCls:'icon-save',//图标
  		pagination:true,//是否打开分页
  		fitColumns:true,//是否自动分配列宽
  		singleSelect:true,//是否支持单行选中
  		rownumbers:true,//是否显示行号
  		url:'${ctx}/role/queryAllRole.action',//获取数据的地址
  		toolbar:'#toolbar',//打开工具栏
  		pageSize:10,//默认每页显示的记录数
  		pageNumber:1,//加载时的页码
  		columns:[[
  		          {field:'roleid',title:'编号',width:100,align:'center'},
  		          {field:'rolename',title:'角色名称',width:100,align:'center'},
  		          {field:'roledesc',title:'角色描述',width:100,align:'center'},
  		          ]]
  	});  
  	//查询
  	function doSearch(){
  		//序列化表格数据
  		var data=$("#serachFrm").serialize();
  		$("#dg").datagrid({
			url:'${ctx}/role/queryAllRole.action?'+data
		});
  	}
  	
  	//全局变量
  	var url;
  	//打开添加弹出层
  	function addRole(){
  		 $('#dlg').dialog('open').dialog('center').dialog('setTitle','添加客户');
         $('#fm').form('clear');
         url = '${ctx}/role/addRole.action';
  	}
  	//打开修改弹出层
  	function updateRole(){
  		 var row = $('#dg').datagrid('getSelected');
         if (row){
             $('#dlg').dialog('open').dialog('center').dialog('setTitle','修改客户');
             $('#fm').form('load',row);
             url = '${role}/customer/updateRole.action';
         }else{
        	 $.messager.show({
					title:'提示',
					msg:'请选中行',
					showType:'show'
				});
         }
    }
  	
  	//提交表单数据，并刷新列表
  	function saveRole(){
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
  	function deleteRole(){
  		var row=$('#dg').datagrid('getSelected');
  		if(row){
  		  $.messager.confirm('提示','你确定删除【'+row.rolename+'】这个角色吗',function(r){
  				if(r){
  					$.post('${ctx}/role/deleteRole.action',{roleid:row.roleid},function(result){
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
  	
  //打开分配角色的弹出层
  	function  toSelectMenu(){
  	//得到选中行
        var row = $('#dg').datagrid('getSelected');
        if (row){
        	$('#dlg_menu').dialog('open').dialog('center').dialog('setTitle','分配菜单');
            $('#fm').form('load',row);
            var url="${ctx}/role/initRoleMenuTree.action?roleid="+row.roleid;
   			$.post(url,function(json){
   				$.fn.zTree.init($("#menuTree"), setting, json);
   			});
        }else{
        	$.messager.show({
                title: '提示',
                msg: "请选中操作行"
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
    
   	//保存角色和菜单之间的关系
   function	saveRoleMenu(){
	   var row = $('#dg').datagrid('getSelected');
	   var roleid=row.roleid;//角色ID
	   var params="?roleid="+roleid;
	   var treeObj = $.fn.zTree.getZTreeObj("menuTree");//得到zTree的树
	   var nodes = treeObj.getCheckedNodes(true);//得到被选择中的节点集合
	   for(var i=0;i<nodes.length;i++){
		   var node=nodes[i];
		   params+="&ids="+node.id;
	   }
	   //?roleid=1&ids=1&ids=2&ids=3
	   $.post("${ctx}/role/saveRoleMenus.action"+params,function(obj){
		   $.messager.show({
               title: '提示',
               msg: obj.msg
           });
	   });
   	}
  	
  	
  	
  	
  	
  	
  </script>
  
  </body>
</html>
