package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.Car;
import com.nexgencarrental.nexGenCarRental.entities.Model;
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

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

        // Başlangıç tarihi kontrolü
        if(addRentalRequest.getStartDate().isBefore(LocalDate.now())){
            throw new RuntimeException("Başlangıç tarihi " + LocalDate.now() + " , yani bugünden daha önce bir tarih olamaz");
        }

        // Bitiş tarihi kontrolü
        if (addRentalRequest.getEndDate().isBefore(addRentalRequest.getStartDate())){
            throw new RuntimeException("Girdiğiniz tarih, " + addRentalRequest.getStartDate() + " bu tarihden ileri bir tarih olmalıdır.");
        }

        // Kiralama süresi kontrolü
        if (ChronoUnit.DAYS.between(addRentalRequest.getStartDate(), addRentalRequest.getEndDate())  > 25
                || 0 == ChronoUnit.DAYS.between(addRentalRequest.getStartDate(), addRentalRequest.getEndDate())) {
            throw new RuntimeException("Bir araç minimum 1 gün, maksimum 25 gün kiralanabilir.");
        }

        // Yeni Sipariş oluşturulması ve kaydedilmesi
        Rental addRental = modelMapperService.forRequest().map(addRentalRequest, Rental.class);

        // Araç kilometresi otomatik olarak id'den alıp kilometreyi çeker.
        GetCarResponse carId = carService.getById(addRentalRequest.getCarId());
        addRental.setStartKilometer(carId.getKilometer());

        // totalPrice hesaplaması burada yapılır
        addRental.setTotalPrice(carId.getDailyPrice() * ChronoUnit.DAYS.between(addRentalRequest.getStartDate(), addRentalRequest.getEndDate()));

        addRental.setEndKilometer(null);
        addRental.setReturnDate(null);
        rentalRepository.save(addRental);
    }

    @Override
    public void update(UpdateRentalRequest updateRentalRequest) {

        // Sipariş id kontrolü
        if (!(rentalRepository.findById(updateRentalRequest.getId()).isPresent())) {
            throw new RuntimeException(updateRentalRequest.getId() + " nolu id'ye sahip araç bulunmamaktadır.");
        }
        // Car id kontrolü
        GetCarResponse getCarResponse = carService.getById(updateRentalRequest.getCarId());
        if (getCarResponse == null) {
            throw new RuntimeException(updateRentalRequest.getCarId() + " id'ye sahip araç sistemde yoktur.");
        }
        // Customer id kontrolü
        GetCustomerResponse getCustomerResponse = customerService.getById(updateRentalRequest.getCustomerId());
        if (getCustomerResponse == null) {
            throw new RuntimeException(updateRentalRequest.getCustomerId() + " id'ye sahip müşteri sistemde yoktur.");
        }

        // Employee id kontrolü
        GetEmployeeResponse getEmployeeResponse = employeeService.getById(updateRentalRequest.getEmployeeId());
        if (getEmployeeResponse == null) {
            throw new RuntimeException(updateRentalRequest.getEmployeeId() + " id'ye sahip çalışan sistemde yoktur.");
        }

        // Başlangıç tarihi kontrolü
        if(updateRentalRequest.getStartDate().isBefore(LocalDate.now())){
            throw new RuntimeException("Başlangıç tarihi " + LocalDate.now() + " , yani bugünden daha önce bir tarih olamaz");
        }

        // Bitiş tarihi kontrolü
        if (updateRentalRequest.getEndDate().isBefore(updateRentalRequest.getStartDate())){
            throw new RuntimeException("Girdiğiniz tarih, " + updateRentalRequest.getStartDate() + " bu tarihden ileri bir tarih olmalıdır.");
        }

        // Kiralama süresi kontrolü
        if (ChronoUnit.DAYS.between(updateRentalRequest.getStartDate(), updateRentalRequest.getEndDate())  > 25
                || 0 == ChronoUnit.DAYS.between(updateRentalRequest.getStartDate(), updateRentalRequest.getEndDate())) {
            throw new RuntimeException("Bir araç minimum 1 gün, maksimum 25 gün kiralanabilir.");
        }

        // Yeni Sipariş oluşturulması ve kaydedilmesi
        Rental addRental = modelMapperService.forRequest().map(updateRentalRequest, Rental.class);

        // Araç kilometresi otomatik olarak id'den alıp kilometreyi çeker.
        GetCarResponse carId = carService.getById(updateRentalRequest.getCarId());
        addRental.setStartKilometer(carId.getKilometer());

        // totalPrice hesaplaması burada yapılır
        addRental.setTotalPrice(carId.getDailyPrice() * ChronoUnit.DAYS.between(updateRentalRequest.getStartDate(), updateRentalRequest.getEndDate()));

        addRental.setEndKilometer(null);
        addRental.setReturnDate(null);
        rentalRepository.save(addRental);

    }

    @Override
    public void delete(int id) {
        Rental deleteRental = rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bu id'ye sahip sipariş bulunamadı."));
        rentalRepository.delete(deleteRental);
        }
    }
