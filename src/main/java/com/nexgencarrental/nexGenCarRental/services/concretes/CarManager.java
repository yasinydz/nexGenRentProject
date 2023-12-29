package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Car;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Model;
import com.nexgencarrental.nexGenCarRental.repositories.CarRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.CarService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.ColorService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.ModelService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.AddCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.UpdateCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarResponse;
import com.nexgencarrental.nexGenCarRental.services.rules.car.CarBusinessRulesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class CarManager extends BaseManager <Car, CarRepository, GetCarResponse, GetCarListResponse, AddCarRequest, UpdateCarRequest> implements CarService{
    private final ModelService modelService;
    private final ColorService colorService;
    private final CarBusinessRulesService carBusinessRulesService;

    public CarManager(CarRepository repository, ModelMapperService modelMapperService, ModelService modelService, ColorService colorService, CarBusinessRulesService carBusinessRulesService) {
        super(repository, modelMapperService, GetCarResponse.class, GetCarListResponse.class, Car.class, AddCarRequest.class, UpdateCarRequest.class);
        this.modelService = modelService;
        this.colorService = colorService;
        this.carBusinessRulesService = carBusinessRulesService;
        this.repository = repository;
    }

    @Override
    public void customAdd(AddCarRequest addCarRequest) {
        // Model id kontrolü
        modelService.getById(addCarRequest.getModelId());

        // Color id kontrolü
        colorService.getById(addCarRequest.getColorId());

        // Boşlukları silerek temizlenmiş plaka değerini al
        String cleanedPlate = addCarRequest.getPlate().replaceAll("\\s", "");

        // CarBusinessRulesManager kullanarak iş kurallarını kontrol etme
        carBusinessRulesService.validateAddCar(cleanedPlate);

        // Yeni aracın oluşturulması ve kaydedilmesi
        Car addCar = modelMapperService.forRequest().map(addCarRequest, Car.class);
        addCar.setPlate(cleanedPlate);
        repository.save(addCar);
    }

    @Override
    public void customUpdate(UpdateCarRequest updateCarRequest) {
        // Model id kontrolü
        modelService.getById(updateCarRequest.getModelId());

        // Color id kontrolü
        colorService.getById(updateCarRequest.getColorId());

        //Araç id kontrolü
        carBusinessRulesService.existsById(updateCarRequest.getId());

        // Boşlukları silerek temizlenmiş plaka değerini al
        String cleanedPlate = updateCarRequest.getPlate().replaceAll("\\s", "");

        // CarBusinessRulesManager kullanarak iş kurallarını kontrol etme
        carBusinessRulesService.validateUpdateCar(updateCarRequest.getId());

        // Yeni aracın oluşturulması ve güncellenmesi
        Car addCar = modelMapperService.forRequest().map(updateCarRequest, Car.class);
        addCar.setPlate(cleanedPlate);
        repository.save(addCar);

    }
}
