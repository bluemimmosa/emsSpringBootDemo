/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niraj.ems.repository;

import com.niraj.ems.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Niraj Khadka
 */
//no need to write @Repository as JpaRepository has already @Repository anotation.
//also provides @Trasactional annotation so no need to annotate @Transactional is Service class.
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    
}
