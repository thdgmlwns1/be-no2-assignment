package org.example.planner.service;

import lombok.RequiredArgsConstructor;
import org.example.planner.dto.request.ScheduleRequestDto;
import org.example.planner.dto.response.ScheduleResponseDto;
import org.example.planner.entitiy.Schedule;
import org.example.planner.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = new Schedule(scheduleRequestDto.getAuthor(),scheduleRequestDto.getPassword(),scheduleRequestDto.getTask());
        Schedule savedSchedule = scheduleRepository.saveSchedule(schedule);
        return new ScheduleResponseDto(savedSchedule);


    }

    @Override
    public List<ScheduleResponseDto> findSchedules(String updatedAt, String author) {
        List<Schedule> scheduleList = scheduleRepository.findSchedules(updatedAt, author);
        return scheduleList.stream().map(ScheduleResponseDto::new).toList();
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findScheduleById(id);
        return new ScheduleResponseDto(schedule);
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = scheduleRepository.findScheduleById(id);


        if (!schedule.getPassword().equals(scheduleRequestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        LocalDateTime now = LocalDateTime.now();
        int updatedRows = scheduleRepository.updateSchedule(id, scheduleRequestDto.getAuthor(),scheduleRequestDto.getTask(), now);

        if (updatedRows == 0) {
            throw new IllegalStateException("일정 수정에 실패했습니다.");
        }

        Schedule updatedschedule = scheduleRepository.findScheduleById(id);
        return new ScheduleResponseDto(updatedschedule);



    }


    @Override
    public void deleteSchedule(Long id, String password) {
        Schedule schedule = scheduleRepository.findScheduleById(id);

        if (!schedule.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        int deleted = scheduleRepository.deleteSchedule(id);
        if (deleted == 0) {
            throw new IllegalStateException("삭제 실패: 이미 삭제되었거나 존재하지 않습니다.");
        }
    }


}
