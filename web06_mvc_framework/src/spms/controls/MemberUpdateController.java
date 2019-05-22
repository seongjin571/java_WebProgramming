package spms.controls;

import java.util.Map;

import spms.bind.DataBinding;
import spms.dao.MysqlMemberDao;
import spms.dto.Member;

public class MemberUpdateController implements Controller,DataBinding {
	MysqlMemberDao memberDao;
	public MemberUpdateController setMemberDao(MysqlMemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Member member = (Member)model.get("member");
		if(member.getEmail() == null) {
			model.put("member",memberDao.selectOne(Integer.parseInt(model.get("no").toString())));
			return "/member/MemberUpdateForm.jsp";
		}
		else {
			memberDao.update((Member)model.get("member"));
			return "redirect:list.do";
		}
	}

	@Override
	public Object[] getDataBinders() {
		
		return new Object[] {
				"member",spms.dto.Member.class,"no",Integer.class
		};
	}

}


