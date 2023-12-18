package com.nexgencarrental.nexGenCarRental.services.rules.base;

public interface BaseRules {
    void existsByName(String name);
    void existsById (int id);

    //String existsByAll (int id);
}
