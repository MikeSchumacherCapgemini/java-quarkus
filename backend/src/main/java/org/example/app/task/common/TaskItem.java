package org.example.app.task.common;

import org.example.app.general.common.ApplicationEntity;

import java.time.LocalDateTime;

public interface TaskItem extends ApplicationEntity {
    String getTitle();

    void setTitle(String title);

    boolean isCompleted();

    void setCompleted(boolean completed);

    boolean isStarred();

    void setStarred(boolean starred);

    LocalDateTime getDeadline();

    void setDeadline(LocalDateTime deadline);
}
