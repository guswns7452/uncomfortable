package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.DBConnection;
import domain.BoardVO;

public class BoardDAO {
	
	// DB에 글을 올리는 메소드
	// FIXME 현재는 테스트를 위한 static 추가함
	public static void insertBoard(BoardVO board) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConnection.getConnection();
			String sql = "insert into board values(?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getBoardNumber());
			pstmt.setString(2, board.getId());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, board.getLikeNumber());
			pstmt.setInt(5, board.getDislikeNumber());
			pstmt.executeUpdate();
		}

		catch (Exception ex) {
			System.out.println("insertBoard() 에러 : " + ex);
		}

		finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
	}

	// 모든 글을 가져오는 그런 메소드
	// FIXME 현재는 테스트를 위한 static 추가함
	public static ArrayList<BoardVO> getBoardList() {
		// DB 연결을 위한 초기 변수 선언
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 전체 board 갯수 가져오기.
		int total_record = getListCount();

		String sql;

		// SQL문 작성하기
		sql = "select * from board";
		
		// board 글을 객체에 담기위한 list들을 생성함.
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();

		// 예외처리 시작, DB 연결 시작
		try {
			// 모든 글 정보 가져옴
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// 한개씩 가져옴
			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoardNumber(rs.getInt("boardNumber"));
				board.setId(rs.getString("id"));
				board.setContent(rs.getString("content"));
				board.setLikeNumber(rs.getInt("likeNumber"));
				board.setDislikeNumber(rs.getInt("dislikeNumber"));
				list.add(board);
			}
			return list;
			
		} catch (Exception ex) {
			System.out.println("getListCount() 에러: " + ex);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return null;
	}

	
	// list 개수를 알아오는 그런 변수 -> 얘가 중요한건 아니고 예시입니다.
	public static int getListCount() {
		// DB 연결을 위한 초기 변수 선언
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int boardCount = 0;

		String sql;

		// SQL문 작성하기
		sql = "select count(*) from board";

		// 예외처리 시작, DB 연결 시작
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next())
				boardCount = rs.getInt(1);

		} catch (Exception ex) {
			System.out.println("getListCount() 에러: " + ex);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return boardCount;
	}
	
	public static ArrayList<BoardVO> getListTop() {
		// DB 연결을 위한 초기 변수 선언
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql;
		
		// board 글을 객체에 담기위한 list들을 생성함.
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();

		// SQL문 작성하기 -> 좋아요 수와 싫어요 수의 차이를 내림차순으로
		sql = "select * from board order by (likeNumber-dislikeNumber) desc LIMIT 10";

		// 예외처리 시작, DB 연결 시작
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoardNumber(rs.getInt("boardNumber"));
				board.setId(rs.getString("id"));
				board.setContent(rs.getString("content"));
				board.setLikeNumber(rs.getInt("likeNumber"));
				board.setDislikeNumber(rs.getInt("dislikeNumber"));
				list.add(board);
			}
			return list;

		} catch (Exception ex) {
			System.out.println("getListTop() 에러: " + ex);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return null;
	}
	
	public static void delete(int deleteBoardNumber) {
		// DB 연결을 위한 초기 변수 선언
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql;
		
		// boardNumber로 삭제함
		sql = "delete * from board where boardNumber = ?";

		// 예외처리 시작, DB 연결 시작
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (deleteBoardNumber));
			pstmt.executeUpdate();

		} catch (Exception ex) {
			System.out.println("delete() 에러: " + ex);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
	}

	
	public void like(int boardNumber) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql;
		
		// boardNumber로 현재 좋아요 수 불러옴
		sql = "select likeNumber from board where boardNumber = ?";

		// 예외처리 시작, DB 연결 시작
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (boardNumber));
			rs = pstmt.executeQuery();
			rs.next();
			
			int likeNumber = rs.getInt(1);
			
			// 업데이트 실행
			sql = "update board set likeNumber = ? where boardNumber = ?";
			
			pstmt.close();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (likeNumber+1));
			pstmt.setInt(2, (boardNumber));
			pstmt.executeUpdate();

		} catch (Exception ex) {
			System.out.println("delete() 에러: " + ex);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
	}
	
	public void dislike(int boardNumber) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql;
		
		// boardNumber로 현재 좋아요 수 불러옴
		sql = "select dislikeNumber from board where boardNumber = ?";

		// 예외처리 시작, DB 연결 시작
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (boardNumber));
			rs = pstmt.executeQuery();
			rs.next();
			
			int dislikeNumber = rs.getInt(1);
			
			// 업데이트 실행
			sql = "update board set dislikeNumber = ? where boardNumber = ?";
			
			pstmt.close();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (dislikeNumber+1));
			pstmt.setInt(2, (boardNumber));
			pstmt.executeUpdate();

		} catch (Exception ex) {
			System.out.println("like() 에러: " + ex);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println("현재 DB에 있는 갯수 : " + getListCount());
		
		ArrayList<BoardVO> boards = getListTop();
		for(BoardVO board : boards) {
			board.printLog();
		}
	}

}
