package com.nexgencarrental.nexGenCarRental.services.dtos.requests.color;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateColorRequest {
    @Positive(message = "Id alanı 0'dan küçük olamaz.")
    private int id;

    @Size(min = 2,message = "En az 2 harften oluşan bir renk giriniz" )
    private String name;
}
