package com.librariansunited.library_borrowing_system.membersmanagement.businesslayer;


import com.librariansunited.library_borrowing_system.EntityNotFoundException;
import com.librariansunited.library_borrowing_system.membersmanagement.domainlayer.Member;
import com.librariansunited.library_borrowing_system.membersmanagement.domainlayer.MemberStatus;
import com.librariansunited.library_borrowing_system.membersmanagement.infrastructurelayer.MemberRepository;

import java.util.List;

public interface MemberService {
    List getAllMembers();
    Member getMemberById(Integer id) throws EntityNotFoundException;
    Member addMember(Member member);
    Member updateMember(Integer id, Member existing) throws EntityNotFoundException;
    void deleteById(Integer id) throws EntityNotFoundException;
    boolean existsById(Integer id);
    List getMembersByMemberStatus(MemberStatus memberStatus);
}

