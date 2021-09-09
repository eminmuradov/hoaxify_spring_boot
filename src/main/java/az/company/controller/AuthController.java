package az.company.controller;


import az.company.model.User;
import az.company.repository.UserRepository;
import az.company.response.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
public class AuthController {

    @Autowired
    UserRepository userRepository;

    ResponseEntity<?> handleAuthentication(@RequestHeader(name = "Authorization", required = false) String authorization) {

        if (authorization == null) {
            ApiError error = new ApiError(401, "Unauthorized error", "/api/1.0/auth");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }

        String bas64encoded = authorization.split("Basic ")[1];
        String decoded = new String(Base64.getDecoder().decode(bas64encoded));

        String[] parts = decoded.split(":");
        String username = parts[0];
        String password = parts[1];

        User inDb = userRepository.findByUsername(username);

        if (inDb == null) {
            ApiError error= new ApiError(401,"Unauthorized error","/api/1.0/auth");
        }


        return ResponseEntity.ok().build();
    }
}
