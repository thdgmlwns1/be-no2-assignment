package org.example.planner.entitiy;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {
    private Long id;
    private String author;
    private String password;
    private String task;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Schedule(String author, String password, String task) {
        this.author = author;
        this.password = password;
        this.task = task;
    }

}
