package com.nexgencarrental.nexGenCarRental.services.abstracts;

import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.AddRentalRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.UpdateRentalRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.rental.GetRentalListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.rental.GetRentalResponse;

import java.util.List;

public interface RentalService {
    public List<GetRentalListResponse> getAll();
    public GetRentalResponse getById(int id);
    public void add (AddRentalRequest addRentalRequest);
    public void update(UpdateRentalRequest updateRentalRequest);
    public void delete(int id);
}
