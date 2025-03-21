<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyServlet04.jsp</title>
</head>
<body>
	<h2>어노테이션으로 매핑 후 Servlet에서 직접 출력하기</h2>
	
		<ul>
		<li> <a href="<%= request.getContextPath() %>/12Servlet/MyServlet04.do"> 표 보기</a> </li>
	</ul>
	<strong>
	<table border="1">
		
		<tr>
			<th>번호</th><th>나라</th><th>수도</th>
		</tr>
		<tr>
			<c:forEach begin="0" end="3" var="i" step="1" >
			<td>${ i }</td>
			</c:forEach>
			<td>${ country }</td>
			<td>${ capital }</td>		
		</tr>
	</table>
	</strong>
</body>
</html>