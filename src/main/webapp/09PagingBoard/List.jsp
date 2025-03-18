<%@page import="utils.BoardPage"%>
<%@page import="model1.board.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="model1.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
//DAO를 생성해 DB에 연결
BoardDAO dao = new BoardDAO(application);

//사용자가 입력한 검색 조건을 map에 저장
Map<String, Object> param = new HashMap<String, Object>();
String searchField = request.getParameter("searchField");
String searchWord = request.getParameter("searchWord");
if (searchWord != null) {
	param.put("searchField", searchField);
	param.put("searchWord", searchWord);
}

//게시물 수 확인
int totalCount = dao.selectCount(param);

//전체 페이지 수 계산
int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
int totalPage = (int)Math.ceil((double)totalCount / pageSize);

//현제 페이지 확인
int pageNum = 1;
String pageTemp = request.getParameter("pageNum");
if (pageTemp != null && !pageTemp.equals(""))
	pageNum = Integer.parseInt(pageTemp);

//목록에 출력할 게시물 범위 계산
int start = (pageNum - 1) * pageSize; // 첫 게시물 번호 (MySQL은 0부터 시작)
param.put("start", start);
param.put("pageSize", pageSize);

//게시물 목록 받기
List<BoardDTO> boardLists = dao.selectListPage(param);
//DB 연결 닫기
dao.close();
%>

<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
crossorigin="anonymous">

<style>


</style>

<meta charset="UTF-8">
<title>회원제 게시판</title>
</head>
<body>
	

	<!-- 공통 링크 -->
	<jsp:include page="../Common/Link.jsp" />
	<h2>목록 보기(List) - 현재 페이지 : <%=pageNum%> (전체 : <%=totalPage%>)</h2>
	<form method="get">
		<table border="1" width="90%">
			<tr>
				<td align="center"><select name="searchField">
						<option value="title">제목</option>
						<option value="content">내용</option>
				</select> <input type="text" name="searchWord" /> <input type="submit"
					value="검색하기" /></td>

			</tr>
		</table>
	</form>
	<!-- 게시물 목록 테이블(표) -->
	<table border="1" width="90%">
		<!-- 각 칼럼의 이름 -->
		<tr>
			<th width="10%">번호</th>
			<th width="50%">제목</th>
			<th width="15%">작성자</th>
			<th width="10%">조회수</th>
			<th width="15%">작성일</th>
		</tr>

<%
//게시물이 하나도 없을 때
if (boardLists.isEmpty()) {
%>
		<tr>
			<td colspan="5" align="center">🧐등록된 게시물이 없습니🧐</td>
		</tr>

<%
}
else {
	//게시물이 있을 때
	int virtualNum = 0;
	int countNum = 0;
	for (BoardDTO dto : boardLists) 
	{
		//virtualNum = totalCount--;
		virtualNum = totalCount - (((pageNum - 1) * pageSize) + countNum++);
%>

		<tr align="center">
			<td><%= virtualNum %></td>
			<td align="left">
				<a href="View.jsp?num=<%= dto.getNum() %>"><%= dto.getTitle() %></a>

			</td>
			<td align="center"><%=dto.getId()%></td>
			<td align="center"><%=dto.getVisitcount()%></td>
			<td align="center"><%=dto.getPostdate()%></td>
		</tr>
<%
	}
}
%>
	</table>
	<!-- 목록 하단의 [글쓰기] 버튼 -->
	<table border="1" width="90%">
		<tr align="center">
			<td>
				<%= BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, request.getRequestURI()) %>
			</td>


			<td><button type="button" onclick="location.href='Write.jsp';">글쓰기
				</button></td>
		</tr>
	</table>
</body>
</html>