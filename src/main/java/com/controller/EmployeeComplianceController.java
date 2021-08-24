package com.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Compliance;
import com.bean.Employee;
import com.bean.StatusReport;
import com.service.ComplianceService;
import com.service.StatusReportService;

@Controller
@RequestMapping("/user")
public class EmployeeComplianceController {
	
	@Autowired
	private ComplianceService complianceService;
	
	@Autowired
	private StatusReportService statusReportService;
	
	@RequestMapping("/newRegulations")
	public ModelAndView viewNewRegulations(Model m, HttpServletResponse response, HttpSession session) throws IOException {
		ModelAndView mav = new ModelAndView();
		
		Employee employee = (Employee) session.getAttribute("employee");
		if(employee == null) {
			response.sendRedirect("/");
			return null;
		}
		
		List<Compliance> allCompliances = complianceService.getNewComplianceForEmployee(employee);
		
		m.addAttribute("allCompliances",allCompliances); 
		mav.setViewName("/regulation/employeeViewRegulation");
		return mav;
	}
	
	@RequestMapping("/oldRegulations")
	public ModelAndView viewOldRegulations(Model m, HttpServletResponse response, HttpSession session) throws IOException {
		ModelAndView mav = new ModelAndView();
		
		Employee employee = (Employee) session.getAttribute("employee");
		if(employee == null) {
			response.sendRedirect("/");
			return null;
		}
		
		List<Compliance> allCompliances = complianceService.getOldComplianceForEmployee(employee);
		
		m.addAttribute("allCompliances",allCompliances); 
		mav.setViewName("/regulation/employee/viewOldRegulation");
		return mav;
	}
	
	@RequestMapping(value = "/addComment/{complianceId}", method = RequestMethod.GET)
	public ModelAndView addComment(@PathVariable(value = "complianceId") int complianceId, Model m, HttpSession session, HttpServletResponse response) throws IOException {
		ModelAndView mav = new ModelAndView();
		
		Compliance compliance = complianceService.getComplianceById(complianceId);
		if(compliance == null) {
			response.sendRedirect("/user/newRegulations");
			return null;
		}
		
		m.addAttribute("compliance",compliance);
		mav.setViewName("/regulation/employee/addComment");
		return mav;
	}
	
	@RequestMapping(value = "/addComment/{complianceId}", method = RequestMethod.POST)
	public String addCommentSubmit(@PathVariable(value = "complianceId") int complianceId, Model m,HttpServletRequest req,final RedirectAttributes redirectAttributes, HttpSession session) {
		
		Compliance compliance = complianceService.getComplianceById(complianceId);
		
		StatusReport statusReport = new StatusReport();
		statusReport.setComments(req.getParameter("comment"));
		Date date = new Date();
		statusReport.setCreateDate(date);
		statusReport.setCompliance(compliance);
		
		Employee employee = (Employee) session.getAttribute("employee");
		
		statusReport.setEmployee(employee);
		statusReport.setDepartment(employee.getDepartment());
		
		int result = statusReportService.addStatusReport(statusReport,compliance);
		
		if(result>0) {
			redirectAttributes.addFlashAttribute("success","Comment added successfully");
		}
		else {
			redirectAttributes.addFlashAttribute("error","Some error occurred in adding Comment");
		}
		
		return "redirect:/user/newRegulations";
	}
	
	
	

}
