package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.repositories.RentalRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.CarService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.CustomerService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.EmployeeService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.RentalService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.AddRentalRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.UpdateRentalRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarListResponse;
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
        return null;
    }

    @Override
    public void add(AddRentalRequest addRentalRequest) {

    }

    @Override
    public void update(UpdateRentalRequest updateRentalRequest) {

    }

    @Override
    public void delete(int id) {

    }
}
