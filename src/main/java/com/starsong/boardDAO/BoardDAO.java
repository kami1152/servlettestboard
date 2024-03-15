package com.starsong.boardDAO;

import java.sql.*;
import java.util.*;

import com.starsong.boardVO.BoardVO;

public class BoardDAO {

	private static Connection conn = null;
	private static PreparedStatement Pstmt = null;

	static {

		try {
			// 1. JDBC 드라이버 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			// 2. DB 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "bituser", // 계정이름
					"1004" // 계정비밀번호
			);

			System.out.println("연결 성공");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public List<BoardVO> list() {
		List<BoardVO> list = new ArrayList<>();

		try {
			String sql = "SELECT bno,BTITLE ,BWRITER ,BDATE  FROM boards ORDER BY bno asc";
			Pstmt = conn.prepareStatement(sql);
			ResultSet rs = Pstmt.executeQuery();
			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBwriter(rs.getString("BWRITER"));
				board.setBdate(rs.getDate("BDATE"));
				list.add(board);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public BoardVO detail(int bno) {
		BoardVO board = new BoardVO();
		try {
			String sql = "SELECT * FROM BOARDS WHERE bno = ?";
			Pstmt = conn.prepareStatement(sql);
			Pstmt.setInt(1, bno);
			ResultSet rs = Pstmt.executeQuery();
			while (rs.next()) {
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBcontent(rs.getString("bcontent"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBdate(rs.getDate("bdate"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return board;
	}

	public int delete(int bno) {
		int updated = 0;
		try {
			String sql = "delete from boards where bno=?";
			Pstmt = conn.prepareStatement(sql);
			Pstmt.setInt(1, bno);
			updated = Pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updated;

	}

	public int update(BoardVO board) {
		int updated = 0;
		try {
			String sql = "update boards set bcontent=? where bno = ?";
			Pstmt = conn.prepareStatement(sql);
			Pstmt.setString(1, board.getBcontent());
			Pstmt.setInt(2, board.getBno());
			updated = Pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return updated;
	}

	public int insert(BoardVO board) {
		int updated = 0;
		try {
			String sql = "INSERT INTO BOARDS (bno, btitle, bcontent, bwriter, bdate) VALUES (board_seq.NEXTVAL, ?, ?, ?, SYSDATE)";
			Pstmt = conn.prepareStatement(sql);
			Pstmt.setString(1, board.getBtitle());
			Pstmt.setString(2, board.getBcontent());
			Pstmt.setString(3, board.getBwriter());
			updated=  Pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return updated;
	}

}
