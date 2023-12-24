package com.nexgencarrental.nexGenCarRental.services.rules.car;

import com.nexgencarrental.nexGenCarRental.repositories.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarBusinessRulesManager implements CarBusinessRulesService {
    private CarRepository carRepository;
    @Override
    public void existsById(int id) {
        if (!carRepository.existsById(id)){
            throw new RuntimeException("The Car with " + id + " the ID number cannot be found in the system.");
        }
    }
}
