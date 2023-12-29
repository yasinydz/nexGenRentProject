package com.nexgencarrental.nexGenCarRental.services.abstracts;

import com.nexgencarrental.nexGenCarRental.entities.concretes.Car;
import com.nexgencarrental.nexGenCarRental.repositories.CarRepository;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.AddCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.UpdateCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.Car.AddCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.Car.UpdateCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.color.AddColorRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.color.UpdateColorRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.Car.GetCarListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.Car.GetCarResponse;

import java.util.List;

public interface CarService extends BaseService<Car, CarRepository, GetCarResponse, GetCarListResponse, AddCarRequest, UpdateCarRequest> {
    void customAdd(AddColorRequest addColorRequest);
    void customUpdate(UpdateColorRequest updateColorRequest);
}
