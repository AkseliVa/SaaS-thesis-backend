package com.thesis.saas.customer;

import com.thesis.saas.employee.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

    public CustomerController(CustomerRepository customerRepository, EmployeeRepository employeeRepository) {this.customerRepository = customerRepository; this.employeeRepository = employeeRepository;}

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

    @PostMapping("/api/customers")
    public Customer newCustomer(@RequestBody Customer customer) { return customerRepository.save(customer);}

    @PutMapping("/api/customers/{id}")
    public Customer updateCustomer(@PathVariable long id, @RequestBody Customer updatedCustomer) {
        return customerRepository.findById(id)
                .map(existingCustomer -> {
                    existingCustomer.setName(updatedCustomer.getName());
                    existingCustomer.setContactPerson(updatedCustomer.getContactPerson());
                    existingCustomer.setContactEmail(updatedCustomer.getContactEmail());
                    existingCustomer.setContactPhone(updatedCustomer.getContactPhone());

                    if (updatedCustomer.getCustomerManager() != null) {
                        var manager = employeeRepository.findById(updatedCustomer.getCustomerManager().getEmployee_id())
                                .orElse(null);
                        existingCustomer.setCustomerManager(manager);
                    }

                    return customerRepository.save(existingCustomer);
                })
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @DeleteMapping("/api/customers/{id}")
    public void deleteCustomer(@PathVariable long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customerRepository.delete(customer);
    }
}
