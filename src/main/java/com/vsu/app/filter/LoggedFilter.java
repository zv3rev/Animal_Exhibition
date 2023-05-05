package com.vsu.app.filter;

import lombok.extern.java.Log;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;

@Configuration
@Log
public class LoggedFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest.getAttribute("loggedId") == null){
            log.log(Level.INFO, "Unlogged user tried to get access to /user/* pages");
            ((HttpServletResponse) servletResponse).sendRedirect("/");
            return;
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Bean
    public FilterRegistrationBean<LoggedFilter> loggingFilter(){
        FilterRegistrationBean<LoggedFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new LoggedFilter());
        registrationBean.addUrlPatterns("/users/*");

        return registrationBean;
    }
}
