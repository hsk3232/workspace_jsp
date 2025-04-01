package model2.mvcboard;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.JDBConnect;
import model1.board.BoardDTO;


public class MVCBoardDAO extends JDBConnect {
	public MVCBoardDAO() {
		super();
	}
	
	// 검색 조건에 맞는 게시물의 개수를 반환
		public int selectCount(Map<String, Object> map) {
			// 결과 (게시물 수)를 담을 변수
			int totalCount = 0;

			// 게시물 수를 얻어오는 쿼리문 작성
			String query = "SELECT COUNT(*) FROM mvcboard";
			if (map.get("searchWord") != null) {
				query += " WHERE " + map.get("searchField") + " " 
			+ " LIKE '%" + map.get("searchWord") + "%'";
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
		public List<MVCBoardDTO> selectListPage(Map<String, Object> map) {
			List<MVCBoardDTO> board = new Vector<MVCBoardDTO>();

			String query = "select * from mvcboard ";
			
			if (map.get("searchWord") != null){
				query += " WHERE " + map.get("searchField")
				+ " LIKE '%" + map.get("searchWord") + "%' ";
			}
			
			query += " ORDER BY idx DESC limit ?,? ";
			
			try {
				psmt = con.prepareStatement(query);
				psmt.setInt(1, (int)map.get("start"));
				psmt.setInt(2, (int)map.get("pageSize"));
				rs = psmt.executeQuery();

				while (rs.next()) {
					MVCBoardDTO dto = new MVCBoardDTO();

					dto.setIdx(rs.getString(1));
					dto.setName(rs.getString(2));
					dto.setTitle(rs.getString(3));
					dto.setContent(rs.getString(4));
					dto.setPostdate(rs.getDate(5));
					dto.setOfile(rs.getString(6));
					dto.setSfile(rs.getString(7));
					dto.setDowncount(rs.getInt(8));
					dto.setPass(rs.getString(9));
					dto.setVisitcount(rs.getInt(10));

					board.add(dto);
				}
			} catch (Exception e) {
				System.out.println("게시물 조회 중 예외 발생");
				e.printStackTrace();
			}
			return board;
		}
		
		public int insertWrite(MVCBoardDTO dto) {
			int result = 0;

			try {
				// insert 쿼리문 작성
				String query = "INSERT INTO mvcboard (name, title, content, ofile, sfile, pass) "+ " VALUES (?, ?, ?, ?, ?, ?)";

				psmt = con.prepareStatement(query);
				psmt.setString(1, dto.getName());
				psmt.setString(2, dto.getTitle());
				psmt.setString(3, dto.getContent());
				psmt.setString(4, dto.getOfile());
				psmt.setString(5, dto.getSfile());
				psmt.setString(6, dto.getPass());

				result = psmt.executeUpdate();
			} catch (Exception e) {
				System.out.println("게시물 입력 중 예외 발생");
				e.printStackTrace();
			}
			return result;
		}
		
		public MVCBoardDTO selectView(String idx) {
			MVCBoardDTO dto = new MVCBoardDTO();

			// 쿼리문 준비
			String query = "SELECT* FROM mvcboard WHERE idx=?";

			try {
				psmt = con.prepareStatement(query);
				// 인파라미터를 일련번호로 설정
				psmt.setString(1, idx);
				// 쿼리실행
				rs = psmt.executeQuery();

				// 결과 처리
				if (rs.next()) {
					dto.setIdx(rs.getString(1));
					dto.setName(rs.getString(2));
					dto.setTitle(rs.getString(3));
					dto.setContent(rs.getString(4));
					dto.setPostdate(rs.getDate(5));
					dto.setOfile(rs.getString(6));
					dto.setSfile(rs.getString(7));
					dto.setDowncount(rs.getInt(8));
					dto.setPass(rs.getString(9));
					dto.setVisitcount(rs.getInt(10));
				}
			} catch (Exception e) {
				System.out.println("게시물 상세보기 중 예외 발생");
				e.printStackTrace();
			}
			return dto;
		}
		
		// 지정한 게시물의 조회수를 1 증가 시킵니다.
		public void updateVisitCount(String idx) {
			// 퀴리문 준비
			String query = "UPDATE mvcboard SET " + " visitcount=visitcount+1 " + " where idx=?";

			try {
				psmt = con.prepareStatement(query);
				// 인파라미터를 일련번호로 설정
				psmt.setString(1, idx);
				// 쿼리 실행
				psmt.executeUpdate();
			} catch (Exception e) {
				System.out.println("게시물 조회수 증가 중 예외 발생");
				e.printStackTrace();
			}
		}
		public void downCountPlus(String idx) {
			// 퀴리문 준비
			String query = "UPDATE mvcboard SET " + " downcount=downcount+1 " + " where idx=?";

			try {
				psmt = con.prepareStatement(query);
				// 인파라미터를 일련번호로 설정
				psmt.setString(1, idx);
				// 쿼리 실행
				psmt.executeUpdate();
			}
			catch (Exception e) {}
		}
		
		
}
