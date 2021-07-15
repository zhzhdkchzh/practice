package com.example.test.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.test.model.DataTablesDTO;
import com.example.test.model.Testmenu;

@Repository
@Mapper
public interface TestMenuRepository {

	List<DataTablesDTO> getTable();

	void insertFile(Testmenu testmenu);

}
