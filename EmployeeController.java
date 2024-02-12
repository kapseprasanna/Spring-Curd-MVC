package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Employee;
import com.example.demo.services.EmployeeServices;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeServices employeeServices;
	
	@GetMapping("/registeration")
	public String show_user(Model model){
		model.addAttribute("emp", new Employee());
		return "registered_emp";
		
	}
	
	@PostMapping("/addemp")
	public String addEmployee(@ModelAttribute Employee employee) {
		System.out.println(employee.getName()+employee.getDept());
		Employee newEmp =employeeServices.addEmployee(employee);
		return "addemp";
		
	}
	
	@GetMapping("/listemp")
	public String getallEmp(Model model){
		List<Employee> empData = employeeServices.getallEmp();
		model.addAttribute("allemp",empData);
		return "emplist";
		
	} 
	
//	localhost:8080/del?id=___ , its a get request 
	@GetMapping("/del")
	public String deleteEmployee(@RequestParam ("id") int id) {
		employeeServices.deleteEmp(id);
		return "redirect:/listemp";
	} 
	
	//localhost:8080/show/___
	//update query 
	@GetMapping("/show/{id}")
	public String showEmpdata(@PathVariable int id,Model model) {
		Employee e = employeeServices.getById(id);
		model.addAttribute("emp",e);
		return "empdetails";
	}
	
	@PostMapping("/update")
	public String updateEmp(@ModelAttribute Employee employee)
	{
		employeeServices.updateEmp(employee);
		return "redirect:/listemp";
	}
	
	
}
