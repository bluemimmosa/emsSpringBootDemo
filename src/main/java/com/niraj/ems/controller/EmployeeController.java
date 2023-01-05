/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niraj.ems.controller;

import com.niraj.ems.model.Employee;
import com.niraj.ems.service.EmployeeService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Niraj Khadka
 */
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }
    
    //build create employee REST API
    @PostMapping //to handle POST request
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        return new ResponseEntity(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }
    
    //build get all employees REST API
    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }
    
    //build get employee by id REST API
    //http://localhost:8080/api/employees/1
    @GetMapping("{id}") //{"id"} is url path variable 
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long eId){
        return new ResponseEntity(employeeService.getEmployeeById(eId), HttpStatus.OK);
    }
    
    //build update employee REST API
    //http://localhost:8080/api/employees/1
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long eId, @RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, eId), HttpStatus.OK);
    }
    
    //build delete employee REST API
    //http://localhost:8080/api/employees/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long eId){
        employeeService.deleteEmployee(eId);
        return new ResponseEntity<String>("Employee Deleted Successfully!", HttpStatus.OK);
    }
}
