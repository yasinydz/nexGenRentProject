package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.Car;
import com.nexgencarrental.nexGenCarRental.entities.Rental;
import com.nexgencarrental.nexGenCarRental.repositories.RentalRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.CarService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.CustomerService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.EmployeeService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.RentalService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.AddRentalRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.UpdateRentalRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.color.GetColorResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.customer.GetCustomerResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.employee.GetEmployeeResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.model.GetModelResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.rental.GetRentalListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.rental.GetRentalResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RentalManager implements RentalService {
    private final RentalRepository rentalRepository;
    private final ModelMapperService modelMapperService;
    private final CarService carService;
    private final CustomerService customerService;
    private final EmployeeService employeeService;

    @Override
    public List<GetRentalListResponse> getAll() {
        return rentalRepository.findAll().stream()
                .map(rental -> modelMapperService.forResponse()
                        .map(rental, GetRentalListResponse.class)).collect(Collectors.toList());
    }

    @Override
    public GetRentalResponse getById(int id) {
        return rentalRepository.findById(id)
                .map(rental -> modelMapperService.forResponse().map(rental, GetRentalResponse.class))
                .orElseThrow();
    }

    @Override
    public void add(AddRentalRequest addRentalRequest) {

        // Car id kontrolü
        GetCarResponse getCarResponse = carService.getById(addRentalRequest.getCarId());
        if (getCarResponse == null) {
            throw new RuntimeException(addRentalRequest.getCarId() + " id'ye sahip araç sistemde yoktur.");
        }

        // Customer id kontrolü
        GetCustomerResponse getCustomerResponse = customerService.getById(addRentalRequest.getCustomerId());
        if (getCustomerResponse == null) {
            throw new RuntimeException(addRentalRequest.getCustomerId() + " id'ye sahip müşteri sistemde yoktur.");
        }

        // Employee id kontrolü
        GetEmployeeResponse getEmployeeResponse = employeeService.getById(addRentalRequest.getEmployeeId());
        if (getEmployeeResponse == null) {
            throw new RuntimeException(addRentalRequest.getEmployeeId() + " id'ye sahip çalışan sistemde yoktur.");
        }

        // Yeni Sipariş oluşturulması ve kaydedilmesi
        Rental addRental = modelMapperService.forRequest().map(addRentalRequest, Rental.class);

        //addRental.setId(0);
        rentalRepository.save(addRental);

    }

    @Override
    public void update(UpdateRentalRequest updateRentalRequest) {

    }

    @Override
    public void delete(int id) {

    }
}
