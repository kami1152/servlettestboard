package com.starsong.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.starsong.user.*;

public class UserController {
	private static final long serialVersionUID = 1L;

	//xml 또는 어노터이션 처리하면 스프링 
	//어노터이션 처리하면 스프링 부트   
	UserService userService = new UserService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	public Object list(HttpServletRequest request, UserVO user) throws ServletException, IOException {
		System.out.println("목록");
		
		//1. 처리
		List<UserVO> list = userService.list(user);
		
		//2. jsp출력할 값 설정
		request.setAttribute("list", list);
		
		return "list";
	}
	
	public Object view(HttpServletRequest request, UserVO user) throws ServletException, IOException {
		System.out.println("상세보기");
		//String userid = request.getParameter("userid");
		//1. 처리
		
		//2. jsp출력할 값 설정
		request.setAttribute("user", userService.view(user));
		return "view";
	}
	
	public Object delete(HttpServletRequest request, UserVO user) throws ServletException, IOException {
		System.out.println("삭제");
		//1. 처리
		int updated = userService.delete(user);
		
//		//2. jsp출력할 값 설정
//		request.setAttribute("updated", updated);
//		
//		return "delete";
		Map<String, Object> map = new HashMap<>();
		if (updated == 1) { //성공
			map.put("status", 0);
		} else {
			map.put("status", -99);
			map.put("statusMessage", "회원 정보 삭제 실패하였습니다");
		}
		
		return map;
	}
	
	public Object updateForm(HttpServletRequest request, UserVO user) throws ServletException, IOException {
		System.out.println("수정화면");
		//1. 처리
		//usersDAO.read(user);
		
		//2. jsp출력할 값 설정
		request.setAttribute("user", userService.updateForm(user));
		
		return "updateForm"; 
	}
	
	public Object update(HttpServletRequest request, UserVO user) throws ServletException, IOException {
		System.out.println("수정");
		
		//1. 처리
		int updated = userService.update(user);
		
//		//2. jsp출력할 값 설정
//		request.setAttribute("updated", updated);
//		
//		return "update";
		Map<String, Object> map = new HashMap<>();
		if (updated == 1) { //성공
			map.put("status", 0);
		} else {
			map.put("status", -99);
			map.put("statusMessage", "회원 정보 수정 실패하였습니다");
		}
		
		return map;
	}
	
	public Object insertForm(HttpServletRequest request) throws ServletException, IOException {
		System.out.println("등록화면");
		//1. 처리
		
		//2. jsp출력할 값 설정
		return "insertForm";
	}
	
	public Object insert(HttpServletRequest request, UserVO user) throws ServletException, IOException {
		System.out.println("등록");
		Map<String, Object> map = new HashMap<>();
		
		if (user.getUserid() == null  || user.getUserid().length() == 0) {
			map.put("status", -1);
			map.put("statusMessage", "사용자 아이디는 null 이거나 길이가 0인 문자열을 사용할 수 없습니다");
		} else {
			//1. 처리
			int updated = userService.insert(user);
			
			if (updated == 1) { //성공
				map.put("status", 0);
			} else {
				map.put("status", -99);
				map.put("statusMessage", "회원 가입이 실패하였습니다");
			}
		}
		return map;
	}

	public Object existUserId(HttpServletRequest request, UserVO userVO) throws ServletException, IOException {
		//1. 처리
		System.out.println("existUserId userid->" + userVO.getUserid());
		UserVO existUser = userService.view(userVO);
		Map<String, Object> map = new HashMap<>();
		System.out.println(existUser);
		
		if (existUser == null) { //사용가능한 아이디
			map.put("existUser", false);
		} else { //사용 불가능 아아디 
			map.put("existUser", true);
		}
		return map;
	}
	
}