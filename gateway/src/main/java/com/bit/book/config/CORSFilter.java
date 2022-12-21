package com.bit.book.config;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Configuration
@WebFilter(filterName = "requestUriServletFilter", urlPatterns = "/**")
public class CORSFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String mainIndex = req.getHeader("Origin");
        if (mainIndex == null) {
            mainIndex = req.getHeader("Referer");
            if (mainIndex != null) {
                Pattern p = Pattern.compile("http://[^/]+");
                Matcher m = p.matcher(mainIndex);
                if (m.find()) {
                    mainIndex = m.group(0);
                }
            }
        }
        mainIndex += ", gateway";
        res.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        res.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        res.addHeader("Access-Control-Max-Age", "3600");
        res.addHeader("Access-Control-Allow-Credentials", "true");
        res.addHeader("Access-Control-Allow-Origin", mainIndex);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
