package ro.ubb.cloud.iParking.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "car_number")
    private String carNumber;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    @Column(name = "is_blocked")
    private Boolean isBlocked;

    @Column(name = "report_number")
    private Integer reportNumber;
}
