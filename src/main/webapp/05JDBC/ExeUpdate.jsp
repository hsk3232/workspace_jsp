<%@page import="java.sql.PreparedStatement"%>
<%@page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JDBC</title></head>
<body>
	<h2>회원 추가 테스트(executeUpdate() 사용)</h2>
	 <%
	 JDBConnect jdbc = new JDBConnect();
	 
	 String id = "test1";
	 String pass = "1111";
	 String name = "테스트1회원";
	 
	 String sql = "INSERT INTO member (id, pass, name) VALUES (?, ?, ?)";
	 //필드명은 가급적 ?보다는 필드명을 적어 주는 것이 오류 적어서 좋다.
	 PreparedStatement psmt = jdbc.con.prepareStatement(sql);
	 psmt.setString(1, id);
	 psmt.setString(2, pass);
	 psmt.setString(3, name);
	 
	 int inResult = psmt.executeUpdate();
	 out.println(inResult + "행이 입력되었습니다.");
	 
	 jdbc.close();
	 %>

</body>
</html>