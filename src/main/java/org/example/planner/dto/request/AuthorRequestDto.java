package org.example.planner.dto.request;

import jakarta.validation.constraints.Email;

import lombok.Getter;

import java.time.LocalDate;


@Getter
public class AuthorRequestDto {
    private String name;
    @Email(message = "email 형식이 아닙니다")
    private String email;

}
