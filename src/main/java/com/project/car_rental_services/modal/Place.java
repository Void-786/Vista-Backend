package com.project.car_rental_services.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "places")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TourPackage> packages;

    public Place() {
    }

    public Place(Integer id, String name, byte[] image, List<TourPackage> packages) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.packages = packages;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public List<TourPackage> getPackages() {
        return packages;
    }

    public void setPackages(List<TourPackage> packages) {
        this.packages = packages;
    }
}
