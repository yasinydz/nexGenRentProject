package com.nexgencarrental.nexGenCarRental.services.dtos.responses.rental;

import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.customer.GetCustomerListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.employee.GetEmployeeListResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRentalResponse {
    private int id;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate returnDate;

    private double startKilometer;

    private double endKilometer;

    private double totalPrice;

    private double discount;

    private GetCarListResponse car;

    private GetCustomerListResponse customer;

    private GetEmployeeListResponse employee;
}
