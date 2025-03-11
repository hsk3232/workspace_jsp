<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String id = request.getParameter("id");
String title = request.getParameter("title");
String content = request.getParameter("content");

JDBConnect jdbc = new JDBConnect();


String sql = "INSERT INTO board (id, title, content, visitcount) VALUES (?, ?, ?, 0)";
//필드명은 가급적 ?보다는 필드명을 적어 주는 것이 오류 적어서 좋다.
PreparedStatement psmt = jdbc.con.prepareStatement(sql);
psmt.setString(1, id);
psmt.setString(2, title);
psmt.setString(3, content);

int inResult = psmt.executeUpdate();
out.println(inResult + "행이 입력되었습니다.");

jdbc.close();

%>

</body>
</html>