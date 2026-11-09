package com.librariansunited.library_borrowing_system.membersmanagement.datamapping;



import com.librariansunited.library_borrowing_system.membersmanagement.domainlayer.EmailAddress;
import com.librariansunited.library_borrowing_system.membersmanagement.domainlayer.Member;
import com.librariansunited.library_borrowing_system.membersmanagement.domainlayer.MemberName;
import com.librariansunited.library_borrowing_system.membersmanagement.presentationlayer.models.MemberRequestModel;
import com.librariansunited.library_borrowing_system.membersmanagement.presentationlayer.models.MemberResponseModel;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemberMapper {

    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ISO_LOCAL_DATE;

    // ------------------------------------------------- entity -> response

    public MemberResponseModel entityToResponseModel(Member member) {

        if (member == null) {
            return null;
        }

        MemberResponseModel responseModel = new MemberResponseModel();

        responseModel.setMemberId(
                member.getId() == null
                        ? null
                        : String.valueOf(member.getId()));

        if (member.getName() != null) {
            responseModel.setFirstName(member.getName().getFirstName());
            responseModel.setLastName(member.getName().getLastName());
        }

        if (member.getEmail() != null) {
            responseModel.setEmail(member.getEmail().getValue());
        }

        if (member.getMemberStatus() != null) {
            responseModel.setMembershipStatus(
                    member.getMemberStatus().name());
        }

        if (member.getMembershipTier() != null) {
            responseModel.setMembershipTier(
                    member.getMembershipTier().name());
        }

        if (member.getRegistrationDate() != null) {
            responseModel.setRegistrationDate(
                    member.getRegistrationDate().format(DATE_FORMAT));
        }

        responseModel.setActiveLoanCount(member.getActiveLoanCount());

        return responseModel;
    }

    public List<MemberResponseModel> entityToResponseModelList(
            List<Member> members) {

        if (members == null) {
            return List.of();
        }

        return members.stream()
                .map(this::entityToResponseModel)
                .collect(Collectors.toList());
    }

    // ------------------------------------------------- request -> entity

    public Member requestModelToEntity(MemberRequestModel requestModel) {

        if (requestModel == null) {
            return null;
        }

        Member member = new Member();

        member.setName(new MemberName(
                requestModel.getFirstName(),
                requestModel.getLastName()));

        member.setEmail(new EmailAddress(requestModel.getEmail()));

        member.setMembershipTier(requestModel.getTier());

        // id, memberStatus, registrationDate and activeLoanCount are
        // deliberately NOT set here: they are owned by the system.

        return member;
    }

    // --------------------------------------- request -> existing entity

    public void updateEntityFromRequestModel(
            MemberRequestModel requestModel,
            Member existingMember) {

        existingMember.setName(new MemberName(
                requestModel.getFirstName(),
                requestModel.getLastName()));

        existingMember.setEmail(new EmailAddress(requestModel.getEmail()));

        existingMember.setMembershipTier(requestModel.getTier());
    }

}