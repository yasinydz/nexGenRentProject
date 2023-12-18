package com.nexgencarrental.nexGenCarRental.controllers;

import com.nexgencarrental.nexGenCarRental.services.abstracts.ColorService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.AddCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.color.AddColorRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.color.UpdateColorRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.color.GetColorListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.color.GetColorResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/colors")
@AllArgsConstructor
public class ColorsController {
    private final ColorService colorService;
    @GetMapping("/getAll")
    public List<GetColorListResponse> getAll(){
        return colorService.getAll();
    }
    @GetMapping("/{id}")
    public GetColorResponse getColorById(int id){
        return colorService.getColorById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddColorRequest addColorRequest) {
        this.colorService.add(addColorRequest);
    }

    @PutMapping("/update")
    public void update(@RequestBody @Valid UpdateColorRequest updateColorRequest){
        colorService.update(updateColorRequest);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        colorService.delete(id);
    }

}
