package filter;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = {"/dataroom/*","*.kosmo"})
public class AuthenticationFilter  extends HttpFilter implements Filter {
	
	public AuthenticationFilter() {
		System.out.println("인증필터 생성자");
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("인증필터-사전작업(로그인 여부 판단)");
		//로그인 확인
		HttpServletRequest req=(HttpServletRequest)request;
		Object checkLogin=req.getSession().getAttribute("USER-ID");
		if(checkLogin ==null) {
			req.setAttribute("NOT-LOGIN","로그인후 이용하세요");
			req.getRequestDispatcher("/session/Login.jsp").forward(request, response);
			return;
		}
		
		chain.doFilter(request, response);
		
	}
}
