package org.example.planner.repository;

import lombok.RequiredArgsConstructor;
import org.example.planner.entitiy.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class AuthorRepository {

    private final JdbcTemplate jdbcTemplate;


    public Author saveAuthor(Author author) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("author")
                .usingGeneratedKeyColumns("id");

        LocalDateTime localDateTime = LocalDateTime.now();

        Map<String, Object> params = new HashMap<>();

        params.put("name", author.getName());
        params.put("email", author.getEmail());
        params.put("createdAt", localDateTime);
        params.put("updatedAt", localDateTime);

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(params));
        return new Author(
                key.longValue(),
                author.getName(),
                author.getEmail(),
                localDateTime,
                localDateTime);

    }
}
