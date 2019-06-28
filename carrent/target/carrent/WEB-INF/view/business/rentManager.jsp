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
  			<!--搜索开始 -->
  		<div class="easyui-panel" style="margin-bottom: 2px;text-align: center;padding: 5px"
  		data-options="title:'查询条件',iconCls:'icon-search',width:'100%'">
  		<form action="#" method="post" id="serachFrm">
  		<div align="left">
			<input class="easyui-textbox" name="rentid" labelAlign="center" label="出租单号：" labelPosition="left" style="width:33%;">  		
			<input class="easyui-textbox" name="identity" labelAlign="center" label="身份证号：" labelPosition="left" style="width:33%;">  		
			<input class="easyui-textbox" name="carnumber" labelAlign="center"  label="车辆号牌：" labelPosition="left" style="width:33%;">  		
			<div style="height: 5px"></div>
			<input class="easyui-datebox" name="begindate" edittable="false" labelAlign="center" 	 label="起租时间：" 	labelPosition="left" style="width:33%;">  		
			<input class="easyui-datebox" name="returndate" edittable="false" labelAlign="center" 	 label="起租时间：" 	labelPosition="left" style="width:33%;">  		
             <label style="margin-left: 15px">还车状态：</label>
               <input name="rentflag" type="radio"  value="1" style="margin-left: 50px">已归还
               <input name="rentflag" type="radio" value="0"style="margin-left: 20px">未归还
			<div style="height: 5px"></div>
			</div>
			<div style="text-align: center;">
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="doSearch()" iconCls="icon-search">查询</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#serachFrm').form('clear')" iconCls="icon-reload">重置</a>
			</div>  
			</form>
  		</div>
  		
  		<!--搜索结束  -->
  		<!--数据表格开始  -->
  		<div id="content">
  		<table id="dg" class="easyui-datagrid"></table>
      	 <div id="toolbar">
 			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateRent()">修改出租单</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteRent()">删除出租单</a>
			 <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-page-excel" plain="true" onclick="exportRent()">导出出租单</a>
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
               		 <label>是否出租：</label>
               		 	<input type="radio" name="rentflag" value="1" disabled="disabled">已归还
						<input type="radio" name="rentflag" value="0" disabled="disabled">未归还
						<input type="hidden" name="rentflag" id="rentflag_1">
            </div>
            <div style="margin-bottom:10px">
                <input name="opername" id="opername" carnumber class="easyui-textbox" required="true"    label="操作人:" style="width:100%">
            </div>
        </form>
  		<!--车辆出租弹出层结束 -->
  		</div>
        <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveRent()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</a>
   	 	</div>
  <script type="text/javascript">
  	$("#dg").datagrid({
  		title:'出租单列表',//标题
  		width:'100%',//宽度
  		height:'410px',//高度
  		iconCls:'icon-save',//图标
  		pagination:true,//是否打开分页
  		fitColumns:true,//是否自动分配列宽
  		singleSelect:true,//是否支持单行选中
  		rownumbers:true,//是否显示行号
  		url:'${ctx}/rent/queryAllRent.action',//获取数据的地址
  		toolbar:'#toolbar',//打开工具栏
  		pageSize:10,//默认每页显示的记录数
  		pageNumber:1,//加载时的页码
  		columns:[[
  		        {field:'rentid',title:'出租单号',width:250,align:'center'},
		          {field:'identity',title:'身份证号',width:150,align:'center'},
		          {field:'carnumber',title:'车辆号牌',width:100,align:'center'},
		          {field:'price',title:'出租价格',width:100,align:'center'},
		          {field:'begindate',title:'起租时间',width:100,align:'center'},
		          {field:'rentflag',title:'结束时间',width:100,align:'center',formatter: function(rentflag){
	     				return rentflag==1?'已归还':'未归还';
	        		}},
		          {field:'opername',title:'操作员',width:100,align:'center'},
		          ]],
  	}); 
  	
  	
  //查询
  	function doSearch(){
  		//序列化表格数据
  		var data=$("#serachFrm").serialize();
  		$("#dg").datagrid({
			url:'${ctx}/rent/queryAllRent.action?'+data
		});
  	}
  
  
  //打开出租单弹出层
  	function updateRent(){
	  //得到选中行
  		 var row = $('#dg').datagrid('getSelected');
         if (row){
					if(row.rentflag===0){
						$('#dlg').dialog('open').dialog('center').dialog('setTitle','修改出租单号');
			             //禁用车牌号的编辑功能
			           	$('#rentid').textbox('readonly',true);
			            $('#identity').textbox('readonly',true);
			            $('#carnumber').textbox('readonly',true);
			            $('#opername').textbox('readonly',true);
						//初始化表单数据
						$('#fm').form('clear');
					    $('#fm').form('load',row);
					    //设置出租状态
					    $('#rentflag_1').val(row.rentflag);
					}else{
						 $.messager.show({
								title:'提示',
								msg:'已归还得出租单号不能再做修改'
							});
					}				        	 
         }else{
        	 $.messager.show({
					title:'提示',
					msg:'请选中行',
					showType:'show'
				});
         }
    }
  	 //保存
    function saveRent(){
        $('#fm').form('submit',{
            url: "${ctx}/rent/updateRent.action",
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
                    $('#dg').datagrid('reload');    // 刷新数据表格
            }
        });
    }
  	 
  //删除信息
  	function deleteRent(){
  		var row=$('#dg').datagrid('getSelected');
  		if(row){
  			if(row.rentflag===0){
  			$.messager.confirm('提示','你确定要删除【'+row.rentid+'】这个出租单吗？',function(r){
  				if(r){
  					$.post('${ctx}/rent/deleteRent.action',{rentid:row.rentid},function(result){
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
					msg:'已归还得出租单号不能再做修改',
					showType:'show'
				});
  			}
  		}else{
  			$.messager.show({
					title:'提示',
					msg:'请选中行',
					showType:'show'
				});
  		}
  	}
  
  function exportRent(){
	  var row=$('#dg').datagrid('getSelected');
		if(row){
			var path="${ctx }/rent/exportRent.action?rentid="+row.rentid;
			window.location.href=path;
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
