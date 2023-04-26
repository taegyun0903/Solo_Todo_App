package toDoApp.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToDoPostDto {
    @NotBlank(message = "할 일을 입력해야 합니다.")
    private String title;

    @Positive
    private int order;

}