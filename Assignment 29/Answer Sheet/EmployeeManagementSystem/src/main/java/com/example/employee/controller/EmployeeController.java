package com.example.employee.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.EmployeeManagementSystemApplication;
import com.example.employee.dao.EmployeeRepository;
import com.example.employee.interceptors.RecordNotFoundException;
import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;

@RestController //@ResponseBody + @Controller
public class EmployeeController {
	
    //1) Logger enable
    Logger logger = LogManager.getLogger(EmployeeController.class);
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployeeRepository repo;
	
	//1) Employee Record save/create
	//@RequestMapping(value = "/save" , method= RequestMethod.POST)
	@PostMapping("/save")
	public Employee saveEmployee(@RequestBody  Employee employee) {
		return employeeService.saveEmployee(employee);
		
	}
	
	@GetMapping("/listEmployee")
	public List<Employee> getAllEmployeeDetails(){
		logger.info("this is calling employeeService.getAllEmployeeDetails()");
		return employeeService.getAllEmployeeDetails();
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<Employee>  getEmployeeById(@PathVariable("id") long id) {
		logger.info("Id= {}",id);
		Optional<Employee> emp=employeeService.getEmployeeById(id);
		if(emp.isPresent()) {
			return new ResponseEntity<>(emp.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	@PutMapping("/update/{id}")
	public Employee updateEmployee(@RequestBody Employee employee, @PathVariable("id") long id) {
		if(! repo.existsById(id)) throw new RecordNotFoundException();
		return employeeService.updateEmployee(employee,id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteEmployee(@PathVariable ("id") long id) {
		 if(! repo.existsById(id)) throw new RecordNotFoundException();
		 employeeService.deleteEmployee(id);
	}
	
	
	@GetMapping("/getByName/{employeeName}")
	public List<Employee> getByName(@PathVariable("employeeName") String employeeName){
		
		return employeeService.getByName(employeeName);
		
	}
	
	
	@GetMapping("/getBySalary/{start}/{end}")
	public List<Employee> getByEmployeeSalaryBetween(@PathVariable("start") int start , @PathVariable ("end") int end)
	{
		return employeeService.getByEmployeeSalaryBetween(start,end);
	}
	
	@GetMapping("/listOfEmployeeName")
	public List<String> listOfEmployeeName(){
		return employeeService.listOfEmployeeName();
		
	}
	
	@GetMapping("/findByEmployeeName/{name}")
	public List<Employee> findByEmployeeName(@PathVariable("name") String name){
		return employeeService.findByEmployeeName(name);
	}
	
	@GetMapping("/listOfCities")
	public List<String> listOfCities(){
		return employeeService.listOfCities();
	}
	
	
	

}
