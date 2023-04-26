package toDoApp.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import toDoApp.CompletedStatus;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    public class ToDoPatchDto {
        private long toDoId;

        private String title;

        private int order;

        private CompletedStatus completedStatus;

        //public void setToDo(long todoId){this.todoId = todoId}
    }


