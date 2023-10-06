package com.epam.repo;

import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;

@Table(name = "music")
@Entity
@Data
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Lob
    //@Column(length=100000)
    private byte[] data;

    private String name;
}
