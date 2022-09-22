package org.example.app.task.common;

import org.example.app.general.common.AbstractEto;

/**
 * {@link TaskItem} implementation as {@link AbstractEto}.
 */
public class TaskItemEto extends AbstractEto implements TaskItem {

  private String title;

  private TaskState state;

  @Override
  public String getTitle() {

    return this.title;
  }

  @Override
  public void setTitle(String title) {

    this.title = title;
  }

  @Override
  public TaskState getState() {

    return this.state;
  }

  @Override
  public void setState(TaskState state) {

    this.state = state;
  }

}
