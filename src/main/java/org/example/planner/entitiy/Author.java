package org.example.planner.entitiy;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Author {
    private Long authorId;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Author(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
