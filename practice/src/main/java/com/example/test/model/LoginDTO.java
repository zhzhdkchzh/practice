package com.example.test.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {	//로그인 및 권한레벨에 사용될 DTO
	private String id;
	private String pw;
	private String rememberId;
	private int level;

}
