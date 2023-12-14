package com.nexgencarrental.nexGenCarRental.services.concretes;

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
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.color.GetColorListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.model.GetModelListResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private final CarRepository carRepository;
    private final ModelService modelService;
    private final ColorService colorService;

    @Override
    public List<GetCarListResponse> getAll() {
        List<Car> carList = carRepository.findAll();
        List<GetCarListResponse> getCarList = new ArrayList<>();
        for (Car car : carList) {
            GetCarListResponse dto = new GetCarListResponse();
            dto.setKilometer(car.getKilometer());
            dto.setYear(car.getYear());
            dto.setDailyPrice(car.getDailyPrice());
            dto.setPlate(car.getPlate());

            Model model = modelService.getModelById(car.getModel().getId());
            dto.setModel(new GetModelListResponse(model.getName()));

            Color color = colorService.getColorById(car.getColor().getId());
            dto.setColor(new GetColorListResponse(color.getName()));

            getCarList.add(dto);
        }
        return getCarList;
    }

    @Override
    public void add(AddCarRequest addCarRequest) {
        Car addCar = new Car();

        Model model = modelService.getModelById(addCarRequest.getModelId());
        if (model == null) {
            throw new RuntimeException("Belirtilen ModelId'ye sahip bir model bulunamadı.");
        }

        Color color = colorService.getColorById(addCarRequest.getColorId());
        if (color == null) {
            throw new RuntimeException("Belirtilen ColorId'ye sahip bir renk bulunamadı.");
        }

        addCar.setKilometer(addCarRequest.getKilometer());
        addCar.setYear(addCarRequest.getYear());
        addCar.setDailyPrice(addCarRequest.getDailyPrice());
        addCar.setPlate(addCarRequest.getPlate().replaceAll("\\s", ""));
        addCar.setModel(model);
        addCar.setColor(color);

        carRepository.save(addCar);
    }

    @Override
    public void update(UpdateCarRequest updateCarRequest) {

        Model model = modelService.getModelById(updateCarRequest.getModelId());
        if (model == null) {
            throw new RuntimeException("Belirtilen ModelId'ye sahip bir model bulunamadı.");
        }

        Color color = colorService.getColorById(updateCarRequest.getColorId());
        if (color == null) {
            throw new RuntimeException("Belirtilen ColorId'ye sahip bir renk bulunamadı.");
        }

        Car updateCar = carRepository.findById(updateCarRequest.getId()).orElseThrow();
        updateCar.setKilometer(updateCarRequest.getKilometer());
        updateCar.setYear(updateCarRequest.getYear());
        updateCar.setDailyPrice(updateCarRequest.getDailyPrice());
        updateCar.setPlate(updateCarRequest.getPlate());
        updateCar.setModel(model);
        updateCar.setColor(color);
        this.carRepository.save(updateCar);
    }

    //
}
