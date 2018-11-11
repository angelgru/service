package com.angel.service.service;

import com.angel.service.domain.Invoice;
import com.angel.service.domain.Service;
import com.angel.service.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ServiceService {

    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public Service findService(Long id) {
        Optional<Service> serviceOptional = serviceRepository.findById(id);
        return serviceOptional.orElse(null);
    }

    public Service addInvoice(Long serviceId, Invoice invoice) {
        Service service = findService(serviceId);
        if(service != null) {
            service.addInvoice(invoice);
            serviceRepository.save(service);
            return service;
        } else
            return null;
    }
}
