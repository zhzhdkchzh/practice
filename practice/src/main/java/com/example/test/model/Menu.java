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
	
	test("#", "TestMenu", "root", 1 ),
	test_up("#", "Up", "TestMenu", 1 ),
	test_down("#", "Down", "TestMenu", 1),
	test_test("#", "Test_1", "TestMenu", 1),
	test_test2("#", "Test_2", "TestMenu", 1),
	
	rt("#", "Reputation", "root", 1),
	rt_1("#", "Usually", "Reputation", 1),
	rt_2("#", "Friendly", "Reputation", 2),
	rt_3("#", "Good", "Reputation", 3),
	rt_4("#", "Firm", "Reputation", 4),
	
	lv("#", "Level", "root", 1),
	lv_1("#", "Manager", "Level", 1),
	lv_2("#", "Leader", "Level", 2),
	lv_3("#", "Director", "Level", 3),
	lv_4("#", "CEO", "Level", 4);
	

	private String url;
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
