package org.example.planner.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.planner.dto.request.AuthorRequestDto;
import org.example.planner.dto.response.AuthorResponseDto;
import org.example.planner.entitiy.Author;
import org.example.planner.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorResponseDto> saveAuthor(@Valid @RequestBody AuthorRequestDto dto) {
        AuthorResponseDto savedAuthor = authorService.saveAuthor(dto);
        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
    }





}
