package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Customer;
import com.nexgencarrental.nexGenCarRental.repositories.CustomerRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.CustomerService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.customer.AddCustomerRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.customer.UpdateCustomerRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.customer.GetCustomerListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.customer.GetCustomerResponse;

import com.nexgencarrental.nexGenCarRental.services.rules.customer.CustomerBusinessRulesService;
import org.springframework.stereotype.Service;



@Service
public class CustomerManager extends BaseManager<Customer, CustomerRepository, GetCustomerResponse, GetCustomerListResponse, AddCustomerRequest, UpdateCustomerRequest> implements CustomerService {

    private final CustomerBusinessRulesService customerBusinessRulesService = null;


    public CustomerManager(CustomerRepository repository, ModelMapperService modelMapperService, CustomerBusinessRulesService customerBusinessRulesService) {
        super(repository, modelMapperService, GetCustomerResponse.class, GetCustomerListResponse.class, Customer.class, AddCustomerRequest.class, UpdateCustomerRequest.class);
        //this.customerBusinessRulesService = customerBusinessRulesService;
    }

    @Override
    public void customAdd(AddCustomerRequest addCustomerRequest) {
        /*customerBusinessRulesService.existsById(addCustomerRequest.getUserId());
        add(addCustomerRequest, Customer.class);*/
    }

    @Override
    public void customUpdate(UpdateCustomerRequest updateCustomerRequest) {
        /*customerBusinessRulesService.existsById(updateCustomerRequest.getUserId());
        update(updateCustomerRequest, Customer.class);*/
    }
}
