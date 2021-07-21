package com.example.test.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.test.model.Another;
import com.example.test.model.DataTablesDTO;
import com.example.test.model.Jstree;
import com.example.test.model.LoginDTO;
import com.example.test.model.UserSelectMenu;

@Repository	
@Mapper		//DAO의 기능을 위해 두 어노테이션 필수
public interface CommonRepository {
	//DAO기능을 하는 인터페이스이며 mapper.xml에 네임플레이스로 설정된 경로와 연결됨
	//mapper는 리소스폴더 아래에 존재해야함
	int loginProc(LoginDTO loginDTO);	//로그인 정보 비교
	List<DataTablesDTO> viewTable();	//테이블 출력을위한 메소드
	int getLevel(String id);			//권한레벨 가져오기
	void levelUp(LoginDTO loginDTO);	
	void levelDown(LoginDTO loginDTO);
	List<UserSelectMenu> getUserMenuList(int level);
	List<DataTablesDTO> getSearchTableList(DataTablesDTO dataTablesDTO);
	void signup(LoginDTO loginDTO);
	List<Another> getAnother();
	void sendMsg(Another another);
	void deleteAnother(String idx);
	List<Jstree> getJsTree();

}
