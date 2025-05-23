package org.example.planner.entitiy;

import com.sun.jdi.LongType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class Schedule2 {
    private Long id;
    private Long authorId;
    private String password;
    private String task;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
