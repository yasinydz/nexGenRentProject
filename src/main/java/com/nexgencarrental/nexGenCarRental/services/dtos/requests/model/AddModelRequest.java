package com.nexgencarrental.nexGenCarRental.services.dtos.requests.model;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddModelRequest {
    @Size(min = 2, message = "En az 2 harften oluşan bir model ismi giriniz")
    private String name;

    @Positive(message = "Brand Id pozitif bir değer olmalıdır.")
    private int brandId;
}
