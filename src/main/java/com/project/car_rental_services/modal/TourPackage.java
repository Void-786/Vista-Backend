package com.project.car_rental_services.modal;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class TourPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String duration;

    @Column(columnDefinition = "TEXT")
    private String tour_highlight;

    private String price;

    @ElementCollection
    @CollectionTable(name = "package_locations", joinColumns = @JoinColumn(name = "package_id"))
    @Column(name = "location")
    private List<String> locations;

    @Column(columnDefinition = "TEXT")
    private String itinerary_heading;

    @OneToMany(mappedBy = "tourPackage", cascade = CascadeType.ALL, orphanRemoval = true ,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Itinerary> itinerary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", nullable = false)
    @JsonBackReference
    private Place place;

    public TourPackage() {
    }

    public TourPackage(Integer id, String title, String duration, String tour_highlight, String price, List<String> locations, String itinerary_heading, List<Itinerary> itinerary, Place place) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.tour_highlight = tour_highlight;
        this.price = price;
        this.locations = locations;
        this.itinerary_heading = itinerary_heading;
        this.itinerary = itinerary;
        this.place = place;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTour_highlight() {
        return tour_highlight;
    }

    public void setTour_highlight(String tour_highlight) {
        this.tour_highlight = tour_highlight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public String getItinerary_heading() {
        return itinerary_heading;
    }

    public void setItinerary_heading(String itinerary_heading) {
        this.itinerary_heading = itinerary_heading;
    }

    public List<Itinerary> getItinerary() {
        return itinerary;
    }

    public void setItinerary(List<Itinerary> itinerary) {
        this.itinerary = itinerary;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
