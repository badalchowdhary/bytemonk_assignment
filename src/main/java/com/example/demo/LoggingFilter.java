package com.example.demo;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class LoggingFilter implements Filter {
    private final Logger logger = Logger.getLogger(LoggingFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) request;


        logger.info("Request received at: " + LocalDateTime.now());
        logger.info("Client IP: " + req.getRemoteAddr());
        logger.info("HTTP Method: " + req.getMethod());
        logger.info("Endpoint accessed: " + req.getRequestURI());


        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
