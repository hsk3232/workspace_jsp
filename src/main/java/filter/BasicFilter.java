package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

//필터 클래스이므로 Filter 인터페이스를 구현
public class BasicFilter implements Filter{
	FilterConfig config;

	//init()은 웹 컨테이너가 시작될 때 자동으로 호출돼 필터를 초기화 함.
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//필터 설정 정보를 config변수에 저장해 doFilter에서 사용
		config = filterConfig;
		//web.xml로부터 매핑된 <filter-name>을 읽어 옴
		String filterName = filterConfig.getFilterName();
		
		System.out.println("BasicFilter -> init() 호출됨 : " + filterName);
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		
		//초기화 매개변수를 읽어 옴 -> 필터 설정 정보가 담긴 config()
		String filterInitParam = config.getInitParameter("FILTER_INIT_PARAM");
		System.out.println("BasicFilter -> 초기화 매개변수 : " + filterInitParam);
		
		//형변환을 해야 호출할 수 있음. -> doFilter의 매개변수 request는 ServletRequest 타입으로 Http--로 형변환해야 함.
		String method = ((HttpServletRequest)request).getMethod();
		System.out.println("BasicFilter -> 전송방식 : " + method);
		
		//필터에서 할 일을 마쳤다면, 동적 자원인 jsp로 제어권을 넘김 -> 웹 브라우저에 보임.
		chain.doFilter(request, response);
	}
	
	@Override
	public void destroy() {
		System.out.println("BasicFilter -> destroy() 호출됨");
	}
	

}
