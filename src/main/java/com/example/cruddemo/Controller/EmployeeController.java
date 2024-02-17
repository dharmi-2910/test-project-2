package com.example.cruddemo.Controller;

import com.example.cruddemo.Repository.EmployeeRepository;
import com.example.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository repo;

    @GetMapping("/employee")
    public List<Employee> getAllEmployee() {
        List<Employee> employees = repo.findAll();
        return employees;
    }

    // localhost:8080/employee/1
    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable int id) {
        Employee employee = repo.findById(id).orElse(null); // Handling the case if the employee with the given id is not found
        return employee;
    }

    @PostMapping("/employee/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createEmployee(@RequestBody Employee employee){
        repo.save(employee);
    }

    @PutMapping("/employee/update/{id}")
    public Employee updateEmployee(@PathVariable int id){
       Employee employee= repo.findById(id).get();
        employee.setName("bvc");
        employee.setEmail("bvc@gmail.com");
        repo.save(employee);
        return employee;
    }

    @DeleteMapping("/employee/delete/{id}")
    public void deleteEmployee(@PathVariable int id){
        Employee employee = repo.findById(id).get();
        repo.delete(employee);
    }

}
