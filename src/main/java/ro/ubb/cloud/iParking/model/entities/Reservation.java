package ro.ubb.cloud.iParking.model.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "is_active")
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
