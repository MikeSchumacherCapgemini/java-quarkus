package org.example.app.task.dataaccess;

import java.util.List;

import org.example.app.general.dataaccess.ApplicationPersistenceEntity;
import org.example.app.task.common.TaskList;

import jakarta.persistence.*;

@Entity
@Table(name = "TASK_LIST")
public class TaskListEntity extends ApplicationPersistenceEntity implements TaskList {

    private String title;

    @OneToMany(mappedBy = "taskList", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TaskItemEntity> items;

    public List<TaskItemEntity> getItems() {
        return items;
    }

    public void setItems(List<TaskItemEntity> items) {
        this.items = items;
    }

    @Override
    public String getTitle() {

        return this.title;
    }

    @Override
    public void setTitle(String title) {

        this.title = title;
    }

}
