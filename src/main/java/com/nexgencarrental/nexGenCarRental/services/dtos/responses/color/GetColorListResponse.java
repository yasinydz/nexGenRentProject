package com.nexgencarrental.nexGenCarRental.services.dtos.responses.color;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetColorListResponse {
    private int id;
    private String name;
    public GetColorListResponse() {
    }
}
