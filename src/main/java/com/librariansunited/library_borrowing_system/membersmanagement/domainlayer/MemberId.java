package com.librariansunited.library_borrowing_system.membersmanagement.domainlayer;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
public class MemberId {
    @Column(name = "member_id")
    private UUID libraryId;
    public MemberId(){
        this.libraryId = UUID.randomUUID();
    }
}
