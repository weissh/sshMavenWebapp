<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String roleName=session.getAttribute("roleName").toString();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
	<title>系统管理-部门管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="extjs/resources/css/ext-all.css">
	<link rel="stylesheet" type="text/css" href="extjs/common.css">
	<script type="text/javascript" charset="utf-8" src="extjs/ext-all-debug.js"></script>
	<script type="text/javascript" src="extjs/ext-lang-zh_CN.js"></script>
	<script type="text/javascript" src="pages/system/department.js"></script>
	<script language="javaScript">
		var basePath = '<%=basePath%>';
		var roleName='<%=roleName%>';
	</script>
  </head>
  
  <body>
  </body>
</html>
