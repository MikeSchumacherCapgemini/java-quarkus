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
public class UcDeleteTaskItem {

    @Inject
    TaskItemRepository taskItemRepo;

    @Inject
    TaskItemMapper taskItemMapper;

    public void delete(Long id){
        taskItemRepo.deleteById(id);
    }

    public void delete(TaskItemEto Item){
        TaskItemEntity toDelete = taskItemMapper.toEntity(Item);
        taskItemRepo.delete(toDelete);
    }
    
}
