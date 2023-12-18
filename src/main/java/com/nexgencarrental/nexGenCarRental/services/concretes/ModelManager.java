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
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.model.GetModelListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.model.GetModelResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {

    private final ModelRepository modelRepository;
    private ModelMapperService modelMapperService;
    private final BrandService brandService;


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
        GetBrandResponse getBrandResponse = brandService.getBrandById(addModelRequest.getBrandId());
        if (getBrandResponse == null) {
            throw new RuntimeException(addModelRequest.getBrandId() + " id'ye sahip marka sistemde yoktur.");
        }

        // Aynı isimde model eklenememe kontrolü
        if (modelRepository.existsByName(addModelRequest.getName().trim().replaceAll("\\s", ""))) {
            throw new RuntimeException("Sistemde,girdiğiniz model bulunuyor.Lütfen farkı bir model giriniz");
        }

        // Yeni aracın oluşturulması ve kaydedilmesi
        Model addModel = modelMapperService.forRequest().map(addModelRequest, Model.class);

        modelRepository.save(addModel);

    }

    @Override
    public void update(UpdateModelRequest updateModelRequest) {
        if(!(modelRepository.existsById(updateModelRequest.getId()))){
            throw new RuntimeException(updateModelRequest.getId()+" nolu id'ye sahip model bulunmamaktadır.");
        }

        //Değiştirmek istenen modelin adını kontrol eder.

        Optional<Model> existingModelOptional = modelRepository.findById(updateModelRequest.getId());
        Model existingModel = existingModelOptional.get();
        String newModel = updateModelRequest.getName().trim().replaceAll("\s", "");

        //Id kontrol eder model varsa hata fırlatır yoksa ekler.

        if (!existingModel.getName().equals(newModel) && modelRepository.existsByName(newModel)) {
            throw new RuntimeException("Model sistemimizde mevcut lütfen farklı bir renk deneyin.");
        }


        Model model = this.modelMapperService.forRequest()
                .map(updateModelRequest, Model.class);

        model.setName(newModel);

        modelRepository.save(model);
    }

    @Override
    public void delete(int id) {
        Model deleteModel = modelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bu id'ye sahip model bulunamadı."));
        modelRepository.delete(deleteModel);
    }


}
