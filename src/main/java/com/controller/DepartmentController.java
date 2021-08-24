package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.service.DepartmentService;
import com.bean.Department;  

@Controller
@RequestMapping("/admin")
public class DepartmentController {
	
	@Autowired
	DepartmentService departmentService;

	
	@RequestMapping("/department")
	public ModelAndView viewDepartment(Model m) {
		ModelAndView mav = new ModelAndView();
		List<Department> allDepartments = departmentService.getAllDepartments();
		
		if(!m.containsAttribute("departmentForm")) {
			Department departmentForm = new Department();
	        m.addAttribute("departmentForm", departmentForm);
		}
		
		m.addAttribute("allDepartments",allDepartments); 
		mav.setViewName("/department/viewDepartment");
		return mav;
	}
	
	@RequestMapping(value = "/addDepartment",method = RequestMethod.POST)
	public String storeDepartmentDetails(@Valid @ModelAttribute("departmentForm") Department departmentForm,
            BindingResult result,HttpServletRequest req,final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.departmentForm", result);
			redirectAttributes.addFlashAttribute("departmentForm", departmentForm);
			return "redirect:/admin/department";
        }
		
		String departmentName = req.getParameter("departmentName");
		Department department = new Department();
		department.setDepartmentName(departmentName);
		
		int result1 = departmentService.addDepartment(departmentForm);
		
		if(result1>0) {
			redirectAttributes.addFlashAttribute("success","Department added successfully");
		}
		else {
			redirectAttributes.addFlashAttribute("error","Some error occurred in adding Department");
		}
		
		return "redirect:/admin/department";
	}
	
	
	
	
}