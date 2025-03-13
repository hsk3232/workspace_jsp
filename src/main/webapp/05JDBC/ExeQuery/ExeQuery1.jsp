<%@ page import="common.JDBConnect" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
table {
	border-collapse: soll;
	text-align: center;
}

td, tr {
	border: 1px solid;
	padding: 4px 10px;
}

caption{
	padding: 5px;
	margin: 20px;
	font-size: 25px;
}


.title {
	text-align: center;
	background-color: skyblue;
}
</style>

<title>JDBC</title>
</head>
<body>
	
	<%
	JDBConnect jdbc = new JDBConnect();
	
	String sql = "SELECT num, title, content, id, postdate, visitcount FROM board where id='musthave'";
	jdbc.stmt = jdbc.con.createStatement();
	
	jdbc.rs = jdbc.stmt.executeQuery(sql);%>
	
	<table>
		<caption>🧐회원 게시 목록 조회🧐</caption>
		<tr>
			<th>num</th>
			<th>title</th>
			<th>content</th>
			<th>id</th>
			<th>postdate</th>
			<th>visitcount</th>
		</tr>
		
	
	<% 
	while(jdbc.rs.next()){ 
	String num = jdbc.rs.getString(1);  
	String title = jdbc.rs.getString(2); 
	String content = jdbc.rs.getString(3);
	String id = jdbc.rs.getString(4);
	java.sql.Date postdate = jdbc.rs.getDate("postdate"); 
	String visitcount = jdbc.rs.getString(6); 
	%>
	<tr>
	<%
	out.print(String.format(
				"<td>%s</td>"+
				"<td>%s</td>"+
				"<td>%s</td>"+ 
				"<td>%s</td>"+
				"<td>%s</td>"+
				"<td>%s</td>", num, title, content, id, postdate, visitcount)); %>
	</tr>
	
	
	<% } %>
		</table>
	<% jdbc.close(); %>
</body>
</html>