/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niraj.ems.service.impl;

import com.niraj.ems.exception.ResourceNotFoundException;
import com.niraj.ems.model.Employee;
import com.niraj.ems.repository.EmployeeRepository;
import com.niraj.ems.service.EmployeeService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Niraj Khadka
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
//need to inject the dependency. U/se constructor based dependency injection for
//mandatory parameters and setter based dependency injection for optional parameters.
    private EmployeeRepository employeeRepository;

    //if only one constructor no need @Autowired, but if more constructor then need @Autowired annotation.
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }
    
    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee); //calling save fucntion from JPARepository.
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
//        Optional<Employee> employee = employeeRepository.findById(id);
//        if(employee.isPresent())
//            return employee.get();
//        else
//            throw new ResourceNotFoundException("Employee", "Employee", id);
//        
        //replacing above if else using lambda expression.
        return employeeRepository.findById(id).orElseThrow(() -> 
                new ResourceNotFoundException("Employee", "Id", id));
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        //First check if the employee exist for given id.
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> 
                new ResourceNotFoundException("Employee", "Id", id));
        
        //update the existingEmployee with the new details from employee.
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        
        //now save the existingEmployee.
        return employeeRepository.save(existingEmployee);
    }
    
}
