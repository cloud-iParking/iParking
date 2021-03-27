package ro.ubb.cloud.iParking.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "parking_place")
public class ParkingPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "number")
    private Integer number;

    @Column(name = "availableFrom")
    private Timestamp availableFrom;

    @Column(name = "availableUntil")
    private Timestamp availableUntil;

    @Column(name = "isFree")
    private Boolean isFree;

    @OneToOne
    @JoinColumn(name = "street_id")
    private Street street;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
