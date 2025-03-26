package org.example.app.task.logic;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import org.example.app.task.common.TaskListEto;
import org.example.app.task.dataaccess.TaskListEntity;
import org.example.app.task.dataaccess.TaskListRepository;

import java.util.Optional;

@ApplicationScoped
@Named
@Transactional
public class UcDeleteTaskList {

    @Inject
    TaskListRepository taskListRepo;

    @Inject
    TaskListMapper taskListMapper;

    public void delete(Long id){
        taskListRepo.deleteById(id);
    }

    public void delete(TaskListEto list){
        TaskListEntity toDelete = taskListMapper.toEntity(list);
        taskListRepo.delete(toDelete);
    }

}
