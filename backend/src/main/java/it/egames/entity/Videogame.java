package it.egames.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
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
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_GENRE_VIDEOGAME"))
    private Genre genre;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "videogame_theme",
            joinColumns = @JoinColumn(name = "videogame_id"),
            foreignKey = @ForeignKey(name = "FK_VG_THEME_MTM"),
            inverseJoinColumns = @JoinColumn(name = "theme_id", foreignKey = @ForeignKey(name = "FK_THEME_VG_MTM")))
    private Set<Theme> theme;
    private String description;
    private Float price;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_PLATFORM_VIDEOGAME"))
    private Platform platform;
    private String pegi;
    private String playerNum;
    @Basic
    private Date releaseDate;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "videogame_gamemode",
            joinColumns = @JoinColumn(name = "videogame_id"),
            foreignKey = @ForeignKey(name = "FK_VG_GAMEMODE_MTM"),
            inverseJoinColumns = @JoinColumn(name = "gamemode_id", foreignKey = @ForeignKey(name = "FK_GAMEMODE_VG_MTM")))
    private Set<GameMode> gameModeSet;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_SOFTWARE_HOUSE_VIDEOGAME"))
    private SoftwareHouse softwareHouse;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_AUDIO_DEV"))
    private Developer audioDev;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_GRAPH_DEV"))
    private Developer graphDev;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_GAME_DEV"))
    private Developer gameDev;
    private boolean ingamePurchases;
    private boolean adultGame;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "videogame_language",
            joinColumns = @JoinColumn(name = "videogame_id"),
            foreignKey = @ForeignKey(name = "FK_VG_LAN_MTM"),
            inverseJoinColumns = @JoinColumn(name = "language_id", foreignKey = @ForeignKey(name = "FK_LAN_VG_MTM")))
    private Set<Language> languageSet;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "videogame_input_type",
            joinColumns = @JoinColumn(name = "videogame_id"),
            foreignKey = @ForeignKey(name = "FK_VIDEOGAME_INPUTTYPE_MTM"),
            inverseJoinColumns = @JoinColumn(name = "input_type_id", foreignKey = @ForeignKey(name = "FK_INPUTTYPE_VIDEOGAME_MTM")))
    private Set<InputType> inputTypeSet;
}