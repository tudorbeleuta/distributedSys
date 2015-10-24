package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

public class Authenticator extends HttpServlet{
	
	UserManager usrManager;
	
	public void init(ServletConfig config){
		this.usrManager = new UserManager();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String path= request.getContextPath();
		User usr=usrManager.getUser(username, password);
		if(usr!=null){
			HttpSession appSession=request.getSession();
			appSession.setAttribute("user", usr);
			response.sendRedirect("/FlightOrganizer/app/markup/logged/mainPage.html");
		}
		else{
			response.sendRedirect("/FlightOrganizer/app/markup/LoginFail.html");
		}
		return;
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		usrManager.addUser(username, password, "admin");
	}
}
