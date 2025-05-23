package org.example.planner.service;

import org.example.planner.dto.request.ScheduleRequestDto;
import org.example.planner.dto.response.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto);
    List<ScheduleResponseDto> findSchedules(String updatedAt, String author);


}
