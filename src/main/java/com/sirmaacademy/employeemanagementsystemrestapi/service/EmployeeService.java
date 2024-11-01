package com.sirmaacademy.employeemanagementsystemrestapi.service;

import com.sirmaacademy.employeemanagementsystemrestapi.model.entity.Employee;
import com.sirmaacademy.employeemanagementsystemrestapi.model.request.EmployeeRegisterRequest;

import java.util.List;

public interface EmployeeService {

    /**
     * Return List of all employees which first name match to searched name.
     */
    List<Employee> findAllByFirstName(String firstName);

    String confirmRegistration(EmployeeRegisterRequest employeeRegisterRequest);

    /**
     * Return List of all Active employees currently working in the company.
     */
    List<Employee> getAllActive();
//    /**
//     * Return List of all employees which last name match to searched name.
//     */
//    List<Employee> findAllByLastName(String lastName);
//
//    /**
//     * Return List of all employees which first and last names match to searched names.
//     */
//    List<Employee> findByFirstAndLastNames(String firstName, String lastName);
//
//    /**
//     * Return employees which id match to the searched.
//     */
//    List<Employee> findById(String id);
//
//    /**
//     * Return List of all employees in searched department.
//     */
//    List<Employee> findAllByDepartment(Department department);
//

//
//    /**
//     * Add new employee in the storage List while program is running.
//     */
//    Employee add(Employee employee);
//
//    /**
//     * Modify employee details - first and last names, department, role, salary, status.
//     */
//    void edit(int id, String firstName, String lastName, Department department, Role role, double salary);
//
//    /**
//     * Change employee status to FIRED when employee is fired or left the company.
//     */
//    void fire(int id);
//
//    /**
//     * Save all employees in csv file upon exit the program.
//     */
//    //void saveAll();
//
//    /**
//     * Return List of strings with incorrect employees data read from csv data file.
//     */
//    List<String> getBrokenEmployeeData();

}
