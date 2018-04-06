<%@page import="kr.co.bit.dao.MemberDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.co.bit.database.ConnectionManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ID 조회</title>
</head>
<body>

<%
	String id = request.getParameter("id");
	MemberDAO dao = new MemberDAO();
	dao.search(id);
	
	out.print(dao.search(id).get(1));
	
	
%>

</body>
</html>