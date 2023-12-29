package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Employee;
import com.nexgencarrental.nexGenCarRental.repositories.EmployeeRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.EmployeeService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.UserService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.employee.*;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.employee.*;
import com.nexgencarrental.nexGenCarRental.services.rules.employee.EmployeeBusinessRulesService;
import org.springframework.stereotype.Service;
@Service
public class EmployeeManager extends BaseManager<
        Employee,
        EmployeeRepository,
        GetEmployeeResponse,
        GetEmployeeListResponse,
        AddEmployeeRequest,
        UpdateEmployeeRequest
        > implements EmployeeService {
    private final UserService userService;
    private final EmployeeBusinessRulesService employeeBusinessRulesService;
    public EmployeeManager(
            EmployeeRepository repository,
            ModelMapperService modelMapperService,
            EmployeeBusinessRulesService employeeBusinessRulesService,
            UserService userService) {
        super(repository,
                modelMapperService,
                GetEmployeeResponse.class,
                GetEmployeeListResponse.class,
                Employee.class,
                AddEmployeeRequest.class,
                UpdateEmployeeRequest.class);
        this.userService = userService;
        this.employeeBusinessRulesService = employeeBusinessRulesService;
    }
    @Override
    public void customAdd(AddEmployeeRequest addEmployeeRequest) {
        userService.getById(addEmployeeRequest.getUserId()); // User id kontrolü
        add(addEmployeeRequest, Employee.class);
    }
    @Override
    public void customUpdate(UpdateEmployeeRequest updateEmployeeRequest) {
        getById(updateEmployeeRequest.getId()); // Employee id kontrolü
        userService.getById(updateEmployeeRequest.getUserId()); // User id kontrolü
        update(updateEmployeeRequest, Employee.class);
    }
}
