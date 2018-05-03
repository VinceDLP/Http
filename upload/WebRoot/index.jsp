<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <body>
  
  <form action="servlet/Upload" method="post" enctype="multipart/form-data">
  <input type="file" name="file"><br>  
   <input type="submit" value="submit"><br>  
  </form>
  </body>
</html>
