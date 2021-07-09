package com.example.test.controller;


import java.net.BindException;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.example.test.model.DataTablesDTO;
import com.example.test.model.LoginDTO;
import com.example.test.model.Menu;
import com.example.test.model.UserSelectMenu;
import com.example.test.model.userMenu;
import com.example.test.service.CommonService;
import com.example.test.util.SessionUtil;

@Controller
public class CommonController {
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	@Autowired
	private CommonService commonService;

	@GetMapping("/")	
	public String home(Model model, @CookieValue(value = "IdCookie", required = false) Cookie coo) {
		
		if (coo != null) {	
			logger.info("쿠키불러오기");
			logger.warn(coo.getValue());
			model.addAttribute("id", coo.getValue());
			logger.info("쿠키전달완료");
			model.addAttribute("check", "true");
		} else {
			logger.info("쿠키없음");

		}
		return "login";
	}

	@GetMapping("/viewTable")	
	public ModelAndView table(HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();	
		LoginDTO dto = (LoginDTO) SessionUtil.getSessionAttribute("user");
		int level = commonService.getLevel(dto.getId());	
		String userLevel = "";	
		if (level == 1)
			userLevel = "Manager";
		else if (level == 2)
			userLevel = "Leader";
		else if (level == 3)
			userLevel = "President";
		else
			userLevel = "ChairMan";

		mv.setViewName("index");	
	

		List<UserSelectMenu> userSelectMenuList = (List<UserSelectMenu>) commonService.getUserMenuList(level);	
		List<userMenu> uml = Menu.getMenu(userSelectMenuList);
		mv.addObject("uml", uml);	
		mv.addObject("search", "/table-search");	
		mv.addObject("loginId", "Login ID : " + dto.getId());	
		mv.addObject("viewName", "/dataTable");	
		mv.addObject("userLevel", userLevel);	
		mv.addObject("xssTest", "/xssTest");
		mv.addObject("level", level);	
		mv.addObject("sideMenu", "/sideMenu");
		mv.addObject("topbar", "/topbar");
		mv.addObject("levelMenu", "/levelMenu");
		return mv;
	}

	@PostMapping("/loginProc")	
	@ResponseBody		
	public int login(LoginDTO loginDTO, HttpServletResponse resp, HttpServletRequest request) {
		
		int result = 0;		 
		try {
			result = commonService.loginProc(loginDTO);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result == 1) {	
			logger.info("로그인성공");
			Cookie coo = new Cookie("IdCookie", loginDTO.getId());		
			request.getSession(true).setAttribute("user", loginDTO);	
			int userLevel = commonService.getLevel(loginDTO.getId());
			request.getSession(true).setAttribute("level", userLevel);	
			logger.info("세션 생성");
			if ("true".contentEquals(loginDTO.getRememberId())) {		
				logger.info("체크박스확인");

				coo.setMaxAge(60 * 60 * 24);	
				coo.setPath("/");				
				resp.addCookie(coo);			
				logger.info("쿠키생성");
			} else if ("false".contentEquals(loginDTO.getRememberId())) {		
				coo.setMaxAge(0);		
				coo.setPath("/");
				resp.addCookie(coo);
				logger.info("쿠키삭제");
			}
		} else if (result == 0) {		
			logger.info("로그인실패");
		}
	
		return result;
	}

	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");	//세션 삭제
		session.removeAttribute("level");
//		SessionUtil.removeAttribute("user");
		logger.info("세션 제거");
		return "login";
	}

	@GetMapping("/signup")
	public String SignUp() {
		return "/signUp";
	}

	@PostMapping("/levelUp")
	@ResponseBody
	public int levelUp(HttpSession session, LoginDTO loginDTO) {	
		LoginDTO dto = (LoginDTO) session.getAttribute("user");		
		int level = loginDTO.getLevel();							
		level = commonService.levelUp(dto.getId(), level + 1);
		session.removeAttribute("level");
		return level;

	}

	@PostMapping("/levelDown")
	@ResponseBody
	public int levelDown(HttpSession session, LoginDTO loginDTO) {	
		LoginDTO dto = (LoginDTO) session.getAttribute("user");
		int level = loginDTO.getLevel();
		level = commonService.levelDown(dto.getId(), level - 1);
		session.removeAttribute("level");
		return level;

	}
	@PostMapping("/sendText")
	@ResponseBody
	public String xssTest(String sendTxt, HttpSession session) {
		session.setAttribute("txt", sendTxt);
		logger.info("/sendText : "+sendTxt);
		return sendTxt;
	}
	
	@RequestMapping("/popup")
	@ResponseBody
	public String popup (HttpSession session) {
	String txt = (String) session.getAttribute("txt");
		return txt;
	}

	@PostMapping("/Up")
	@ResponseBody
	public int up(HttpSession session) {
		LoginDTO ld =  (LoginDTO) session.getAttribute("user");
		int userLevel = commonService.getLevel(ld.getId());
		if(userLevel>=4) {
			return 0;
		}
		else {
		int	level = commonService.levelUp(ld.getId(), userLevel + 1);
		session.removeAttribute("level");
		return level;
		}
			
	}

	@PostMapping("/Down")
	@ResponseBody
	public int Down(HttpSession session) {
		LoginDTO ld =  (LoginDTO) session.getAttribute("user");
		int userLevel = commonService.getLevel(ld.getId());
		if(userLevel<=1) {
			return 0;
		}
		else {
		int	level = commonService.levelUp(ld.getId(), userLevel - 1);
		session.removeAttribute("level");
		return level;
		}
			
	}
	@GetMapping("/searchTable") 
	@ResponseBody
	public ModelAndView SearchTable(DataTablesDTO dataTablesDTO, Model model ) {
		ModelAndView mv = new ModelAndView();
		logger.debug(dataTablesDTO.getName()+"@@@@@");
		List<DataTablesDTO> searchDataTablesList = commonService.getSearchTableList(dataTablesDTO);
		mv.addObject("tableLst", searchDataTablesList);
		mv.setViewName("forward:/viewTable");
		return mv;
	}
	
}