package com.dimitri.angular.middleware.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

/**
 * Enabling CORS support - Access-Control-Allow-Origin Not ever used !!!
 * 
 * @author Dimitri <campion.dimitri@gmail.com>
 * @version 0.0.1
 *
 */
public class CORSFilter extends OncePerRequestFilter {
	private static final Logger LOGGER = LoggerFactory.getLogger(CORSFilter.class);

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain)
			throws ServletException, IOException {

		LOGGER.info("Filtering on...........................................................");
		CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
		if (csrf != null) {
			Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
			String token = csrf.getToken();
			if (cookie == null || token != null && !token.equals(cookie.getValue())) {
				cookie = new Cookie("XSRF-TOKEN", token);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, HEAD, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers",
				"Origin, authorization, accept, X-Requested-With,x-csrftoken, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers,Authorization");

		filterChain.doFilter(request, response);

	}
	
	

	/**
	 * Default constructor
	 */
	public CORSFilter() {
		super();
	}
}