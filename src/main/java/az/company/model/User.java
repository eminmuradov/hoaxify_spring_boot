package az.company.model;

import az.company.response.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(generator = "user_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_seq", sequenceName = "seq_user", allocationSize = 1)
    private int id;


    @NotNull(message = "{hoaxify.constraint.username.NotNull.message}")
    @Size(min = 4, max = 255)
    @UniqueUsername
    @JsonView(Views.Base.class)
    private String username;


    @NotNull
    @Size(min = 4, max = 255)
    @JsonView(Views.Base.class)
    private String displayName;


    @NotNull(message = "{hoaxify.constraint.password.NotNull.message}")
    @Size(min = 4, max = 255)
    @Pattern(regexp = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message ="{hoaxify.constraint.password.Pattern.message}")
    @JsonView(Views.Sensitive.class)
    private String password;

    @JsonView(Views.Base.class)
    private String image;

}
