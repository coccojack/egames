package it.egames.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.Set;

@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @Basic
    private Date birthdate;
    private String email;
    private String password;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_ACCOUNT_ROLE"))
    private AccountRole accountRole;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "preferred_videogame",
            joinColumns = @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "FK_PREFERRED_VIDEOGAME_MTM")),
            inverseJoinColumns = @JoinColumn(name = "videogame_id", foreignKey = @ForeignKey(name = "FK_VIDEOGAME_PREFERRED_MTM")))
    private Set<Videogame> videogameSet;
}
