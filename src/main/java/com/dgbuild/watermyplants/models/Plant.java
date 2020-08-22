package com.dgbuild.watermyplants.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "plants")
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long plantid;

    @Column(nullable = false)
    private String nickname;

    private String species;

    private int frequency;

    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = "plants")
    private User user;


}
