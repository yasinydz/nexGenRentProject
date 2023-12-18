package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.services.abstracts.EmployeeService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.employee.AddEmployeeRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.employee.UpdateEmployeeRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.employee.GetEmployeeListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.employee.GetEmployeeResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeManager implements EmployeeService {
    @Override
    public List<GetEmployeeListResponse> getAll() {
        return null;
    }

    @Override
    public GetEmployeeResponse getById(int id) {
        return null;
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
