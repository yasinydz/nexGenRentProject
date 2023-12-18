package com.nexgencarrental.nexGenCarRental.services.abstracts;

import com.nexgencarrental.nexGenCarRental.services.dtos.requests.customer.AddCustomerRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.customer.UpdateCustomerRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.user.AddUserRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.user.UpdateUserRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.customer.GetCustomerListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.customer.GetCustomerResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.user.GetUserListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.user.GetUserResponse;

import java.util.List;

public interface CustomerService {
    public List<GetCustomerListResponse> getAll();
    public GetCustomerResponse getById(int id);
    public void add (AddCustomerRequest addCustomerRequest);
    public void update(UpdateCustomerRequest updateCustomerRequest);
    public void delete(int id);
}
