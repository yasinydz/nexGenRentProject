package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.Brand;
import com.nexgencarrental.nexGenCarRental.entities.Color;
import com.nexgencarrental.nexGenCarRental.entities.Model;
import com.nexgencarrental.nexGenCarRental.repositories.BrandRepository;
import com.nexgencarrental.nexGenCarRental.repositories.ModelRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.BrandService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.brand.AddBrandRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.brand.GetBrandResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.model.GetModelResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService{
    private final BrandRepository brandRepository;
    private ModelMapperService modelMapperService;


    @Override
    public GetBrandResponse getBrandById(int id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() ->
                new RuntimeException(id + " girdiğiniz id'ye sahip marka sistemde bulunamıyor."));

        return modelMapperService.forResponse().map(brand, GetBrandResponse.class);
    }

    @Override
    public void add(AddBrandRequest addBrandRequest) {
        if (brandRepository.existsByName(addBrandRequest.getName().trim().replaceAll("\\s", ""))){
            throw new RuntimeException("Sistemde bu marka bulunuyor,farklı bir marka giriniz.");
        }

        Brand addBrand = modelMapperService.forRequest().map(addBrandRequest, Brand.class);

        brandRepository.save(addBrand);
    }

}
