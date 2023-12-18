package com.nexgencarrental.nexGenCarRental.services.dtos.responses.model;

import com.nexgencarrental.nexGenCarRental.services.dtos.responses.color.GetColorListResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetModelListResponse {
    private int id;
    private String name;
}
