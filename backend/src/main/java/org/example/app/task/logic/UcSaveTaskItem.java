package org.example.app.task.logic;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import org.example.app.task.common.TaskItemEto;
import org.example.app.task.dataaccess.TaskItemEntity;
import org.example.app.task.dataaccess.TaskItemRepository;

@ApplicationScoped
@Named
@Transactional
public class UcSaveTaskItem {

    @Inject
    TaskItemRepository taskItemRepo;

    @Inject
    TaskItemMapper taskItemMapper;

    public Long save(TaskItemEto toSave){
        TaskItemEntity taskItem = taskItemMapper.toEntity(toSave);
        taskItemRepo.save(taskItem);
        return taskItem.getId();
    }

}
