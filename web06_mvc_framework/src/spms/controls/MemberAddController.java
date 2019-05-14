package spms.controls;

import java.util.Map;

import spms.dao.MysqlMemberDao;
import spms.dto.Member;

public class MemberAddController  implements Controller{
	MysqlMemberDao memberDao;
	public MemberAddController setMemberDao(MysqlMemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		if(model.get("member") == null) {
			return "/member/MemberForm.jsp";
		}
		else {
			Member member =(Member)model.get("member");
			memberDao.insert(member);
			return "redirect:list.do";
		}
	}

}
