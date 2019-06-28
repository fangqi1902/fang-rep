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
  		<div class="easyui-panel" style="margin-bottom: 2px;text-align: center;padding: 5px"
  		data-options="title:'查询条件',iconCls:'icon-search',width:'100%'">
  		<form action="#" method="post" id="serachFrm">
  			<div align="left">
			<input class="easyui-textbox" name="carnumber" labelAlign="center" label="车辆牌照：" labelPosition="left" style="width:33%;">  		
			<input class="easyui-textbox" name="cartype" labelAlign="center" label="车辆类型：" labelPosition="left" style="width:33%;">  		
			<div style="height: 5px"></div>
			<input class="easyui-textbox" name="color" labelAlign="left"  label="车辆颜色：" labelPosition="left" style="width:33%;">  		
			<input class="easyui-textbox" name="description" labelAlign="center" 	 label="车辆描述：" 	labelPosition="left" style="width:33%;">  		
             <label style="margin-left: 20px">是否出租：</label>
               <input name="isrenting" type="radio"  value="1" style="margin-left: 50px">已出租
               <input name="isrenting" type="radio" value="0"style="margin-left: 20px">未出租
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
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addCar()">添加车辆</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateCar()">修改车辆</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteCar()">删除车辆</a>
   		 </div>
    	</div>
    	<div id="dlg" class="easyui-dialog" style="width:800px" data-options="closed:true,modal:true,border:'thin',buttons:'#dlg-buttons'">
        <form id="fm" method="post" novalidate style="margin:0;padding:10px 50px">
            <div style="margin-bottom:10px">
                <input name="carnumber" class="easyui-textbox" required="true" label="车辆牌照:" style="width:60%">
            </div>
            <div style="margin-bottom:10px">
                <input name="cartype" class="easyui-textbox" required="true" label="车辆类型:" style="width:60%">
            </div>
            <div style="margin-bottom:10px">
                <input name="color" class="easyui-textbox" required="true" label="车辆颜色:" style="width:60%">
            </div>
            <div style="margin-bottom:10px">
                <input name="price" class="easyui-numberbox" required="true" label="购买价格:" style="width:60%">
            </div>
            <div style="margin-bottom:10px">
                <input name="rentprice" class="easyui-numberbox" required="true" label="出租价格:" style="width:60%">
            </div>
            <div style="margin-bottom:10px">
                <input name="deposit" class="easyui-numberbox" required="true"  label="出租押金:" style="width:60%">
            </div>
            <div style="margin-bottom:10px;">
               		 <label>是否出租：</label>	<input name="isrenting" type="radio"  value="1" style="margin-left: 100px">已出租
                			<input name="isrenting" type="radio" value="0"style="margin-left: 20px">未出租
            </div>
            <div style="margin-bottom:10px">
                <input name="description" class="easyui-textbox" required="true" multiline="true"   label="车辆描述:" style="width:100%;height: 80px">
                <input type="hidden" name="carimg" id="carimg" value="../resources/images/defaulttitle.jpg">
            </div>
             <img id="carimg_img" style="position: absolute;left: 60%;top: 50px;border-radius:10px" width="35%" height="230px" src="${ctx }/resources/images/defaulttitle.jpg">
        </form>
        <form action="" id="imgFrm" enctype="multipart/form-data" method="post" novalidate style="margin:0;padding:0px 50px">
        	<div style="margin-bottom:10px,30px">
                <input class="easyui-filebox" name="mf" style="width:80%"
				data-options="label:'选择图片:',required:true,labelAlign:'center',
				labelPosition:'left',accept:'image/*'">
			<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-add'"	onclick="uploadCarImg()">上传</a>
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveCar()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</a>
    </div>
  
  <script type="text/javascript">
  	$("#dg").datagrid({
  		title:'汽车列表',//标题
  		width:'100%',//宽度
  		height:'340px',//高度
  		iconCls:'icon-save',//图标
  		pagination:true,//是否打开分页
  		fitColumns:true,//是否自动分配列宽
  		singleSelect:true,//是否支持单行选中
  		rownumbers:true,//是否显示行号
  		url:'${ctx}/car/queryAllCars.action',//获取数据的地址
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
  	//查询
  	function doSearch(){
  		//序列化表格数据
  		var data=$("#serachFrm").serialize();
  		$("#dg").datagrid({
			url:'${ctx}/car/queryAllCars.action?'+data
		});
  	}
  	
  	//全局变量
  	var url;
  	//打开添加弹出层
  	function addCar(){
  		 $('#dlg').dialog('open').dialog('center').dialog('setTitle','添加车辆');
  		//恢复车牌号的编辑功能
         $('#carnumber').textbox('readonly',false);
         $('#fm').form('clear');
         //设置默认图片
         $("#carimg_img").attr({src:"./resources/images/defaulttitle.jpg"});
         $("#carimg").val("./resources/images/defaulttitle.jpg");
         url = '${ctx}/car/addCar.action.action';
  	}
  	//打开修改弹出层
  	function updateCar(){
  		 var row = $('#dg').datagrid('getSelected');
         if (row){
             $('#dlg').dialog('open').dialog('center').dialog('setTitle','修改车辆');
             //禁用车牌号的编辑功能
             $('#carnumber').textbox('readonly',true);
             //设置车辆图片
             $("#carimg_img").attr({src:row.carimg.substr(1,row.carimg.length)});
             $('#fm').form('load',row);
             url = '${ctx}/car/updateCar.action';
         }else{
        	 $.messager.show({
					title:'提示',
					msg:'请选中行',
					showType:'show'
				});
         }
    }
  	
  	//提交表单数据，并刷新列表
  	function saveCar(){
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
                }     
        });
    }
  	
  	//使用AJAX上传图片
  	function uploadCarImg(){
  		//得到上传文件的表单
  		//var imgFrm=$("#imgFrm");//--->转成js对象  $("#imgFrm")[0]
        var formData = new FormData($("#imgFrm")[0]);//文件上传的相关参数
        $.ajax({
        	url:'${ctx }/car/uploadCarImg.action',
        	type:'POST',
        	data:formData,
    		async:false,
   			cache:false,
   			contentType:false,
   			processData:false,
   			success:function(obj){
   				var  path=obj.path;
   				//alert(path);
   				$("#carimg_img").attr({src:path.substr(1,path.length)});
   				//给表单设置值 ，目地是当提交表单时把这个图片地址传到后台
   				$("#carimg").val(path);
   			},
   			error:function(rv){
   				alert(rv);
   			}
        });
  	}
  	
  	
  	
  	
  	//删除信息
  	function deleteCar(){
  		var row=$('#dg').datagrid('getSelected');
  		if(row){
  			$.messager.confirm('提示','你确定要删除【'+row.carnumber+'】这辆车吗？',function(r){
  				if(r){
  					$.post('${ctx}/car/deleteCar.action',{carnumber:row.carnumber},function(result){
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
