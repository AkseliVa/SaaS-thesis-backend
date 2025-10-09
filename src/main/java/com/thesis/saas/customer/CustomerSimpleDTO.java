package com.thesis.saas.customer;

public record CustomerSimpleDTO(
        long customer_id,
        String name
) {
    public static CustomerSimpleDTO fromEntity(Customer customer) {
        return new CustomerSimpleDTO(
                customer.getCustomer_id(),
                customer.getName()
        );
    }
}
