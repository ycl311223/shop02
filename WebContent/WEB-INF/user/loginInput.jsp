<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Map" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
</head>
<body>
<jsp:include page="inc.jsp"/>
<form action="user.do?method=login" method="post">
<table width="600" class="thin-border" align="center">
	<tr>
	<td>用户名：</td><td><input type="text" name="username" value="${username }"/></td>
	</tr>
	
	
	<tr>
	<td>用户密码：</td><td><input type="password" name="password"/></td>
	</tr>
	
	<tr>

		<!-- <td> -->
			<%	
				
				Map<String,String> error1=(Map<String,String>)request.getAttribute("errors");
				if(error1.get("errorMsg") !=null){
					%>
					<td><%= error1.get("errorMsg")%></td>
					<%
					
				}
			%>
		<!-- </td> -->
	</tr>
	<tr>
	<td colspan="2">
		<input type="submit" value="用户登录"/><input type="reset"/>
	</td>
	</tr>
</table>
</form>
</body>
</html>