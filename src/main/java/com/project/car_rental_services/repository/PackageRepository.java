package com.project.car_rental_services.repository;

import com.project.car_rental_services.modal.TourPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageRepository extends JpaRepository<TourPackage, Integer> {
    List<TourPackage> findByPlaceId(int placeId);
}