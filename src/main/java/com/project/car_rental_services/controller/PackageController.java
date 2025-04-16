package com.project.car_rental_services.controller;

import com.project.car_rental_services.modal.TourPackage;
import com.project.car_rental_services.service.PackageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/place/package")
public class PackageController {

    private final PackageService packageService;

    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @PostMapping("/admin/add-package")
    public ResponseEntity<?> addPackage(@RequestParam int placeId, @RequestBody TourPackage tourPackage) {
        System.out.println("Received tour package : " + tourPackage);
        packageService.addPackage(placeId, tourPackage);
        for(var p: tourPackage.getItinerary()) {
            System.out.println(p.getDay() + " " + p.getDescription() + " " + p.getHeading());
        }
        return ResponseEntity.ok("Package added successfully");
    }

    @PutMapping("/admin/update-package")
    public ResponseEntity<?> updatePackage(@RequestParam int packageId, @RequestBody TourPackage updatedPackage) {
        packageService.updatePackage(packageId, updatedPackage);
        return ResponseEntity.ok("Package updated successfully");
    }

    @DeleteMapping("/admin/remove-package")
    public ResponseEntity<?> removePackage(@RequestParam int packageId) {
        packageService.removePackage(packageId);
        return ResponseEntity.ok("Package removed successfully");
    }

    @GetMapping("/all-packages")
    public ResponseEntity<?> getAllPackages() {
        List<TourPackage> tourPackage = packageService.getAllPackages();
        for(var p: tourPackage) {
            System.out.println(p.getTitle());
            for(var i: p.getItinerary()) {
                System.out.println(i.getDay() + " " + i.getDescription() + " " + i.getHeading());
            }
        }
        return ResponseEntity.ok(tourPackage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPackageById(@PathVariable int id) {
        return ResponseEntity.ok(packageService.getPackageById(id));
    }

    @GetMapping("/filter-by-place/{placeId}")
    public ResponseEntity<List<TourPackage>> filterPackageByPlace(@PathVariable int placeId) {
        List<TourPackage> tourPackages = packageService.filterPackageByPlace(placeId);
        return ResponseEntity.ok(tourPackages);
    }

}