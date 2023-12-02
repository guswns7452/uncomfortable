package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardVO;
import domain.UserVO;
import persistance.BoardDAO;

public class boardController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());

		System.out.println("[board Request] " + command);

		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");

		// 글 등록하기 메소드
		if (command.equals("/board/write.do")) {// 글 등록하기
			requestBoardWrite(request);
			// FIXME jsp 부분 변경해야함.
			RequestDispatcher rd = request.getRequestDispatcher("/");
			rd.forward(request, response);
			return ;
		}
		// 글 조회하기
		else if (command.equals("/board")) {
			try {
				UserVO user = (UserVO) request.getSession().getAttribute("user");
				System.out.println(user.getId());
			} catch (NullPointerException e) {
				System.out.println("로그인 상태가 아닙니다.");
			}

			requestBoardRead(request);
			// FIXME jsp 부분 변경해야함.
			RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
			rd.forward(request, response);
			return ;
		}

		// 좋아요 요청
		if (command.equals("/board/like.do")) {
			like(request);
			
			RequestDispatcher rd = request.getRequestDispatcher("/board");
			rd.forward(request, response);
			
		}

		// 싫어요 요청
		if (command.equals("/board/dislike.do")) {
			dislike(request);
			
			RequestDispatcher rd = request.getRequestDispatcher("/board");
			rd.forward(request, response);
			
		}

		// top10 글 출력하기
		else if (command.equals("/board/top")) {
			requestBoardTop(request);
			// FIXME jsp 부분 변경해야함.
			RequestDispatcher rd = request.getRequestDispatcher("/top.jsp");
			rd.forward(request, response);
		}
		// 글 삭제하기
		else if (command.equals("/board/delete")) {
			requestBoardDelete(request);
			// FIXME jsp 부분 변경해야함.
			RequestDispatcher rd = request.getRequestDispatcher("./board/delete.jsp");
			rd.forward(request, response);
		}

	}

	public void requestBoardWrite(HttpServletRequest request) {
		BoardDAO dao = new BoardDAO();

		BoardVO board = new BoardVO();
		board.setBoardNumber(dao.getListCount() + 1); // last number
		board.setId(request.getParameter("id"));
		board.setContent(request.getParameter("content"));
		board.setLikeNumber(0);
		board.setDislikeNumber(0);

		dao.insertBoard(board);
	}

	public void requestBoardRead(HttpServletRequest request) {
		BoardDAO dao = new BoardDAO();

		ArrayList<BoardVO> board = new ArrayList<BoardVO>();
		board = dao.getBoardList();

		// jsp에서 forEach로 반복함.
		request.setAttribute("boardList", board);
	}

	public void requestBoardTop(HttpServletRequest request) {
		BoardDAO dao = new BoardDAO();

		ArrayList<BoardVO> board = new ArrayList<BoardVO>();
		board = dao.getListTop();

		// jsp에서 forEach로 반복함.
		request.setAttribute("boardListTop", board);
	}

	public void requestBoardDelete(HttpServletRequest request) {
		BoardDAO dao = new BoardDAO();

		int deleteBoardNumber = Integer.parseInt(request.getAttribute("boardNumber").toString());
		dao.delete(deleteBoardNumber);
	}

	public void like(HttpServletRequest request) {
		BoardDAO dao = new BoardDAO();

		dao.like(Integer.valueOf(request.getParameter("boardNumber")));
	}

	public void dislike(HttpServletRequest request) {
		BoardDAO dao = new BoardDAO();

		dao.dislike(Integer.valueOf(request.getParameter("boardNumber")));
	}
	

}
