package soa.paymentservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


}
