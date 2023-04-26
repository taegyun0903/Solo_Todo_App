package toDoApp;

import org.mapstruct.Mapper;
import toDoApp.Dto.ToDoPatchDto;
import toDoApp.Dto.ToDoPostDto;
import toDoApp.Dto.ToDoResponseDto;

//Dto -> Entity, Entity -> Dto
@Mapper(componentModel = "spring")
public interface ToDoMapper {
    ToDo ToDoPostDtoToToDo(ToDoPostDto toDoPostDto);

    ToDo ToDoPatchDtoToToDo(ToDoPatchDto toDoPatchDto);

    //ToDo엔티티객체 -> 리스폰스DTo
    ToDoResponseDto ToDoToToDoResponseDto(ToDo todo);
}