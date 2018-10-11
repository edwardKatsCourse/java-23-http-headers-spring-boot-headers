package com.telran.java23.mongo.springdatamongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class SpringDataMongoApplication {


    /**
     * Relational DataBase Management System (SQL DB)
     *
     * RDBMS (SQL) vs   NoSQL
     * -------------------------
     * MySQL            MongoDB
     * PostgreSQL       ElasticSearch
     * MS SQL           Cassandra
     * DB2              Redis
     * OracleDB
     *
     *             Terminology
     *    SQL                       MongoDB
     * --------------------------------------
     * Database                    Database
     * Table                       Collection
     * Row                         Document
     * Column                      Field
     *
     *                              [{"name":"John"}, {"name":"John"}]
     * {
     *     "name":"Peter",
     *     "car" : {
     *         "brand":"Seat",
     *         "model":"Leon"
     *     }
     * }
     */


    /**
     *
     * Spring Data
     * CrudRepository
     *
     * %database_name%Repository
     * MongoRepository
     * ElasticsearchRepository
     * CassandraRepository
     *
     * JpaRepository
     */




    public static void main(String[] args) {
        SpringApplication.run(SpringDataMongoApplication.class, args);
    }
}
//users -> 10,000
//images per user -> 200

//userId    List<Image> images



//Relational Database
class Car {
    Employee currentDriver;
    Company company;
    List<Employee> previousDrivers;
    RentCompany rentCompany;
}

class CarCrashStats {
    Map<Employee, Integer> carCrashes;
}

class RentCompany {
    List<Car> cars;
}

class Employee {
    Company company;
    Car car;
}

class Company {
    List<Employee> employees;
    RentCompany rentCompany;
}
