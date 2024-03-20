package com.starsong.board;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;

public class BoardService {
	private static final long serialVersionUID = 1L;
	BoardDAO boardDAO = new BoardDAO();

	public BoardService() {
		super();
	}

	public List<BoardVO> list(BoardVO board) throws ServletException, IOException {

		return boardDAO.list(board);
	}

	public BoardVO view(BoardVO board) throws ServletException, IOException {
		return boardDAO.detail(board);
	}

	public int delete(BoardVO board) throws ServletException, IOException {
		return boardDAO.delete(board);
	}

	public Object updateForm(BoardVO board) throws ServletException, IOException {
		return boardDAO.detail(board);
	}

	public int update(BoardVO board) throws ServletException, IOException {
		return boardDAO.update(board);
	}
	public int insert(BoardVO board ) throws ServletException, IOException{
		return boardDAO.insert(board);
	}
}