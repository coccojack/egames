package it.egames.dto.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @Basic
    private Date birthDate;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_SOFTWARE_HOUSE_DEVELOPER"))
    private SoftwareHouse softwareHouse;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_SPECIALTY_DEVELOPER"))
    private Specialty specialty;
}
