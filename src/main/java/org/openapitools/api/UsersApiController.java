package org.openapitools.api;

import lombok.RequiredArgsConstructor;
import org.openapitools.model.ErrorResponse;
import org.openapitools.model.SuccessResponse;
import org.openapitools.model.UserRegistration;
import org.openapitools.model.UserResponse;

import org.openapitools.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.context.request.NativeWebRequest;

import jakarta.validation.constraints.*;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-20T21:07:46.519360700-05:00[America/Bogota]", comments = "Generator version: 7.7.0")
@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersApiController implements UsersApi {

    private final NativeWebRequest request;
    private final UserService userService;


    //@Autowired
    //public UsersApiController(NativeWebRequest request) {
        //this.request = request;
   // }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

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










}
