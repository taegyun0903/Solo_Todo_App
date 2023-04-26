package toDoApp;

import lombok.Getter;

public enum CompletedStatus {
    TODO_FALSE("시작 전"),
    TODO_ONGOING("진행중"),
    TODO_DONE("완료");

    @Getter
    private String status;

    CompletedStatus(String status) {
        this.status = status;
    }
}