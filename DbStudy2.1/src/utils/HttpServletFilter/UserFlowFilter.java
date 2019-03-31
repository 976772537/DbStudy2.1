package utils.HttpServletFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UserFlowFilter implements Filter {
    private static Long userCount=0L;
    public static void setUserCount(long count){
        userCount=count;
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        filterConfig.getServletContext ().setAttribute ("userCount",userCount);
        System.out.println ("用户流量过滤已启动");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        userCount++;
        req.getServletContext ().setAttribute ("userCount",userCount);
        filterChain.doFilter (servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
