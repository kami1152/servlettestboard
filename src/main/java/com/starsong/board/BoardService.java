package com.starsong.board;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starsong.board.BoardDAO;

/**
 * Servlet implementation class board
 */
public class BoardService extends HttpServlet {
	
	BoardDAO boardDAO = new BoardDAO();
	BoardVO boardVO;
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardService() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doService(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doService(request, response);
	}

	private void doService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		ObjectMapper obhecMapper = new ObjectMapper();
		String contentType = request.getContentType();
		
	
		
		// 한글 설정
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");

		switch (action) {
		case "list" -> list(request, response);
		case "insert" -> create(request, response);
		case "view" -> view(request, response);
		case "delete" -> delete(request, response);
		case "updateForm" -> updateForm(request, response);
		case "update" -> update(request, response);
		}
		// 3. jsp 포워딩
		// 포워딩
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/" + action + ".jsp");
		rd.forward(request, response);

	}

	private void updateForm(HttpServletRequest request, HttpServletResponse response) {
		int bno = Integer.parseInt(request.getParameter("bno"));
		BoardVO board = boardDAO.detail(bno);
		request.setAttribute("board", board);

	}

	private int update(HttpServletRequest request, HttpServletResponse response) {
		int bno = Integer.parseInt(request.getParameter("bno"));
		BoardVO board = boardDAO.detail(bno);
		board.setBcontent(request.getParameter("bcontent"));
		int updated = boardDAO.update(board);
		return updated;
	}

	private Object delete(HttpServletRequest request, HttpServletResponse response) {
		int bno = Integer.parseInt(request.getParameter("bno"));
		int update = boardDAO.delete(bno);
		request.setAttribute("update", update);
		return null;
	}

	private BoardVO view(HttpServletRequest request, HttpServletResponse response) {
		int bno = Integer.parseInt(request.getParameter("bno"));
		BoardVO board = boardDAO.detail(bno);
		request.setAttribute("board", board);
		return board;
	}

	private void create(HttpServletRequest request, HttpServletResponse response) {
		BoardVO board = new BoardVO();
		board.setBtitle(request.getParameter("btitle"));
		board.setBwriter(request.getParameter("bwriter"));
		board.setBcontent(request.getParameter("bcontent"));
		int update = boardDAO.insert(board);
		request.setAttribute("board", board);
		
		
	}

	private void list(HttpServletRequest request, HttpServletResponse response) {
		List<BoardVO> boardlist = boardDAO.list();
		request.setAttribute("list", boardlist);
	}

}
