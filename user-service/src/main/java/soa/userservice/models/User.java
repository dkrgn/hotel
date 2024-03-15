package soa.userservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(columnDefinition = "VARCHAR(255)")
    private String firstName;

    @NonNull
    @Column(columnDefinition = "VARCHAR(255)")
    private String lastName;

    @NonNull
    @Column(columnDefinition = "VARCHAR(100)")
    private String email;

    @NonNull
    @Column(columnDefinition = "VARCHAR(50)")
    private String mobileNumber;
}
