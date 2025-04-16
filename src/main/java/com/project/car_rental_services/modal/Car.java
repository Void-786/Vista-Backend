package com.project.car_rental_services.modal;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Lob
    private byte[] image;

    private String type;

    private Integer seats;

    public Car(Integer id, String name, byte[] image, String type, Integer seats) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.type = type;
        this.seats = seats;
    }

    public Car() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }
}