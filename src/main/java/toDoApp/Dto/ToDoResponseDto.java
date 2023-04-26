package toDoApp.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import toDoApp.CompletedStatus;

@Builder
@Getter
@Setter
public class ToDoResponseDto {

    private long toDoId;
    private String title;
    private int order;
    private CompletedStatus completedStatus;

}