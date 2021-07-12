package com.example.test.service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Comparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.model.DataTablesDTO;
import com.example.test.model.LoginDTO;
import com.example.test.model.Menu;
import com.example.test.model.UserSelectMenu;
import com.example.test.model.userMenu;
import com.example.test.repository.CommonRepository;
import com.example.test.util.SHA265Util;

@Service
public class CommonService {
	private static final Logger logger = LoggerFactory.getLogger(CommonService.class);
	private SHA265Util sha256; // 암호화

	@Autowired
	private CommonRepository cr;

	public int loginProc(LoginDTO loginDTO) throws Exception {
		Map<String, String> loginInfo = new HashMap<String, String>();
		int rs = 0;
		try {
			String pw = sha256.sha256(loginDTO.getId(), loginDTO.getPw()); // 패스워드 암호화를 위한 메소드
			loginDTO.setPw(pw);

			rs = cr.loginProc(loginDTO);


		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rs;
	}

	public List<DataTablesDTO> viewTable() { // 출력할 테이블 조회
		List<DataTablesDTO> tableLst = cr.viewTable();

		return tableLst;
	}

	public int getLevel(String id) {
		int userLevel = cr.getLevel(id);
		return userLevel;
	}

	public int levelUp(String id, int level) {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setId(id);
		loginDTO.setLevel(level);
		cr.levelUp(loginDTO);
		return level;

	}

	public int levelDown(String id, int level) {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setId(id);
		loginDTO.setLevel(level);
		cr.levelUp(loginDTO);
		return level;

	}

	public List<UserSelectMenu> getUserMenuList(int level) {
		List<UserSelectMenu> userMenuList = cr.getUserMenuList(level);

		return userMenuList;
	}

	public List<DataTablesDTO> getSearchTableList(DataTablesDTO dataTablesDTO) {
		List<DataTablesDTO> searchDataTablesList = cr.getSearchTableList(dataTablesDTO);
		return searchDataTablesList;
	}

}
