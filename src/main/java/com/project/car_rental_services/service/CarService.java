package com.project.car_rental_services.service;

import com.project.car_rental_services.modal.Car;
import com.project.car_rental_services.repository.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepo;

    public CarService(CarRepository carRepo) {
        this.carRepo = carRepo;
    }

    public void addCar(String name, MultipartFile image, String type, Integer seats) throws IOException {
        byte[] imageBytes = image.getBytes();
        Car car = new Car();
        car.setName(name);
        car.setImage(imageBytes);
        car.setType(type);
        car.setSeats(seats);
        carRepo.save(car);
    }

    public Optional<Car> getCarById(Integer id) {
        return carRepo.findById(id);
    }

    public void deleteCarById(Integer id) {
        carRepo.deleteById(id);
    }

    public List<Car> getAllCars() {
        return carRepo.findAll();
    }

    public List<Car> getCarByType(String type) {
        return carRepo.findByType(type);
    }

    public Car updateCar(Integer id, Car updatedCar) {
        Optional<Car> existingCarOptional = carRepo.findById(id);

        if (existingCarOptional.isPresent()) {
            Car existingCar = existingCarOptional.get();
            existingCar.setName(updatedCar.getName());
            existingCar.setImage(updatedCar.getImage());
            existingCar.setType(updatedCar.getType());
            existingCar.setSeats(updatedCar.getSeats());
            return carRepo.save(existingCar);
        } else {
            throw new RuntimeException("Car with Id " + id + " not found");
        }
    }
}