package com.librariansunited.library_borrowing_system.membersmanagement.domainlayer;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailAddress {

    @Column(name = "email", nullable = false, unique = true)
    @Email
    private String value;
}
