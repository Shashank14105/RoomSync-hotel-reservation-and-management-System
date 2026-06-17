package com.roomsync.controller;

import com.roomsync.model.Customer;
import com.roomsync.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(
            CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Customer addCustomer(
            @RequestBody Customer customer){

        return customerService.addCustomer(customer);
    }

    @GetMapping
    public List<Customer> getCustomers(){

        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomer(
            @PathVariable String id){

        return customerService.getCustomerById(id);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(
            @PathVariable String id,
            @RequestBody Customer customer){

        return customerService.updateCustomer(
                id,
                customer);
    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(
            @PathVariable String id){

        customerService.deleteCustomer(id);

        return "Customer deleted successfully";
    }
}