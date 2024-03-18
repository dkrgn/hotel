package soa.roomservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.lang.NonNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rooms")
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(name = "room_number", unique = true, nullable = false)
    private String roomNumber;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @NonNull
    @Column(name = "capacity", nullable = false)
    @ColumnDefault("1")
    private Integer capacity;

    @NonNull
    @Column(name = "is_occupied", nullable = false)
    private boolean isOccupied;

    @NonNull
    @Column(name = "cost", nullable = false)
    private int cost;

}
