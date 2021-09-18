package az.company.controller;


import az.company.model.User;
import az.company.repository.UserRepository;
import az.company.response.ApiError;
import az.company.response.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;


@RestController
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    UserRepository userRepository;


    @PostMapping("/api/1.0/auth")
    @JsonView(Views.Sensitive.class)
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
            ApiError error = new ApiError(401, "Unauthorized error", "/api/1.0/auth");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
        String hashedPassword = inDb.getPassword();
        if (!passwordEncoder.matches(password, hashedPassword)) {
            ApiError error = new ApiError(401, "Unauthorized error", "/api/1.0/auth");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }

        return ResponseEntity.ok(inDb);

    }
}
