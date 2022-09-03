package com.example.employee.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.employee.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
	
	List<Employee> readByEmployeeNameStartsWith(String employeeName);
	
	List<Employee> readByEmployeeSalaryBetween(int start,int end);

    @Query("Select employeeName from Employee where employeeName like '_a%'")
	List<String> listOfEmployeeName();
    
    @Query("Select e from Employee e where e.employeeName =:n")
    List<Employee> abcd(@Param("n")  String name);
    
    @Query(value="select city from emp e join address a where e.address_id = a.id", nativeQuery = true)
	List<String> listOfCity();
	
	

}
