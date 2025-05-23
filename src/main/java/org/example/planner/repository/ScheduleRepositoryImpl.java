package org.example.planner.repository;

import lombok.RequiredArgsConstructor;
import org.example.planner.dto.request.ScheduleRequestDto;
import org.example.planner.entitiy.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        parmeters.put("password",schedule.getPassword());
        parmeters.put("task", schedule.getTask());
        parmeters.put("createdAt", localDateTime);
        parmeters.put("updatedAt", localDateTime);

        Number id = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parmeters));

        return new Schedule(id.longValue(),schedule.getAuthor(),schedule.getPassword(),schedule.getTask(),localDateTime,localDateTime);
    }

    @Override
    public List<Schedule> findSchedules(String updatedAt, String author) {
        StringBuilder sql = new StringBuilder("SELECT * FROM schedule WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (updatedAt != null && !updatedAt.isEmpty()) {
            sql.append(" AND DATE(updated_at) = ?");
            params.add(LocalDate.parse(updatedAt));
        }

        if (author != null && !author.isEmpty()) {
            sql.append(" AND author = ?");
            params.add(author);
        }

        sql.append(" ORDER BY updated_at DESC");

        return jdbcTemplate.query(sql.toString(), params.toArray(), (rs, rowNum) ->
                new Schedule(
                        rs.getLong("id"),
                        rs.getString("author"),
                        rs.getString("password"),
                        rs.getString("task"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                )
        );
    }

    @Override
    public Schedule findScheduleById(Long id) {
        List<Schedule> result= jdbcTemplate.query("SELECT * FROM schedule WHERE id=?", scheduleRowMapper(),id);
        return result.stream().findAny().orElse(null);
    }

    @Override
    public int updateSchedule(Long id, String author, String task, LocalDateTime updatedAt) {
        return jdbcTemplate.update("UPDATE schedule SET author = ?, task = ?, updated_at = ? WHERE id = ?",
                author, task, updatedAt, id);
    }

    @Override
    public int deleteSchedule(Long id) {
        return jdbcTemplate.update("DELETE FROM schedule WHERE id = ?", id);
    }


    private RowMapper<Schedule> scheduleRowMapper() {
        return (rs, rowNum) -> new Schedule(
                rs.getLong("id"),
                rs.getString("author"),
                rs.getString("password"),
                rs.getString("task"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("updated_at").toLocalDateTime()
        );
    }



}
