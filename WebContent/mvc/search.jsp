<%@page import="kr.co.bit.vo.MemberVO"%>
<%@page import="java.util.ArrayList"%>
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
	
	String id = (String)request.getAttribute("id");
	MemberDAO dao = new MemberDAO();
	
	ArrayList<MemberVO> list = dao.search(id);
	
	out.print(list.get(0).getId());
	
	
%>

</body>
</html>