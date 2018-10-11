package com.telran.java23.mongo.springdatamongo.repository;

import com.telran.java23.mongo.springdatamongo.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    boolean existsByUsername(String username);
    List<Employee> findAllByCompanyName(String companyName);
}
