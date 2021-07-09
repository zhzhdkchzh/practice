package com.example.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class userMenu {
	private int level;
	private String url;
	private String mName;
	private String parent;
	
}