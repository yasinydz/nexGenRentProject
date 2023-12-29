package com.nexgencarrental.nexGenCarRental.services.rules.car;

import com.nexgencarrental.nexGenCarRental.repositories.CarRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.ColorService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarBusinessRulesManager implements CarBusinessRulesService {
    private final ModelService modelService;
    private final ColorService colorService;
    private CarRepository carRepository;
    @Override
    public void existsById(int id) {
        if (!carRepository.existsById(id)){
            throw new RuntimeException("The Car with " + id + " the ID number cannot be found in the system.");
        }
    }
    @Override
    public void validateAddCar(String plate) {
        // Aynı plakada başka bir araç olup olmadığını kontrol etme
        if (carRepository.existsByPlate(plate)) {
            throw new RuntimeException("Sistemde bu plaka bulunuyor, farklı bir plaka giriniz.");
        }
    }
    @Override
    public void validateUpdateCar(int id) {
        // Araç id kontrolü
        if (!carRepository.existsById(id)) {
            throw new RuntimeException("No vehicles with this id were found.");
        }
    }
}
