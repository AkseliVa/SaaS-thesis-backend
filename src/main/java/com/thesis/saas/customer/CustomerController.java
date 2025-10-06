package com.thesis.saas.customer;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {this.customerRepository = customerRepository;}

    @GetMapping("/api/customers")
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(CustomerDTO::fromEntity)
                .toList();
    }

    @GetMapping("/api/customers/{id}")
    public CustomerDTO getCustomerById(@PathVariable long id) {
        return customerRepository.findById(id)
                .map(CustomerDTO::fromEntity)
                .orElse(null);
    }

    @PostMapping("api/customers")
    public Customer newCustomer(@RequestBody Customer customer) { return customerRepository.save(customer);}

    @DeleteMapping("/api/customers/{id}")
    public void deleteCustomer(@PathVariable long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customerRepository.delete(customer);
    }
}
