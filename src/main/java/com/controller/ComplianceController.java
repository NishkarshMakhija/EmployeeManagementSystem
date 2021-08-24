package com.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bean.Compliance;
import com.bean.Department;
import com.bean.StatusReport;
import com.editor.SimpleDepartmentEditor;
import com.service.DepartmentService;
import com.service.ComplianceService;
import com.service.StatusReportService;

@Controller
@RequestMapping("/admin")
public class ComplianceController {
	
	@Autowired
	private ComplianceService complianceService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private StatusReportService statusReportService;
	
	@RequestMapping("/viewRegulation")
	public ModelAndView viewEmployee(Model m) {
		ModelAndView mav = new ModelAndView();
		List<Compliance> allCompliances = complianceService.getAllCompliances();
		
		m.addAttribute("allCompliances",allCompliances); 
		mav.setViewName("/regulation/viewRegulation");
		return mav;
	}
	
	@RequestMapping("/addRegulation")
	public ModelAndView addEmployee(Model m) {
		ModelAndView mav = new ModelAndView();
		List<Department> allDepartments = departmentService.getAllDepartments();
		
		if(!m.containsAttribute("regulationForm")) {
			Compliance regulationForm = new Compliance();
	        m.addAttribute("regulationForm", regulationForm);
		}
		
		m.addAttribute("allDepartments",allDepartments); 
		mav.setViewName("/regulation/addRegulation");
		return mav;
	}
	
	@InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Department.class, new SimpleDepartmentEditor(departmentService));
    }
	
	@RequestMapping(value = "/addRegulation",method = RequestMethod.POST)
	public String storeEmployeeDetails(@Valid @ModelAttribute("regulationForm") Compliance regulationForm,
            BindingResult result,HttpServletRequest req,final RedirectAttributes redirectAttributes) {
			
		Date today = new Date();
		regulationForm.setCreateDate(today);
		
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.regulationForm", result);
			redirectAttributes.addFlashAttribute("regulationForm", regulationForm);
			return "redirect:/admin/addRegulation";
        }
		
		
		int result1 = complianceService.addCompliance(regulationForm);
		
		if(result1>0) {
			redirectAttributes.addFlashAttribute("success","Regulation added successfully");
		}
		else {
			redirectAttributes.addFlashAttribute("error","Some error occurred in adding Regulation");
		}
		
		return "redirect:/admin/viewRegulation";
	}
	
	@RequestMapping(value = "/viewComments/{complianceId}", method = RequestMethod.GET)
	public ModelAndView viewComment(@PathVariable(value = "complianceId") int complianceId, Model m,HttpServletRequest req,final RedirectAttributes redirectAttributes, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		List<StatusReport> allStatusReport = statusReportService.getStatusReport(complianceId);
		
		m.addAttribute("allStatusReport",allStatusReport); 
		mav.setViewName("/regulation/viewComments");
		return mav;
	}
}