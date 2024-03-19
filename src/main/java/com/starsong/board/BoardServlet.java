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
public class BoardServlet extends HttpServlet {

	BoardDAO boardDAO = new BoardDAO();
	BoardVO boardVO;

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardServlet() {
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

		String jspPage = switch (action) {
		case "list" -> list(request, response);
		case "insert" -> insert(request, response);
		case "view" -> view(request, response);
		case "delete" -> delete(request, response);
		case "updateForm" -> updateForm(request, response);
		case "update" -> update(request, response);

		default -> "";
		};
		// 3. jsp 포워딩

		if (jspPage.startsWith("redirect:")) {
			response.sendRedirect(jspPage.substring("redirect:".length()));
		} else {
			// 포워딩
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/board/" + action + ".jsp");
			rd.forward(request, response);
		}
	}

	private String updateForm(HttpServletRequest request, HttpServletResponse response) {
		int bno = Integer.parseInt(request.getParameter("bno"));
		BoardVO board = boardDAO.detail(bno);
		request.setAttribute("board", board);
		return "updateForm";

	}

	private String update(HttpServletRequest request, HttpServletResponse response) {
		int bno = Integer.parseInt(request.getParameter("bno"));
		BoardVO board = boardDAO.detail(bno);
		board.setBtitle(request.getParameter("btitle"));
		board.setBcontent(request.getParameter("bcontent"));
		int updated = boardDAO.update(board);
		request.setAttribute("updated", updated);
		return "update";
	}

	private String delete(HttpServletRequest request, HttpServletResponse response) {
		int bno = Integer.parseInt(request.getParameter("bno"));
		int update = boardDAO.delete(bno);
		request.setAttribute("update", update);
		return "delete";
	}

	private String view(HttpServletRequest request, HttpServletResponse response) {
		int bno = Integer.parseInt(request.getParameter("bno"));
		BoardVO board = boardDAO.detail(bno);
		request.setAttribute("board", board);
		return "view";
	}

	private String insert(HttpServletRequest request, HttpServletResponse response) {
		BoardVO board = new BoardVO();
		board.setBtitle(request.getParameter("btitle"));
		board.setBwriter(request.getParameter("bwriter"));
		board.setBcontent(request.getParameter("bcontent"));
		int updated = boardDAO.insert(board);
		request.setAttribute("updated", updated);

		return "redirect:board.do?action=list";
	}

	private String list(HttpServletRequest request, HttpServletResponse response) {

		List<BoardVO> boardlist = boardDAO.list();
		request.setAttribute("list", boardlist);

		return "list";
	}

}
