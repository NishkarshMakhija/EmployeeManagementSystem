package com.controller;

import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
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

import com.bean.Employee;
import com.bean.Login;
import com.editor.SimpleDepartmentEditor;
import com.bean.Department;
import com.service.EmployeeService;
import com.service.PasswordService;
import com.service.DepartmentService;


@Controller
@RequestMapping("/admin")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@RequestMapping("/viewEmployee")
	public ModelAndView viewEmployee(Model m) {
		ModelAndView mav = new ModelAndView();
		List<Employee> allEmployees = employeeService.getAllEmployees();
		
		m.addAttribute("allEmployees",allEmployees); 
		mav.setViewName("/employee/viewEmployee");
		return mav;
	}
	
	@RequestMapping("/addEmployee")
	public ModelAndView addEmployee(Model m) {
		ModelAndView mav = new ModelAndView();
		List<Department> allDepartments = departmentService.getAllDepartments();
		
		if(!m.containsAttribute("employeeForm")) {
			Employee employeeForm = new Employee();
	        m.addAttribute("employeeForm", employeeForm);
		}
		
		m.addAttribute("allDepartments",allDepartments); 
		mav.setViewName("/employee/addEmployee");
		return mav;
	}
	
	@InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Department.class, new SimpleDepartmentEditor(departmentService));
    }
	
	@RequestMapping(value = "/addEmployee",method = RequestMethod.POST)
	public String storeEmployeeDetails(@Valid @ModelAttribute("employeeForm") Employee employeeForm,
            BindingResult result,HttpServletRequest req,final RedirectAttributes redirectAttributes) {
		
		Date dob = null;
		try {
			dob = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("dob"));
			employeeForm.setDob(dob);
		} catch (ParseException e) {
			result.rejectValue("dob", "error.employeeForm", "Invalid Date of Birth.");
			e.printStackTrace();
		}
		
		if(! employeeService.checkDateOfBirth(LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(employeeForm.getDob())))) {
			result.rejectValue("dob", "error.employeeForm", "Employee should be minimum of 24 years old.");
		}
		
		
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.employeeForm", result);
			redirectAttributes.addFlashAttribute("employeeForm", employeeForm);
			return "redirect:/admin/addEmployee";
        }
		
		Login login = new Login();

		login.setRole("employee");
		String encryptedPassword = PasswordService.getMD5(employeeForm.getPassword());
		login.setPassword(encryptedPassword);
		
		int result1 = employeeService.addEmployee(employeeForm,login);
		
		if(result1>0) {
			redirectAttributes.addFlashAttribute("success","Employee added successfully");
		}
		else {
			redirectAttributes.addFlashAttribute("error","Some error occurred in adding Employee");
		}
		
		return "redirect:/admin/viewEmployee";
	}
	
	@RequestMapping(value = "/editEmployee/{employeeId}", method = RequestMethod.GET)
	public ModelAndView editEmployee(@PathVariable(value = "employeeId") int employeeId, Model m) {
		ModelAndView mav = new ModelAndView();
		List<Department> allDepartments = departmentService.getAllDepartments();
		
		m.addAttribute("allDepartments",allDepartments);
		
		if(!m.containsAttribute("employeeForm")) {
			Employee employeeForm = employeeService.getEmployeeById(employeeId);
	        m.addAttribute("employeeForm", employeeForm);
		}
		mav.setViewName("/employee/editEmployee");
		return mav;
	}
	
	@RequestMapping(value = "/editEmployee/{employeeId}", method = RequestMethod.POST)
	public String editEmployeeSubmit(@PathVariable(value = "employeeId") int employeeId,@Valid @ModelAttribute("employeeForm") Employee employeeForm,
            BindingResult result, Model m,HttpServletRequest req,final RedirectAttributes redirectAttributes) {
		Date dob = null;
		try {
			dob = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("dob"));
			employeeForm.setDob(dob);
		} catch (ParseException e) {
			result.rejectValue("dob", "error.employeeForm", "Invalid Date of Birth.");
			e.printStackTrace();
		}
		
		if(! employeeService.checkDateOfBirth(LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(employeeForm.getDob())))) {
			result.rejectValue("dob", "error.employeeForm", "Employee should be minimum of 24 years old.");
		}
		
		
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.employeeForm", result);
			redirectAttributes.addFlashAttribute("employeeForm", employeeForm);
			return "redirect:/admin/editEmployee/"+employeeForm.getEmpId();
        }
		
		employeeForm.setEmpId(employeeId);
		
		Login login = new Login();

		login.setRole("employee");
		String encryptedPassword = PasswordService.getMD5(employeeForm.getPassword());
		login.setPassword(encryptedPassword);
		
		int result1 = employeeService.updateEmployeeDetails(employeeForm,login);
		
		if(result1>0) {
			redirectAttributes.addFlashAttribute("success","Employee updated successfully");
		}
		else {
			redirectAttributes.addFlashAttribute("error","Some error occurred in updating Employee");
		}
		
		return "redirect:/admin/viewEmployee";
	}
}