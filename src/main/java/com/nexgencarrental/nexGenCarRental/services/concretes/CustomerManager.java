package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.services.abstracts.CustomerService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.customer.AddCustomerRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.customer.UpdateCustomerRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.customer.GetCustomerListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.customer.GetCustomerResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {
    @Override
    public List<GetCustomerListResponse> getAll() {
        return null;
    }

    @Override
    public GetCustomerResponse getById(int id) {
        return null;
    }

    @Override
    public void add(AddCustomerRequest addCustomerRequest) {

    }

    @Override
    public void update(UpdateCustomerRequest updateCustomerRequest) {

    }

    @Override
    public void delete(int id) {

    }
}
