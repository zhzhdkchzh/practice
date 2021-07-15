package com.example.test.controller;


import java.util.List;

import javax.servlet.http.HttpSession;
import com.example.test.model.Testmenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.example.test.model.DataTablesDTO;
import com.example.test.service.TestMenuService;

@Controller
public class TestMenuController {
	private static final Logger logger = LoggerFactory.getLogger(TestMenuController.class);
	@Autowired
	private TestMenuService uploadService;
	
	@GetMapping("/upload")
	public ModelAndView upload(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		List<DataTablesDTO> DataTalbeList = uploadService.getTable();
		mv.setViewName("index");
		mv.addObject("tableLst", DataTalbeList);
		mv.addObject("mainFrame", "/TestMenu/upload");
		mv.addObject("uml", session.getAttribute("uml"));	
		mv.addObject("level", session.getAttribute("level"));	
		mv.addObject("loginId", session.getAttribute("loginId"));	
		mv.addObject("userLevel", session.getAttribute("userLevel"));
		return mv;
	}
	@GetMapping("/download")
	public ModelAndView download(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		List<DataTablesDTO> DataTalbeList = uploadService.getTable();
		mv.setViewName("index");
		mv.addObject("tableLst", DataTalbeList);
		mv.addObject("mainFrame", "/TestMenu/download");
		mv.addObject("uml", session.getAttribute("uml"));	
		mv.addObject("level", session.getAttribute("level"));	
		mv.addObject("loginId", session.getAttribute("loginId"));	
		mv.addObject("userLevel", session.getAttribute("userLevel"));
		return mv;
	}
	@PostMapping("/uploadFile")
	@ResponseBody
	public ResponseEntity<String> Testmenu( Testmenu testmenu){
		return new ResponseEntity<String>(uploadService.insertFile(testmenu), HttpStatus.OK);
	}
	
}
