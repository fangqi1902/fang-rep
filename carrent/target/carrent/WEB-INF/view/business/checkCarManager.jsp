<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<link rel="stylesheet"
	href="${ctx }/resources/zTree/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<link rel="stylesheet" type="text/css"
	href="${ctx }/resources/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx }/resources/css/wu.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx }/resources/css/icon.css" />
<script type="text/javascript"
	src="${ctx }/resources/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/zTree/js/jquery.ztree.core.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/easyui/datagrid-detailview.js"></script>

</head>
<body>
	<!--搜索开始 -->
	<div class="easyui-panel"
		style="margin-bottom: 2px;text-align: center;padding: 5px"
		data-options="title:'查询条件',iconCls:'icon-search',width:'100%'">
		<input class="easyui-searchbox" id="search_rentid"
			data-options="prompt:'请输入出租单号',searcher:doSearch" style="width:50%">
	</div>
	<!--搜索结束  -->
	<div id="content" style="width: 100%;">
		<!--检查单  -->
		<div class="easyui-panel" style="width: 100%;text-align: center;"
			data-options="title:'生成检查单',iconCls:'icon-ok'">
			<form id="fm" method="post" novalidate
				style="margin:0;padding:10px 30px;">
				<table width="100%">
					<tr>
						<td width="50%"><input class="easyui-textbox" name="checkid" id="checkid"
							required="true" labelAlign="center" label="检查单号:"
							labelPosition="left" style="width:100%;">
						</td>
						<td width="50%"><input class="easyui-datebox"  
							name="checkdate" required="true" labelAlign="center"
							label="检查时间:" labelPosition="left" style="width:100%;">
						</td>
					</tr>
					<tr>
						<td width="50%"><input class="easyui-textbox" name="rentid"  id="rentid"
							required="true" labelAlign="center" label="出租单号:"
							labelPosition="left" style="width:100%;">
						</td>
						<td width="50%"><input class="easyui-textbox" name="opername"
							required="true" labelAlign="center" label="操作员:"
							labelPosition="left" style="width:100%;">
						</td>
					</tr>
					<tr>
						<td width="50%"><input class="easyui-textbox" name="problem"
							required="true" labelAlign="center" label="存在问题:"
							labelPosition="left" style="width:100%;">
						</td>
						<td width="50%"><input class="easyui-numberbox"
							name="paymoney" required="true" labelAlign="center" label="赔付金额:"
							labelPosition="left" style="width:100%">
						</td>
					</tr>
					<tr>
						<td colspan="2"><input class="easyui-textbox"
							multiline="true" name="checkdesc" required="true"
							labelAlign="center" label="问题描述:" labelPosition="left"
							style="width:100%;height: 60px"></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><a href="javascript:void(0)"
							class="easyui-linkbutton" onclick="saveCheck()"
							iconCls="icon-save">保存</a></td>
					</tr>
				</table>
			</form>
		</div>
		<div style="height: 3px"></div>
		<!--详情内容   布局：西中东-->
		<div class="easyui-layout" style="width: 100%;height: 200px">
			<!--汽车信息  -->
			<div class="easyui-panel"
				data-options="region:'west',split:true,title:'车辆信息',iconCls:'icon-ok',width:'33%'">
				<table width="100%" cellspacing="10">
					<tr>
						<td align="right" width="40%">车辆号牌:</td>
						<td align="left" id="car_carnumber"></td>
					</tr>
					<tr>
						<td align="right">车辆颜色:</td>
						<td align="left" id="car_color"></td>
					</tr>
					<tr>
						<td align="right">车辆类型:</td>
						<td align="left" id="car_cartype"></td>
					</tr>
					<tr>
						<td align="right">车辆价格:</td>
						<td align="left" id="car_price"></td>
					</tr>
					<tr>
						<td align="right">车辆押金:</td>
						<td align="left" id="car_deposit"></td>
					</tr>
					<tr>
						<td align="right">车辆图片:</td>
						<td align="left"><img alt="未找到图片" id="car_carimg" width="150"
							height="120" src="./resources/images/defaulttitle.jpg"></td>
					</tr>
				</table>
			</div>
			<!--出租单信息  -->
			<div class="easyui-panel"
				data-options="region:'center',split:true,title:'车辆信息',iconCls:'icon-ok',width:'33%'">
				<table width="100%" cellspacing="8">
					<tr>
						<td align="right" width="40%">出租单号:</td>
						<td align="left" id="rent_rentid"></td>
					</tr>
					<tr>
						<td align="right">出租价格:</td>
						<td align="left" id="rent_price"></td>
					</tr>
					<tr>
						<td align="right">开始时间:</td>
						<td align="left" id="rent_begindate"></td>
					</tr>
					<tr>
						<td align="right">还车时间:</td>
						<td align="left" id="rent_returndate"></td>
					</tr>
					<tr>
						<td align="right">操作员:</td>
						<td align="left" id="rent_opername"></td>
					</tr>
				</table>
			</div>
			<!-- 出租单信息结束 -->
			<!--客户信息  -->
			<div class="easyui-panel"
				data-options="region:'east',split:true,title:'车辆信息',iconCls:'icon-ok',width:'33%'">
				<table width="100%" cellspacing="10">
					<tr>
						<td align="right" width="40%">身份证号:</td>
						<td align="left" id="customer_identity"></td>
					</tr>
					<tr>
						<td align="right">客户姓名:</td>
						<td align="left" id="customer_custname"></td>
					</tr>
					<tr>
						<td align="right">客户地址:</td>
						<td align="left" id="customer_address"></td>
					</tr>
					<tr>
						<td align="right">客户电话:</td>
						<td align="left" id="customer_phone"></td>
					</tr>
					<tr>
						<td align="right">客户性别:</td>
						<td align="left" id="customer_sex"></td>
					</tr>
				</table>
			</div>
			<!--客户信息结束  -->
		</div>
		<!--详情内容结束  -->
		
	</div>
	<script type="text/javascript">
			//隐藏详情内容
			//入口函数
			$(function(){
				$("#content").hide();	//必须在整个页面加载完成之后再隐藏  否则easyui的样式无法正确渲染			
			});
			
		//根据出租单号查询，如果存在就查询数详细信息
		function doSearch(value) {
				//alert(value);
				$.post("${ctx}/check/checkRentByRentId.action",{rentid:value},function(rentObj){
						//alert(rentObj);
						if(rentObj==null){
							$.messager.show({
								title:'提示',								
								msg:'出租单号不存在，重核对后再提交'
							});
						}else{
							//alert(rentObj.rentflag);
							if(rentObj.rentflag===1){
		    					$.messager.show({
		          					title:'提示',
		          					msg:'该出租单号相关的车辆已归还'
		          				});
		    				}else{
		    					$("#content").show();
		    					//发送请求到后台初始化页面数据   车辆信息  检查单自动生成信息  客户信息   出租单信息
		    					$.post("${ctx}/check/initData.action",{rentid:value},function(map){
		    						//设置检查单数据
		    						var check = map.check;
		    						$("#fm").form("load",check);
		    						 $('#checkid').textbox('readonly',true);
		    						 $('#rentid').textbox('readonly',true);
		    						 $('#opername').textbox('readonly',true);
		    						//设置车辆信息
		    						var car = map.car;
		    						$("#car_carnumber").html(car.carnumber);
		  							$("#car_color").html(car.color);
		  							$("#car_cartype").html(car.cartype);
		  							$("#car_price").html(car.price);
		  							$("#car_deposit").html(car.deposit);
		  							$("#car_carimg").attr({src:car.carimg.substr(1,car.carimg.length)});
		    						//设置出租单信息
		  							var rent=map.rent;
		  							$("#rent_rentid").html(rent.rentid);
		  							$("#rent_price").html(rent.price);
		  							$("#rent_begindate").html(rent.begindate);
		  							$("#rent_returndate").html(rent.returndate);
		  							$("#rent_opername").html(rent.opername);
		  							//设置客户信息
		  							var customer=map.customer;
		  							$("#customer_identity").html(customer.identity);
		  							$("#customer_custname").html(customer.custname);
		  							$("#customer_address").html(customer.address);
		  							$("#customer_phone").html(customer.phone);
		  							$("#customer_sex").html(customer.sex==1?'男':'女');
		    						
		    						
		    					},"json");
		    				}
						}					
				},"json");
		}
		
		//保存  添加检查单信息
    	function saveCheck(){
    		$('#fm').form('submit',{
                url: "${ctx}/check/addCheck.action",
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
                  		$("#content").hide();      
                }
            });
    	}
	</script>

</body>
</html>
