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
    @Column(columnDefinition = "VARCHAR(255)", name = "first_name")
    private String firstName;

    @NonNull
    @Column(columnDefinition = "VARCHAR(255)", name = "last_name")
    private String lastName;

    @NonNull
    @Column(columnDefinition = "VARCHAR(50)", name = "mobile_number")
    private String mobileNumber;

    @NonNull
    @Column(columnDefinition = "VARCHAR(100)", name = "email")
    private String email;

    @NonNull
    @Column(columnDefinition = "VARCHAR(255)", name = "password")
    private String password;

}
