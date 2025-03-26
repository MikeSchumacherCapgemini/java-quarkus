package org.example.app.task.logic;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import org.example.app.task.common.TaskItemEto;
import org.example.app.task.dataaccess.TaskItemEntity;
import org.example.app.task.dataaccess.TaskItemRepository;

import java.util.Optional;

@ApplicationScoped
@Named
@Transactional
public class UcFindTaskItem {

    @Inject
    TaskItemRepository taskItemRepo;

    @Inject
    TaskItemMapper taskItemMapper;

    public TaskItemEto findById(Long id){
        Optional<TaskItemEntity> item = this.taskItemRepo.findById(id);
        return item.map(entity -> this.taskItemMapper.toEto(entity)).orElse(null);
    }
    
}
