package it.egames.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "purchase_videogame")
@Data
public class PurchaseVideogame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_PURCHASE_MTM"))
    private Purchase purchase;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_VIDEOGAME_MTM"))
    private Videogame videogame;
    private Integer quantity;
}
