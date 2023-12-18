package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.repositories.CustomerRepository;
import com.nexgencarrental.nexGenCarRental.repositories.EmployeeRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.EmployeeService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.employee.AddEmployeeRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.employee.UpdateEmployeeRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.customer.GetCustomerResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.employee.GetEmployeeListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.employee.GetEmployeeResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeManager implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapperService modelMapperService;
    @Override
    public List<GetEmployeeListResponse> getAll() {
        return null;
    }

    @Override
    public GetEmployeeResponse getById(int id) {
        return employeeRepository.findById(id)
                .map(employee -> modelMapperService.forResponse().map(employee, GetEmployeeResponse.class))
                .orElseThrow();
    }

    @Override
    public void add(AddEmployeeRequest addEmployeeRequest) {

    }

    @Override
    public void update(UpdateEmployeeRequest updateEmployeeRequest) {

    }

    @Override
    public void delete(int id) {

    }
}
