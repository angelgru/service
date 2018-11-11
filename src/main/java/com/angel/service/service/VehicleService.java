package com.angel.service.service;


import com.angel.service.domain.Service;
import com.angel.service.domain.Vehicle;
import com.angel.service.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Optional;

@Component
public class VehicleService{

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle findOne(Long vehicleId) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicleId);
        return vehicleOptional.orElse(null);
    }


    public Vehicle addService(Long vehicleId, Service service) {
        Vehicle vehicle = findOne(vehicleId);
        if(vehicle != null) {
            vehicle.addService(service);
            vehicleRepository.save(vehicle);
            return vehicle;
        } else
            return null;
    }
}
