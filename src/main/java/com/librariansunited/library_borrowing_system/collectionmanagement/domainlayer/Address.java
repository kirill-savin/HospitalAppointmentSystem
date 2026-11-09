package com.librariansunited.library_borrowing_system.collectionmanagement.domainlayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
// @Setter do not generate setters for Value Objects because they are immutable
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
// NoArgsConstructor is required by JPA, but it is protected so that no one can generate an invalid instance outside the persistence layer.
public class Address {
    private String street;
    @Column(name = "province")
    private String province;
    @Column(name = "city")
    private String city;
    @Column(name = "postal_code")
    private String postalCode;

    public Address(String street, String province,
                   String city, String postalCode) {
        if (street == null || street.isBlank())
            throw new IllegalArgumentException("Street is required.");
        if (province == null || province.isBlank())
            throw new IllegalArgumentException("Province is required.");
        if (city == null || city.isBlank())
            throw new IllegalArgumentException("City is required.");
        if (postalCode == null || postalCode.isBlank())
            throw new IllegalArgumentException("Postal code is required.");

        this.street = street;
        this.province = province;
        this.city = city;
        this.postalCode = postalCode;
    }
}
