package az.company.response;


import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@ToString
public class GenericResponse {

    private int code;
    private String message;
}
