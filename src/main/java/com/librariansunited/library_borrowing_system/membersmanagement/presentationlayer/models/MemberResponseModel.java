package com.librariansunited.library_borrowing_system.membersmanagement.presentationlayer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseModel {

    private String memberId;
    private String firstName;
    private String lastName;
    private String email;
    private String membershipStatus;
    private String membershipTier;
    private String registrationDate;
    private int activeLoanCount;

}
