package it.egames.dto;

import lombok.Data;

@Data
public class VideogameDTO {
    private String title;
    private String category;
    private String description;
    private Float price;

    private String platformName;
}
