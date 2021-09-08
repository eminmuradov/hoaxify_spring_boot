package az.company.model;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UniqueUsernameValidation.class})

public @interface UniqueUsername {

    String message() default "Unique username";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
