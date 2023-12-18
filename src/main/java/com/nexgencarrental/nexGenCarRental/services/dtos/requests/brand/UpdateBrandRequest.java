package com.nexgencarrental.nexGenCarRental.services.dtos.requests.brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UpdateBrandRequest {
    private int id;
    private String name;
}
