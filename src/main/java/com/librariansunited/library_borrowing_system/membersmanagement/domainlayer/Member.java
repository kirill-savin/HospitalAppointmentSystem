package com.librariansunited.library_borrowing_system.membersmanagement.domainlayer;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.util.UUID;


@Entity
@Table(name = "members")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Embedded
    private MemberId memberLibraryId;

    @Embedded
    private MemberName name;

    @Valid
    @Embedded
    private EmailAddress email;

    @Min(value = 0, message = "Active loan count cannot be negative")
    private int activeLoanCount;

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;
    @Enumerated(EnumType.STRING)
    private MembershipTier membershipTier;
    private LocalDate registrationDate;

    public Member(MemberId memberLibraryId, MemberName name, EmailAddress email, MembershipTier tier){
        this.memberLibraryId = memberLibraryId;
        this.name = name;
        this.email = email;
        this.membershipTier = tier;
        this.memberStatus = MemberStatus.ACTIVE;
        this.activeLoanCount = 0;
        this.registrationDate = LocalDate.now();
    }




}