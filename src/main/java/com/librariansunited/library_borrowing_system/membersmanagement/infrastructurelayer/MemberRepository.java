package com.librariansunited.library_borrowing_system.membersmanagement.infrastructurelayer;

import com.librariansunited.library_borrowing_system.membersmanagement.domainlayer.EmailAddress;
import com.librariansunited.library_borrowing_system.membersmanagement.domainlayer.Member;
import com.librariansunited.library_borrowing_system.membersmanagement.domainlayer.MemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

public interface MemberRepository extends JpaRepository <Member, Integer> {
    List findByMemberStatus(@RequestParam MemberStatus memberStatus);
    boolean existsByEmail(EmailAddress email);
}
