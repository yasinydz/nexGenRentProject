package com.nexgencarrental.nexGenCarRental.services.rules.brand;

import com.nexgencarrental.nexGenCarRental.entities.Brand;
import com.nexgencarrental.nexGenCarRental.repositories.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrandBusinessRulesManager implements BrandBusinessRulesService {
    private BrandRepository brandRepository;
    @Override
    public void existsByName(String name) {

        if (brandRepository.existsByName(name.trim().replaceAll("\\s", ""))){
            throw new RuntimeException("Brand name is already exists!");
        }

    }

    @Override
    public void existsById(int id) {
        brandRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("The Brand with the ID number " + id + " cannot be found in the system."));
    }
}
