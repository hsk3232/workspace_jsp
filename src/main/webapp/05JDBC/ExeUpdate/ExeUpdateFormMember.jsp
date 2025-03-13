<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
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
<meta charset="UTF-8">
<title>ExeUpdateFormMember</title>
</head>
<body>
	<h2>🧐추가할 회원 정보를 입력해 주세요🧐</h2>
	<form action = "ExeUpdateMember.jsp" method="post">
	<table>
	
		<tr> 
		<td>ID</td>
		<td><input type="text" name="id" /></td>
		</tr>
		
		<tr>
		<td>Pass</td>
		<td><input type="text" name="pass" /></td>
		</tr>
		
		<tr>
		<td>Name</td>
		<td><input type="text" name="name" /></td>
		</tr>
		
	</table>
		<input type="submit" value="입력">
	</form>
	
</body>
</html>