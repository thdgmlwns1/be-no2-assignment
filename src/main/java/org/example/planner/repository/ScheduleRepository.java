package org.example.planner.repository;


import org.example.planner.dto.request.ScheduleRequestDto;
import org.example.planner.entitiy.Schedule;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository {
    Schedule saveSchedule(Schedule schedule);
    List<Schedule> findSchedules(String updatedAt, String author);
    Schedule findScheduleById(Long id);
    int updateSchedule(Long id, String author, String task, LocalDateTime updatedAt);

}
