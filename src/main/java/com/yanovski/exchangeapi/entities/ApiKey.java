package com.yanovski.exchangeapi.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ApiKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String apiKey;

    @Column
    private Boolean active;

    @Column
    private String description;
}
