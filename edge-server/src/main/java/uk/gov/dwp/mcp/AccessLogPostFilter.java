package uk.gov.dwp.mcp;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class AccessLogPostFilter extends ZuulFilter {

	private static Logger LOGGER = LoggerFactory.getLogger(AccessLogPostFilter.class);

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public Object run() {
		final RequestContext ctx = RequestContext.getCurrentContext();
		final HttpServletResponse response = ctx.getResponse();

		LOGGER.info(String.format("%s response status %s", response.getStatus()));

		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}
}
