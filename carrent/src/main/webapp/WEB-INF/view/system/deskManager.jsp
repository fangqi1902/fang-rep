<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>首页----工作台</title>
    
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
    <div class="easyui-layout" style="width: 100%;height: 100%">
    	<div class="easyui-panl" 
    			data-options="title:'系统公告',iconCls:'icon-save',region:'center',width:'70%',split:true">
    			<table id="dg"> </table>
    	</div>
    	<div class="easyui-panel" 
    	data-options="title:'当前日历',iconCls:'icon-save',region:'east',width:'30%',split:true">
		<div class="easyui-calendar" style="width: 100%;height: 100%"></div>    	
    	</div>
    </div>
    <!--系统公告列表  -->
    <div id="dlg" class="easyui-dialog" style="width:1000px;height: 600px" data-options="closed:true,modal:true,border:'thin',buttons:'#dlg-buttons'">
        <form id="fm" method="post" novalidate style="margin:0;padding:20px 50px">
            <div style="margin-bottom:10px">
               <div id="title" style="text-align: center;font-size: 25px;font-weight: bold;"></div>
            </div>
            <hr>
             <div style="margin-bottom:10px;text-align: right;" >
               <span id="opername"></span>
               <span id="createtime" style="margin-left: 30px"></span>
            </div>
            <hr>
            <div style="margin-bottom:10px;">
           		 <div id="content" ></div>
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</a>
    </div>
    <script type="text/javascript">
	//加载数据
	$("#dg").datagrid({
		border:0,
		url:'${ctx}/news/queryAllNews.action',
		singleSelect:true,  //是否支持单行选中
		method:'get',  //请求方式
		width:'100%',
		height:'100%',
		fitColumns:true,  //是否自动分配列
		rownumbers:true,  //是否显示行号
		pagination:true,  //是否启用分页
		columns:[[
		        {field:'title',title:'新闻标题',width:100,align:'center'},
		        {field:'createtime',title:'发布时间',width:100,align:'center'},
		        {field:'opername',title:'发布人',width:100,align:'center'},
		    ]],
		onDblClickRow:function(index,row){//双击打开详细内容
			showNew(row);
		}
	});
	function showNew(row){
		 $('#dlg').dialog('open').dialog('center').dialog('setTitle','新闻内容');//把新闻内容展示到弹出层
		 $("#title").html(row.title);
		 $("#createtime").html("发布时间:"+row.createtime);
		 $("#opername").html("作者:"+row.opername);
		 $("#content").html(row.content);
	}
    
    </script>
  </body>
  
</html>
