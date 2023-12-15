package com.nexgencarrental.nexGenCarRental.services.abstracts;

import com.nexgencarrental.nexGenCarRental.entities.Model;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.model.UpdateModelRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.model.GetModelResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ModelService {
    GetModelResponse getModelById(int id);
}
