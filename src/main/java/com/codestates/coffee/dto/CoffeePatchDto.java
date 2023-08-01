package com.codestates.coffee.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Range;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Pattern;

@Getter
public class CoffeePatchDto {
    private long coffeeId;

    @Nullable
    private String korName;

    @Nullable
    @Pattern(regexp = "^[a-zA-Z]+(\\s[a-zA-Z]+)*$", message = "영문(대소문자 모두 가능), 한 칸의 공백(스페이스)만 포함될 수 있습니다.")
    private String engName;

    @Nullable
    @Range(min = 100, max = 50000)
    private int price;

    public void setCoffeeId(long coffeeId) {
        this.coffeeId = coffeeId;
    }
}
