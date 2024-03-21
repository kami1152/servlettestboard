package com.starsong.user;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO {
	private String userid;
	private String userpassword;
	private String username;
	private int    userage;
	private String useremail;
	
	private String userpassword2;
	//실행 명령 필드 
	private String action;

	
	//uuid
	private String useruuid;
	private String autologin;
	//검색키
	private String searchKey;
	
	public UserVO(String userid, String userpassword, String username, int userage, String useremail) {
		super();
		this.userid = userid;
		this.userpassword = userpassword;
		this.username = username;
		this.userage = userage;
		this.useremail = useremail;
	}
	
	public boolean isEmptySearchKey() {
		return searchKey == null || searchKey.length() == 0; 
	}
	public boolean isEqualPassword(UserVO userVO) {
		return userVO != null && userpassword.equals(userVO.getUserpassword());
	}
	
}