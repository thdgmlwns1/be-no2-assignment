package org.example.planner.service;

import org.example.planner.dto.request.Schedule2RequestDto;
import org.example.planner.dto.request.ScheduleRequestDto;
import org.example.planner.dto.response.Schedule2ResponseDto;
import org.example.planner.dto.response.Schedule2WithAuthorDto;
import org.example.planner.dto.response.ScheduleResponseDto;
import org.example.planner.entitiy.Schedule2;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto);
    List<ScheduleResponseDto> findSchedules(String updatedAt, String author);
    ScheduleResponseDto findScheduleById(Long id);
    ScheduleResponseDto updateSchedule(Long id,ScheduleRequestDto scheduleRequestDto);
    void deleteSchedule(Long id, String password);

    /// /////////Lv3//////////////////////////////
    Schedule2ResponseDto saveSchedule2(Schedule2RequestDto scheduleRequestDto);
    List<Schedule2ResponseDto> getSchedulesByAuthorId(Long authorId);

    /// /////////////////Lv. 4////////////////////////////
    List<Schedule2WithAuthorDto> getPagedSchedulesWithAuthor(int page, int size);

}
