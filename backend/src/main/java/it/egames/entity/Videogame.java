package it.egames.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Videogame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Lob
    @Column(name = "image", columnDefinition = "MEDIUMBLOB")
    private byte[] image;
    @ManyToOne
    private Genre genre;
    @ManyToMany
    @JoinTable(
            name = "videogame_theme",
            joinColumns = @JoinColumn(name = "videogame_id"),
            inverseJoinColumns = @JoinColumn(name = "theme_id"))
    private Set<Theme> theme;
    private String description;
    private Float price;
    @ManyToOne
    private Platform platform;
    private String pegi;
    private String playerNum;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "videogame_gamemode",
            joinColumns = @JoinColumn(name = "videogame_id"),
            inverseJoinColumns = @JoinColumn(name = "gamemode_id"))
    private Set<GameMode> gameModeSet;
    @ManyToOne
    private SoftwareHouse softwareHouse;
    @ManyToOne
    private Developer audioDev;
    @ManyToOne
    private Developer graphDev;
    @ManyToOne
    private Developer gameDev;
}