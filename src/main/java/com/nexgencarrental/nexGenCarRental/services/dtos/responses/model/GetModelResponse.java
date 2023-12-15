package com.nexgencarrental.nexGenCarRental.services.dtos.responses.model;

import com.nexgencarrental.nexGenCarRental.entities.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetModelResponse {
    private int id;
    private String name;
}
