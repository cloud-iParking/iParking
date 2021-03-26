package ro.ubb.cloud.iParking.service.dto;

import lombok.Data;

@Data
public class UserDTO {

    private String lastName;
    private String firstName;
    private String email;
    private String phone;
    private String username;
    private String password;
    private String carNumber;
    private Boolean isAdmin;
    private Boolean isBlocked;

    public UserDTO() {

    }
}
