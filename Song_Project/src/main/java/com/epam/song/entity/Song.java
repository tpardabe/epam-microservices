package com.epam.song.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "song")
@Data
public class Song {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    String artist;

    String album;

    String length;

    Integer resourceId;//TODO: resource

    String year;
}