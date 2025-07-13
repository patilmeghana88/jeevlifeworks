package com.ems.controller;

import com.ems.dto.ApiResponse;
import com.ems.exception.EmployeeNotFoundException;
import com.ems.model.Employee;
import com.ems.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Employee>>> getAll() {
        List<Employee> employees = service.getAllEmployees();
        return ResponseEntity.ok(new ApiResponse<>("success", "Fetched all employees", employees));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> getById(@PathVariable int id) {
        Employee emp = service.getEmployeeById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found."));
        return ResponseEntity.ok(new ApiResponse<>("success", "Employee found", emp));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(@Valid @RequestBody Employee employee) {
        service.addEmployee(employee);
        return ResponseEntity.ok(new ApiResponse<>("success", "Employee created successfully", null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable int id, @Valid @RequestBody Employee employee) {
        service.updateEmployee(id, employee);
        return ResponseEntity.ok(new ApiResponse<>("success", "Employee updated successfully", null));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable int id) {
        service.deleteEmployee(id);
        return ResponseEntity.ok(new ApiResponse<>("success", "Employee deleted successfully", null));
    }

    @GetMapping("/high-salary")
    public ResponseEntity<ApiResponse<List<Employee>>> getHighSalary(@RequestParam double threshold) {
        List<Employee> filtered = service.filterHighSalary(threshold);
        return ResponseEntity.ok(new ApiResponse<>("success", "Employees with salary above " + threshold, filtered));
    }

    @GetMapping("/group-by-department")
    public ResponseEntity<ApiResponse<Map<String, List<Employee>>>> groupByDept() {
        Map<String, List<Employee>> grouped = service.groupByDepartment();
        return ResponseEntity.ok(new ApiResponse<>("success", "Employees grouped by department", grouped));
    }
}

