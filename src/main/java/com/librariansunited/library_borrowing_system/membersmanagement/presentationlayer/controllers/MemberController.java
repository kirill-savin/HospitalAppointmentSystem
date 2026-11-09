package com.librariansunited.library_borrowing_system.membersmanagement.presentationlayer.controllers;

import com.librariansunited.library_borrowing_system.EntityNotFoundException;
import com.librariansunited.library_borrowing_system.membersmanagement.businesslayer.MemberService;
import com.librariansunited.library_borrowing_system.membersmanagement.datamapping.MemberMapper;
import com.librariansunited.library_borrowing_system.membersmanagement.domainlayer.Member;
import com.librariansunited.library_borrowing_system.membersmanagement.domainlayer.MemberStatus;
import com.librariansunited.library_borrowing_system.membersmanagement.presentationlayer.models.MemberRequestModel;
import com.librariansunited.library_borrowing_system.membersmanagement.presentationlayer.models.MemberResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper memberMapper;

    public MemberController(MemberService memberService, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }


    // ---------------------------------------------------------------- queries
    @GetMapping
    public ResponseEntity getAllMembers() {
        List memberResponseModels =
                this.memberMapper.entityToResponseModelList(memberService.getAllMembers());
        return ResponseEntity.ok(memberResponseModels);
    }

    @GetMapping(params = "memberStatus")
    public ResponseEntity getMembersByMemberStatus(
            @RequestParam MemberStatus memberStatus) {

        List memberResponseModels =
                this.memberMapper.entityToResponseModelList(this.memberService.getMembersByMemberStatus(memberStatus));

        return ResponseEntity.ok(memberResponseModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity getMember(
            @PathVariable Integer id) {
        try {
            MemberResponseModel memberResponseModel =
                    this.memberMapper.entityToResponseModel(memberService.getMemberById(id));

            return ResponseEntity.ok(memberResponseModel);
        }
        catch (EntityNotFoundException e) {

            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity addMember(
            @RequestBody MemberRequestModel memberRequestModel) {

        Member member = this.memberMapper.requestModelToEntity(memberRequestModel);
        Member savedMember = this.memberService.addMember(member);

        MemberResponseModel memberResponseModel =
                this.memberMapper.entityToResponseModel(savedMember);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(memberResponseModel);

    }

    @PutMapping("/{id}")
    public ResponseEntity updateMember(
            @PathVariable Integer id,
            @RequestBody MemberRequestModel memberRequestModel) {
        try {
            Member member = this.memberMapper.requestModelToEntity(memberRequestModel);
            Member updatedMember =
                    memberService.updateMember(id, member);
            MemberResponseModel memberResponseModel =
                    this.memberMapper.entityToResponseModel(updatedMember);
            return ResponseEntity.ok(memberResponseModel);
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMember(
            @PathVariable Integer id) {
        try {
            memberService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}