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

    private String lastwatered;

    private String frequency;

    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = "plants")
    private User user;

    public Plant() {
    }

    public Plant(String nickname, String species, String lastwatered, String frequency, User user) {
        this.nickname = nickname;
        this.species = species;
        this.frequency = frequency;
        this.user = user;
        this.lastwatered = lastwatered;
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

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLastwatered() {
        return lastwatered;
    }

    public void setLastwatered(String lastwatered) {
        this.lastwatered = lastwatered;
    }
}
