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
import org.springframework.web.bind.annotation.RequestParam;
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
		//브라우저에서 id기억하기 체크박스 체크후 로그인할경우 생기는 쿠키를 가져오기 위해 @CookieValue 어노테이션 사용
		//value는 해당 쿠키의 쿠키네임이며 required = false 속성을 설정함으로써 쿠키가 null일경우에도 NPE 예외 발생X
		if (coo != null) {	//id기억하기 체크후 로그인 한적이있다면 coo!=null 없다면 coo==null
			model.addAttribute("id", coo.getValue());	//쿠키value 모델값으로 전달
			model.addAttribute("check", "true");		//체크박스 속성에 checked = true 를 설정해주기위해 전달
		} else {
			logger.info("Non Cookie");

		}
		return "login";		//로그인 화면 출력
	}

	@GetMapping("/viewTable")		//로그인에 성공했다면 해당 메소드 실행
	public ModelAndView table(HttpSession session) throws Exception {	
		//ModelAndView는 말그대로 model값과 view정보를 같이 넘겨줄수있음
		ModelAndView mv = new ModelAndView();	//생성자
		LoginDTO dto = (LoginDTO) SessionUtil.getSessionAttribute("user");	//로그인시 생성된 세션 가져옴
		int level = commonService.getLevel(dto.getId());	//사용자 권한 레벨 조회
		String userLevel = "";	//사용자 권한을 브라우저상에 띄워주기위한 변수
		if (level == 1)
			userLevel = "L1";
		else if (level == 2)
			userLevel = "L2";
		else if (level == 3)
			userLevel = "L3";
		else
			userLevel = "L4";

		mv.setViewName("index");	//index.html 페이지이동
	

		List<UserSelectMenu> userSelectMenuList = (List<UserSelectMenu>) commonService.getUserMenuList(level);	//상단에 띄워질 메뉴	
		List<userMenu> uml = Menu.getMenu(userSelectMenuList); //enum(열거형 클래스)을이용해 해당 메뉴들을 가져옴
		mv.addObject("uml", uml);	
		mv.addObject("level", level);	
		mv.addObject("loginId", "Login ID : " + dto.getId());	
		mv.addObject("userLevel", userLevel);
		mv.addObject("mainFrame", "/mainFrame");	//로그인후 메인화면에 띄워질 html 파일경로
		session.setAttribute("uml", uml);
		session.setAttribute("loginId", "Login ID : " + dto.getId());
		session.setAttribute("userLevel", userLevel);
		session.setAttribute("level", level);

		return mv;
	}

	@GetMapping("/sendCondition") 
	public ModelAndView sendCondition(DataTablesDTO dataTablesDTO, HttpSession session) {
		List<DataTablesDTO> searchDataTablesList = commonService.getSearchTableList(dataTablesDTO);
		ModelAndView mv = new ModelAndView();
		mv.addObject("searchDataTablesList", searchDataTablesList);
		mv.setViewName("index");
		mv.addObject("mainFrame", "/mainFrame");
		mv.addObject("uml", session.getAttribute("uml"));	
		mv.addObject("level", session.getAttribute("level"));	
		mv.addObject("loginId", session.getAttribute("loginId"));	
		mv.addObject("userLevel", session.getAttribute("userLevel"));
	

	
		return mv;
	}
	

	@GetMapping("/beforeSearch") 
	public ModelAndView beforeSearch(HttpSession session) {
		DataTablesDTO dataTablesDTO = new DataTablesDTO();
		logger.info("beforeBtn");
		List<DataTablesDTO> searchDataTablesList = commonService.getSearchTableList(dataTablesDTO);
		ModelAndView mv = new ModelAndView();
		mv.addObject("searchDataTablesList", searchDataTablesList);
		mv.setViewName("index");
		mv.addObject("mainFrame", "/mainFrame");
		mv.addObject("uml", session.getAttribute("uml"));	
		mv.addObject("level", session.getAttribute("level"));	
		mv.addObject("loginId", session.getAttribute("loginId"));	
		mv.addObject("userLevel", session.getAttribute("userLevel"));

		return mv;
	}
	
	@PostMapping("/loginProc")	//로그인버튼 클릭시 실행
	@ResponseBody			//사용자가 입력한 데이터를 스크립트 단에서 가져오기때문에 해당 어노테이션 사용 or 클래스에 @RestController 사용
	public int login(LoginDTO loginDTO, HttpServletResponse resp, HttpServletRequest request) {	//
			//메소드 타입이 int인 이유는 입력한 데이터가 존재하는 데이터인지 확인만 하고 확인한결과값을 클라이언트로 넘겨줌 
			//로그인 성공 후 또는 로그인 실패 후 작업은 스크립트에서 해결
		int result = 0;		 //db조회 결과값을 담을 변수 초기화작업
		try {				//try catch문을 쓰는 이유는 서비스단에서 이뤄지는 암호화작업에 예외처리때문에 해당메소드에서도 예외처리해줘야함
			result = commonService.loginProc(loginDTO);	//요청데이터를 logunDTO 담고 loginProc 메소드에 인수값으로 전달
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result == 1) {	//로그인 성공
			
			Cookie coo = new Cookie("IdCookie", loginDTO.getId());		//쿠키생성 value는 id
			request.getSession(true).setAttribute("user", loginDTO);	//로그인정보 세션저장
			int userLevel = commonService.getLevel(loginDTO.getId());	//로그인정보를 가지고 db에저장된 해당 사용자의 권한 level조회
			request.getSession(true).setAttribute("level", userLevel);	//사용자 권한레벨 세션저장

			if ("true".contentEquals(loginDTO.getRememberId())) {		//체크박스에 체크를 했을경우
				coo.setMaxAge(60 * 60 * 24);	//쿠키유지기간 30일
				coo.setPath("/");				//쿠키가 저장되는 경로
				resp.addCookie(coo);			//쿠키 추가

			} else if ("false".contentEquals(loginDTO.getRememberId())) {	//체크박스 체크를 안했을경우	
				coo.setMaxAge(0);		//유지기간 0초 -> 쿠키가 없음
				coo.setPath("/");

			}
		} else if (result == 0) {		//로그인 실패
			// 0인상태 그대로 반환
		}
	
		return result;
	}

	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");	//세션 삭제
		session.removeAttribute("level");
		session.removeAttribute("uml");
		session.removeAttribute("level");
		session.removeAttribute("loginId");
		session.removeAttribute("userLevel");
//		SessionUtil.removeAttribute("user");
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
	@ResponseBody	//xss 필터 적용을 확인하기위한 메소드
	public String xssTest(String sendTxt, HttpSession session) {
		session.setAttribute("txt", sendTxt);
		return sendTxt;
	}
	
	@RequestMapping("/popup")
	@ResponseBody
	public String popup (HttpSession session) {	//xss 필터 적용을 확인하기위한 메소드
	String txt = (String) session.getAttribute("txt");
		return txt;
	}

	@PostMapping("/Up")
	@ResponseBody
	public int up(HttpSession session) {	//권한레벨 올리는 메소드
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
	public int Down(HttpSession session) {//권한레벨 내리는 메소드
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
	

}
