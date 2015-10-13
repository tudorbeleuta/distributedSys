package controller;

import java.io.IOException;

import javax.jws.WebService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Authenticator extends HttpServlet{
	
	UserManager usrManager;
	
	public void init(ServletConfig config){
		usrManager = new UserManager();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		HttpSession appSession=request.getSession();
		ServletContext context=appSession.getServletContext();
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		usrManager.addUser(username, password, "admin");
	}
}
