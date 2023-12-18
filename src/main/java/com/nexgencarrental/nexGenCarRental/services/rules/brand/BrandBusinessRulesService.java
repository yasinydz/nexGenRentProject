package com.nexgencarrental.nexGenCarRental.services.rules.brand;

public interface BrandBusinessRulesService {
    String existsByBrandName(String name);
    String existsByBrandId (int id);

    //String existsByBrandAll (int id);

}
