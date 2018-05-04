package de.cdelmonte.afs.dante.utils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserContextFilter implements Filter {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserContextFilter.class);

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

    LOGGER.debug("I am entering the dante afs service id with auth token: {}",
        httpServletRequest.getHeader("Authorization"));

    UserContextHolder.getContext()
        .setCorrelationId(httpServletRequest.getHeader(UserContext.CORRELATION_ID));
    UserContextHolder.getContext().setUserId(httpServletRequest.getHeader(UserContext.USER_ID));
    UserContextHolder.getContext()
        .setAuthToken(httpServletRequest.getHeader(UserContext.AUTH_TOKEN));

    filterChain.doFilter(httpServletRequest, servletResponse);
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {}

  @Override
  public void destroy() {}
}
