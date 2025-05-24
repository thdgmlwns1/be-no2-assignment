package org.example.planner.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private String author;
    @NotBlank(message = "비밀번호는 필수 입니다")
    private String password;
    @NotBlank(message = "할일은 필수 입니다")
    @Size(max = 200,message = "200자 이하 제한 입니다")
    private String task;


}
