package org.openapitools.service;

import jakarta.validation.constraints.NotNull;
import org.openapitools.model.UserRegistration;
import org.openapitools.model.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserResponse createUser(@NotNull UserRegistration userRegistration);

    List<UserResponse> getAllUsers();

    Optional<UserResponse> getUserById(@NotNull String id);

    Optional<UserResponse> updateUser(String id, @NotNull UserRegistration userRegistration);

    boolean deleteUser(@NotNull String id);
}
