package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.Car;
import com.nexgencarrental.nexGenCarRental.entities.Color;
import com.nexgencarrental.nexGenCarRental.entities.Model;
import com.nexgencarrental.nexGenCarRental.repositories.CarRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.CarService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.ColorService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.ModelService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.AddCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.UpdateCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.color.GetColorListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.color.GetColorResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.model.GetModelListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.model.GetModelResponse;
import com.nexgencarrental.nexGenCarRental.services.rules.car.CarBusinessRulesService;
import com.nexgencarrental.nexGenCarRental.services.rules.model.ModelBusinessRulesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private final CarRepository carRepository;
    private ModelMapperService modelMapperService;
    private final ModelService modelService;
    private final ColorService colorService;
    private CarBusinessRulesService carBusinessRulesService;
    @Override
    public List<GetCarListResponse> getAll() {
        return carRepository.findAll().stream()
                .map(car -> modelMapperService.forResponse()
                        .map(car, GetCarListResponse.class)).collect(Collectors.toList());
    }
    @Override
    public GetCarResponse getById(int id) {
        return carRepository.findById(id)
                .map(car -> modelMapperService.forResponse().map(car, GetCarResponse.class))
                .orElseThrow(() ->
                        new RuntimeException(id + " girdiğiniz id'ye sahip araç sistemde bulunamıyor."));
    }
    @Override
    public void add(AddCarRequest addCarRequest) {

        // Model id kontrolü
        modelService.getModelById(addCarRequest.getModelId());


        // Color id kontrolü
        colorService.getColorById(addCarRequest.getColorId());

        // Boşlukları silerek temizlenmiş plaka değerini al
        String cleanedPlate = addCarRequest.getPlate().replaceAll("\\s", "");

        // Aynı plakada başka bir araç olup olmadığını kontrol etme
        if (carRepository.existsByPlate(cleanedPlate)) {
            throw new RuntimeException("Sistemde bu plaka bulunuyor, farklı bir plaka giriniz.");
        }

        // Yeni aracın oluşturulması ve kaydedilmesi
        Car addCar = modelMapperService.forRequest().map(addCarRequest, Car.class);

        // Temizlenmiş plaka değerini kullanarak kayıt yapma
        addCar.setPlate(cleanedPlate);
        carRepository.save(addCar);
    }

    @Override
    public void update(UpdateCarRequest updateCarRequest) {

        // Model id kontrolü
        modelService.getModelById(updateCarRequest.getModelId());


        // Color id kontrolü
        colorService.getColorById(updateCarRequest.getColorId());


        // Araç id kontrolü
        carBusinessRulesService.existsById(updateCarRequest.getId());

        // Yeni aracın güncellenmesi ve kaydedilmesi
        Car car = this.modelMapperService.forRequest()
                .map(updateCarRequest, Car.class);

        String updatePlate = updateCarRequest.getPlate();

        // Boşlukları silerek temizlenmiş plaka değerini al
        String cleanedPlate = updatePlate.replaceAll("\\s", "");
        car.setPlate(cleanedPlate);

        carRepository.save(car);

    }
    @Override
    public void delete(int id) {
        Car deleteCar = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bu id'ye sahip araç bulunamadı."));
        carRepository.delete(deleteCar);
    }
}
