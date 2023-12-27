package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Model;
import com.nexgencarrental.nexGenCarRental.repositories.ModelRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.BrandService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.ModelService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.model.AddModelRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.model.UpdateModelRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.brand.GetBrandResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.model.GetModelListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.model.GetModelResponse;
import com.nexgencarrental.nexGenCarRental.services.rules.model.ModelBusinessRulesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {

    private final ModelRepository modelRepository;
    private ModelMapperService modelMapperService;
    private final BrandService brandService;
    private ModelBusinessRulesService modelBusinessRulesService;


    @Override
    public List<GetModelListResponse> getAll() {
        return modelRepository.findAll().stream()
                .map(car -> modelMapperService.forResponse()
                        .map(car, GetModelListResponse.class)).collect(Collectors.toList());
    }

    @Override
    public GetModelResponse getModelById(int id) {
        Model model = modelRepository.findById(id).orElseThrow(() ->
                new RuntimeException(id + " girdiğiniz id'ye sahip model sistemde bulunamıyor."));

        return modelMapperService.forResponse().map(model, GetModelResponse.class);
    }

    @Override
    public void add(AddModelRequest addModelRequest) {

        // Brand id kontrolü
        GetBrandResponse getBrandResponse = brandService.getById(addModelRequest.getBrandId());
        if (getBrandResponse == null) {
            throw new RuntimeException(addModelRequest.getBrandId() + " id'ye sahip marka sistemde yoktur.");
        }

        // Aynı isimde model eklenememe kontrolü

        modelBusinessRulesService.existsByName(addModelRequest.getName());


        // Yeni aracın oluşturulması ve kaydedilmesi
        Model addModel = modelMapperService.forRequest().map(addModelRequest, Model.class);
        addModel.setId(0);
        modelRepository.save(addModel);

    }

    @Override
    public void update(UpdateModelRequest updateModelRequest) {
        modelBusinessRulesService.existsById(updateModelRequest.getId());
        modelBusinessRulesService.existsByName(updateModelRequest.getName());
        brandService.getById(updateModelRequest.getBrandId());


        Model model = this.modelMapperService.forRequest()
                .map(updateModelRequest, Model.class);

        modelRepository.save(model);
    }

    @Override
    public void delete(int id) {
        Model deleteModel = modelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bu id'ye sahip model bulunamadı."));
        modelRepository.delete(deleteModel);
    }


}
