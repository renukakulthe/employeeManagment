package com.example.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee.dao.EmployeeRepository;
import com.example.employee.model.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public List<Employee> getAllEmployeeDetails(){
		return employeeRepository.findAll();
	}
	
	public Optional<Employee> getEmployeeById(Long id) {
		return employeeRepository.findById(id);
	}

	public Employee updateEmployee(Employee newEntity, long id) {
		  Employee oldEntity =employeeRepository.findById(id).get();//record get 
		  //oldEntity = db entry 
		  if(oldEntity !=null) {
			  if(newEntity.getEmployeeName()!=null) {
			    oldEntity.setEmployeeName(newEntity.getEmployeeName());
			  }
			  
			  if(newEntity.getEmployeeRole() !=null) {
			   oldEntity.setEmployeeRole(newEntity.getEmployeeRole());
			  }
			  
			  if(newEntity.getAddress().getCity()!=null) {
				  oldEntity.getAddress().setCity(newEntity.getAddress().getCity());
			  }
			  
			  if(newEntity.getAddress().getState()!=null) {
				  oldEntity.getAddress().setState(newEntity.getAddress().getState());
			  }
			  
			  employeeRepository.save(oldEntity);
		  }
		    
		return oldEntity;
	}

	public void deleteEmployee(long id) {
	      Employee oldEntity = employeeRepository.findById(id).get();
	      if(oldEntity!=null) {
	    	  
	    	  employeeRepository.delete(oldEntity);
	    	  
	      }
	      
	}

	public List<Employee> getByName(String employeeName) {
		return employeeRepository.readByEmployeeNameStartsWith(employeeName);
	}

	public List<Employee> getByEmployeeSalaryBetween(int start, int end) {
		
		return employeeRepository.readByEmployeeSalaryBetween(start, end);
	}

	public List<String> listOfEmployeeName() {
		// TODO Auto-generated method stub
		return employeeRepository.listOfEmployeeName();
	}

	public List<Employee> findByEmployeeName(String name) {
		// TODO Auto-generated method stub
		return employeeRepository.abcd(name);
	}

	public List<String> listOfCities() {
		// TODO Auto-generated method stub
		return employeeRepository.listOfCity();
	}

	

}
