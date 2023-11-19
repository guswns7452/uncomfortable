package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardVO;
import persistance.BoardDAO;

public class boardController {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
	
		// 글 등록하기 메소드
		if (command.equals("/board/write")) {// 글 등록하기
			requestBoardWrite(request);
			//FIXME jsp 부분 변경해야함.
			RequestDispatcher rd = request.getRequestDispatcher("./board/main.jsp");
			rd.forward(request, response);
		}
		// 글 조회하기
		else if (command.equals("/board")) {
			requestBoardRead(request);
			//FIXME jsp 부분 변경해야함.
			RequestDispatcher rd = request.getRequestDispatcher("./board/main.jsp");
			rd.forward(request, response);
		}
		// top10 글 출력하기
		else if (command.equals("/top")) {
			requestBoardTop(request);
			//FIXME jsp 부분 변경해야함.
			RequestDispatcher rd = request.getRequestDispatcher("./board/top.jsp");
			rd.forward(request, response);
		}
		// 글 삭제하기
		else if (command.equals("/board/delete")) {
			requestBoardDelete(request);
			//FIXME jsp 부분 변경해야함.
			RequestDispatcher rd = request.getRequestDispatcher("./board/delete.jsp");
			rd.forward(request, response);
		}
		
	}
	
	public void requestBoardWrite(HttpServletRequest request){
		BoardDAO dao = new BoardDAO();		
		
		BoardVO board = new BoardVO();
		board.setBoardNumber(dao.getListCount()); // last number 
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
		board = dao.getBoardList();
		
		// jsp에서 forEach로 반복함.		
		request.setAttribute("boardList", board);
	}
	
	public void requestBoardDelete(HttpServletRequest request) {
		BoardDAO dao = new BoardDAO();
		
		int deleteBoardNumber = Integer.parseInt(request.getAttribute("boardNumber").toString());
		dao.delete(deleteBoardNumber);
		
	}
	
}
