package it.egames.dto;

import lombok.Data;

@Data
public class VideogameDTO {
    private String title;
    private String genre;
    private String description;
    private Float price;

    private String platformName;
}
