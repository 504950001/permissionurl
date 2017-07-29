package top.zemal.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.zemal.VO.SessionCache;
import top.zemal.config.CommonUrlConfig;
import top.zemal.service.BaseService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Set;

@WebServlet
public class AuthInterceptor implements HandlerInterceptor {

	public final static String SESSION_KEY = "username";

	@Autowired
	BaseService baseService;

	@Autowired
	CommonUrlConfig commonUrlConfig;

	/**
	 * 1.在调用控制器方法前，拦截
	 * 
	 * @return false 拦截
	 * @return true 放行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		System.out.println("----进入拦截器----");
		String uri = request.getRequestURI();

		// 1.公用url拦截器进行放行
		if (uri.endsWith("/login") || uri.endsWith("/logout")
				|| uri.endsWith("/unauthorized")) {
			return true;// 放行
		}

		if (uri.endsWith("/swagger-ui.html")) { // 接口
			return true;// 放行
		}

		// 2.登录拦截
		HttpSession session = request.getSession();
		System.out.println(SESSION_KEY);
		//判断用户ID是否存在，不存在就跳转到登录界面
		if(session.getAttribute(SESSION_KEY) == null){
			System.out.println("---session is null---");
			response.sendRedirect(request.getContextPath()+"/login");
			return false;
		}

		// 登录后的首页,如果一个权限的没有这展示为空
		if (uri.endsWith("/index")) { // 接口
			System.out.println("---登录后都可以访问的url---");
			return true;// 放行
		}

		// 3. 匿名访问拦截器 (暂无)

		// 4.权限拦截
		SessionCache sessionCache = (SessionCache) session.getAttribute(AuthInterceptor.SESSION_KEY);

		if (sessionCache == null){
			response.sendRedirect(request.getContextPath()+"/unauthorized");
			return false;
		}

		if (sessionCache != null){
			Set<String> stringSet = sessionCache.getMenuUrl();
			if (stringSet != null && stringSet.size()>0){
				if (!stringSet.contains(uri)){
					response.sendRedirect(request.getContextPath()+"/unauthorized");
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * 2.在调用控制器方法后，拦截（在生成视图之前）
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
	}

	/**
	 * 3.在视图生成之后（后台所有所有逻辑都完成后）
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
