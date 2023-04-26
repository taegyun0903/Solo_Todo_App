package toDoApp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long toDoId;

    @Column
    private String title;

    @Column(name ="orders",nullable = false)
    private int order;

    @Enumerated(EnumType.STRING)
    private CompletedStatus completedStatus = CompletedStatus.TODO_FALSE;


}