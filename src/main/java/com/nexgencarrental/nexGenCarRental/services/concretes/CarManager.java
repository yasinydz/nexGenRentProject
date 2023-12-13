package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.entities.Car;
import com.nexgencarrental.nexGenCarRental.repositories.CarRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.CarService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.AddCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarListResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private final CarRepository carRepository;

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
            getCarList.add(dto);
        }
        return getCarList;


    }

    @Override
    public void add(AddCarRequest addCarRequest) {
        Car addCar=new Car();
        addCar.setKilometer(addCarRequest.getKilometer());
        addCar.setYear(addCarRequest.getYear());
        addCar.setDailyPrice(addCarRequest.getDailyPrice());
        addCar.setPlate(addCarRequest.getPlate().replaceAll("\s", ""));

        carRepository.save(addCar);
    }





}
