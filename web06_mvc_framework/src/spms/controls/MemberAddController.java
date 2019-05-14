package spms.controls;

import java.util.Map;

import spms.bind.DataBinding;
import spms.dao.MysqlMemberDao;
import spms.dto.Member;

public class MemberAddController  implements Controller, DataBinding{
	MysqlMemberDao memberDao;
	public MemberAddController setMemberDao(MysqlMemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Member member = (Member)model.get("member");
		if(member.getEmail() == null) {
			return "/member/MemberForm.jsp";
		}
		else {
			memberDao.insert(member);
			return "redirect:list.do";
		}
	}
	@Override
	public Object[] getDataBinders() {
		return new Object[] {
				"member",spms.dto.Member.class
		};
	}

}
