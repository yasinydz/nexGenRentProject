package com.nexgencarrental.nexGenCarRental.services.dtos.requests.car;

import com.nexgencarrental.nexGenCarRental.entities.Model;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.color.AddColorRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.model.AddModelRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.color.GetColorListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.model.GetModelListResponse;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCarRequest {

    @Min(value = 0,message = "sıfır veya sıfırdan büyük bir değer giriniz")
    private double kilometer;

    @Min(value = 2005, message = "2005 ve 2024 aralıgında olmalıdır")
    @Max(value = 2024, message = "2024'den büyük olamaz")
    private int year;

    //inclusive true olmalı çünkü inclusive=false değerinde 0'ı dahil etmiyor.
    @DecimalMin(value = "0.0", inclusive = true, message = "Günlük fiyat sıfırdan küçük olamaz")
    private double dailyPrice;

    @Pattern(regexp = "^\\d{1,2}\\s[A-Z]{1,3}\\s\\d{1,4}$", message = "'34 ABC 456' bu formata göre giriş yapınız")
    private String plate;

    //@PositiveOrZero yazmamız yeterli (@Positive de 0 dahil etmiyor)
    @PositiveOrZero(message = "Model Id 0'dan küçük olamaz.")
    private int modelId;

    @PositiveOrZero(message = "Color Id 0'dan küçük olamaz.")
    private int colorId;

}
