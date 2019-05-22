package spms.controls;

import java.util.Map;

import spms.bind.DataBinding;
import spms.dao.MysqlMemberDao;

public class MemberDeleteController implements Controller,DataBinding{
	MysqlMemberDao memberDao;
	public MemberDeleteController setMemberDao(MysqlMemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		memberDao.delete(Integer.parseInt(model.get("no").toString()));
		return "redirect:list.do";
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] {
				"no",Integer.class
		};
	} 

}
