package ro.ubb.cloud.iParking.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private Integer id;
    private String lastName;
    private String firstName;
    private String email;
    private String phone;
    private String username;
    private String password;
    private String carNumber;
    private Boolean isAdmin;
    private Boolean isBlocked;
    private Integer reportNumber;

}
