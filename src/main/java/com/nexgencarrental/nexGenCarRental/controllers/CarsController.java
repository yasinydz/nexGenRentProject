package com.nexgencarrental.nexGenCarRental.controllers;

import com.nexgencarrental.nexGenCarRental.services.abstracts.CarService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.AddCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.UpdateCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cars")
@AllArgsConstructor
public class CarsController {
    private final CarService carService;

    @GetMapping("/getAll")
    public List<GetCarListResponse> getAll(){
        return this.carService.getAll();
    }
    @GetMapping("/{id}")
    public GetCarResponse getById(@PathVariable int id){
        return carService.getById(id);
    }
    @PostMapping("/add")
    public void add(@RequestBody @Valid AddCarRequest addCarRequest){
        this.carService.add(addCarRequest);
    }
    @PutMapping("/update")
    public void update(@Valid @RequestBody UpdateCarRequest updateCarRequest) {
        this.carService.update(updateCarRequest);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id ) {
        carService.delete(id);
    }

}
