package toDoApp;

import org.springframework.stereotype.Service;
import toDoApp.Exception.BusinessLogicException;
import toDoApp.Exception.ExceptionCode;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ToDoService {

    private final ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository){
        this.toDoRepository = toDoRepository;
    }
    //Service 계층 Entity 타입 받아 Entity타입 객체 리턴
    public ToDo createToDo(ToDo toDo){
        //순서 중복되는지 검증하는 로직 추가
        //레포지토리에 저장
        ToDo savedToDo = toDoRepository.save(toDo);

        //엔티티 형태로 리턴
        return savedToDo;
    }

    //할일 수정 로직
    //오류가 나서 para에 toDoId 추가 -> 오류 해결 (원인은 모름..ㅎ)
    public ToDo updateToDo(long toDoId,ToDo toDo) {
        //존재하는 할일 목록인지 확인
        ToDo findToDo = verifyToDo(toDoId);

        //각 변수가 존재한다면(변경 내용이 있다면) 바꿔서 저장해주기
        Optional.ofNullable(toDo.getTitle())
                .ifPresent(title -> findToDo.setTitle(title));

        Optional.ofNullable(toDo.getOrder())
                .ifPresent(order -> findToDo.setOrder(order));

        Optional.ofNullable(toDo.getCompletedStatus())
                .ifPresent(completedStatus -> findToDo.setCompletedStatus(completedStatus));

        return toDoRepository.save(findToDo);
    }

    //할일 1개 가져오기
    public ToDo findToDo(long toDoId){
        return verifyToDo(toDoId);
    }

    //할일 전부(리스트) 가져오기
    public List<ToDo> findToDoList(){
        return toDoRepository.findAll(); //페이지네이션 어려워서 구현모탐...ㅎㅎㅎㅎ
    }


    //할일 1개 삭제
    public void deleteToDo(long toDoId){
        ToDo findToDo = verifyToDo(toDoId);
        toDoRepository.delete(findToDo);
    }

    //할일 전부 삭제
    public void deleteAllToDo(){
        toDoRepository.deleteAll();
    }


    //검증메서드
    private ToDo verifyToDo(long toDoId){
        //Todo가 null일때 예외 발생
        Optional<ToDo> optionalToDo = toDoRepository.findById(toDoId);
        ToDo findToDo = optionalToDo.orElseThrow(()-> //orElseThrow => null일때 에외 발생
                new BusinessLogicException(ExceptionCode.TODO_NOT_FOUND));

        return findToDo;



    }


}