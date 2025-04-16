package com.project.car_rental_services.service;

import com.project.car_rental_services.modal.Car;
import com.project.car_rental_services.modal.Place;
import com.project.car_rental_services.repository.PlaceRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {

    private final PlaceRepository placeRepo;

    public PlaceService(PlaceRepository placeRepo) {
        this.placeRepo = placeRepo;
    }

    public void addPlace(String name, MultipartFile image) throws IOException {
        byte[] imageBytes = image.getBytes();
        Place place = new Place();
        place.setName(name);
        place.setImage(imageBytes);
        placeRepo.save(place);
    }

    public void deletePlaceById(Integer id) {
        placeRepo.deleteById(id);
    }

    public Place updatePlaceById(Integer id, Place updatedPlace) {
        Optional<Place> existingPlace = placeRepo.findById(id);

        if (existingPlace.isPresent()) {
            Place place = existingPlace.get();
            place.setName(updatedPlace.getName());
            place.setImage(updatedPlace.getImage());
            return placeRepo.save(place);
        } else {
            throw new RuntimeException("Place with Id " + id + " not found");
        }
    }

    public Optional<Place> getPlaceById(Integer id) {
        return placeRepo.findById(id);
    }

    public List<Place> getAllPlaces() {
        return placeRepo.findAll();
    }
}
