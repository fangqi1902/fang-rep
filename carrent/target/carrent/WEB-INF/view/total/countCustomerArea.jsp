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

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	 <script type="text/javascript" src="${ctx}/resources/js/jquery-1.8.0.min.js"></script>
</head>

<body>
	<!-- 显示图表的容器 -->
	<div id="container" ></div>
	<!-- 引入higcharts的js文件 -->
	    <script src="highcharts/highcharts.js"></script>
	    <script type="text/javascript">
	    $.ajax({
	    	//请求地址
	    	url:'${ctx}/tjfx/addressColumnChart.action',
	    	//请求方式
	    	type:'POST',
	    	//获取的数据类型
	    	dataType:'json',
	    	//将后台传过来的json字符串转为json对象
	    	success:function(data){
	    		 // 图表配置
	            var options = {
	                chart: {
	                    type: 'column'                          //指定图表的类型，默认是折线图（line）,bar:条形图,column:柱形图
	                },
	                title: {
	                    text: '客户地区图表'                 // 标题
	                },
	                subtitle: {
	                    text: '数据来源--武汉尚学堂'
	                },
	                xAxis: {
	                    categories: data.categories   // x 轴分类
	                },
	                yAxis: {
	                    title: {
	                        text: '客户数量(个)'                // y 轴标题
	                    }
	                },
	                series: [{                              // 数据列
	                    name: '地区',                        // 数据列名
	                    data: data.data1                     // 数据
	                }]
	            };
	            // 图表初始化函数
	            var chart = Highcharts.chart('container', options);
	    	},
	    	 complete:function(xhr,textStatus){
	    		 console.log('finish');
	    	 }
	    });
	    </script>
</body>
</html>
