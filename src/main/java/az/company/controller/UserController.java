package az.company.controller;


import az.company.model.User;
import az.company.response.ApiResponse;
import az.company.response.GenericResponse;
import az.company.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "api/1.0")
@AllArgsConstructor
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private UserService userService;



    @PostMapping(value = "users")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createUser(@RequestBody User user) {
        String username=user.getUsername();
        if (username==null || username.isEmpty()){
            ApiResponse error=new ApiResponse(400,"Validation Error","/api/1.0/users");
            Map<String,String> validationErrors= new HashMap<>();
            validationErrors.put("username","Username cannot be null!!!");
            error.setValidationErrors(validationErrors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
          userService.createUser(user);
          return ResponseEntity.ok(new GenericResponse(200,"User created!"));
    }
}
