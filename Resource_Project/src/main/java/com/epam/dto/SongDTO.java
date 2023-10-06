package com.epam.dto;


import lombok.Data;

@Data
public class SongDTO {

    String name;

    String artist;

    String album;

    String length;

    Integer resourceId;

    String year;
}
