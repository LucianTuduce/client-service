package ro.fortech.beans;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ro.fortech.constants.Constants;

@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthFilter implements Filter {

	public AuthFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			HttpSession ses = req.getSession(false);
			String reqURI = req.getRequestURI();
			if (reqURI.indexOf("/loginJSF.xhtml") >= 0 || (ses != null && ses.getAttribute(Constants.AUTHORIZATION) != null)) {
				chain.doFilter(request, response);
			} else {
				res.sendRedirect(req.getContextPath() + "/loginJSF.xhtml");
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		}
	}

	@Override
	public void destroy() {

	}
}
