package com.project.car_rental_services.controller;

import com.project.car_rental_services.modal.Place;
import com.project.car_rental_services.service.PlaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/places")
public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping("/admin/add/new-place")
    public ResponseEntity<?> addPlace(@RequestParam("name") String name,@RequestParam("image") MultipartFile image) {
        try {
            placeService.addPlace(name, image);
            return ResponseEntity.ok("Place Added Successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image: " + e.getMessage());
        }
    }

    @DeleteMapping("/admin/delete/delete-place")
    public ResponseEntity<?> deletePlace(@RequestParam("placeId") Integer id) {
        placeService.deletePlaceById(id);
        return ResponseEntity.ok("Place deleted Successfully");
    }

    @PutMapping("/admin/update/update-place")
    public ResponseEntity<?> updatePlace(@RequestParam("placeId") Integer id,@RequestParam("name") String name,@RequestParam(value = "image", required = false) MultipartFile newImage) {
        try {
            Optional<Place> existingPlace = placeService.getPlaceById(id);

            if (existingPlace.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Place not found");
            }

            Place place = existingPlace.get();
            place.setName(name);

            if (newImage != null && !newImage.isEmpty()) {
                byte[] newImageBytes = newImage.getBytes();
                place.setImage(newImageBytes);
            } else {
                byte[] currentImageBytes = place.getImage();
                if (currentImageBytes == null) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No image found for the car in the database");
                }
                place.setImage(currentImageBytes);
            }

            Place updatedPlace = placeService.updatePlaceById(id, place);
            return ResponseEntity.ok(updatedPlace);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing image");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating car");
        }
    }

    @GetMapping("/all-places")
    public ResponseEntity<List<Place>> getAllPlaces() {
        try {
            List<Place> place = placeService.getAllPlaces();
            return ResponseEntity.ok(place);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/get-place/{id}")
    public ResponseEntity<Place> getPlaceById(@PathVariable("id") Integer id) {
        Optional<Place> place = placeService.getPlaceById(id);
        return place.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}
