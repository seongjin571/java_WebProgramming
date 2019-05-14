package spms.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import spms.bind.DataBinding;
import spms.dao.MysqlMemberDao;
import spms.dto.Member;

public class LoginController implements Controller, DataBinding {
	MysqlMemberDao memberDao;
	public LoginController setMemberDao(MysqlMemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		if (model.get("email") == null) {
			return "LoginForm.jsp";
		}else {
			Member member = memberDao.exist(model.get("email").toString(),model.get("password").toString());
			if(member != null) {
		        HttpSession session = (HttpSession)model.get("session");
		        session.setAttribute("member", member);
		        return "redirect:../member/list.do";
			}
			else {
				return "/auth/LoginFail.jsp";
			}
			
		}
	}
	@Override
	public Object[] getDataBinders() {
		return new Object[] {
				
		}
	}

}




