package com.example.test.controller;


import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;
import com.example.test.model.Testmenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.example.test.model.DataTablesDTO;
import com.example.test.model.Jstree;
import com.example.test.model.Test;
import com.example.test.service.TestMenuService;

@Controller
public class TestMenuController {
	private static final Logger logger = LoggerFactory.getLogger(TestMenuController.class);
	@Autowired
	private TestMenuService testMenuService;
	
	@GetMapping("/upload")
	public ModelAndView upload(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		List<DataTablesDTO> DataTalbeList = testMenuService.getTable();
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
		List<DataTablesDTO> DataTalbeList = testMenuService.getTable();
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
	public ResponseEntity<String> Testmenu(@RequestBody Testmenu testmenu){	//ResponseEntity<String> ??????????????? ???????????? String
		/*
		 * ResponseEntity??? 3????????? ???????????? ???????????? (??????, ??????, ????????????)
		 * HttpHeaders headers = new HttpHeaders();
		 * headers.add("Name", "Value");
		 * return new ResponseEntity<String>(headers, body??? ???????????? ???????????????, ????????????);
		 *  insertFile??? ???????????? ???????????? body??? ??????
		 *  HttpStatus.OK??? ?????????????????? 200??? ??????
		*/
		String rs = testMenuService.insertFile(testmenu);
		return new ResponseEntity<String>(rs, HttpStatus.OK);	
	}
	
	@PostMapping("/testAjax")
	@ResponseBody
	public HashMap<String, String> testAjax(@RequestBody Test test, Model model){
		logger.info(test.getTest());
		test.setTest("???????????????");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("test", "???????????????");
		map.put("test2", "????????? 2");
		return map;
	}
	@GetMapping("/edit/jstree")
	public ModelAndView a(ModelAndView mv, HttpSession session) {
		mv.setViewName("index");
		mv.addObject("mainFrame", "/TestMenu/edit");
		mv.addObject("uml", session.getAttribute("uml"));	
		mv.addObject("level", session.getAttribute("level"));	
		mv.addObject("loginId", session.getAttribute("loginId"));	
		mv.addObject("userLevel", session.getAttribute("userLevel"));
		return mv;
	}
	@PostMapping("/jstree/add")
	@ResponseBody
	public void addNode(@RequestBody Jstree jstree) {
		testMenuService.addNode(jstree);
	}
	@PostMapping("/jstree/delete")
	@ResponseBody
	public int deleteNode(@RequestBody Jstree jstree) {
		testMenuService.deleteNode(jstree);
		return 1;
	}
	@PostMapping("/jstree/modify")
	@ResponseBody
	public int modifyNode(@RequestBody Jstree jstree) {
		testMenuService.modifyNode(jstree);
		return 1;
	}
}
