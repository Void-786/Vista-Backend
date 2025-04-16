package com.project.car_rental_services.service;

import com.project.car_rental_services.modal.Itinerary;
import com.project.car_rental_services.modal.TourPackage;
import com.project.car_rental_services.modal.Place;
import com.project.car_rental_services.repository.ItineraryRepository;
import com.project.car_rental_services.repository.PackageRepository;
import com.project.car_rental_services.repository.PlaceRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PackageService {

    private final PackageRepository packageRepo;
    private final PlaceRepository placeRepo;
    private final ItineraryRepository itineraryRepo;

    public PackageService(PackageRepository packageRepo, PlaceRepository placeRepo, ItineraryRepository itineraryRepo) {
        this.packageRepo = packageRepo;
        this.placeRepo = placeRepo;
        this.itineraryRepo = itineraryRepo;
    }

    public void addPackage(int placeId, TourPackage tourPackage) {
        Place place = placeRepo.findById(placeId).orElseThrow(() -> new RuntimeException("Place not found"));
        tourPackage.setPlace(place);
        packageRepo.save(tourPackage);
    }

    @Transactional
    public void updatePackage(int packageId, TourPackage updatedPackage) {
        TourPackage existingPackage = packageRepo.findById(packageId)
                .orElseThrow(() -> new RuntimeException("Package not found"));

        existingPackage.setTitle(updatedPackage.getTitle());
        existingPackage.setDuration(updatedPackage.getDuration());
        existingPackage.setPrice(updatedPackage.getPrice());
        existingPackage.setTour_highlight(updatedPackage.getTour_highlight());
        existingPackage.setLocations(updatedPackage.getLocations());
        existingPackage.setItinerary_heading(updatedPackage.getItinerary_heading());

        List<Itinerary> updatedItinerary = updatedPackage.getItinerary();
        List<Itinerary> existingItinerary = existingPackage.getItinerary();

        List<Itinerary> toRemove = existingItinerary.stream()
                .filter(existingItem -> updatedItinerary.stream()
                        .noneMatch(updateItem -> updateItem.getId() != null && updateItem.getId().equals(existingItem.getId())))
                .toList();

        existingItinerary.removeAll(toRemove);
        itineraryRepo.deleteAll(toRemove);

        updatedItinerary.forEach(updatedItem -> {
            if (updatedItem.getId() != null) {
                Itinerary existingItem = existingItinerary.stream()
                        .filter(item -> item.getId().equals(updatedItem.getId()))
                        .findFirst()
                        .orElse(null);

                if (existingItem != null) {
                    existingItem.setDay(updatedItem.getDay());
                    existingItem.setHeading(updatedItem.getHeading());
                    existingItem.setDescription(updatedItem.getDescription());
                }
            } else {
                updatedItem.setTourPackage(existingPackage);
                existingItinerary.add(updatedItem);
            }
        });

        packageRepo.save(existingPackage);
    }

    public void removePackage(int packageId){
        if(!packageRepo.existsById(packageId)) {
            throw new RuntimeException("Package not found");
        }

        packageRepo.deleteById(packageId);
    }

    public List<TourPackage> getAllPackages(){
        return packageRepo.findAll();
    }

    public TourPackage getPackageById(int id) {
        return packageRepo.findById(id).orElseThrow(() -> new RuntimeException("Package not found"));
    }

    public List<TourPackage> filterPackageByPlace(int placeId) {
        return packageRepo.findByPlaceId(placeId);
    }

}
