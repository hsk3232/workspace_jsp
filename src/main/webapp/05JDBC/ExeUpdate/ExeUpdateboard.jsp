<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
</head>
<body>
	
	<%
	String id = request.getParameter("id");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	JDBConnect jdbc = new JDBConnect();
	
	String sql = "INSERT INTO board (id, title, content, visitcount) VALUES (?, ?, ?, 0)";
	
	PreparedStatement psmt = jdbc.con.prepareStatement(sql);
	psmt.setString(1, id);
	psmt.setString(2, title);
	psmt.setString(3, content);

	int inResult = psmt.executeUpdate();
	out.println(inResult + "개의 게시글이 입력되었습니다.");

	jdbc.close();
	%>
</body>
</html>