package com.nexgencarrental.nexGenCarRental.services.rules.car;

import com.nexgencarrental.nexGenCarRental.services.rules.base.BaseRules;

public interface CarBusinessRulesService extends BaseRules {
    void validateAddCar(String plate);

    void validateUpdateCar(int id);
}
