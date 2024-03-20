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
import com.starsong.user.UserVO;

/**
 * Servlet implementation class board
 */
public class BoardController {

	private static final long serialVersionUID = 1L;

	BoardService boardService = new BoardService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Object list(HttpServletRequest request, BoardVO board) throws IOException, ServletException {
		List<BoardVO> boardlist = boardService.list(board);
		request.setAttribute("list", boardlist);
		return "list";
	}

	public Object view(HttpServletRequest request, BoardVO board) throws IOException, ServletException {
		request.setAttribute("board", boardService.view(board));
		return "view";
	}

	public String updateForm(HttpServletRequest request, BoardVO board) throws IOException, ServletException {
		request.setAttribute("board", boardService.updateForm(board));
		return "updateForm";

	}

	public Object update(HttpServletRequest request, BoardVO board) throws IOException, ServletException {

		int updated = boardService.update(board);
		Map<String, Object> map = new HashMap<>();
		if (updated == 1) {
			System.out.println("업데이트 성공");
			map.put("status", 0);
		} else {
			map.put("status", -99);
			map.put("statusMessage", "회원 정보 수정 실패하였습니다");
		}

		return map;
	}

	public Object delete(HttpServletRequest request, BoardVO board) throws IOException, ServletException {
		int updated = boardService.delete(board);
		Map<String, Object> map = new HashMap<>();
		if(updated == 1) {
			map.put("status", 0);
		}else {
			map.put("status", -99);
			map.put("statusMessage", "등록 실패");
		}
		
		return map;
	}

	public Object insert(HttpServletRequest request, BoardVO board) throws IOException, ServletException {
		System.out.println("등록");
		Map<String, Object> map = new HashMap<>();

		if (board.getBcontent().length() == 0 || board.getBtitle().length() == 0) {
			map.put("status", -1);
			map.put("statusMessage", "제목 내용은 필수 입력입니다.");
		} else {
			// 1. 처리
			int updated = boardService.insert(board);

			if (updated == 1) { // 성공
				map.put("status", 0);
			} else {
				map.put("status", -99);
				map.put("statusMessage", "등록 실패");
			}
		}
		return map;
	}

	public Object insertForm(HttpServletRequest request, BoardVO board) throws IOException, ServletException {
		return "insertForm";
	}

}
