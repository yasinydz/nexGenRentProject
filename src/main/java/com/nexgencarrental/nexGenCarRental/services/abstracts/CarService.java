package com.nexgencarrental.nexGenCarRental.services.abstracts;

import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.AddCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.color.GetColorListResponse;

import java.util.List;

public interface CarService {
    public List<GetCarListResponse> getAll();
    public void add(AddCarRequest addCarRequest);
}
