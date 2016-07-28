package geocollector.servlet.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.crypto.hash.Sha256Hash;

import geocollector.dao.LayerDao;
import geocollector.dao.LayerDaoImpl;
import geocollector.dao.PointDao;
import geocollector.dao.PointDaoImpl;
import geocollector.dao.UserDao;
import geocollector.dao.UserDaoImpl;
import geocollector.model.Layer;
import geocollector.model.Point;
import geocollector.model.Role;
import geocollector.model.User;

/* SERVLET PARA CRIAR USUARIO NO BANCO */

@WebServlet("/AllTest")
public class AllTest extends HttpServlet {

	private static final long serialVersionUID = -8889263614960112108L;

	private UserDao userDao;
	private LayerDao layerDao;
	private PointDao pointDao;

	@Override
	public void init() {
		userDao = new UserDaoImpl();
		pointDao = new PointDaoImpl();
		layerDao = new LayerDaoImpl();
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		


		User user = new User();
		user.setEmail("guilhermemrr@gmail.com");
		user.setName("Guilherme");
		user.setPassword(new Sha256Hash("123456").toHex());
		user.setActivated(true);
		user.setRole(Role.ROLE_USER);
		user = userDao.insertUser(user);
		

		
		User user2 = new User();
		user2.setEmail("222@gmail.com");
		user2.setName("222");
		user2.setPassword(new Sha256Hash("123456").toHex());
		user2.setActivated(true);
		user2.setRole(Role.ROLE_USER);
		user2 = userDao.insertUser(user2);
		
		System.err.println("## ALL USER:");
		System.err.println(userDao.getAll());

		
		System.err.println("## RECUPERADO POR EMAIL:"+ user2.toString());
		boolean existEmail= userDao.exists("guilhermemrr@gmail.com");
		System.err.println("exist email:"+existEmail);

		

		
		userDao.active(user);
		user = userDao.findById(user.getId());
		System.out.println("### USER ATIVADO:" + user.toString());
		
		System.err.println("### FIND BY EMAIL EMAIL: "+ userDao.findByEmail("guilhermemrr@gmail.com"));

		
		System.err.println("### ID USANDO EMAIL: "+ userDao.getId("guilhermemrr@gmail.com"));
		
		Layer layer1 = new Layer();
		layer1.setColor("blue");
		layer1.setName("layer-1");
		layer1.setUser(user);
		layer1 =  layerDao.insertLayer(layer1);


		Layer layer2 = new Layer();
		layer2.setColor("red");
		layer2.setName("layer-2");
		layer2.setUser(user);
		layer2 =  layerDao.insertLayer(layer2);
		
		user.addLayer(layer1);
		user.addLayer(layer2);
		userDao.update(user);
		
		
		Point p1 = new Point();
		p1.setLayer(layer1);
		p1 = pointDao.insertPoint(p1);
		
		Point p2 = new Point();
		p2.setLayer(layer1);
		p2 = pointDao.insertPoint(p2);
			
		
		layer1.addPoint(p1);
		layer1.addPoint(p2);

		layerDao.update(layer1);
		//pointDao.create(p);
		
		System.err.println("### 2 LAYERS INSERIDAS. 2 PONTOS NA LAYER 1.");
		
		System.err.println(pointDao.getAll());
		System.err.println(layerDao.getAll());
		System.err.println(userDao.getAll());
		
		System.err.println("### TESTE UPDATE COLOR LAYER 1");
		user = userDao.findById(1);
		for(Layer l : user.getLayers())
			System.err.println(l.toString());
		
		layer1.setColor("RED");
		layerDao.update(layer1);
		
		user = userDao.findById(1);
		for(Layer l : user.getLayers())
			System.err.println(l.toString());
		
		System.err.println("FIM TESTE");

		
		System.err.println("## REMOVENDO USUARIOS DEVE REMOVER TUDO ::::");
		userDao.delete(user);
		System.err.println(pointDao.getAll());
		System.err.println(layerDao.getAll());
		System.err.println(userDao.getAll());		
		

		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.print("OLHAR CONSOLE");
		out.flush();
		out.close();
	}
}
