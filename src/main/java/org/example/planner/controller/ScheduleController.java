package org.example.planner.controller;

import org.example.planner.dto.request.ScheduleRequestDto;
import org.example.planner.dto.response.ScheduleResponseDto;
import org.example.planner.service.ScheduleService;
import org.example.planner.service.ScheduleServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }


    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createMemo(@RequestBody ScheduleRequestDto requestDto) {

        return new ResponseEntity<>(scheduleService.saveSchedule(requestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getSchedules(
            @RequestParam(required = false) String updatedAt,
            @RequestParam(required = false) String author) {

        List<ScheduleResponseDto> schedules = scheduleService.findSchedules(updatedAt, author);
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable long id) {
        ScheduleResponseDto schedule = scheduleService.findScheduleById(id);
        return ResponseEntity.ok(schedule);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable long id, @RequestBody ScheduleRequestDto requestDto) {
        ScheduleResponseDto schedule = scheduleService.updateSchedule(id,requestDto);

        return ResponseEntity.ok(schedule);
    }

}
