package com.nexgencarrental.nexGenCarRental.services.rules.user;

import com.nexgencarrental.nexGenCarRental.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserBusinessRulesManager implements UserBusinessRulesService {
    private UserRepository userRepository;
    @Override
    public void existsById(int id) {
        if(!userRepository.existsById(id)){
            throw new RuntimeException("The User with " + id + " the ID number cannot be found in the system.");
        }

    }

    @Override
    public void existsByName(String name) {

    }
}
