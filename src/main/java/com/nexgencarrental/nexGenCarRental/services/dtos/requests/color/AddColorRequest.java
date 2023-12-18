package com.nexgencarrental.nexGenCarRental.services.dtos.requests.color;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddColorRequest {
    @Size(min = 2,message = "En az 2 harften oluşan bir renk giriniz" )
    @Pattern(regexp = "^[A-Z][a-z]{1,13}$",message="İlk harfi BÜYÜK sonra ki harfler KÜÇÜK olacak şekilde giriniz.(Örn:'Beyaz'")
    private String name;
}
