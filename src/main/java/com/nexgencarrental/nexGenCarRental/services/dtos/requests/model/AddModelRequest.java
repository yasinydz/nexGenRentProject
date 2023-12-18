package com.nexgencarrental.nexGenCarRental.services.dtos.requests.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddModelRequest {
    @Size(min = 2, message = "En az 2 harften oluşan bir model ismi giriniz")
    @Pattern(regexp = "^[A-Z][a-z]{1,13}$",message="İlk harfi BÜYÜK sonra ki harfler KÜÇÜK olacak şekilde giriniz.(Örn:'Focus'")
    private String name;

    @Positive(message = "Brand Id pozitif bir değer olmalıdır.")
    private int brandId;
}
