package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Car;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Rental;
import com.nexgencarrental.nexGenCarRental.repositories.RentalRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.*;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.AddRentalRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.UpdateRentalRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.rental.GetRentalListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.rental.GetRentalResponse;
import com.nexgencarrental.nexGenCarRental.services.rules.rental.RentalBusinessRulesService;
import org.springframework.stereotype.Service;

@Service
public class RentalManager extends BaseManager <
        Rental,
        RentalRepository,
        GetRentalResponse,
        GetRentalListResponse,
        AddRentalRequest,
        UpdateRentalRequest
        > implements RentalService{
    private final CarService carService;
    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final RentalBusinessRulesService rentalBusinessRulesService;

    public RentalManager(
            RentalRepository repository,
            ModelMapperService modelMapperService,
            CarService carService, CustomerService customerService,
            EmployeeService employeeService,
            RentalBusinessRulesService rentalBusinessRulesService) {
        super(repository,
                modelMapperService,
                GetRentalResponse.class,
                GetRentalListResponse.class,
                Rental.class,
                AddRentalRequest.class,
                UpdateRentalRequest.class);
        this.carService = carService;
        this.customerService = customerService;
        this.employeeService = employeeService;
        this.rentalBusinessRulesService = rentalBusinessRulesService;
    }
    @Override
    public void customAdd(AddRentalRequest addRentalRequest) {
        carService.getById(addRentalRequest.getCarId()); // Car id kontrolü
        customerService.getById(addRentalRequest.getCustomerId()); // Customer id kontrolü
        employeeService.getById(addRentalRequest.getEmployeeId()); // Employee id kontrolü
        add(addCarRequest, Car.class);

    }
    @Override
    public void customUpdate(UpdateRentalRequest updateRentalRequest) {

    }
   /* @Override
    public void add(AddRentalRequest addRentalRequest) {

        // Car id kontrolü
        carService.getById(addRentalRequest.getCarId());

        // Customer id kontrolü
        customerService.getById(addRentalRequest.getCustomerId());

        // Employee id kontrolü
        employeeService.getById(addRentalRequest.getEmployeeId());

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

        // Rental id kontrolü
        rentalBusinessRulesService.existsById(updateRentalRequest.getId());

        // Car id kontrolü
        carService.getById(updateRentalRequest.getCarId());

        // Customer id kontrolü
        customerService.getById(updateRentalRequest.getCustomerId());

        // Employee id kontrolü
        employeeService.getById(updateRentalRequest.getEmployeeId());

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

    }*/

}