package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter(urlPatterns = {"/dataroom/*","*.kosmo"})

public class RunTimeFilter extends HttpFilter implements Filter {
       
   
    public RunTimeFilter() {
        System.out.println("RunTimeFilter생성자");
    }

	public void destroy() {
		System.out.println("RunTimeFilter destroy()호출");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		//사전 작업 코드
		
		System.out.println("====doFilter호출전 사전작업====");
		HttpServletRequest req=(HttpServletRequest)request;
		String uri = req.getRequestURI();
		long startTime = System.currentTimeMillis();
		chain.doFilter(request, response);//서블릿 실행
		//사후 작업 코드
		System.out.println("====doFilter호출후 사후작업====");
		long endTime = System.currentTimeMillis();
		System.out.println(String.format("요청 URI:%s,서블릿 실행시간:%s",uri,(endTime-startTime)/1000.0+"초"));
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("RunTimeFilter init()호출");
	}

}
