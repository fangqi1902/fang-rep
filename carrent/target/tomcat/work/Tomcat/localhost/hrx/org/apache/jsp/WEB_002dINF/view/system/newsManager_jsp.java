/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-10-12 13:05:54 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.view.system;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class newsManager_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
      out.write("<html>\r\n");
      out.write("  <head>\r\n");
      out.write("    <base href=\"");
      out.print(basePath);
      out.write("\">\r\n");
      out.write("    \r\n");
      out.write("    <title>My JSP 'logInfoManager.jsp' starting page</title>\r\n");
      out.write("    \r\n");
      out.write("\t<meta http-equiv=\"pragma\" content=\"no-cache\">\r\n");
      out.write("\t<meta http-equiv=\"cache-control\" content=\"no-cache\">\r\n");
      out.write("\t<meta http-equiv=\"expires\" content=\"0\">    \r\n");
      out.write("\t<meta http-equiv=\"keywords\" content=\"keyword1,keyword2,keyword3\">\r\n");
      out.write("\t<meta http-equiv=\"description\" content=\"This is my page\">\r\n");
      out.write("\t  <link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/umeditor/themes/default/css/umeditor.css\" type=\"text/css\" rel=\"stylesheet\">\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/easyui/themes/metro/easyui.css\" />\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/icon.css\" />\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/js/jquery-1.8.0.min.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/easyui/jquery.easyui.min.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/easyui/locale/easyui-lang-zh_CN.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" charset=\"utf-8\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/umeditor/umeditor.config.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" charset=\"utf-8\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/umeditor/umeditor.min.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/umeditor/lang/zh-cn/zh-cn.js\"></script>\r\n");
      out.write("  </head>\r\n");
      out.write("  <body>\r\n");
      out.write("  \t\t<div class=\"easyui-panel\" style=\"margin-bottom: 2px;text-align: center;padding: 5px\"\r\n");
      out.write("  \t\tdata-options=\"title:'查询条件',iconCls:'icon-search',width:'100%'\">\r\n");
      out.write("  \t\t<form action=\"#\" method=\"post\" id=\"serachFrm\">\r\n");
      out.write("\t\t\t<input class=\"easyui-textbox\" name=\"title\" labelAlign=\"center\" label=\"新闻标题：\" labelPosition=\"left\" style=\"width:40%;\">  \t\t\r\n");
      out.write("\t\t\t<input class=\"easyui-textbox\" name=\"content\" labelAlign=\"center\" label=\"新闻内容：\" labelPosition=\"left\" style=\"width:40%;\">  \t\t\r\n");
      out.write("\t\t\t<div style=\"height: 5px\"></div>\r\n");
      out.write("\t\t\t<input class=\"easyui-datebox\" name=\"startDate\" labelAlign=\"center\" editable=\"false\" label=\"开始时间：\" labelPosition=\"left\" style=\"width:40%;\">  \t\t\r\n");
      out.write("\t\t\t<input class=\"easyui-datebox\" name=\"endDate\" labelAlign=\"center\" editable=\"false\" label=\"结束时间：\" labelPosition=\"left\" style=\"width:40%;\">  \t\t\r\n");
      out.write("\t\t\t<div style=\"height: 5px\"></div>\r\n");
      out.write("\t\t\t<div style=\"text-align: center;\">\r\n");
      out.write("\t\t\t\t\t<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" onclick=\"doSearch()\" iconCls=\"icon-search\">查询</a>\r\n");
      out.write("\t\t\t\t\t<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" onclick=\"javascript:$('#serachFrm').form('clear')\" iconCls=\"icon-reload\">重置</a>\r\n");
      out.write("\t\t\t</div>  \t\t\r\n");
      out.write("  \t\t</form>\r\n");
      out.write("  \t\t</div>\r\n");
      out.write("  \r\n");
      out.write("  \t\t<table id=\"dg\" class=\"easyui-datagrid\"></table>\r\n");
      out.write("  \t\t <div id=\"toolbar\">\r\n");
      out.write("        <a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" iconCls=\"icon-add\" plain=\"true\" onclick=\"newNews()\">添加新闻</a>\r\n");
      out.write("        <a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" iconCls=\"icon-edit\" plain=\"true\" onclick=\"editNews()\">修改新闻</a>\r\n");
      out.write("        <a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" iconCls=\"icon-remove\" plain=\"true\" onclick=\"destroyNews()\">删除新闻</a>\r\n");
      out.write("    \t</div> \t\r\n");
      out.write("    \t    <div id=\"dlg\" class=\"easyui-dialog\" style=\"width:1000px\" data-options=\"closed:true,modal:true,border:'thin',buttons:'#dlg-buttons'\">\r\n");
      out.write("        <form id=\"fm\" method=\"post\" novalidate style=\"margin:0;padding:20px 50px\">\r\n");
      out.write("            <div style=\"margin-bottom:10px\">\r\n");
      out.write("            \t<input name=\"id\" id=\"id\" type=\"hidden\">\r\n");
      out.write("            \t<input name=\"opername\"  type=\"hidden\">\r\n");
      out.write("            \t<input name=\"createtime\" id=\"createtime\" type=\"hidden\">\r\n");
      out.write("                <input name=\"title\" class=\"easyui-textbox\" required=\"true\" label=\"新闻标题:\" style=\"width:100%\">\r\n");
      out.write("            </div>\r\n");
      out.write("            <div style=\"margin-bottom:10px\">\r\n");
      out.write("            <label class=\"easyui-label\">新闻内容:</label>\r\n");
      out.write("\t            <div id=\"content\" name=\"content\" style=\"width:100%;height:240px;\">\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </form>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div id=\"dlg-buttons\">\r\n");
      out.write("        <a href=\"javascript:void(0)\" class=\"easyui-linkbutton c6\" iconCls=\"icon-ok\" onclick=\"saveNews()\" style=\"width:90px\">保存</a>\r\n");
      out.write("        <a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" iconCls=\"icon-cancel\" onclick=\"javascript:$('#dlg').dialog('close')\" style=\"width:90px\">取消</a>\r\n");
      out.write("    </div>\r\n");
      out.write("  \r\n");
      out.write("  <script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("  \t$(\"#dg\").datagrid({\r\n");
      out.write("  \t\ttitle:'公告列表',//标题\r\n");
      out.write("  \t\twidth:'100%',//宽度\r\n");
      out.write("  \t\theight:'340px',//高度\r\n");
      out.write("  \t\ticonCls:'icon-save',//图标\r\n");
      out.write("  \t\tpagination:true,//是否打开分页\r\n");
      out.write("  \t\tfitColumns:true,//是否自动分配列宽\r\n");
      out.write("  \t\tsingleSelect:true,//是否支持单行选中\r\n");
      out.write("  \t\trownumbers:true,//是否显示行号\r\n");
      out.write("  \t\turl:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/news/queryAllNews.action',//获取数据的地址\r\n");
      out.write("  \t\ttoolbar:'#toolbar',//打开工具栏\r\n");
      out.write("  \t\tpageSize:10,//默认每页显示的记录数\r\n");
      out.write("  \t\tpageNumber:1,//加载时的页码\r\n");
      out.write("  \t\tcolumns:[[\r\n");
      out.write("  \t\t          {field:'id',title:'编号',width:100,align:'center'},\r\n");
      out.write("  \t\t          {field:'title',title:'新闻标题',width:100,align:'center'},\r\n");
      out.write("  \t\t          {field:'content',title:'新闻内容',width:100,align:'center'},\r\n");
      out.write("  \t\t          {field:'createtime',title:'发布时间',width:100,align:'center'},\r\n");
      out.write("  \t\t          {field:'opername',title:'发布人',width:100,align:'center'},\r\n");
      out.write("  \t\t          ]]\r\n");
      out.write("  \t});  \r\n");
      out.write("  \t//查询\r\n");
      out.write("  \tfunction doSearch(){\r\n");
      out.write("  \t\t//序列化表格数据\r\n");
      out.write("  \t\tvar data=$(\"#serachFrm\").serialize();\r\n");
      out.write("  \t\t$(\"#dg\").datagrid({\r\n");
      out.write("\t\t\turl:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/news/queryAllNews.action?'+data\r\n");
      out.write("\t\t});\r\n");
      out.write("  \t}\r\n");
      out.write("  \t\r\n");
      out.write("  \t var url;\r\n");
      out.write("     function newNews(){\r\n");
      out.write("         $('#dlg').dialog('open').dialog('center').dialog('setTitle','添加新闻');\r\n");
      out.write("         $('#fm').form('clear');\r\n");
      out.write("         url = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/news/addNews.action';\r\n");
      out.write("     }\r\n");
      out.write("     function editNews(){\r\n");
      out.write("     \t//得到当前用户的选中行的数据  \r\n");
      out.write("         var row = $('#dg').datagrid('getSelected');\r\n");
      out.write("         $('#fm').form('clear');\r\n");
      out.write("         if (row){\r\n");
      out.write("             $('#dlg').dialog('open').dialog('center').dialog('setTitle','修改新闻');\r\n");
      out.write("             $('#fm').form('load',row);\r\n");
      out.write("             $(\"#content\").html(\"\");//设置内容为空\r\n");
      out.write("             $('#content').html(row.content);//把新闻内容回显\r\n");
      out.write("             url = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/news/updateNews.action';\r\n");
      out.write("         }else{\r\n");
      out.write("         \t  $.messager.show({\r\n");
      out.write("                   title: '提示',\r\n");
      out.write("                   msg: '请选中操作行'\r\n");
      out.write("               });\r\n");
      out.write("         }\r\n");
      out.write("     }\r\n");
      out.write("     function saveNews(){\r\n");
      out.write("         $('#fm').form('submit',{\r\n");
      out.write("             url: url,\r\n");
      out.write("             onSubmit: function(){\r\n");
      out.write("                 return $(this).form('validate');\r\n");
      out.write("             },\r\n");
      out.write("             success: function(result){\r\n");
      out.write("             \tvar res=eval('('+result+')');\r\n");
      out.write("                 $('#dlg').dialog('close');        // close the dialog\r\n");
      out.write("                 $('#dg').datagrid('reload');    // reload the News data\r\n");
      out.write("                 $.messager.show({\r\n");
      out.write("                     title: '提示',\r\n");
      out.write("                     msg: res.msg\r\n");
      out.write("                 });\r\n");
      out.write("             }\r\n");
      out.write("         });\r\n");
      out.write("     }\r\n");
      out.write("  \t\r\n");
      out.write("  \t\r\n");
      out.write("  \t\r\n");
      out.write("  \t\r\n");
      out.write("  \t//删除信息\r\n");
      out.write("  \tfunction destroyNews(){\r\n");
      out.write("  \t\tvar row=$('#dg').datagrid('getSelected');\r\n");
      out.write("  \t\tif(row){\r\n");
      out.write("  \t\t\t$.messager.confirm('提示','你确定要删除这条公告吗？',function(r){\r\n");
      out.write("  \t\t\t\tif(r){\r\n");
      out.write("  \t\t\t\t\t$.post('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/news/deleteNews.action',{id:row.id},function(result){\r\n");
      out.write("  \t\t\t\t\t\t$.messager.show({\r\n");
      out.write("  \t\t\t\t\t\t\ttitle:'提示',\r\n");
      out.write("  \t\t\t\t\t\t\tmsg:result.msg\r\n");
      out.write("  \t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t $('#dg').datagrid('reload');//刷新界面 \t\t\t\t\t\t\r\n");
      out.write("  \t\t\t\t\t},'json');\r\n");
      out.write("  \t\t\t\t}\r\n");
      out.write("  \t\t\t});\r\n");
      out.write("  \t\t}else{\r\n");
      out.write("  \t\t\t$.messager.show({\r\n");
      out.write("\t\t\t\t\ttitle:'提示',\r\n");
      out.write("\t\t\t\t\tmsg:'请选中行',\r\n");
      out.write("\t\t\t\t\tshowType:'show'\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("  \t\t}\r\n");
      out.write("  \t}\r\n");
      out.write("  \t\r\n");
      out.write("    //初始化富文本编辑器\r\n");
      out.write("\t //实例化编辑器\r\n");
      out.write("\tvar um = UM.getEditor('content');\r\n");
      out.write("  \t\r\n");
      out.write("  </script>\r\n");
      out.write("  \r\n");
      out.write("  </body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
