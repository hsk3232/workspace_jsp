<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="common.Person"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL - set 2</title>
</head>
<body>
	<h4>List 컬랙션 이용하기</h4>
	<%
	ArrayList<Person> pList = new ArrayList<Person>();
	pList.add(new Person("성삼문", 55));
	pList.add(new Person("박팽년", 60));
	%>

	<c:set var="personList" value="<%= pList %>" scope="request" />
	<ul>
		<li>이름 : ${ requestScope.personList[0].name }</li>
		<li>나이 : ${ personList[0].age }</li>
	</ul>

	<h4>Map 컬랙션 이용하기</h4>

	<%
	Map<String, Person> pMap = new HashMap<String, Person>();
	pMap.put("personArgs1", new Person("홍지민", 33));
	pMap.put("personArgs2", new Person("이개", 24));
	%>
	<c:set var="personMap" value="<%= pMap %>" scope="request" />
	<ul>
		<li>이름 : ${ requestScope.personMap.personArgs2.name }</li>
		<li>나이 : ${ personMap.personArgs2.age }</li>

	</ul>

</body>
</html>