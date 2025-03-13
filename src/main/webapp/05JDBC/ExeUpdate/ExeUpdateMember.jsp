<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 추가</title>
</head>
<body>
	
	<%
	String id = request.getParameter("id");
	String pass = request.getParameter("pass");
	String name = request.getParameter("name");
	
	JDBConnect jdbc = new JDBConnect();
	
	String sql = "insert into member (id,pass, name) VALUES (?, ?, ?)";
	
	PreparedStatement psmt = jdbc.con.prepareStatement(sql);
	psmt.setString(1, id);
	psmt.setString(2, pass);
	psmt.setString(3, name);

	int inResult = psmt.executeUpdate();
	out.println(inResult + "개의 정보가 입력되었습니다.");

	jdbc.close();
	%>
</body>
</html>