package com.nexgencarrental.nexGenCarRental.services.rules.brand;

import com.nexgencarrental.nexGenCarRental.repositories.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrandBusinessRulesManager implements BrandBusinessRulesService {
    @Override
    public String existsByBrandName(String name) {
        return null;
    }

    @Override
    public String existsByBrandId(int id) {
        return null;
    }
    //private final BrandRepository brandRepository;


}
