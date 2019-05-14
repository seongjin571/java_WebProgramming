package spms.controls;

import java.util.Map;

import spms.dao.MysqlMemberDao;

public class MemberListController implements Controller{
	MysqlMemberDao memberDao;
	public MemberListController setMemberDao(MysqlMemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		model.put("members",memberDao.selectList());
		return "/member/MemberList.jsp";
	}

}
