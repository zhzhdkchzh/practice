package com.example.test.model;




import java.util.ArrayList;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Menu {
	/*
	 * enum 클래스가 사용되는 이유는 여러가지 값들에게 동일한 의미를 부여할때 주로 사용됨
	 * Y(yes, 1, true)
	 * N(no, 0, false) 
	 */
	test("#", "TestMenu", "root", 1 ),
	test_up("#", "A", "TestMenu", 1 ),
	test_down("#", "B", "TestMenu", 1),
	test_test("/upload", "Upload", "TestMenu", 1),
	test_test2("/download", "Download", "TestMenu", 1),
	
	rt("#", "Reputation", "root", 1),
	rt_1("#", "Usually", "Reputation", 1),
	rt_2("#", "Friendly", "Reputation", 2),
	rt_3("#", "Good", "Reputation", 3),
	rt_4("#", "Firm", "Reputation", 4),
	
	lv("#", "Level", "root", 1),
	lv_1("#", "L1", "Level", 1),
	lv_2("#", "L2", "Level", 2),
	lv_3("#", "L3", "Level", 3),
	lv_4("#", "L4", "Level", 4);
	

	private String url;	//name 설정
	private String mName;
	private String parent;
	private int level;
	
	
	public static List<userMenu> getMenu(List<UserSelectMenu> userSelectMenuList) {
		
		List<userMenu> uml = new ArrayList<userMenu>();
		for (UserSelectMenu usm : userSelectMenuList) {
			userMenu um = new userMenu();
			um.setUrl(Menu.valueOf(usm.getMenu_code()).getUrl());
			um.setParent(Menu.valueOf(usm.getMenu_code()).getParent());
			um.setLevel(Menu.valueOf(usm.getMenu_code()).getLevel());
			um.setMName(Menu.valueOf(usm.getMenu_code()).getMName());
			uml.add(um);
		}
		
		return uml;
	}

}
