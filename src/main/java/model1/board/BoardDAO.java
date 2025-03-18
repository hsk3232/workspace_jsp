package model1.board;

import jakarta.servlet.ServletContext;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import common.JDBConnect;

public class BoardDAO extends JDBConnect {
	public BoardDAO(ServletContext application) {
		super(application);
	}

	// 검색 조건에 맞는 게시물의 개수를 반환
	public int selectCount(Map<String, Object> map) {
		// 결과 (게시물 수)를 담을 변수
		int totalCount = 0;

		// 게시물 수를 얻어오는 쿼리문 작성
		String query = "SELECT COUNT(*) FROM board";
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " " + " LIKE '%" + map.get("searchWord") + "%'";
		}

		try {
			// 쿼리문 생성
			stmt = con.createStatement();
			// 쿼리 실행
			rs = stmt.executeQuery(query);
			// 커서를 첫 번째 행으로 이동
			rs.next();
			// 첫 번째 컬럼 값을 가져옴
			totalCount = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("게시물 수를 구하는 중 예외 발생");
			e.printStackTrace();
		}

		return totalCount;
	}

	// 검색 조건에 맞는 게시물 목록을 반환
	public List<BoardDTO> selectList(Map<String, Object> map) {
		List<BoardDTO> bbs = new Vector<BoardDTO>();

		String query = "SELECT * FROM board ";
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " " + " LIKE '%" + map.get("searchWord") + "%' ";
		}

		query += " ORDER BY num DESC ";

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				BoardDTO dto = new BoardDTO();

				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString("visitcount"));

				bbs.add(dto);
			}
		} catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		return bbs;
	}

	public List<BoardDTO> selectListPage(Map<String, Object> map) {
		List<BoardDTO> bbs = new Vector<BoardDTO>();

		String query = "SELECT * FROM board ";

		if (map.get("searchWord") != null) {

			query += " WHERE " + map.get("searchField") 
					+ " LIKE '%" + map.get("searchWord") + "%' ";
		}
			query += " ORDER BY num DESC limit ?,? ";

		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, (int)map.get("start"));
			psmt.setInt(2, (int)map.get("pageSize"));

			rs = psmt.executeQuery();

			// 결과 처리

			while (rs.next()) {
					BoardDTO dto = new BoardDTO();
					dto.setNum(rs.getString("num"));
					dto.setTitle(rs.getString("title"));
					dto.setContent(rs.getString("content"));
					dto.setPostdate(rs.getDate("postdate"));
					dto.setId(rs.getString("id"));
					dto.setVisitcount(rs.getString("visitcount"));

				bbs.add(dto);
			}

		} catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		return bbs;
	}

	public int insertWrite(BoardDTO dto) {
		int result = 0;

		try {
			// insert 쿼리문 작성
			String query = "INSERT INTO board (title, content, id, visitcount) " + " VALUES (?, ?, ?, 0)";

			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getId());

			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}

	// 지정한 게시물을 찾아 내용을 반환
	public BoardDTO selectView(String num) {
		BoardDTO dto = new BoardDTO();

		// 쿼리문 준비
		String query = "SELECT B.*, M.name " + " FROM member M INNER JOIN board B " + " ON M.id=B.id " + " WHERE num=?";

		try {
			psmt = con.prepareStatement(query);
			// 인파라미터를 일련번호로 설정
			psmt.setString(1, num);
			// 쿼리실행
			rs = psmt.executeQuery();

			// 결과 처리
			if (rs.next()) {
				dto.setNum(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString(6));
				dto.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			System.out.println("게시물 상세보기 중 예외 발생");
			e.printStackTrace();
		}
		return dto;
	}

	// 지정한 게시물의 조회수를 1 증가 시킵니다.
	public void updateVisitCount(String num) {
		// 퀴리문 준비
		String query = "UPDATE board SET " + " visitcount=visitcount+1 " + " where num=?";

		try {
			psmt = con.prepareStatement(query);
			// 인파라미터를 일련번호로 설정
			psmt.setString(1, num);
			// 쿼리 실행
			psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();
		}
	}

	// 게시물을 수정하는 메서드
	public int updateEdit(BoardDTO dto) {
		int result = 0;

		try {
			// 쿼리문 템플릿
			String query = "UPDATE board SET title=?, content=? WHERE num=?";

			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getNum());

			result = psmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("게시물 수정 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}

	public int deletePost(BoardDTO dto) {
		int result = 0;

		try {
			// 쿼리문 템플릿
			String query = "DELETE FROM board WHERE num=?";

			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getNum());
			result = psmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("게시물 삭제 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}

}
