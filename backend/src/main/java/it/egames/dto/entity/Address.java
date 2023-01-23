package it.egames.dto.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_CUSTOMER_ADDRESS"))
    private Customer customer;
    private String line;
    private String country;
    private String state;
    private String zipCode;
}
