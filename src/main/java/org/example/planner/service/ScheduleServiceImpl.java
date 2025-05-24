package org.example.planner.service;

import lombok.RequiredArgsConstructor;
import org.example.planner.dto.request.Schedule2RequestDto;
import org.example.planner.dto.request.ScheduleRequestDto;
import org.example.planner.dto.response.Schedule2ResponseDto;
import org.example.planner.dto.response.Schedule2WithAuthorDto;
import org.example.planner.dto.response.ScheduleResponseDto;
import org.example.planner.entitiy.Schedule;
import org.example.planner.entitiy.Schedule2;
import org.example.planner.exeption.InvalidPasswordException;
import org.example.planner.exeption.ScheduleNotFoundException;
import org.example.planner.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        if (schedule == null) {
            throw new ScheduleNotFoundException("선택한 일정을 찾을 수 없습니다.");
        }
        return new ScheduleResponseDto(schedule);
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = scheduleRepository.findScheduleById(id);


        if (schedule == null) {
            throw new ScheduleNotFoundException("삭제할 일정이 존재하지 않습니다.");
        }


        if (!schedule.getPassword().equals(scheduleRequestDto.getPassword())) {
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
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

        if (schedule == null) {
            throw new ScheduleNotFoundException("삭제할 일정이 존재하지 않습니다.");
        }


        if (!schedule.getPassword().equals(password)) {
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
        }

        int deleted = scheduleRepository.deleteSchedule(id);
        if (deleted == 0) {
            throw new IllegalStateException("삭제 실패: 이미 삭제되었거나 존재하지 않습니다.");
        }
    }



    /// /////////////////////Lv3///////////////////////////
    @Override
    public Schedule2ResponseDto saveSchedule2(Schedule2RequestDto scheduleRequestDto) {
        Schedule2 schedule = new Schedule2(scheduleRequestDto.getAuthorId(),scheduleRequestDto.getPassword(),scheduleRequestDto.getTask());
        Schedule2 savedSchedule = scheduleRepository.saveSchedule2(schedule);
        return new Schedule2ResponseDto(savedSchedule);
    }

    @Override
    public List<Schedule2ResponseDto> getSchedulesByAuthorId(Long authorId) {
        List<Schedule2> schedules = scheduleRepository.findByAuthorId(authorId);

        return schedules.stream()
                .map(schedule -> new Schedule2ResponseDto(schedule))
                .collect(Collectors.toList());
    }

    /// ////////////////////Lv. 4 /////////////////////
    public List<Schedule2WithAuthorDto> getPagedSchedulesWithAuthor(int page, int size) {
        int offset = page * size;
        return scheduleRepository.findPagedSchedule2WithAuthor(offset, size);
    }





}
