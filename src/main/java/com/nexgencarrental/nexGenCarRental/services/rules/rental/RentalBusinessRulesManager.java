package com.nexgencarrental.nexGenCarRental.services.rules.rental;

import com.nexgencarrental.nexGenCarRental.repositories.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RentalBusinessRulesManager implements RentalBusinessRulesService{
    private RentalRepository rentalRepository;
    @Override
    public void existsById(int id) {
        if(!rentalRepository.existsById(id)){
            throw new RuntimeException("The Rental with " + id + " the ID number cannot be found in the system.");
        }
    }
}
