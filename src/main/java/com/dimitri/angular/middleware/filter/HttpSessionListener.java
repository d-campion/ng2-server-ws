package com.dimitri.angular.middleware.filter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HttpSessionEvent listener
 * 
 * @author Dimitri <campion.dimitri@gmail.com>
 * @version 0.0.1
 *
 */

//public class HttpSessionListener implements HttpSessionListener {
public class HttpSessionListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpSessionListener.class);

	/**
	 * All user session connected to BDC
	 */
	private static Map<String, HttpSession> sessions = new HashMap<String, HttpSession>();


	public void sessionCreated(final HttpSessionEvent event) {
		sessions.put(event.getSession().getId(), event.getSession());
		LOGGER.debug(new StringBuffer("new session created : ").append(event.getSession().getId()).toString());
	}


	public void sessionDestroyed(final HttpSessionEvent event) {
		sessions.remove(event.getSession().getId());
	}

	/**
	 * Default constructor
	 */
	public HttpSessionListener() {
		super();
	}
}