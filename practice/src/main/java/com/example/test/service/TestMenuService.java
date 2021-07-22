package com.example.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.model.DataTablesDTO;
import com.example.test.model.Jstree;
import com.example.test.model.Testmenu;
import com.example.test.repository.TestMenuRepository;

@Service
public class TestMenuService {
	@Autowired
	private TestMenuRepository testMenuRepository;
	public List<DataTablesDTO> getTable() {
		List<DataTablesDTO> DataTablsList = testMenuRepository.getTable();
		return DataTablsList;
	}
	public String insertFile(Testmenu testmenu) {
		testMenuRepository.insertFile(testmenu);
		String rs= "success";
		return rs;
	}
	public void addNode(Jstree jstree) {
		testMenuRepository.addNode(jstree);
	}
	public void deleteNode(Jstree jstree) {
		testMenuRepository.deleteNode(jstree);
		
	}
	public void modifyNode(Jstree jstree) {
		testMenuRepository.modifyNode(jstree);
		
	}

}
