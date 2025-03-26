package org.example.app.task.logic;

import java.util.List;
import java.util.Optional;

import org.example.app.task.common.TaskItemEto;
import org.example.app.task.common.TaskListEto;
import org.example.app.task.dataaccess.TaskItemEntity;
import org.example.app.task.dataaccess.TaskListEntity;
import org.mapstruct.Mapper;

/**
 * {@link Mapper} for {@link org.example.app.task.common.TaskItem}.
 */
@Mapper(componentModel = "cdi")
public interface TaskListMapper {

    List<TaskItemEto> toEtos(List<TaskItemEntity> items);

    TaskListEto toEto(TaskListEntity list);

    TaskListEntity toEntity(TaskListEto list);
}
