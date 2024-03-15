package com.starsong.boardVO;

import java.util.Date;

import lombok.*;

@Data
public class BoardVO{
	private int bno;
	private String btitle;
	private String bcontent;
	private String bwriter;
	private Date bdate;
}