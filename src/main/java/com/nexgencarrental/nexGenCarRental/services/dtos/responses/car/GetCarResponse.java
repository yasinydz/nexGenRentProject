package com.nexgencarrental.nexGenCarRental.services.dtos.responses.car;

import com.nexgencarrental.nexGenCarRental.services.dtos.responses.color.GetColorListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.model.GetModelListResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GetCarResponse {
    private int kilometer;
    private int year;
    private double dailyPrice;
    private String plate;
    private GetModelListResponse model;
    private GetColorListResponse color;
}
