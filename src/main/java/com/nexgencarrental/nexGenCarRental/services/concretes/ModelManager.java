package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.Car;
import com.nexgencarrental.nexGenCarRental.entities.Model;
import com.nexgencarrental.nexGenCarRental.repositories.CarRepository;
import com.nexgencarrental.nexGenCarRental.repositories.ModelRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.BrandService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.ColorService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.ModelService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.model.AddModelRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.model.UpdateModelRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.brand.GetBrandResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.color.GetColorResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.model.GetModelResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {

    private final ModelRepository modelRepository;
    private ModelMapperService modelMapperService;
    private final BrandService brandService;


    @Override
    public GetModelResponse getModelById(int id) {
        Model model = modelRepository.findById(id).orElseThrow(() ->
                new RuntimeException(id + " girdiğiniz id'ye sahip model sistemde bulunamıyor."));

        return modelMapperService.forResponse().map(model, GetModelResponse.class);
    }

    @Override
    public void add(AddModelRequest addModelRequest) {

        // Brand id kontrolü
        //GetBrandResponse getBrandResponse = brandService.getBrandById(addModelRequest.getBrandId());

       // if (getBrandResponse == null) {
      //      throw new RuntimeException(addModelRequest.getBrandId() + " id'ye sahip marka sistemde yoktur.");
        //}


        // Yeni aracın oluşturulması ve kaydedilmesi
        Model addModel = modelMapperService.forRequest().map(addModelRequest, Model.class);

        modelRepository.save(addModel);

    }


}
