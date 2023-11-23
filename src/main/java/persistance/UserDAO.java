package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.DBConnection;
import domain.UserVO;

public class UserDAO {
	// 회원 가입할 때 insert할 로직
	// FIXME 현재는 테스트를 위한 static 추가함
	public int insertUser(UserVO user) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConnection.getConnection();
			String sql = "insert into user values(?, ?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getId())
			pstmt.setString(2, user.getPasswd());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getMobile());
			pstmt.setString(5, user.getEmail());
			pstmt.setInt(6, user.getStudentId());
			pstmt.executeUpdate();

			return 1;
		}

		catch (Exception ex) {
			System.out.println("insertUser() 에러 : " + ex);
			return -1;
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

	// ID로 회원 정보를 불러오는 메소드
	// FIXME 현재는 테스트를 위한 static 추가함
	public UserVO getUser(String Id) {
		// DB 연결을 위한 초기 변수 선언
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql;

		// SQL문 작성하기
		sql = "select * from user where id = ?";

		// 예외처리 시작, DB 연결 시작
		try {

			// 사용자 정보 가져옴
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Id);
			rs = pstmt.executeQuery();

			// 한개씩 가져옴
			UserVO user = new UserVO();
			while (rs.next()) {
				user.setId(rs.getString("id"));
				user.setPasswd(rs.getString("passwd"));
				user.setUserName(rs.getString("userName"));
				user.setMobile(rs.getString("mobile"));
				user.setEmail(rs.getString("email"));
				user.setStudentId(rs.getInt("studentId"));
			}
			return user;

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

	public static int getListCount() {
		// DB 연결을 위한 초기 변수 선언
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int userCount = 0;

		String sql;

		// SQL문 작성하기
		sql = "select count(*) from user";

		// 예외처리 시작, DB 연결 시작
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next())
				userCount = rs.getInt(1);

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
		return userCount;
	}

	// 정보 수정 할 때
	// FIXME 현재는 테스트를 위한 static 추가함
	// insert 참고
	// parameter로 받은 user는 update할 새로운 user
	// id가 같은 user를 찾고 그 user와 다른 값 update
	public int updateUser(UserVO user) {
		// DB 연결을 위한 초기 변수 선언
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String id = user.getId();

		String sql = "update user set passwd=?, userName=?, mobile=?, email=?, studentId=? where id=?";
		// 예외처리 시작, DB 연결 시작
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getPasswd());
			pstmt.setString(2, user.getUserName());
			pstmt.setString(3, user.getMobile());
			pstmt.setString(4, user.getEmail());
			pstmt.setInt(5, user.getStudentId());
			pstmt.setString(6, id);
			pstmt.executeUpdate();
			// 수정 성공
			return 1;

		} catch (Exception ex) {
			System.out.println("getListCount() 에러: " + ex);
			// 수정 실패
			return -1;
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

	// 로그인
	public int login(String id, String passwd) {
		// DB 연결을 위한 초기 변수 선언
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select passwd from user where id = ?";
			conn = DBConnection.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).equals(passwd)) {
					return 1; // 비밀번호 일치
				} else {
					return 0; // 비밀번호 불일치
				}
			}
			return -1; // 아이디가 없음
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; // DB 오류
	}

	// 회원 탈퇴할 때 DB의 내용을 지우는 메소드 -> 필요할까?

	// 테스트 하려면 main에서 메소드들 호출
	public static void main(String[] args) {
		/*
		String id = "jjj";
		String passwd = "1324";
		String userName = "하하하";
		String mobile = "010-7654-8753";
		String email = "sdjfad@tukorea.ac.kr";
		int studentId = 123415621;
		System.out.println("현재 DB에 있는 갯수 : " + getListCount());
		UserVO user = new UserVO();
		user.setId(id);
		user.setPasswd(passwd);
		user.setUserName(userName);
		user.setMobile(mobile);
		user.setEmail(email);
		user.setStudentId(studentId);
		
		updateUser(user);
		*/
		// UserVO user = getUser(id);
		// user.printLog();
	}
}
