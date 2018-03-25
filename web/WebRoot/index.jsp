<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <body>
  <form action = "servlet/Myservlet" method = "get">
  editText:<input type = "text" name = "editText"><br>
  <input type = "submit" value = "send">
  </form>
  </body>
</html>
