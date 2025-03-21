package servlet;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import membership.MemberDAO;
import membership.MemberDTO;

@WebServlet(urlPatterns = "/12Servlet/MemberAuth.mvc",
			initParams = {
					@WebInitParam(name="admin_id", value="nakja"),
					@WebInitParam(name="admin_pw", value="1234")
			})

public class MemberAuth extends HttpServlet{
	private static final long serialVersionUID = 1L;
	MemberDAO dao;
	
	
	
	@Override
	public void init() throws ServletException {
		// application 내장 객체 얻기
		ServletContext application = this.getServletContext();
		
		String driver = application.getInitParameter("MySQLDriver");
		String url = application.getInitParameter("MySQLURL");
		String id = application.getInitParameter("MySQLId");
		String pwd = application.getInitParameter("MySQLPwd");
		
		dao = new MemberDAO(driver, url, id, pwd);
	}




	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String admin_id = this.getInitParameter("admin_id");
		
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		
		MemberDTO memberDTO = dao.getMemberDTO(id, pass);
		
		String memberName = memberDTO.getName();
		if (memberName != null) {
			req.setAttribute("authMessage", memberName + " 회원님 반갑다 얌마");
		} else {
				if (admin_id.equals(id))
					req.setAttribute("authMessage", admin_id + "는 킹왕짱 관리자");
				else 
					req.setAttribute("authMessage", "넌 회원이 아니잖아");
		}
		req.getRequestDispatcher("/12Servlet/MemberAuth.jsp").forward(req, resp);
			
	}
	@Override
	public void destroy() {
		dao.close();
	}

	
	
}
