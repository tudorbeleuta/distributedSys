package filtering;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;

import model.User;

public class LoginFilter implements Filter {

	private ServletContext context;
	private static final String ROLE_ADMIN = "admin";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		context = filterConfig.getServletContext();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		if (isAdmin(request)) {
			chain.doFilter(request, response);
		} else {
			this.context.log("permission denied!");
			if (request instanceof HttpServletRequest) {
				HttpServletResponse resp = (HttpServletResponse) response;
				resp.sendRedirect("app/markup/AdminPermissionDenied.html");
			}
		}

		return;

	}

	private boolean isAdmin(ServletRequest request) {

		return checkSessionUserRole(getSession(request), ROLE_ADMIN);
	}

	private boolean checkSessionUserRole(HttpSession session, String role) {
		if (session != null) {
			User usr = (User) session.getAttribute("user");
			if (usr != null && usr.getRole().equals(role))
				return true;
		}
		return false;
	}

	private HttpSession getSession(ServletRequest request) {
		if (request instanceof HttpServletRequest) {
			HttpServletRequest req = (HttpServletRequest) request;
			return req.getSession(false);
		}
		return null;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
