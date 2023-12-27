package com.nexgencarrental.nexGenCarRental.services.abstracts;

import com.nexgencarrental.nexGenCarRental.services.dtos.requests.model.AddModelRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.model.UpdateModelRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.model.GetModelListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.model.GetModelResponse;

import java.util.List;

public interface ModelService {
    public List<GetModelListResponse> getAll();
    public GetModelResponse getModelById(int id);
    public void add(AddModelRequest addModelRequest);
    public void update(UpdateModelRequest updateModelRequest);
    public void delete(int id);
}
