package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

//@webFilter 애너테이션을 통해 urlPatterns에 명시한 요청과 매핑
@WebFilter(filterName="AnnoFilter", urlPatterns="/15FilterListener/AnnoFilter.jsp")
public class AnnoFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//매개변수로 전달된 값을 읽어와서 콘솔에 출력
		String searchField = request.getParameter("searchField");
		String searchWord = request.getParameter("searchWord");
		System.out.println("검색 필드 : " + searchField);
		System.out.println("검색어 : " + searchWord);
		
		//jsp로 제어권 전달.
		chain.doFilter(request, response);
		
		//아래 코드를 통해 어디서 필터가 실행되는지 확인 가능
		System.out.println("AnnoFilter 후처리");
	}
	
	@Override
	public void destroy() {
		
	}
	
	
}
