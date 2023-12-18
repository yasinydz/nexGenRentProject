package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.Brand;
import com.nexgencarrental.nexGenCarRental.entities.Color;
import com.nexgencarrental.nexGenCarRental.entities.Model;
import com.nexgencarrental.nexGenCarRental.repositories.BrandRepository;
import com.nexgencarrental.nexGenCarRental.repositories.ModelRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.BrandService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.brand.AddBrandRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.brand.UpdateBrandRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.brand.GetBrandListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.brand.GetBrandResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.color.GetColorListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.model.GetModelResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService{
    private final BrandRepository brandRepository;
    private ModelMapperService modelMapperService;


    @Override
    public List<GetBrandListResponse> getAll() {
        return brandRepository.findAll().stream()
                .map(car -> modelMapperService.forResponse()
                        .map(car, GetBrandListResponse.class)).collect(Collectors.toList());
    }

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

    @Override
    public void update(UpdateBrandRequest updateBrandRequest) {
        if(!(brandRepository.existsById(updateBrandRequest.getId()))){
            throw new RuntimeException(updateBrandRequest.getId()+" nolu id'ye sahip marka bulunmamaktadır.");
        }

        //Değiştirmek istenen markanın adını kontrol eder.

        Optional<Brand> existingColorOptional = brandRepository.findById(updateBrandRequest.getId());
        Brand existingBrand = existingColorOptional.get();
        String newBrand = updateBrandRequest.getName().trim().replaceAll("\s", "");

        //Id kontrol eder renk varsa hata fırlatır yoksa ekler.

        if (!existingBrand.getName().equals(newBrand) && brandRepository.existsByName(newBrand)) {
            throw new RuntimeException("Marka sistemimizde mevcut lütfen farklı bir renk deneyin.");
        }


        Brand brand = this.modelMapperService.forRequest()
                .map(updateBrandRequest, Brand.class);

        brand.setName(newBrand);

        brandRepository.save(brand);
    }

    @Override
    public void delete(int id) {

    }

}
