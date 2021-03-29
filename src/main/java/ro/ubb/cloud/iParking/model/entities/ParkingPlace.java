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
@Table(name = "parking_place")
public class ParkingPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "number")
    private Integer number;

    @Column(name = "available_from")
    private Timestamp availableFrom;

    @Column(name = "available_until")
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
