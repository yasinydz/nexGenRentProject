package com.nexgencarrental.nexGenCarRental.services.dtos.requests.brand;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UpdateBrandRequest {
    @Positive(message = "Id alanı 0'dan küçük olamaz.")
    private int id;

    @Size(min = 2,message = "En az 2 harften oluşan bir marka giriniz" )
    @Pattern(regexp = "^[A-Z][a-z]{1,13}$",message="Boşluksuz ilk harfi BÜYÜK sonra ki harfler KÜÇÜK olacak şekilde giriniz.(Örn:'Ford'")
    private String name;
}
