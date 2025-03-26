package org.example.app.task.logic;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import org.example.app.task.common.TaskListEto;
import org.example.app.task.dataaccess.TaskItemEntity;
import org.example.app.task.dataaccess.TaskListEntity;
import org.example.app.task.dataaccess.TaskListRepository;

@ApplicationScoped
@Named
@Transactional
public class UcSaveTaskList {

    @Inject
    TaskListRepository taskListRepo;

    @Inject
    TaskListMapper taskListMapper;

    public Long save(TaskListEto toSave){
        TaskListEntity taskList = taskListMapper.toEntity(toSave);
        taskListRepo.save(taskList);
        return taskList.getId();
    }
}
