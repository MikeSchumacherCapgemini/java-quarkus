package org.example.app.task.dataaccess;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.assertj.core.api.Assertions;
import org.example.app.general.dataaccess.ApplicationPersistenceEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;

@QuarkusTest
public class TaskItemRepositoryTest extends Assertions {

    @Inject
    TaskItemRepository taskItemRepo;

    @Test
    public void testFindById() {
        Long itemId = 11L;
        TaskItemEntity item = taskItemRepo.findById(itemId).get();
        assertThat(item.getTitle()).isEqualTo("Milk");
    }

    //@Test
    public void testGetAllAndOnlyTaskLists(){
        List<Long> listIds = new LinkedList<>();
        for(TaskItemEntity item : taskItemRepo.findAll()){

        }
        assertThat(listIds.size()).isEqualTo(4);
    }

    @Test
    public void testAddTaskItemToNonEmptyTaskList(){
        TaskItemEntity taskItem = taskItemRepo.findAll().getFirst();
        int previousCount = 0;
        boolean newList=true;
        for(TaskItemEntity task : taskItemRepo.findAll()){
            if(task.getTaskList().equals(taskItem.getTaskList())){
                previousCount++;
            }
            else if(newList){
                task.setTaskList(taskItem.getTaskList());
            }
        }

        int newCount = 0;
        for(TaskItemEntity task : taskItemRepo.findAll()){
            if(task.getTaskList().equals(taskItem.getTaskList())){
                newCount++;
            }
        }
        assertThat(newCount).isNotEqualTo(previousCount);
    }

    @Test
    public void testFindAllTaskListsWithTaskItemsDueInAWeek(){
        List<TaskItemEntity> resultItems = new LinkedList<>();
        for(TaskItemEntity taskItem : taskItemRepo.findAll()){
            if (taskItem.getDeadline()!=null){
                if(ChronoUnit.DAYS.between(taskItem.getDeadline(), LocalDateTime.now())<=7){
                    resultItems.add(taskItem);
                }
            }
        }
        assertThat(resultItems.size()).isEqualTo(0);
    }

    @Test
    public void testFindAllListsWithOpenItems(){
        List<TaskListEntity> resultItems = new LinkedList<>();
        for(TaskItemEntity taskItem : taskItemRepo.findAll()){
            if(!taskItem.isCompleted() && !resultItems.contains(taskItem.getTaskList())){
                resultItems.add(taskItem.getTaskList());
            }
        }
        assertThat(resultItems.size()).isEqualTo(4);
    }

    @Test
    public void testDeleteAllCompletedTaskItems(){
        List<TaskItemEntity> taskItems = taskItemRepo.findAll();
        int previousSize = taskItems.size();
        for(TaskItemEntity taskItem : taskItems){
            if(taskItem.isCompleted()){
                taskItemRepo.delete(taskItem);
            }
        }
        assertThat(taskItemRepo.findAll().size()).isEqualTo(previousSize-2);
    }
}