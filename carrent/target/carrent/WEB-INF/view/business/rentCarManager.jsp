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
	<script type="text/javascript" src="${ctx }/resources/easyui/datagrid-detailview.js"></script>

  </head>
  <body>
  			<!--搜索开始 -->
  		<div class="easyui-panel" style="margin-bottom: 2px;text-align: center;padding: 5px"
  		data-options="title:'查询条件',iconCls:'icon-search',width:'100%'">
			<input class="easyui-searchbox" id="search_identity"  data-options="prompt:'请输入客户身份证号',searcher:doSearch" style="width:50%">
  		</div>
  		<!--搜索结束  -->
  		<!--数据表格开始  -->
  		<div id="content">
  		<table id="dg" class="easyui-datagrid"></table>
      	 <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="rentCar()">出租车辆</a>
   		 </div>
  		</div>
  		<!--数据表格结束  -->
  		<!--车辆出租弹出层开始  -->
  		<div id="dlg" class="easyui-dialog" style="width:600px" data-options="closed:true,modal:true,border:'thin',buttons:'#dlg-buttons'">
        <form id="fm" method="post" novalidate style="margin:0;padding:10px 50px">
            <div style="margin-bottom:10px">
                <input name="rentid" id="rentid" class="easyui-textbox" required="true" label="出租单号:"   style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="identity" id="identity" class="easyui-textbox" required="true" label="身份证号:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="carnumber"  id="carnumber" class="easyui-textbox" required="true" label="车牌号码:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="price" class="easyui-numberbox" required="true" label="出租价格:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="begindate" class="easyui-datebox" required="true"  label="出租时间:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input name="returndate" class="easyui-datebox" required="true"  label="归还时间:" style="width:100%">
            </div>
            <div style="margin-bottom:10px;">
               		 <label>是否出租：</label>	<input name="isrenting" type="radio"  value="1" style="margin-left: 100px">已出租
                			<input name="rentflag" type="radio" value="0"style="margin-left: 20px">未出租
            </div>
            <div style="margin-bottom:10px">
                <input name="opername" id="opername" carnumber class="easyui-textbox" required="true"    label="操作人:" style="width:100%">
            </div>
        </form>
  		<!--车辆出租弹出层结束 -->
  		</div>
        <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveRentCar()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</a>
   	 	</div>
  <script type="text/javascript">
  
  
	$("#content").hide();//隐藏表格信息
	function initTable(){
  	$("#dg").datagrid({
  		title:'日志列表',//标题
  		width:'100%',//宽度
  		height:'410px',//高度
  		iconCls:'icon-save',//图标
  		pagination:true,//是否打开分页
  		fitColumns:true,//是否自动分配列宽
  		singleSelect:true,//是否支持单行选中
  		rownumbers:true,//是否显示行号
  		url:'${ctx}/car/queryAllCars.action?isrenting=0',//获取数据的地址
  		toolbar:'#toolbar',//打开工具栏
  		pageSize:10,//默认每页显示的记录数
  		pageNumber:1,//加载时的页码
  		columns:[[
  		        {field:'carnumber',title:'车牌号',width:100,align:'center'},
		          {field:'cartype',title:'类型',width:100,align:'center'},
		          {field:'color',title:'颜色',width:100,align:'center'},
		          {field:'price',title:'购买价格',width:100,align:'center'},
		          {field:'rentprice',title:'出租价格',width:100,align:'center'},
		          {field:'deposit',title:'出租押金',width:100,align:'center'},
		          {field:'isrenting',title:'是否出租',width:100,align:'center',formatter: function(isrenting){
	     				return isrenting==1?'已出租':'未出租';
	        		}},
		          {field:'description',title:'车辆描述',width:100,align:'center'},
		          ]],
		 view: detailview,    
		 detailFormatter:function(index,row){    
		 return "<img width=250px height=150px src="+(row.carimg.substr(1,row.carimg.length))+"></img>";    
		 },
  	}); 
	}
  	
  //根据身份证号查询，如果客户存在就显示为出租车辆
  	function doSearch(value){
	 	// alert(value);
  		$.post("${ctx}/rent/checkCustomerIdentity.action",{identity:value},function(result){
  			//alert(result);
  			if(result){
  				$('#content').show();//显示表格信息
  				initTable();//初始化表格信息
  			}else{
  				$.messager.show({
					title:'提示',
					msg:'身份证号不存在，请检查身份证是否正确'
				});
				$("#content").hide();//隐藏表格信息
  			}
  		},"json");
  	}
  
  //打开车辆出租弹出层
  	function rentCar(){
  		var identity=$("#search_identity").val();
  		 var row = $('#dg').datagrid('getSelected');
         if (row){
             $('#dlg').dialog('open').dialog('center').dialog('setTitle','车辆出租');
             //禁用车牌号的编辑功能
           	$('#rentid').textbox('readonly',true);
            $('#identity').textbox('readonly',true);
            $('#carnumber').textbox('readonly',true);
            $('#opername').textbox('readonly',true);
			//初始化表单数据
			$.post("${ctx}/rent/initRentFormData.action",{carnumber:row.carnumber,identity:identity,price:row.rentprice},function(rentObj){
				 $('#fm').form('load',rentObj);
			},"json");
         }else{
        	 $.messager.show({
					title:'提示',
					msg:'请选中行',
					showType:'show'
				});
         }
    }
  	 //保存
    function saveRentCar(){
        $('#fm').form('submit',{
            url: "${ctx}/rent/addRent.action",
            onSubmit: function(){
            	//做验证
                return $(this).form('validate');
            },
            success: function(result){
                var result = eval('('+result+')');
                    $.messager.show({
                        title: '提示',
                        msg: result.msg
                    });
                    $('#dlg').dialog('close');        //关闭弹出层
                  	//隐藏表格
                  	$("#content").hide();
            }
        });
    }
  	
  </script>
  
  </body>
</html>
