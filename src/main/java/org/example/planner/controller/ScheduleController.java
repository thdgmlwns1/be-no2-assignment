package org.example.planner.controller;

import org.example.planner.dto.request.Schedule2RequestDto;
import org.example.planner.dto.request.ScheduleRequestDto;
import org.example.planner.dto.response.Schedule2ResponseDto;
import org.example.planner.dto.response.ScheduleResponseDto;
import org.example.planner.entitiy.Schedule2;
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
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto) {

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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto requestDto) {

        scheduleService.deleteSchedule(id, requestDto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /// ////////////////Lv3//////////////////////////////////

    @PostMapping("/lv3")
    public ResponseEntity<Schedule2ResponseDto> createSchedule2(@RequestBody Schedule2RequestDto requestDto) {

        return new ResponseEntity<>(scheduleService.saveSchedule2(requestDto), HttpStatus.CREATED);
    }

    @GetMapping("/lv3/{authorId}")
    public ResponseEntity<List<Schedule2>> getSchedulesByAuthor(@PathVariable Long authorId) {
        return ResponseEntity.ok(scheduleService.getSchedulesByAuthorId(authorId));
    }


}
