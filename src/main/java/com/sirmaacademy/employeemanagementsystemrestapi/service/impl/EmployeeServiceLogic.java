package com.sirmaacademy.employeemanagementsystemrestapi.service.impl;


import com.sirmaacademy.employeemanagementsystemrestapi.exceptions.EmployeeAlreadyExistsException;
import com.sirmaacademy.employeemanagementsystemrestapi.model.entity.Account;
import com.sirmaacademy.employeemanagementsystemrestapi.model.entity.Employee;
import com.sirmaacademy.employeemanagementsystemrestapi.model.request.EmployeeRegisterRequest;
import com.sirmaacademy.employeemanagementsystemrestapi.repository.AccountRepository;
import com.sirmaacademy.employeemanagementsystemrestapi.repository.EmployeeRepository;
import com.sirmaacademy.employeemanagementsystemrestapi.service.EmployeeService;
import com.sirmaacademy.employeemanagementsystemrestapi.validation.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeServiceLogic implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public EmployeeServiceLogic(EmployeeRepository employeeRepository, AccountRepository accountRepository) {
        this.employeeRepository = employeeRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Employee> findAllByFirstName(String firstName) {

        return this.employeeRepository.findAllByFirstName(firstName).orElseThrow(NoSuchElementException::new);
    }

    /**
     * Return List of all Active employees currently working for the company.
     * If no Active employees empty List is returned.
     */
    @Override
    public List<Employee> getAllActive() {
        return  this.employeeRepository.findAllWithStatusActive()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public String confirmRegistration(EmployeeRegisterRequest employeeRegisterRequest) {

        if (this.employeeRepository.existsByPersonalIdNumber(employeeRegisterRequest.getPersonalIdNumber())) {
            throw new EmployeeAlreadyExistsException(
                    "Employee with id: '"
                            + employeeRegisterRequest.getPersonalIdNumber()
                            + "' is already registered.");
        }

        Employee employee = employeeRepository.save(
                new Employee(
                        employeeRegisterRequest.getFirstName(),
                        employeeRegisterRequest.getMiddleName(),
                        employeeRegisterRequest.getLastName(),
                        employeeRegisterRequest.getPersonalIdNumber(),
                        Validate.department(employeeRegisterRequest.getDepartment()),
                        Validate.position(employeeRegisterRequest.getPosition()),
                        employeeRegisterRequest.getSalary(),
                        Validate.roles(employeeRegisterRequest.getRoles())));

        Account account = accountRepository.save(createEmployeeAccount(employee));

        /**
         * Print the accounts username and password in the console.
         * It can be implemented secure printing for account credentials.
         */
        Print.printAccountDetails(account);

        return String.format("Employee: %s, %s, %s was successfully registered." +
                        " %s %s account was created successfully.",
                employee.getFirstName(),
                employee.getMiddleName(),
                employee.getLastName(),
                employee.getFirstName(),
                employee.getMiddleName());
    }

    private Account createEmployeeAccount(Employee employee) {
        return new Account(employee.getFirstName(), employee.getLastName(), employee);
    }

    //    @Override
//    public List<Employee> findAllByLastName(String lastName) {
//        return this.employeeRepository.findAllByLastName(lastName);
//    }
//
//    @Override
//    public List<Employee> findByFirstAndLastNames(String firstName, String lastName) {
//        return this.employeeRepository.findByFirstAndLastNames(firstName, lastName);
//    }
//
//    @Override
//    public List<Employee> findById(String id) {
//        return this.employeeRepository.findById(id);
//    }
//
//
//    @Override
//    public List<Employee> findAllByDepartment(Department department) {
//        return this.employeeRepository.findAllByDepartment(department);
//    }

//    /**
//     * Add new employee to storage List while program running.
//     * Set unique id to every employee.
//     */
//    @Override
//    public Employee add(Employee employee) {
//        int id = ensureIdUniqueness();
//        Employee employee = new Employee(id, firstName, lastName, department, role, salary);
//        this.employeeRepository.add(employee);
//    }
//
//    /**
//     * Modify employee details - first and last names, department, role, salary, status.
//     * Throw NoSuchElementException if employee id not found.
//     */
//    @Override
//    public void edit(int id, String firstName, String lastName, Department department, Role role, double salary) {
//
//            List<Employee> employeeList = this.employeeRepository.findById(id);
//
//            if (employeeList.size() > 1) {
//                throw new IllegalArgumentException("There are more than one employee with id: " + id);
//            } else if (employeeList.isEmpty()) {
//                throw new NullPointerException();
//            }
//
//            Employee employee = employeeList.getFirst();
//            employee.setFirstName(firstName);
//            employee.setLastName(lastName);
//            employee.setDepartment(department);
//            employee.setRole(role);
//            employee.setSalary(salary);
//            this.employeeRepository.modifyDetails(id, employee);
//
//    }
//
//    /**
//     * Change employee status to FIRED when employee no longer work for the company.
//     * @throws NoSuchElementException if employee id not found.
//     */
//    @Override
//    public void fire(int id) {
//
//        List<Employee> employeeList = this.employeeRepository.findById(id);
//
//        if (employeeList.size() > 1) {
//            throw new IllegalArgumentException("There are more than one employee with id: " + id);
//        } else if (employeeList.isEmpty()) {
//            throw new NullPointerException("Employee with id: " + id + " not found.");
//        }
//
//            Employee employee = employeeList.getFirst();
//            employee.setStatus(Status.FIRED);
//            this.employeeRepository.modifyDetails(id, employee);
//    }
//
//    /**
//     * Return List of strings with incorrect employees data read from csv data file.
//     */
//    @Override
//    public List<String> getBrokenEmployeeData() {
//        return this.employeeRepository.getBrokenEmployeeData();
//    }
//
//    /**
//     * Save all employees data in csv file upon exit the program.
//     */
//    @Override
//    public void saveAll() {
//        this.employeeRepository.persistToFile();
//    }
//
//    /**
//     * Return next unique id in series.
//     */
//    private int ensureIdUniqueness() {
//        List<Employee> employeeList = this.employeeRepository.getEmployeeList();
//        employeeList.sort(Comparator.comparing(Employee::getId));
//
//        if (employeeList.isEmpty()) {
//            return INITIAL_ID_VALUE;
//        }
//        return employeeList.getLast().getId() + 1;
//    }

}
