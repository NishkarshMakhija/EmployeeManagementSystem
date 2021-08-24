package com.editor;

import java.beans.PropertyEditorSupport;

import com.bean.Department;
import com.service.DepartmentService;

public class SimpleDepartmentEditor extends PropertyEditorSupport {
	
	private DepartmentService departmentService;

    public SimpleDepartmentEditor(DepartmentService departmentService){
        this.departmentService = departmentService;
    }
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Department department = null;
        try {
            Integer id = Integer.parseInt(text);
            department = departmentService.getDepartmentById(id);
            //System.out.println("Department name:" + department.getDepartmentName());
        } catch (NumberFormatException ex) {
            //System.out.println("Department will be null");
        }
        setValue(department);
    }
	
}
