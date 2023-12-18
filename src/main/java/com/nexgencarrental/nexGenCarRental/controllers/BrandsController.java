package com.nexgencarrental.nexGenCarRental.controllers;

import com.nexgencarrental.nexGenCarRental.services.abstracts.BrandService;
import com.nexgencarrental.nexGenCarRental.services.abstracts.ColorService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.brand.AddBrandRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.brand.UpdateBrandRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.color.AddColorRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.color.UpdateColorRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.brand.GetBrandListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.brand.GetBrandResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.color.GetColorResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/brands")
@AllArgsConstructor

public class BrandsController {
    private final BrandService brandService;
    @GetMapping("/getAll")
    public List<GetBrandListResponse> getAll(){
        return brandService.getAll();
    }
    @GetMapping("/{id}")
    public GetBrandResponse getBrandById(int id){
        return brandService.getBrandById(id);
    }
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddBrandRequest addBrandRequest) {
        this.brandService.add(addBrandRequest);
    }
    @PutMapping("/update")
    public void update(@RequestBody @Valid UpdateBrandRequest updateBrandRequest){
        brandService.update(updateBrandRequest);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        brandService.delete(id);
    }
}


