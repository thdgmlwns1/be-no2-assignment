package org.example.planner.dto.response;

import lombok.Getter;
import org.example.planner.entitiy.Schedule;
import org.example.planner.entitiy.Schedule2;

import java.time.LocalDateTime;

@Getter
public class Schedule2ResponseDto {

    private Long    id;
    private Long authorId;
    private String password;
    private String task;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Schedule2ResponseDto(Schedule2 schedule) {
        this.id = schedule.getId();
        this.authorId= schedule.getAuthorId();
        this.password = schedule.getPassword();
        this.task = schedule.getTask();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
    }
}
