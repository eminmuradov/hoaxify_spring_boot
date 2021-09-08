package az.company.model;

import az.company.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidation implements ConstraintValidator<UniqueUsername,String> {

    @Autowired
    UserRepository userRepository;


    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        User user=userRepository.findByUsername(username);

        if (user !=null){
            return false;
        }

        return true;
    }
}
