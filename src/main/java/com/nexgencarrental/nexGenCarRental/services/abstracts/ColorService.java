package com.nexgencarrental.nexGenCarRental.services.abstracts;

import com.nexgencarrental.nexGenCarRental.entities.Color;
import com.nexgencarrental.nexGenCarRental.entities.Model;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.AddCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.color.AddColorRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.color.UpdateColorRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.color.GetColorListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.color.GetColorResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ColorService {
    GetColorResponse getColorById(int id);
    List<GetColorListResponse> getAll();
    public void add(AddColorRequest addColorRequest);
    void update(UpdateColorRequest updateColorRequest);
    void delete(int id);
}
