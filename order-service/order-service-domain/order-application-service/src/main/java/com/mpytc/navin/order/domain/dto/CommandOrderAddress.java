package com.mpytc.navin.order.domain.dto;


public record CommandOrderAddress (

        String street,
        String postalCode,
        String city
) {
}
