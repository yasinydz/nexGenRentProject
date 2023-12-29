package com.nexgencarrental.nexGenCarRental.services.dtos.requests.model;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateModelRequest {
    @Positive(message = "Id 0'dan küçük olamaz")
    private int id;

    @Size(min = 2,message = "Girilen model en az 2 harfli olmalıdır.")
    private String name;

    @Positive(message = "Brand Id 0'dan küçük olamaz")
    private int brandId;
}
