package com.codestates.coffee.controller;

import com.codestates.coffee.dto.CoffeePatchDto;
import com.codestates.coffee.dto.CoffeePostDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/v2/coffees")
public class CoffeeControllerV2 {

    // Coffee information registration
    @PostMapping
    public ResponseEntity<?> postCoffee(@Valid @RequestBody CoffeePostDto coffeePostDto) {

        return new ResponseEntity<>(coffeePostDto, HttpStatus.CREATED);
    }

    // Edit Coffee information
    @PatchMapping("/{coffee_id}")
    public ResponseEntity<?> patchCoffee(@PathVariable("coffee_id") @Positive Long coffeeId,
                                         @Valid @RequestBody CoffeePatchDto coffeePatchDto) {

        coffeePatchDto.setCoffeeId(coffeeId);

        return new ResponseEntity<>(coffeePatchDto, HttpStatus.OK);
    }

    // Search coffee info by coffeeId
    @GetMapping("/{coffee-id}")
    public ResponseEntity<?> getCoffee(@PathVariable("coffee-id") long coffeeId) {
        System.out.println("# coffeeId: " + coffeeId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Search Coffees info
    @GetMapping
    public ResponseEntity<?> getCoffees() {
        System.out.println("# get Coffees");

        // Not Impl
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // Delete coffee information
    @DeleteMapping("/{coffee-id}")
    public ResponseEntity<?> deleteCoffee(@PathVariable("coffee-id") Long coffeeId) {

        // No need business logic
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
