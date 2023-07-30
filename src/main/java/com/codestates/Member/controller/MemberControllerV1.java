package com.codestates.Member.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/v1/members")
public class MemberControllerV1 {

    private final Map<Long, Map<String, Object>> members = new HashMap<>();

    @PostConstruct
    public void init() {
        Map<String, Object> member1 = new HashMap<>();
        long memberId = 1L;
        member1.put("memberId", memberId);
        member1.put("email", "hgd@gmail.com");
        member1.put("name", "홍길동");
        member1.put("phone", "010-1234-5678");

        members.put(memberId, member1);
    }


    @PostMapping
    public ResponseEntity<?> postMember(@RequestHeader("user-agent") Map<String, String> headers,
                                        @RequestParam("email") String email,
                                        @RequestParam("name") String name,
                                        @RequestParam("phone") String phone) {

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue());
        }

        HttpHeaders headers1 = new HttpHeaders();
        headers1.set("Client-Geo-Location", "Korea,Seoul");

        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("name", name);
        map.put("phone", phone);

        return new ResponseEntity<>(map, headers1, HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity<?> patchMember(@PathVariable("member-id") Long memberId,
                                         @RequestParam("phone") String phone) {
        if (!members.containsKey(memberId)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Map<String, Object> modifiedMember = members.get(memberId);
        modifiedMember.put("phone", phone);

        members.put(memberId, modifiedMember);

        return new ResponseEntity<>(members, HttpStatus.OK);
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
        if (!members.containsKey(memberId)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        members.remove(memberId);

        return new ResponseEntity<>(members, HttpStatus.NO_CONTENT);
    }
}
