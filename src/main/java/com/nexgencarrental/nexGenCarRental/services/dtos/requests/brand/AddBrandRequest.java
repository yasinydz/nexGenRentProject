package com.nexgencarrental.nexGenCarRental.services.dtos.requests.brand;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AddBrandRequest {
    @Size(min = 2,message = "En az 2 harften oluşan bir marka giriniz" )
    private String name;
}
