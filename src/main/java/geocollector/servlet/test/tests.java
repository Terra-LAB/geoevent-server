package geocollector.servlet.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* SERVLET PARA CRIAR USUARIO NO BANCO */

@WebServlet("/Test")
public class tests extends HttpServlet {

	private static final long serialVersionUID = -8889263614960112108L;

	public void init() {

	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.print("TEM SERV");
		out.flush();
		out.close();
	}
}
