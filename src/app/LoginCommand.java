package app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dom.User;
import dom.mappers.UserMapper;

public class LoginCommand implements FrontCommand{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		User user = UserMapper.find(req.getParameter("username"));
		if(user!=null && user.getPassword().equals((String)req.getParameter("password"))){
			req.setAttribute("user", user);
			req.getRequestDispatcher("WEB-INF/JSP/mainPage.jsp").forward(req, resp);
		}
		else{
			req.setAttribute("error", "Login Invalid");
			req.getRequestDispatcher("WEB-INF/JSP/login.jsp").forward(req, resp);
		}
		
		
	}
}
