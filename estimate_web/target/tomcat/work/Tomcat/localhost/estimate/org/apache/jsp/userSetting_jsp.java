/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2018-08-23 06:24:06 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class userSetting_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html; charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"utf-8\">\r\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("    <title>账户设置</title>\r\n");
      out.write("    <meta name=\"keywords\" content=\"index\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("    <meta name=\"renderer\" content=\"webkit\">\r\n");
      out.write("    <meta http-equiv=\"Cache-Control\" content=\"no-siteapp\" />\r\n");
      out.write("    <link rel=\"icon\" type=\"image/png\" href=\"assets/i/favicon.png\">\r\n");
      out.write("    <link rel=\"apple-touch-icon-precomposed\" href=\"assets/i/app-icon72x72@2x.png\">\r\n");
      out.write("    <meta name=\"apple-mobile-web-app-title\" content=\"Amaze UI\" />\r\n");
      out.write("    <script src=\"http://cdn.bootcss.com/echarts/3.3.2/echarts.min.js\"></script>\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/amazeui.min.css\" />\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/amazeui.datatables.min.css\" />\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/css/app.css\">\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery-1.11.0.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body data-type=\"widgets\">\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/js/theme.js\"></script>\r\n");
      out.write("    <div class=\"am-g tpl-g\">\r\n");
      out.write("       <!-- 头部 -->\r\n");
      out.write("\t\t<header>\r\n");
      out.write("\t\t\t\t<!-- logo -->\r\n");
      out.write("\t\t\t\t<div class=\"am-fl tpl-header-logo\">\r\n");
      out.write("\t\t\t\t\t<img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/assets/img/shentie.png\" alt=\"\" style=\"height:55px ;width: 55px;margin-left:-10px ;\">\r\n");
      out.write("\t\t\t\t\t<h3 style=\"display: inline;font: '楷体';\">什贴中学教学评价系统</h3>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t<!-- 右侧内容 -->\r\n");
      out.write("\t\t\t<div class=\"tpl-header-fluid\">\r\n");
      out.write("\t\t\t\t<!-- 侧边切换 -->\r\n");
      out.write("\t\t\t\t<div class=\"am-fl tpl-header-switch-button am-icon-list\"\r\n");
      out.write("\t\t\t\t\tstyle=\"padding-top: 20px; padding-bottom: 10px\">\r\n");
      out.write("\t\t\t\t\t<span> </span>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<!-- 其它功能-->\r\n");
      out.write("\t\t\t\t<div class=\"am-fr tpl-header-navbar\">\r\n");
      out.write("\t\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t\t<!-- 欢迎语 -->\r\n");
      out.write("\t\t\t\t\t\t<li class=\"am-text-sm tpl-header-navbar-welcome\"><a\r\n");
      out.write("\t\t\t\t\t\t\thref=\"javascript:;\">欢迎您, <span>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${session._user_name }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</span>\r\n");
      out.write("\t\t\t\t\t\t</a></li>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<!-- 退出 -->\r\n");
      out.write("\t\t\t\t\t\t<li class=\"am-text-sm\"><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/loginAction_logout\"> <span\r\n");
      out.write("\t\t\t\t\t\t\t\tclass=\"am-icon-sign-out\"></span> 退出\r\n");
      out.write("\t\t\t\t\t\t</a></li>\r\n");
      out.write("\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("        </header>\r\n");
      out.write("        <!-- 侧边导航栏 -->\r\n");
      out.write("        <div class=\"left-sidebar\">\r\n");
      out.write("            <!-- 用户信息 -->\r\n");
      out.write("            <div class=\"tpl-sidebar-user-panel\">\r\n");
      out.write("                <div class=\"tpl-user-panel-slide-toggleable\">\r\n");
      out.write("                    <!-- <div class=\"tpl-user-panel-profile-picture\">\r\n");
      out.write("                        <img src=\"assets/img/user04.png\" alt=\"\">\r\n");
      out.write("                    </div> -->\r\n");
      out.write("                    <span class=\"user-panel-logged-in-text\">\r\n");
      out.write("              <i class=\"am-icon-circle-o am-text-success tpl-user-panel-status-icon\"></i>\r\n");
      out.write("             \t");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${session._user_name }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\r\n");
      out.write("          </span>\r\n");
      out.write("          <div id=\"nowClock\"></div>\r\n");
      out.write("          \r\n");
      out.write("          <script type=\"text/javascript\">\r\n");
      out.write("          \tdocument.getElementById(\"nowClock\").onload = function(){disptime()};\r\n");
      out.write("          \tfunction disptime(){\r\n");
      out.write("          \t\t//获取当前时间\r\n");
      out.write("          \t\tvar today = new Date();\r\n");
      out.write("          \t\t//获取时分秒\r\n");
      out.write("          \t\tvar hh = today.getHours();\r\n");
      out.write("          \t\tif(hh >=0 && hh < 10){\r\n");
      out.write("          \t\t\thh = \"0\" + hh;\r\n");
      out.write("          \t\t}\r\n");
      out.write("          \t\tvar mm = today.getMinutes();\r\n");
      out.write("          \t\tif(mm >=0 && mm < 10){\r\n");
      out.write("          \t\t\tmm = \"0\" + mm;\r\n");
      out.write("          \t\t}\r\n");
      out.write("          \t\tvar ss = today.getSeconds();\r\n");
      out.write("          \t\tif(ss >=0 && ss < 10){\r\n");
      out.write("          \t\t\tss = \"0\" + ss;\r\n");
      out.write("          \t\t}\r\n");
      out.write("          \t\t//设置div的内容为当前时间\r\n");
      out.write("          \t\tdocument.getElementById(\"nowClock\").innerHTML = \r\n");
      out.write("          \t\t\t\"<h2>\"+hh+\":\"+mm+\":\"+ss+\"</h2>\";\r\n");
      out.write("          \t}\r\n");
      out.write("          \tsetInterval(\"disptime()\",1000);\r\n");
      out.write("          </script>\r\n");
      out.write("                    <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/manage/userAction_toUserSetting\" class=\"tpl-user-panel-action-link\"> <span class=\"am-icon-pencil\"></span> 账号设置</a>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <!-- 菜单 -->\r\n");
      out.write("            <ul class=\"sidebar-nav\">\r\n");
      out.write("            \r\n");
      out.write("\t\r\n");
      out.write("\t\t\t\t<li class=\"sidebar-nav-link\">\r\n");
      out.write("                    <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/pageAction_toIndex\" >\r\n");
      out.write("                        <i class=\"am-icon-home sidebar-nav-link-logo\"></i> 首页\r\n");
      out.write("                    </a>\r\n");
      out.write("                </li>\r\n");
      out.write("            </ul>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <!-- 内容区域 -->\r\n");
      out.write("\t\t<div class=\"tpl-content-wrapper\">\r\n");
      out.write("\t\t\t<div class=\"row-content am-cf\">\r\n");
      out.write("\t\t\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t\t\t<div class=\"am-u-sm-12 am-u-md-12 am-u-lg-12\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"widget am-cf\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t<form class=\"am-form\" data-am-validator action=\"userAction_saveUser\"  method=\"post\">\r\n");
      out.write("\t\t\t\t\t\t\t\t  <fieldset>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t  <legend>密码修改</legend>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t  <div class=\"am-form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t    <label for=\"password\">密码：</label>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t    <input type=\"password\" id=\"password\" name=\"password\" placeholder=\"请输入6位以上的密码\" pattern=\"^\\d{6}$\" required/>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t  </div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t  <div class=\"am-form-group\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t    <label for=\"repsword\">确认密码：</label>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t    <input type=\"password\" id=\"repsword\" name=\"repsword\" placeholder=\"请与上面输入的值一致\" data-equal-to=\"#password\" required/>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t  </div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t  <input id=\"sub\" type=\"button\" name=\"Submit\" value=\"提交\" class=\"am-btn am-btn-secondary\" >\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</fieldset>\r\n");
      out.write("\t\t\t\t\t\t\t\t</form>\r\n");
      out.write("\t\t\t\t\t\t\t\t<script>\r\n");
      out.write("\t\t\t\t\t\t\t\t(function(){\r\n");
      out.write("\t\t\t\t\t\t\t\t    var sub = document.getElementById(\"sub\");\r\n");
      out.write("\t\t\t\t\t\t\t\t    //初始化移入移出事件\r\n");
      out.write("\t\t\t\t\t\t\t\t    if(sub.addEventListener){\r\n");
      out.write("\t\t\t\t\t\t\t\t        sub.addEventListener(\"click\", test);  \r\n");
      out.write("\t\t\t\t\t\t\t\t    }else if(sub.attachEvent){\r\n");
      out.write("\t\t\t\t\t\t\t\t        sub.attachEvent(\"onClick\", test);\r\n");
      out.write("\t\t\t\t\t\t\t\t    }\r\n");
      out.write("\t\t\t\t\t\t\t\t})();\r\n");
      out.write("\t\t\t\t\t\t\t\t \r\n");
      out.write("\t\t\t\t\t\t\t\tfunction test(){\r\n");
      out.write("\t\t\t\t\t\t\t\t    var password = document.getElementById(\"password\");\r\n");
      out.write("\t\t\t\t\t\t\t\t    var passwordConfirm = document.getElementById(\"repsword\");\r\n");
      out.write("\t\t\t\t\t\t\t\t    if(password.value.length < 6)\r\n");
      out.write("\t\t\t\t\t\t\t\t    \talert(\"请输入六位以上的密码\")\r\n");
      out.write("\t\t\t\t\t\t\t\t    else if(password.value != passwordConfirm.value)\r\n");
      out.write("\t\t\t\t\t\t\t\t        alert(\"对不起，您2次输入的密码不一致\");\r\n");
      out.write("\t\t\t\t\t\t\t\t    else\r\n");
      out.write("\t\t\t\t\t\t\t\t    document.forms[0].submit();\r\n");
      out.write("\t\t\t\t\t\t\t\t     \r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t</script>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("    <script src=\"assets/js/amazeui.min.js\"></script>\r\n");
      out.write("    <script src=\"assets/js/amazeui.datatables.min.js\"></script>\r\n");
      out.write("    <script src=\"assets/js/dataTables.responsive.min.js\"></script>\r\n");
      out.write("    <script src=\"assets/js/app.js\"></script>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("</html>");
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
