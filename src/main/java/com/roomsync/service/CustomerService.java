package com.roomsync.service;

import com.roomsync.exception.ResourceNotFoundException;
import com.roomsync.model.Customer;
import com.roomsync.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(
            CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomerById(String id){

        return customerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer not found"));
    }

    public Customer updateCustomer(
            String id,
            Customer updatedCustomer){

        Customer customer = getCustomerById(id);

        customer.setName(updatedCustomer.getName());
        customer.setEmail(updatedCustomer.getEmail());
        customer.setPhone(updatedCustomer.getPhone());

        return customerRepository.save(customer);
    }

    public void deleteCustomer(String id){
        customerRepository.deleteById(id);
    }
}