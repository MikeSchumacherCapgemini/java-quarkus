package org.example.app.task.common;

import org.example.app.general.common.ApplicationEntity;
import org.example.app.task.dataaccess.TaskItemEntity;

import java.util.List;

public interface TaskList extends ApplicationEntity {

    String getTitle();

    void setTitle(String title);
}
