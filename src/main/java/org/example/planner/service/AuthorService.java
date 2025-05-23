package org.example.planner.service;

import lombok.RequiredArgsConstructor;
import org.example.planner.dto.request.AuthorRequestDto;
import org.example.planner.dto.response.AuthorResponseDto;
import org.example.planner.dto.response.ScheduleResponseDto;
import org.example.planner.entitiy.Author;
import org.example.planner.entitiy.Schedule;
import org.example.planner.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorResponseDto saveAuthor(AuthorRequestDto dto) {

        Author author = new Author( dto.getName(), dto.getEmail());
        Author savedAuthor = authorRepository.saveAuthor(author);
        return new AuthorResponseDto(savedAuthor);


    }
}
