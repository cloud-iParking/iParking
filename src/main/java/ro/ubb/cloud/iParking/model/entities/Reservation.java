package ro.ubb.cloud.iParking.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "isActive")
    private Boolean isActive;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @OneToOne
    @JoinColumn(name = "parking_place_id")
    private ParkingPlace parkingPlace;

    @ManyToOne
    @JoinColumn(name = "loaner_id")
    private User loaner;
}
