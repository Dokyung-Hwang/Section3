package com.codestates.coffee.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
public class CoffeePostDto {

    @NotBlank
    private String engName;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+(\\s[a-zA-Z]+)*$", message = "영문(대소문자 모두 가능), 한 칸의 공백(스페이스)만 포함될 수 있습니다.")
    private String korName;

    @NotNull
    @Range(min = 100, max = 50000, message = "가격은 100이상, 50000 이하여야 합니다.")
    private int price;

}
