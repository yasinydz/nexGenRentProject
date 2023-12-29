package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Model;
import com.nexgencarrental.nexGenCarRental.repositories.ModelRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.ModelService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.model.AddModelRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.model.UpdateModelRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.model.GetModelListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.model.GetModelResponse;
import com.nexgencarrental.nexGenCarRental.services.rules.model.ModelBusinessRulesService;
import org.springframework.stereotype.Service;

@Service
public class ModelManager extends BaseManager<Model, ModelRepository, GetModelResponse, GetModelListResponse, AddModelRequest, UpdateModelRequest> implements ModelService {

    private final ModelBusinessRulesService modelBusinessRulesService;

    public ModelManager(ModelRepository repository, ModelMapperService modelMapperService, ModelBusinessRulesService modelBusinessRulesService) {
        super(repository, modelMapperService, GetModelResponse.class, GetModelListResponse.class, Model.class, AddModelRequest.class, UpdateModelRequest.class);
        this.modelBusinessRulesService = modelBusinessRulesService;
    }

    @Override
    public void customAdd(AddModelRequest addModelRequest) {
        modelBusinessRulesService.existsByName(addModelRequest.getName());
        add(addModelRequest, Model.class);
    }

    @Override
    public void customUpdate(UpdateModelRequest updateModelRequest) {
        modelBusinessRulesService.existsById(updateModelRequest.getId());
        modelBusinessRulesService.existsByName(updateModelRequest.getName());
        update(updateModelRequest, Model.class);
    }
}
