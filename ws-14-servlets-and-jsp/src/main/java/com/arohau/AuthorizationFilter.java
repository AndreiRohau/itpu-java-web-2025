package com.arohau;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class AuthorizationFilter implements Filter {
	private static final String COMMAND = "command";
	private static final String ROLE = "role";
	private static final String LOGIN_COMMAND = "login";
	private static final String LOGOUT_COMMAND = "logout";

	Map<String, Set<String>> roleCommands;

	@Override
	public void init(FilterConfig config) throws ServletException {
		roleCommands = new HashMap<>();

		// TODO: Add your code here.
		Enumeration<String> initParameterNames = config.getInitParameterNames();
		while (initParameterNames.hasMoreElements()) {
			String initParameterName = initParameterNames.nextElement();
			String[] initParameterArray = config.getInitParameter(initParameterName).split(" ");
			roleCommands.put(initParameterName, Set.of(initParameterArray));
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// optional design with isAllowedProceeding
		boolean isAllowedProceeding = true;

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		if (req.getServletPath().equalsIgnoreCase("/commandHandler") ||
				req.getParameter(COMMAND).matches("logout") ||
				req.getParameter(COMMAND).matches("log") ||
				req.getParameter(COMMAND).matches("reg") ||
				req.getParameter(COMMAND).matches("changeLanguage") ||
				req.getParameter(COMMAND).matches("goToPage") ||
				req.getSession().getAttribute(ROLE) != null) {

			chain.doFilter(request, response);
		}if (hasSomeCondition()) {
			res.sendError(403);
		} else {
			res.sendRedirect("index.jsp");
		}
	}

	private static boolean hasSomeCondition() {
		return false;
	}
}