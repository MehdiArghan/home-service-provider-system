package com.example.homeserviceprovidersystem.service.impl;

import com.example.homeserviceprovidersystem.customeException.CustomBadRequestException;
import com.example.homeserviceprovidersystem.customeException.CustomEntityNotFoundException;
import com.example.homeserviceprovidersystem.entity.Customer;
import com.example.homeserviceprovidersystem.entity.Wallet;
import com.example.homeserviceprovidersystem.repositroy.CustomerRepository;
import com.example.homeserviceprovidersystem.service.CustomerService;
import com.example.homeserviceprovidersystem.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final WalletService walletService;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, WalletService walletService) {
        this.customerRepository = customerRepository;
        this.walletService = walletService;
    }

    @Override
    public Customer save(Customer customer) {
        customerRepository.findByEmail(customer.getEmail()).ifPresent(existingCustomer -> {
            throw new CustomBadRequestException("Email already exists");
        });
        customer.setRegistrationDate(LocalDate.now());
        customer.setRegistrationTime(LocalTime.now());
        customer.setWallet(walletService.save(new Wallet(2000.0)));
        return customerRepository.save(customer);
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomEntityNotFoundException("customer with this id was not found"));
    }
}
