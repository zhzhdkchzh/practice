package com.example.test.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSelectMenu {

	private String menu_code;
	private String menu_name;
	private int menu_level;

}
