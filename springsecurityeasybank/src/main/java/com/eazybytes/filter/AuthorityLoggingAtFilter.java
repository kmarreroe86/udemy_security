package com.eazybytes.filter;


import javax.servlet.*;
import java.io.IOException;
import java.util.logging.Logger;

public class AuthorityLoggingAtFilter extends GenericFilter {
    private final Logger LOG =
            Logger.getLogger(AuthorityLoggingAtFilter.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        LOG.info("Authentication Validation is in progress");
        chain.doFilter(request, response);
    }
}
