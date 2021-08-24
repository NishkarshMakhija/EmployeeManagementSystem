package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;


import com.bean.Login;
import com.service.LoginService;
import com.service.PasswordService;
import com.service.DepartmentService;
import com.service.EmployeeService;
import com.bean.Employee;  

@Controller
public class MainController {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	DepartmentService departmentService;

	
	@RequestMapping("/")
	public ModelAndView index(Model model, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		String role = (String) session.getAttribute("role");
		
		if(role != null) {
			Employee employee = (Employee) session.getAttribute("employee");
			
			if(employee != null && role.equalsIgnoreCase("admin")) {
				mav.setViewName("redirect:/admin/department");
				return mav;
			}
			else if(employee != null && role.equalsIgnoreCase("admin")) {
				mav.setViewName("redirect:/user/newRegulations");
				return mav;
			}
		}
		
		mav.setViewName("index1");
		return mav;
	}
	
	@RequestMapping(value = "/checkLogin",method = RequestMethod.POST)
	public String checkLogin(HttpServletRequest req, RedirectAttributes redirectAttributes, HttpSession session) {
		Login login = new Login();
		login.setUserid(Integer.parseInt(req.getParameter("empId")));
		String encryptedPassword = PasswordService.getMD5(req.getParameter("password"));
		login.setPassword(encryptedPassword);
		
		Login loggedUser = loginService.checkLogin(login);
		if(loggedUser == null) {
			session.removeAttribute("employee");
			session.removeAttribute("role");
			redirectAttributes.addFlashAttribute("error","Either Employee Id or Password is wrong.");
			return "redirect:/";
		}
		else {
			Employee employee = employeeService.getEmployeeById(loggedUser.getUserid());
			session.setAttribute("employee", employee);
			
			if(loggedUser.getRole().equalsIgnoreCase("admin")) {
				session.setAttribute("role", "admin");
				return "redirect:/admin/department";
			}
			else {
				session.setAttribute("role", "employee");
				return "redirect:/user/newRegulations";
			}
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest req, RedirectAttributes redirectAttributes, HttpSession session) {
		
		session.removeAttribute("employee");
		session.removeAttribute("role");
		session.invalidate();
		return "redirect:/";
	}
	
	
}
