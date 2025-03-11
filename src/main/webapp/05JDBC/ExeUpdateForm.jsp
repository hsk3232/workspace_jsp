<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>
table {
	border-collapse: soll;
	text-align :center;
}

td, tr {
	border: 1px solid;
	padding: 4px 10px;

}

.title {text-align :center;
	background-color: skyblue;
}




</style>

<title>데이터</title>
</head>
<body>

<h2>🧐추가할 회원 정보를 입력해 주세요🧐</h2>
<form action="ExeUpdate2.jsp" method="post">
<table>
	<tr>
	<td><h3>ID</h3></td>
	<td><h3>Title</h3></td>
	<td><h3>Content</h3></td>
	</tr>
	
	<tr>
	<td><input type="text" name="id" /></td>
	<td><input type="text" name="title" /></td>
	<td><input type="text" name="content" /></td>
	</tr>
</table>
	 <input type="submit" value="입력하기" />
</form>
</body>
</html>