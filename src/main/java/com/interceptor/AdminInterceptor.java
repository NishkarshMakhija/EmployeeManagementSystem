package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.bean.Employee;

public class AdminInterceptor implements HandlerInterceptor {
	
	private void addFlashAttribute(HttpServletRequest request, HttpServletResponse response,String key, String value) {
		// create a flashmap
		FlashMap flashMap = new FlashMap();

		// store the message
		flashMap.put(key, value);

		// create a flashmapMapManger with `request`
		FlashMapManager flashMapManager = RequestContextUtils.getFlashMapManager(request);

		// save the flash map data in session with falshMapManager
		flashMapManager.saveOutputFlashMap(flashMap, request, response);
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		String role = (String) session.getAttribute("role");
		
//		System.out.println("Pre handle Admin");
//		System.out.println("Role = "+role);
//		System.out.println(request.getContextPath());
		
		if(role != null && role.equalsIgnoreCase("admin")) {
			Employee employee = (Employee) session.getAttribute("employee");
			if(employee != null) {
				return true;
			}
		}
		
		addFlashAttribute(request,response,"error","Your login session is expired. Please Login again.");
		response.sendRedirect(request.getContextPath());
		
		return false;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	

}
