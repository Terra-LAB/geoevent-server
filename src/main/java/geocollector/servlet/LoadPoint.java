package geocollector.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import geocollector.dao.PointDao;
import geocollector.dao.PointDaoImpl;
import geocollector.model.Point;
import geocollector.util.Links;



@WebServlet("/informacoesPonto")
public class LoadPoint extends HttpServlet {
	private static final long serialVersionUID = 7531979311103648115L;
	private PointDao pointDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		pointDao = new PointDaoImpl();
		int idPoint = Integer.parseInt(request.getParameter("idPoint"));
		
		Point p = pointDao.findById(idPoint);
		
		request.setAttribute("point", p);
		request.getRequestDispatcher(Links.USER_INFORMACOESPONTO).forward(request, response);
	}
}