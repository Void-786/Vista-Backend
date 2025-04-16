package com.project.car_rental_services.repository;

import com.project.car_rental_services.modal.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItineraryRepository extends JpaRepository<Itinerary, Integer> {
}
