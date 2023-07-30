package com.codestates.coffee.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/v1/coffees")
public class CoffeeController {

    private final Map<Long, Map<String, Object>> coffees = new HashMap<>();

    @PostConstruct
    public void init() {
        Map<String, Object> coffee1 = new HashMap<>();
        long coffeeId = 1L;
        coffee1.put("coffeeId", coffeeId);
        coffee1.put("korName", "바닐라 라떼");
        coffee1.put("engName", "Vanilla Latte");
        coffee1.put("price", 4500);

        coffees.put(coffeeId, coffee1);
    }

    // Coffee information registration
    @PostMapping
    public ResponseEntity<?> postCoffee(@RequestHeader("user-agent") String userAgent,
                                        @RequestParam("engName") String engName,
                                        @RequestParam("korName") String korName,
                                        @RequestParam("price") int price) {

        System.out.println("user-agent: " + userAgent);

        Map<String, Object> map = new HashMap<>();
        map.put("engName", engName);
        map.put("korName", korName);
        map.put("price", price);

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    // Edit Coffee information
    @PatchMapping("/{coffee_id}")
    public ResponseEntity<?> patchCoffee(@PathVariable("coffee_id") Long coffeeId,
                                         @RequestParam("korName") String korName,
                                         @RequestParam("price") int price) {
        if (!coffees.containsKey(coffeeId)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Map<String, Object> modifiedCoffee = coffees.get(coffeeId);
        modifiedCoffee.put("korName", korName);
        modifiedCoffee.put("price", price);

        coffees.put(coffeeId, modifiedCoffee);

        return new ResponseEntity<>(coffees, HttpStatus.OK);
    }

    // Search coffee info by coffeeId
    @GetMapping("/{coffee-id}")
    public ResponseEntity<?> getCoffee(@PathVariable("coffee-id") long coffeeId) {
        System.out.println("# coffeeId: " + coffeeId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Search Coffees info
    @GetMapping
    public ResponseEntity<?> getCoffees(HttpEntity httpEntity) {
        for(Map.Entry<String, List<String>> entry : httpEntity.getHeaders().entrySet()){
            System.out.println("key: " + entry.getKey()
                    + ", " + "value: " + entry.getValue());
        }

        System.out.println("host: " + httpEntity.getHeaders().getHost());
        System.out.println("# get Coffees");

        return new ResponseEntity<>(HttpStatus.OK);
    }


    // Delete coffee information
    @DeleteMapping("/{coffee-id}")
    public ResponseEntity<?> deleteCoffee(@PathVariable("coffee-id") Long coffeeId) {
        if (!coffees.containsKey(coffeeId)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        coffees.remove(coffeeId);

        return new ResponseEntity<>(coffees, HttpStatus.NO_CONTENT);
    }
}
