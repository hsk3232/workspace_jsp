package edu.pnu;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/test") // ==> http://localhost:8080/MustHave/test
public class MyFirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		
		arg1.setContentType("text/html;"); //charset=UTF-8
		PrintWriter out = arg1.getWriter();
		out.write("<h2>안녕하세요.</h2>");
		
	}
}
