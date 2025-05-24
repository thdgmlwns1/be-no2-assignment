package org.example.planner.dto.response;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import org.example.planner.entitiy.Author;

@Getter
public class AuthorResponseDto {
    private Long authorId;
    private String name;
    private String email;

    public AuthorResponseDto(Author author) {
        this.authorId = author.getAuthorId();
        this.name = author.getName();
        this.email = author.getEmail();
    }
}
