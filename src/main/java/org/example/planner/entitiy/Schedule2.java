package org.example.planner.entitiy;

import com.sun.jdi.LongType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.logging.log4j.message.LoggerNameAwareMessage;

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


    public Schedule2(Long authorId, String password, String task) {
        this.authorId = authorId;
        this.password = password;
        this.task = task;
    }
}
