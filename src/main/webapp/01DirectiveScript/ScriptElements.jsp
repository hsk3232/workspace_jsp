<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%!public int add(int num1, int num2) {
    	return num1 +num2;
    }
    public int multipl(int num1, int num2){
    	return num1*num2;
    }
    public double div(double num1, double num2){
    	return num1/num2;
    }
    public int minus(int num1, int num2){
    	return num1-num2;
    }
    %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<head>
<title>스크립트 요소</title>
</head>
</head>
<body>
<%
int result = add(10, 20);
int num1 = 100;
int num2 = 50;

try{
	Integer.parseInt(request.getParameter("num1"));
	Integer.parseInt(request.getParameter("num2"));
} catch(Exception e){		
}
%>

	덧셈 결과 1 : <%= result %> <br /> 
	덧셈 결과 2 : <%= add(30, 40) %><br /> 
	덧셈 결과 3 : <%= add(num1, num2) %><br /> <br /> 
	곱셈 결과 : <%= multipl(num1, num2) %><br /> 
	뺄셈 결과 : <%= minus(num1, num2) %><br /> 
	나눗셈 결과 : <%= div(num1, num2) %><br /> <br /> 

<%
	out.println("덧셈 결과 1 : " + result + "<br />");
	out.println("덧셈 결과 2 : " + add(30, 40) + "<br /><br />");
%>

<%
	out.print("덧셈 결과 1 : " + result + "<br />");
	out.print("덧셈 결과 2 : " + add(30, 40) + "<br />");
%>

<!-- HTML 파일 상에서 볼 때 println으로 쓰는 것은 화면 상에 줄 바꿈이 아니라, 소스보기에서 줄 바꿈임 -->
	

</body>
</html>