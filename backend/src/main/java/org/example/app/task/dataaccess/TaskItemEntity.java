package org.example.app.task.dataaccess;

import java.time.LocalDateTime;

import org.example.app.general.dataaccess.ApplicationPersistenceEntity;
import jakarta.persistence.*;
import org.example.app.task.common.TaskItem;

@Entity
@Table(name = "TASK_ITEM")
public class TaskItemEntity extends ApplicationPersistenceEntity implements TaskItem {

    private String title;

    private boolean completed;

    private boolean starred;

    private LocalDateTime deadline;

    @ManyToOne
    @JoinColumn(name = "list_id", referencedColumnName = "id")
    private TaskListEntity taskList;

    @Override
    public String getTitle() {

        return this.title;
    }

    @Override
    public void setTitle(String title) {

        this.title = title;
    }

    @Override
    public boolean isCompleted() {

        return this.completed;
    }

    @Override
    public void setCompleted(boolean completed) {

        this.completed = completed;
    }

    @Override
    public boolean isStarred() {

        return this.starred;
    }

    @Override
    public void setStarred(boolean starred) {

        this.starred = starred;
    }

    @Override
    public LocalDateTime getDeadline() {

        return this.deadline;
    }

    @Override
    public void setDeadline(LocalDateTime deadline) {

        this.deadline = deadline;
    }

    public TaskListEntity getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskListEntity taskList) {
        this.taskList = taskList;
    }
}
