package com.nexgencarrental.nexGenCarRental.services.rules.model;

import com.nexgencarrental.nexGenCarRental.services.rules.base.BaseRules;

public interface ModelBusinessRulesService extends BaseRules {
    void existsByName(String name);
    void canDeleteModel(int id);
}
