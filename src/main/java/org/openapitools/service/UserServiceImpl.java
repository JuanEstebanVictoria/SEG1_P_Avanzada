package org.openapitools.service;

import jakarta.validation.constraints.NotNull;
import org.openapitools.model.UserRegistration;
import org.openapitools.model.UserResponse;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {

    private final List<UserResponse> users = new ArrayList<>();


    @Override
    public UserResponse createUser(@NotNull UserRegistration userRegistration) {
        UserResponse userCreated = new UserResponse();
        userCreated.setId(UUID.randomUUID().toString());
        userCreated.setFullName(userRegistration.getFullName());
        userCreated.setEmail(userRegistration.getEmail());
        userCreated.setRol(userRegistration.getRol().getValue());
        users.add(userCreated);

        return userCreated;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return users;
    }

    @Override
    public Optional<UserResponse> getUserById(@NotNull String id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<UserResponse> updateUser(String id, @NotNull UserRegistration userRegistration) {
        for(UserResponse user : users) {
            if(user.getId().equals(id)) {
                user.setFullName(userRegistration.getFullName());
                user.setEmail(userRegistration.getEmail());
                user.setRol(userRegistration.getRol().getValue());
                return Optional.of(user);

            }
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteUser(@NotNull String id) {
        return users.removeIf(user -> user.getId().equals(id));
    }



}