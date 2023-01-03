package it.egames.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_ACCOUNT_ROLE"))
    private AccountRole accountRole;
    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_CUSTOMER_ACCOUNT"))
    private Customer customer;
}
