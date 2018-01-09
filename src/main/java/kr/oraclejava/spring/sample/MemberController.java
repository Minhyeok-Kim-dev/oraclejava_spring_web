package kr.oraclejava.spring.sample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {
	private static ApplicationContext ctx;
	private static JdbcTemplate jdbcTemplate;
	private static NamedParameterJdbcTemplate jdbcTemplate2;
	
	static {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = ctx.getBean(JdbcTemplate.class);
		jdbcTemplate2 = ctx.getBean(NamedParameterJdbcTemplate.class);	// Parameter 사용시
	}
	
	@RequestMapping(value="/member/list", method=RequestMethod.GET)
	public String displayMembers(Map<String, Object> model) {
		
		String sql = "SELECT * FROM members";
		List<Map<String, Object>> members = jdbcTemplate.queryForList(sql);
		
		model.put("members", members);
		
		return "member/list";
	}
	
	@RequestMapping(value="/member/add", method=RequestMethod.GET)
	public String createMember(Map<String, Object> model) {
		model.put("memberForm", new MemberForm());
		return "member/add";
	}
	
	@RequestMapping(value="/member/add", method=RequestMethod.POST)
	public String createMember(MemberForm form) {
		Member member = new Member();
		member.setMemberId(form.getMemberId());
		member.setPasswd(form.getPasswd());
		member.setEmail(form.getEmail());
		member.setUserType(form.getUserType());
		member.setActive("1");
		
		String sql = "INSERT INTO members (MEMBERID, PASSWD, EMAIL, USERTYPE, ACTIVE) VALUES ("
					+":memberId, :passwd, :email, :userType, :active)";
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("memberId", member.getMemberId());
		parameters.put("passwd", member.getPasswd());
		parameters.put("email", member.getEmail());
		parameters.put("userType", member.getUserType());
		parameters.put("active", member.getActive());
	
		//jdbcTemplate.update(sql, parameters);
		jdbcTemplate2.update(sql, parameters);
					
		return "redirect:/member/list";
	}
	
	@RequestMapping(value="/member/edit/{memberId}", method=RequestMethod.GET)
	public String EditMember(Map<String, Object> model,
			@PathVariable String memberId) {
		
		Map<String, Object> map = 
				jdbcTemplate.queryForMap("SELECT * FROM members WHERE memberId = ?", memberId);
		
		MemberForm form = new MemberForm();
		form.setMemberId(map.get("MEMBERID").toString());
		form.setPasswd(map.get("PASSWD").toString());
		form.setEmail(map.get("EMAIL").toString());
		form.setUserType(map.get("USERTYPE").toString());
		
		model.put("memberForm", form);
		
		return "member/edit";
	}
	
	@RequestMapping(value="/member/edit/{memberId}", method=RequestMethod.POST)
	public String EditMember(MemberForm form,
			@PathVariable String memberId) {
		Member member = new Member();
		member.setPasswd(form.getPasswd());
		member.setEmail(form.getEmail());
		member.setUserType(form.getUserType());
		
		String sql = "UPDATE members SET PASSWD=:passwd, EMAIL=:email, USERTYPE=:userType"
			 	   + " WHERE MEMBERID = :memberId";
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("passwd", member.getPasswd());
		parameters.put("email", member.getEmail());
		parameters.put("userType", member.getUserType());
		parameters.put("memberId", memberId);
		
		jdbcTemplate2.update(sql, parameters);
		
		return "redirect:/member/list";
	}
	
	@RequestMapping(value="/member/delete/{memberId}", method=RequestMethod.GET)
	public String DeleteMember(@PathVariable String memberId) {
		
		jdbcTemplate.update("DELETE FROM members WHERE memberId = ?", memberId);
		
		return "redirect:/member/list";
	}
}
