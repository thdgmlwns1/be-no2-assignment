package org.example.planner.repository;

import lombok.RequiredArgsConstructor;
import org.example.planner.dto.request.ScheduleRequestDto;
import org.example.planner.entitiy.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;


    @Override
    public Schedule saveSchedule(Schedule schedule) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");
        Map<String, Object> parmeters = new HashMap<>();

        LocalDateTime localDateTime = LocalDateTime.now();
        parmeters.put("author", schedule.getAuthor());
        parmeters.put("task", schedule.getTask());
        parmeters.put("password",schedule.getPassword());
        parmeters.put("createdAt", localDateTime);
        parmeters.put("updatedAt", localDateTime);

        Number id = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parmeters));

        return new Schedule(id.longValue(),schedule.getAuthor(),schedule.getPassword(),schedule.getTask(),localDateTime,localDateTime);
    }
}
