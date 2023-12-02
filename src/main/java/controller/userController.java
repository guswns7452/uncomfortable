package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.UserVO;
import persistance.UserDAO;

public class userController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());

		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");

		System.out.println("[User Request] " + command);
		
		// 로그인 페이지 요청
		if (command.equals("/user/login")) {
			System.out.println("tlqkf");
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}
		
		// 회원가입
		else if (command.equals("/signup")) {
			regist(request, response);
			// FIXME jsp 부분 변경해야함.
			RequestDispatcher rd = request.getRequestDispatcher("./board/main.jsp");
			rd.forward(request, response);
		}
		// 정보 수정
		else {
			update(request, response);
			// FIXME jsp 부분 변경해야함.
			RequestDispatcher rd = request.getRequestDispatcher("./board/main.jsp");
			rd.forward(request, response);
		}

	}

	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserDAO userDAO = new UserDAO(); // userDAO 인스턴스 생성
		// TODO user form 에서 받아오는 방식
		int result = userDAO.login(request.getParameter("id"), request.getParameter("passwd"));
		if (result == 1) {
			// 로그인 성공
			PrintWriter script = response.getWriter();
			script.println("<script>");
			// 메인 페이지
			// FIXME jsp 부분 변경해야함.
			// script.println("location.href = 'main.jsp'");
			
			// 로그인 성공시 세션 등록
			HttpSession session = request.getSession(); // 에러 발생할 수도 있음. 아닐 수도.
			session.setAttribute("customer", request.getParameter("id"));
			session.setMaxInactiveInterval(1800); // 30분 동안 session 유지
			
			script.println("</script>");
		} else if (result == 0) {
			// 비밀번호 틀림
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('비밀번호가 틀립니다.')");
			script.println("history.back()");
			script.println("</script>");
		} else if (result == -1) {
			// 아이디 틀림
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('존재하지 않는 아이디입니다.')");
			script.println("history.back()");
			script.println("</script>");
		} else if (result == -2) {
			// DB 오류
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('데이터베이스 오류가 발생헀습니다.')");
			script.println("history.back()");
			script.println("</script>");
		}
	}

	public void regist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (request.getParameter("id") == null || request.getParameter("passwd") == null || request.getParameter("userName") == null || request.getParameter("mobile") == null
				|| request.getParameter("email") == null || Integer.parseInt(request.getParameter("studentId")) == 0) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('입력이 안 된 사항이 있습니다.')");
			script.println("history.back()");
			script.println("</script>");
		} else {
			UserDAO userDAO = new UserDAO();
			UserVO user = new UserVO();
			user.setId(request.getParameter("id"));
			user.setPasswd(request.getParameter("passwd"));
			user.setUserName(request.getParameter("userName"));
			user.setMobile(request.getParameter("mobile"));
			user.setEmail(request.getParameter("email"));
			user.setStudentId(Integer.parseInt(request.getParameter("studentId")));
			
			int result = userDAO.insertUser(user);
			if (result == -1) {
				// 중복 아이디 입력시
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('이미 존재하는 아이디입니다.')");
				script.println("history.back()");
				script.println("</script>");
			} else {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				// 메인으로 이동
				// FIXME jsp 부분 변경해야함.
				//script.println("location.href = 'main.jsp'");
				script.println("</script>");
			}
		}
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (request.getParameter("id") == null || request.getParameter("passwd") == null || request.getParameter("userName") == null || request.getParameter("mobile") == null
				|| request.getParameter("email") == null || Integer.parseInt(request.getParameter("studentId")) == 0) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('입력이 안 된 사항이 있습니다.')");
			script.println("history.back()");
			script.println("</script>");
		} else {
			UserDAO userDAO = new UserDAO();
			UserVO user = new UserVO();
			user.setId(request.getParameter("id"));
			user.setPasswd(request.getParameter("passwd"));
			user.setUserName(request.getParameter("userName"));
			user.setMobile(request.getParameter("mobile"));
			user.setEmail(request.getParameter("email"));
			user.setStudentId(Integer.parseInt(request.getParameter("studentId")));
			
			int result = userDAO.updateUser(user);
			if (result == -1) {
				// 중복 아이디 입력시
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('이미 존재하는 아이디입니다.')");
				script.println("history.back()");
				script.println("</script>");
			} else {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				// 메인으로 이동
				// FIXME jsp 부분 변경해야함.
				//script.println("location.href = 'main.jsp'");
				script.println("</script>");
			}
		}
	}
}
