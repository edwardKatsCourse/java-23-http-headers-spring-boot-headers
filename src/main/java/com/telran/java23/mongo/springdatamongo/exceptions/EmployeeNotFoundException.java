package com.telran.java23.mongo.springdatamongo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
public class EmployeeNotFoundException extends RuntimeException {
}
