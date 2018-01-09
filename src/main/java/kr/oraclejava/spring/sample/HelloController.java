package kr.oraclejava.spring.sample;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	// Logging관련 Wrapper 클래스
	private static final Logger logger =
			LoggerFactory.getLogger(HelloController.class);
	
	// Redirect 처리
	@RequestMapping("/hello")
	public String helloWorld() {
		logger.info("hello world");
		
		return "redirect:/hello2";
	}
	
	// Model 처리
	@RequestMapping("/hello2")
	public String helloWorld2(Map<String, Object> model) {
		model.put("msg", "Hello2");
		model.put("date", LocalDateTime.of(2018, 1, 9, 10, 5).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		
		return "hello2";
	}
	
	// Get 처리
	@RequestMapping(value="/hello3", method=RequestMethod.GET)
	public String hello3(@RequestParam("test") String test, @RequestParam("test2") String test2,
			Map<String, Object> model) {
		model.put("test", test);
		model.put("test2", test2);
		return "hello3";
	}
	
	// JSON 처리 (Ajax에서 처리 등등)
	@RequestMapping(value="/user", method=RequestMethod.GET)
	@ResponseBody
	public UserInfo getUser(@RequestParam(value="userId", required=false) String userId) {
		UserInfo info = new UserInfo();
		
		if(userId == null)
			userId = "mhKim";
		
		info.setUserId(userId);
		info.setUserName("name1");
		info.setDeptNo("10");
		
		return info;
	}
	
	// Xml 처리 (jaxb 라이브러리)
	@RequestMapping(value="/userx", method=RequestMethod.GET)
	@ResponseBody
	public UserInfoXml getUserXml(@RequestParam(value="userId", required=false) String userId) {
		UserInfoXml info = new UserInfoXml();
		
		if(userId == null)
			userId = "mhKim";
		
		info.setUserId(userId);
		info.setUserName("name1");
		info.setDeptNo("10");
		
		return info;
	}
	
	// REST 처리
	@RequestMapping(value="/userRest/{userId},{deptNo}", method=RequestMethod.GET)
	@ResponseBody
	public UserInfo getUserRest(
			@PathVariable("userId") String userId,
			@PathVariable("deptNo") String deptNo) {
		UserInfo info = new UserInfo();
		
		if(userId == null)
			userId = "mhKim";
		
		info.setUserId(userId);
		info.setUserName("name1");
		info.setDeptNo(deptNo);
		
		return info;
	}
	
}
