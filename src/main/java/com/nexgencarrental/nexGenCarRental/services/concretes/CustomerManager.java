package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Customer;
import com.nexgencarrental.nexGenCarRental.repositories.CustomerRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.CustomerService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.brand.AddBrandRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.brand.UpdateBrandRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.customer.AddCustomerRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.customer.UpdateCustomerRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.customer.GetCustomerListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.customer.GetCustomerResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerManager extends BaseManager<Customer, CustomerRepository, GetCustomerResponse, GetCustomerListResponse, AddCustomerRequest, UpdateCustomerRequest> implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapperService modelMapperService;

    public CustomerManager(CustomerRepository repository, ModelMapperService modelMapperService, Class<GetCustomerResponse> responseType, Class<GetCustomerListResponse> listResponseType, Class<Customer> entityClass, Class<AddCustomerRequest> requestType, Class<UpdateCustomerRequest> updateRequestType, CustomerRepository customerRepository, ModelMapperService modelMapperService1) {
        super(repository, modelMapperService, responseType, listResponseType, entityClass, requestType, updateRequestType);
        this.customerRepository = customerRepository;
        this.modelMapperService = modelMapperService1;
    }

    @Override
    public void customAdd(AddCustomerRequest addCustomerRequest) {

    }

    @Override
    public void customUpdate(UpdateCustomerRequest updateCustomerRequest) {

    }
}
