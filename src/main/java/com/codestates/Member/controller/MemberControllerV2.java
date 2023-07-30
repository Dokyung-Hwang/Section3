package com.codestates.Member.controller;

import com.codestates.Member.dto.MemberPatchDto;
import com.codestates.Member.dto.MemberPostDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/v2/members")
public class MemberControllerV2 {

    @PostMapping
    public ResponseEntity<?> postMember(@RequestBody MemberPostDto memberPostDto) {

        return new ResponseEntity<>(memberPostDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity<?> patchMember(@PathVariable("member-id") Long memberId,
                                         @RequestBody MemberPatchDto memberPatchDto) {

        memberPatchDto.setMemberId(memberId);
        memberPatchDto.setName("홍길동");

        return new ResponseEntity<>(memberPatchDto, HttpStatus.OK);
    }


    @GetMapping("{member-id}")
    public ResponseEntity<?> getMember(@PathVariable("member-id") long memberId) {
        System.out.println("# memberId: " + memberId);


        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getMembers(HttpServletResponse httpServletResponse) {
        System.out.println("# get Members");
        httpServletResponse.addHeader("Client-Geo-Location", "Korea,Seoul");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity<?> deleteMember(@PathVariable("member-id") Long memberId) {
        // No need business logic

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
