package com.nexgencarrental.nexGenCarRental.controllers;

import com.nexgencarrental.nexGenCarRental.services.abstracts.CarService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.AddCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.UpdateCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarListResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cars")
@AllArgsConstructor
public class CarsController {

    //
    private final CarService carService;

    @GetMapping("/getAll")
    public List<GetCarListResponse> getAll(){
        return this.carService.getAll();
    }
    @PostMapping("/add")
    public void add(@RequestBody @Valid AddCarRequest addCarRequest){
        this.carService.add(addCarRequest);
    }

    @PutMapping("/update")
    public String update(@Valid @RequestBody UpdateCarRequest updateCarRequest) {
        return this.carService.update(updateCarRequest);
    }


}
