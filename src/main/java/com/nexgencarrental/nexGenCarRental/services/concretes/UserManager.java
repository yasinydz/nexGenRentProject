package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.services.abstracts.UserService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.user.AddUserRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.user.UpdateUserRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.user.GetUserListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.user.GetUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class UserManager implements UserService {
    @Override
    public List<GetUserListResponse> getAll() {
        return null;
    }

    @Override
    public GetUserResponse getById(int id) {
        return null;
    }

    @Override
    public void add(AddUserRequest addUserRequest) {

    }

    @Override
    public void update(UpdateUserRequest updateUserRequest) {

    }

    @Override
    public void delete(int id) {

    }
}
