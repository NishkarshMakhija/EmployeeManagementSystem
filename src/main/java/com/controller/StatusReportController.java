package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bean.Compliance;
import com.bean.Employee;
import com.bean.StatusReport;
import com.service.ComplianceService;
import com.service.StatusReportService;

@Controller
@RequestMapping("/user")
public class StatusReportController {
	
	@Autowired
	private ComplianceService complianceService;
	
	@Autowired
	private StatusReportService statusReportService;
	
	@RequestMapping(value = "/viewComments/{complianceId}", method = RequestMethod.GET)
	public ModelAndView viewComment(@PathVariable(value = "complianceId") int complianceId, Model m,HttpServletRequest req,final RedirectAttributes redirectAttributes, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		Employee employee = (Employee) session.getAttribute("employee");
		
		List<StatusReport> allStatusReport = statusReportService.getStatusReport(complianceId,employee);
		
		m.addAttribute("allStatusReport",allStatusReport); 
		mav.setViewName("/regulation/employee/viewComments");
		return mav;
	}
	
	@RequestMapping(value = "/updateComments/{complianceId}", method = RequestMethod.GET)
	public ModelAndView updateComments(@PathVariable(value = "complianceId") int complianceId, Model m,HttpServletRequest req,final RedirectAttributes redirectAttributes, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		Compliance compliance = complianceService.getComplianceById(complianceId);
		
		m.addAttribute("compliance",compliance); 
		mav.setViewName("/regulation/employee/updateComments");
		return mav;
	}

}
