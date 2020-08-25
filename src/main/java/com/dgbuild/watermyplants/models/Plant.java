package com.dgbuild.watermyplants.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "plants")
@JsonIgnoreProperties(value = "hasValueForFrequency")
public class Plant extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long plantid;

    @Column(nullable = false)
    private String nickname;

    private String species;

    @Transient
    public boolean hasValueForFrequency = false;

    private int frequency;

    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = "plants")
    private User user;

    public Plant() {
    }

    public Plant(String nickname, String species, int frequency, User user) {
        this.nickname = nickname;
        this.species = species;
        this.frequency = frequency;
        this.user = user;
    }

    public long getPlantid() {
        return plantid;
    }

    public void setPlantid(long plantid) {
        this.plantid = plantid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        hasValueForFrequency = true;
        this.frequency = frequency;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
