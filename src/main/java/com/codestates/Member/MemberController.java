package com.codestates.Member;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/v1/members")
public class MemberController {

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

}
