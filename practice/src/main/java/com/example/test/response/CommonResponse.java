package com.example.test.response;

import com.example.test.model.Menu;

import lombok.Getter;

@Getter
public class CommonResponse {

	private Menu data;
	public CommonResponse (Menu data) {
		this.data = data;
	}
}
