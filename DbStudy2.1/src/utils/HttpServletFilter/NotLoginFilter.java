package utils.HttpServletFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class NotLoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println ("检测登陆过滤器已开启");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        if(req.getSession ().getAttribute ("user")==null){
            req.getRequestDispatcher ("/main_jsp/login.jsp").forward (servletRequest,servletResponse);
        }
        filterChain.doFilter (servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println ("检测登陆过滤器已关闭");
    }
}
