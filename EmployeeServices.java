package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Employee;
import com.example.demo.reposiotry.EmployeeRepository;

@Service
public class EmployeeServices {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public Employee addEmployee(Employee employee) {
		Employee newEmp= employeeRepository.save(employee);
		return newEmp;
	}
	
	public List<Employee> getallEmp() {
		return employeeRepository.findAll();
	}
	
	public void deleteEmp(int id) {
		// check if id is valid or not 
		if(getById(id)!=null)
		employeeRepository.deleteById(id);
	}
	
	public Employee getById(int id) {
		return employeeRepository.findById(id)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"this " + id +" does not esixts!!"));
	}
	
	public Employee updateEmp(Employee employee)
	{
		return employeeRepository.save(employee);
	}

}
