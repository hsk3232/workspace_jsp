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
#in{
height: 200px

}
textarea {
    width: 98%;
    height: 190px;
    border: 1px solid;
    border-color: black;
    resize: none;
  }


.title {text-align :center;
	background-color: skyblue;
}

</style>


<title>board 입력</title>
</head>
<body>
<h2>✏️새로운 글을 올려 주세요✏️</h2>
	<form action = "ExeUpdateboard.jsp" method="post">
	<table>
	
		<tr> 
		<td>Title</td>
		<td><input type="text" name="title" /></td>
		</tr>
		
		<tr>
		<td>Content</td>
		<td id="in">
		<textarea rows="10" cols="10" name="content"></textarea>
		</td>
		</tr>
		
		<tr>
		<td>ID</td>
		<td><input type="text" name="id" /></td>
		</tr>
		
	</table>
		<input type="submit" value="입력">
	</form>
</body>
</html>