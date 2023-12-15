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
                .orElseThrow();
    }
    @Override
    public void add(AddCarRequest addCarRequest) {

        // Model id kontrolü
        GetModelResponse getModelResponse = modelService.getModelById(addCarRequest.getModelId());
        if (getModelResponse == null) {
            throw new RuntimeException(addCarRequest.getModelId() + " id'ye sahip model sistemde yoktur.");
        }

        // Color id kontrolü
        GetColorResponse getColorResponse = colorService.getColorById(addCarRequest.getColorId());
        if (getColorResponse == null) {
            throw new RuntimeException(addCarRequest.getColorId() + " id'ye sahip renk sistemde yoktur.");
        }

        // Aynı plakada başka bir araç olup olmadığını kontrol etme
        if (carRepository.existsByPlate(addCarRequest.getPlate().replaceAll("\\s", ""))) {
            throw new RuntimeException("Sistemde bu plaka bulunuyor, farklı bir plaka giriniz.");
        }

        // Yeni aracın oluşturulması ve kaydedilmesi
        Car addCar = modelMapperService.forRequest().map(addCarRequest, Car.class);
        carRepository.save(addCar);

    }
    @Override
    public void update(UpdateCarRequest updateCarRequest) {

        // Model id kontrolü
        GetModelResponse getModelResponse = modelService.getModelById(updateCarRequest.getModelId());
        if (getModelResponse == null) {
            throw new RuntimeException(updateCarRequest.getModelId() + " Güncellemek istediğiniz model id'ye sahip model sistemde yoktur.");
        }

        // Color id kontrolü
        GetColorResponse getColorResponse = colorService.getColorById(updateCarRequest.getColorId());
        if (getColorResponse == null) {
            throw new RuntimeException(updateCarRequest.getColorId() + " Güncellemek istediğiniz renk id'ye sahip renk sistemde yoktur.");
        }

        // Araç id kontrolü
        if (!(carRepository.findById(updateCarRequest.getId()).isPresent())) {
            throw new RuntimeException(updateCarRequest.getId() + " nolu id'ye sahip araç bulunmamaktadır.");
        }

        // Yeni aracın güncellenmesi ve kaydedilmesi
        Car updateCar = this.modelMapperService.forRequest()
                .map(updateCarRequest, Car.class);
        carRepository.save(updateCar);

    }
    @Override
    public void delete(int id) {
        Car deleteCar = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bu id'ye sahip araç bulunamadı."));
        carRepository.delete(deleteCar);
    }
}
