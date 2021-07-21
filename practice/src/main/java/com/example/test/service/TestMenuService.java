package com.example.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.model.DataTablesDTO;
import com.example.test.model.Testmenu;
import com.example.test.repository.TestMenuRepository;

@Service
public class TestMenuService {
	@Autowired
	private TestMenuRepository uploadRepository;
	public List<DataTablesDTO> getTable() {
		List<DataTablesDTO> DataTablsList = uploadRepository.getTable();
		return DataTablsList;
	}
	public String insertFile(Testmenu testmenu) {
		uploadRepository.insertFile(testmenu);
		String rs= "success";
		return rs;
	}

}
