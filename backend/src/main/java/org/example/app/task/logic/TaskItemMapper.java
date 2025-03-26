package org.example.app.task.logic;

import java.util.ArrayList;
import java.util.List;

import org.example.app.task.common.TaskItemEto;
import org.example.app.task.dataaccess.TaskItemEntity;
import org.mapstruct.Mapper;

/**
 * {@link Mapper} for {@link org.example.app.task.common.TaskItem}.
 */
@Mapper(componentModel = "cdi")
public interface TaskItemMapper {

    /**
     * @param items the {@link List} of {@link TaskItemEntity items} to convert.
     * @return the {@link List} of converted {@link TaskItemEto}s.
     */
    List<TaskItemEto> toEtos(List<TaskItemEntity> items);

    /**
     * @param item the {@link TaskItemEntity} to map.
     * @return the mapped {@link TaskItemEto}.
     */
    TaskItemEto toEto(TaskItemEntity item);

    /**
     * @param item the {@link TaskItemEto} to map.
     * @return the mapped {@link TaskItemEntity}.
     */
    TaskItemEntity toEntity(TaskItemEto item);
}