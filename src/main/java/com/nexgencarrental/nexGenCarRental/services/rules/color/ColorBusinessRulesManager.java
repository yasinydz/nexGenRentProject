package com.nexgencarrental.nexGenCarRental.services.rules.color;

import com.nexgencarrental.nexGenCarRental.repositories.ColorRepository;
import com.nexgencarrental.nexGenCarRental.services.rules.base.BaseRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ColorBusinessRulesManager implements ColorBusinessRulesService {
    private ColorRepository colorRepository;
    @Override
    public void existsByName(String name) {
        if (colorRepository.existsByName(name.trim().replaceAll("\\s", ""))){
            throw new RuntimeException("The Color name is already exists!");
        }
    }

    @Override
    public void existsById(int id) {
        if(!colorRepository.existsById(id)){
            throw new RuntimeException("The Color with " + id + " the ID number cannot be found in the system.");
        }

    }
}
