package com.codestates.coffee;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/v1/coffees")
public class CoffeeController {

    @PostMapping
    public ResponseEntity<?> postCoffee(@RequestParam("engName") String engName,
                             @RequestParam("korName") String korName,
                             @RequestParam("price") int price) {

        Map<String, Object> map = new HashMap<>();
        map.put("engName", engName);
        map.put("korName", korName);
        map.put("price", price);


        long savedCoffeeId = 1L;

//        return new ResponseEntity<>(map, HttpStatus.CREATED);
        return ResponseEntity.created(URI.create("/members/" + savedCoffeeId)).body(map);
    }

    @GetMapping("/{coffee-id}")
    public ResponseEntity<?> getCoffee(@PathVariable("coffee-id") long coffeeId) {
        System.out.println("# coffeeId: " + coffeeId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getCoffees() {
        System.out.println("# get Coffees");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
