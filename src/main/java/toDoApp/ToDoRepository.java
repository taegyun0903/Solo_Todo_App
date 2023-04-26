package toDoApp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ToDoRepository extends JpaRepository<ToDo,Long> {
    @Override
    Optional<ToDo> findById(Long toDoId);
}