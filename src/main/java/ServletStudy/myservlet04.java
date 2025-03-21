package ServletStudy;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

WebServlet("/12Servlet/MyServlet04.do")
public class myservlet04  extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	String[] arr1 = {"대한민국", "미국"};
	String[] arr2 = {"서울", "워싱턴"};
	
		
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException, IOException {
			for(int i = 0; i <arr1.length; i++) {
			req.setAttribute("country", arr1);
			req.setAttribute("capital", arr2);
			req.getRequestDispatcher("/12Servlet/MyServlet04.jsp").forward(req, resp);
			}
		}
}
