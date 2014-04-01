<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String staffName="";
Object object=session.getAttribute("staffName");
if(object!=null){
	staffName=session.getAttribute("staffName").toString();
}else{
	response.sendRedirect("login.html");
}

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>主页</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">    
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <link rel="stylesheet" type="text/css" href="extjs/resources/css/ext-all.css">
  <script type="text/javascript" charset="utf-8" src="extjs/ext-all-debug.js"></script>
  <script type="text/javascript" src="extjs/ext-lang-zh_CN.js"></script>
  <script type="text/javascript" src="index.js"></script>
  </head>
  <body>
    <div id="center" class="x-hide-display">
        <iframe id="iframeContent" width="100%" height="100%" frameborder="0" scrolling="no"></iframe>
    </div>
    <div id="south" class="x-hide-display">
        <p align="right">south - generally for informational stuff, also could be for status bar</p>
    </div>
    <div id="north" class="x-hide-display">
        <p align="right"><strong>用户名：</strong><%=staffName==null?"":staffName%>
        <%if(staffName!=null){%>
        <strong><a href="staff_logout.action">注销</a></strong>
        <%}%>
        </p>

    </div>
  </body>
</html>
