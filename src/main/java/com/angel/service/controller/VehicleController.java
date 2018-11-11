package com.angel.service.controller;

import com.angel.service.domain.Service;
import com.angel.service.domain.Vehicle;
import com.angel.service.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;


    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<Vehicle> findOne(@PathVariable("vehicleId") Long vehicleId) {
        Vehicle vehicle = vehicleService.findOne(vehicleId);
        if(vehicle != null)
            return new ResponseEntity<>(vehicle, HttpStatus.FOUND);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{vehicleId}/service")
    public ResponseEntity<Vehicle> addService(@PathVariable("vehicleId") Long vehicleId,
                                              @RequestBody Service service) {
        Vehicle vehicle = vehicleService.addService(vehicleId, service);
        if(vehicle != null)
            return new ResponseEntity<>(vehicle, HttpStatus.FOUND);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
