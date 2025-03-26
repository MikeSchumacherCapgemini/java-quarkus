package org.example.app.task.logic;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import org.example.app.task.common.TaskListCto;
import org.example.app.task.common.TaskListEto;
import org.example.app.task.dataaccess.TaskListEntity;
import org.example.app.task.dataaccess.TaskListRepository;

import java.util.Optional;

@ApplicationScoped
@Named
@Transactional
public class UcFindTaskList {

    @Inject
    TaskListRepository taskListRepo;

    @Inject
    TaskListMapper taskListMapper;

    @Inject
    TaskItemMapper taskItemMapper;

    public TaskListEto findById(Long id){
        Optional<TaskListEntity> item = this.taskListRepo.findById(id);
        return item.map(entity -> this.taskListMapper.toEto(entity)).orElse(null);
    }
    public TaskListCto findWithItems(Long listId) {

        Optional<TaskListEntity> taskList = this.taskListRepo.findById(listId);
        if (taskList.isEmpty()) {
            return null;
        }
        TaskListCto cto = new TaskListCto();
        TaskListEntity taskListEntity = taskList.get();
        cto.setList(this.taskListMapper.toEto(taskListEntity));
        cto.setItems(this.taskItemMapper.toEtos(taskListEntity.getItems()));
        return cto;
    }

}
