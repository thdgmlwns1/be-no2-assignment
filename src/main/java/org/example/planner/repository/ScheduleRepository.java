package org.example.planner.repository;


import org.example.planner.dto.request.ScheduleRequestDto;
import org.example.planner.entitiy.Schedule;

public interface ScheduleRepository {
    Schedule saveSchedule(Schedule schedule);
}
