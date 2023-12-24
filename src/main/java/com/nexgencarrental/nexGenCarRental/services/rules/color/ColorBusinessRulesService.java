package com.nexgencarrental.nexGenCarRental.services.rules.color;

import com.nexgencarrental.nexGenCarRental.services.rules.base.BaseRules;

public interface ColorBusinessRulesService extends BaseRules {
    void existsByName(String name);
}
