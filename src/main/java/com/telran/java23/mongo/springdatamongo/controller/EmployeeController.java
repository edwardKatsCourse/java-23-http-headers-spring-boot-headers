package com.telran.java23.mongo.springdatamongo.controller;

import com.telran.java23.mongo.springdatamongo.entity.Employee;
import com.telran.java23.mongo.springdatamongo.exceptions.EmployeeNotFoundException;
import com.telran.java23.mongo.springdatamongo.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/save")
    public Employee save(@RequestBody Employee employee) {

        if (employeeRepository.existsByUsername(employee.getUsername())) {
            throw new RuntimeException("User already exists!");
        }
        //employee without ID
        return employeeRepository.save(employee);
        //employee with ID
    }

    @PutMapping("/update/{id}")
    public Employee update(@PathVariable("id") String id,
                           @RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "company", required = false) String company) {


        Employee employee = employeeRepository.findById(id)
                .orElseThrow(
                        () -> new EmployeeNotFoundException()
                );

        if (name != null) {
            employee.setName(name);
        }

        if (company != null) {
            employee.setCompanyName(company);
        }

        return employeeRepository.save(employee);
    }

    @GetMapping("/")
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable("id") String id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new EmployeeNotFoundException()
        );
    }

    @GetMapping("/company/{companyName}")
    public List<Employee> getByCompany(@PathVariable("companyName") String companyName) {
        return employeeRepository.findAllByCompanyName(companyName);
    }

    //method.invoke()
    @GetMapping("/java-23")
    public String headers(@RequestHeader(value = "java-23", required = false) String java23Header) {
        if (java23Header.equalsIgnoreCase("authenticated")) {
            return "Hurray";
        } else {
            throw new RuntimeException("You shall not pass! (c) Gendalf");
        }
    }

    @GetMapping("/all-headers")
    public HeaderResponse getAllHeadersSentByClient(HttpServletRequest request,
                                                    HttpServletResponse response) {
        //Map<Object, Object>
        HeaderResponse headerResponse = new HeaderResponse();


        Map<String, String> requestHeaders = Collections.list(request.getHeaderNames())
                .stream()
                .map(headerName -> new Pair<String, String>(headerName, request.getHeader(headerName)))
                .collect(Collectors.toMap(
                        Pair::getKey,
                        Pair::getValue,
                        (value1, value2) -> value1 + ", " + value2
                ));

        headerResponse.setRequestHeaders(requestHeaders);


        response.setHeader("Spring Version", SpringVersion.getVersion());
        response.setHeader("Spring Boot Version", SpringBootVersion.getVersion());

        Map<String, String> responseHeaders = response.getHeaderNames()
                .stream()
                .map(headerName -> new Pair<String, String>(headerName, response.getHeader(headerName)))
                .collect(Collectors.toMap(
                        Pair::getKey,
                        Pair::getValue,
                        (value1, value2) -> value1 + ", " + value2
                ));


        headerResponse.setResponseHeaders(responseHeaders);

        return headerResponse;
    }

}

@Data
class HeaderResponse {
    private Map<String, String> requestHeaders;
    private Map<String, String> responseHeaders;
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class Pair<K, V> {
    K key;
    V value;
}