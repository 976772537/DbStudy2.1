package utils.HttpServletFilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class EncodeFilter implements Filter{

	private String charset;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		String method=req.getMethod();
		if(method.equalsIgnoreCase("post")){
			request.setCharacterEncoding(charset);
			response.setCharacterEncoding(charset);
			chain.doFilter(request, response);
		}else{
			MyRequest mr=new MyRequest(req);
		chain.doFilter(mr, response);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		String ct=config.getInitParameter("encode");
		if(ct==null||ct.trim().isEmpty()){
			charset="utf-8";
		}else{
			charset=ct;
		}
	}
}
