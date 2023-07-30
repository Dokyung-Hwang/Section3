package com.codestates.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    @PostMapping
    public ResponseEntity<?> postOrder(HttpServletRequest httpServletRequest,
                                       @RequestParam("memberId") long memberId,
                                       @RequestParam("coffeeId") long coffeeId) {

        System.out.println("user-agent: " + httpServletRequest.getHeader("user-agent"));
        System.out.println("accept: " + httpServletRequest.getHeader("accept"));
        System.out.println("cache-control: " + httpServletRequest.getHeader("cache-control"));

        Map<String, Long> map = new HashMap<>();
        map.put("memberId", memberId);
        map.put("coffeeId", coffeeId);

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<?> getOrder(@PathVariable("order-id") long orderId) {
        System.out.println("# orderId: " + orderId);


        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getOrders() {
        System.out.println("# get Orders");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
