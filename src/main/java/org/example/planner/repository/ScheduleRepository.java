package org.example.planner.repository;


import org.example.planner.dto.request.ScheduleRequestDto;
import org.example.planner.entitiy.Schedule;
import org.example.planner.entitiy.Schedule2;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository {
    Schedule saveSchedule(Schedule schedule);
    List<Schedule> findSchedules(String updatedAt, String author);
    Schedule findScheduleById(Long id);
    int updateSchedule(Long id, String author, String task, LocalDateTime updatedAt);
    int deleteSchedule(Long id);

    /// //Lv3//////////
    Schedule2 saveSchedule2(Schedule2 schedule);
    List<Schedule2> findByAuthorId(Long authorId);
}
