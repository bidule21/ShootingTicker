package de.janbrodda.shootingticker.server.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.google.appengine.tools.appstats.AppstatsFilter;

public class CustomAppstatsFilter extends AppstatsFilter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		if (request instanceof HttpServletRequest) {
			String url = ((HttpServletRequest) request).getRequestURL().toString();

			// We do not want these to show up in appstats
			if (url.contains("appstats")
					|| url.contains("_ah/")) {
				chain.doFilter(request, response);
				return;
			} else {
				super.doFilter(request, response, chain);
			}
		}
	}
}
