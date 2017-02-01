package uk.gov.dwp.mcp;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class AccessLogPreFilter extends ZuulFilter {

	private static Logger LOGGER = LoggerFactory.getLogger(AccessLogPreFilter.class);

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public Object run() {
		final RequestContext ctx = RequestContext.getCurrentContext();
		final HttpServletRequest request = ctx.getRequest();

		LOGGER.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}
}
