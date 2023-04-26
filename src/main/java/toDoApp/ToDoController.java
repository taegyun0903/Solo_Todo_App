package toDoApp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import toDoApp.Dto.ToDoPatchDto;
import toDoApp.Dto.ToDoPostDto;
import toDoApp.Dto.ToDoResponseDto;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin //Cors설정
@RestController
@RequestMapping(value="/todo")
@Validated
@Slf4j
public class ToDoController {
    private final ToDoService toDoService;
    private final ToDoMapper mapper;

    public ToDoController(ToDoService toDoService, ToDoMapper mapper) {
        this.toDoService = toDoService;
        this.mapper = mapper;
    }

    //할일 등록 메서드
    @PostMapping
    public ResponseEntity postToDo(@Valid @RequestBody ToDoPostDto toDoPostDto) {
        ToDo toDo = toDoService.createToDo(mapper.ToDoPostDtoToToDo(toDoPostDto));

        return new ResponseEntity<>(mapper.ToDoToToDoResponseDto(toDo), HttpStatus.CREATED);
    }

    //할일 수정 메서드
    @PatchMapping("/{toDo-id}")
    public ResponseEntity patchToDo(@PathVariable("toDo-id") @Positive long toDoId,
                                    @Valid @RequestBody ToDoPatchDto toDoPatchDto) {
        ToDo toDo = toDoService.updateToDo(toDoId,mapper.ToDoPatchDtoToToDo(toDoPatchDto));

        return new ResponseEntity<>(mapper.ToDoToToDoResponseDto(toDo), HttpStatus.OK);

    }

    //할일 1개 가져오는 메서드
    @GetMapping("/todo/todo")
    public ResponseEntity getToDo(
            @PathVariable("toDo-id") @Positive long toDoId) {
        ToDo toDo = toDoService.findToDo(toDoId);
        return new ResponseEntity<>(mapper.ToDoToToDoResponseDto(toDo), HttpStatus.OK);
    }

    //할일 리스트 가져오는 메서드
    @GetMapping
    public ResponseEntity getToDoList() {
        List<ToDo> toDoList = toDoService.findToDoList();
        List<ToDoResponseDto> responseToDoList = toDoList.stream().map(toDo -> mapper.ToDoToToDoResponseDto(toDo)).collect(Collectors.toList());
        return new ResponseEntity<>(responseToDoList, HttpStatus.OK);
    }

    //할일 1개 삭제 메서드
    @DeleteMapping("/{toDo-id}")
    public ResponseEntity deleteToDo(@PathVariable("toDo-id") @Positive long toDoId) {

        int order = toDoService.findToDo(toDoId).getOrder();
        toDoService.deleteToDo(toDoId);
        System.out.println(order + "번째 할 일이 삭제되었습니다.");
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    //할일 전부 삭제 메서드
    @DeleteMapping
    public ResponseEntity deleteAllToDo(){
        toDoService.deleteAllToDo();
        System.out.println("할일이 전부 삭제되었습니다.");
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}

