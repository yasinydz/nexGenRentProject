package com.nexgencarrental.nexGenCarRental.services.rules.brand;

import com.nexgencarrental.nexGenCarRental.services.rules.base.BaseRules;

public interface BrandBusinessRulesService extends BaseRules {
    void existsByName(String name);

}
