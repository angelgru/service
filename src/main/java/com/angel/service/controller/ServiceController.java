package com.angel.service.controller;

import com.angel.service.domain.Invoice;
import com.angel.service.domain.Service;
import com.angel.service.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/service")
public class ServiceController {

    private final ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<Service> findOne(@PathVariable("serviceId") Long serviceId) {
        Service service = serviceService.findService(serviceId);
        if(service != null)
            return new ResponseEntity<>(service, HttpStatus.FOUND);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{serviceId}/invoice")
    public ResponseEntity<Service> addInvoice(@PathVariable("serviceId") Long serviceId,
                                              @RequestBody Invoice invoice) {
        Service service = serviceService.addInvoice(serviceId, invoice);
        if (service != null)
            return new ResponseEntity<>(service, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
