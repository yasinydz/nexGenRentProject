package com.nexgencarrental.nexGenCarRental.services.dtos.responses.model;

import com.nexgencarrental.nexGenCarRental.services.dtos.responses.color.GetColorListResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetModelListResponse {
    private int id;
    private String name;

    public GetModelListResponse() {
    }
}
