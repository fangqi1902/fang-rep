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
<title>饼状图</title>
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
	    	url:'${ctx}/tjfx/countCarColor.action',
	    	//请求方式
	    	type:'POST',
	    	//获取的数据类型
	    	dataType:'json',
	    	//将后台传过来的json字符串转为json对象
	    	success:function(data){
	    		 // 图表配置
	            var options = {
	                chart: {
	                	 plotBackgroundColor: null,
	                     plotBorderWidth: null,
	                     plotShadow: false,
	                     type: 'pie',  //指定图表的类型，默认是折线图（line）,bar:条形图,column:柱形图
	                },
	                title: {
	                    text: '车辆颜色比例统计'                 // 标题
	                },
	                tooltip: {
	            	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	                },
	                subtitle: {
	                    text: '数据来源--武汉尚学堂'
	                },
	                plotOptions: {
	                    pie: {
	                        allowPointSelect: true,
	                        cursor: 'pointer',
	                        dataLabels: {
                            enabled: true,
                            color: '#000000',
                            connectorColor: '#000000',
                            format: '<b>{point.name}</b>: {point.percentage:.1f} %'
	                        }
	                    }
	                },
	                series: [{ 
	                    name: '比例',                        
	                    data: data
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