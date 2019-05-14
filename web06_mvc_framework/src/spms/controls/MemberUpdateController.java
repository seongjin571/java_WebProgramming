package spms.controls;

import java.util.Map;

import javax.servlet.ServletContext;

import spms.dao.MysqlMemberDao;
import spms.dto.Member;

public class MemberUpdateController implements Controller {
	MysqlMemberDao memberDao;
	public MemberUpdateController setMemberDao(MysqlMemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		if(model.get("member") == null) {
			model.put("member",memberDao.selectOne(Integer.parseInt(model.get("no").toString())));
			return "/member/MemberUpdateForm.jsp";
		}
		else {
			memberDao.update((Member)model.get("member"));
			return "redirect:list.do";
		}
	}

}
