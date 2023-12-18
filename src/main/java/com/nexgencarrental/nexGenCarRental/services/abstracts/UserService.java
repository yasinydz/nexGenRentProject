package com.nexgencarrental.nexGenCarRental.services.abstracts;

import com.nexgencarrental.nexGenCarRental.services.dtos.responses.user.GetUserResponse;

public interface UserService {
    GetUserResponse getById(int id);
}
