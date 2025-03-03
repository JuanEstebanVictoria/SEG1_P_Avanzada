package org.openapitools.api;

import lombok.RequiredArgsConstructor;
import org.openapitools.model.UserRegistration;
import org.openapitools.model.UserResponse;

import org.openapitools.service.UserService;
import org.openapitools.service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersApiController {


    private final UserService userService;


    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRegistration userRegistration) {
        UserResponse usuarioCreado = userService.createUser(userRegistration);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(usuarioCreado.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(usuarioCreado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {
         return  userService.getUserById(id)
                 .map(ResponseEntity::ok)
                 .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    private ResponseEntity<UserResponse> updateUser(@PathVariable String id, @Valid @RequestBody UserRegistration userUpdate) {
    return userService.updateUser(id, userUpdate)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());


    }
    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteUser(@PathVariable String id) {
        return userService.deleteUser(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }








}
