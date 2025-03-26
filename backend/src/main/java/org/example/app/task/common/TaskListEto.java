package org.example.app.task.common;

import org.example.app.general.common.AbstractEto;
import org.example.app.task.dataaccess.TaskItemEntity;

import java.util.List;

public class TaskListEto extends AbstractEto implements TaskList {

    private String title;

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String title) {
        this.title=title;
    }
}
