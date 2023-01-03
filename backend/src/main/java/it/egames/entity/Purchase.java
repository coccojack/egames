package it.egames.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_CUSTOMER"))
    private Customer customer;
    private String status;
    private Float total;
    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_PURCHASE_ADDRESS"))
    private Address address;
}
