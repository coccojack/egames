package it.egames.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_CUSTOMER_WISHLIST"))
    private Customer customer;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "wishlist_videogame",
            joinColumns = @JoinColumn(name = "wishlist_id", foreignKey = @ForeignKey(name = "FK_WISHLIST_VIDEOGAME_MTM")),
            inverseJoinColumns = @JoinColumn(name = "videogame_id", foreignKey = @ForeignKey(name = "FK_VIDEOGAME_WISHLIST_MTM")))
    private Set<Videogame> videogameSet;
}
