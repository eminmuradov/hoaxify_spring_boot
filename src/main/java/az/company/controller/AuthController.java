package az.company.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private static final Logger logger= LoggerFactory.getLogger(AuthController.class);


    void handleAuthentication(@RequestHeader(name = "Authorization",required = false) String authorization){
        if (authorization==null){

        }
    }
}
