package org.example.app.task.service;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.example.app.task.common.TaskItemEto;
import org.example.app.task.common.TaskListCto;
import org.example.app.task.common.TaskListEto;
import org.example.app.task.logic.*;

import java.net.URI;

@Path("/task")
public class TaskService {
    @Inject
    private UcFindTaskItem ucFindTaskItem;

    @Inject
    private UcDeleteTaskItem ucDeleteTaskItem;

    @Inject
    private UcSaveTaskItem ucSaveTaskItem;

    @Inject
    private UcFindTaskList ucFindTaskList;

    @Inject
    private UcDeleteTaskList ucDeleteTaskList;

    @Inject
    private UcSaveTaskList ucSaveTaskList;

    @GET
    @Path("/list/{id}")
    @Operation(summary = "Fetch task list", description = "Fetch a task list", operationId = "findTaskList")
    @APIResponse(responseCode = "200", description = "Task List")
    @APIResponse(responseCode = "404", description = "Task list not found")
    @APIResponse(responseCode = "500", description = "Server unavailable or a server-side error occurred")
    public TaskListEto findTaskList(@Parameter(description = "The id of the task list to retrieve", required = true) @PathParam("id") Long id) {

        TaskListEto task = this.ucFindTaskList.findById(id);
        if (task == null) {
            throw new NotFoundException("TaskList with id " + id + " does not exist.");
        }
        return task;
    }

    @GET
    @Path("/list-with-items/{id}")
    @Operation(operationId = "findTaskListWithItems", summary = "Fetch task list with tasks", description = "Fetch a task list including all of its task items")
    @APIResponse(responseCode = "200", description = "Task list with task items")
    @APIResponse(responseCode = "404", description = "Task list not found")
    @APIResponse(responseCode = "500", description = "Server unavailable or a server-side error occurred")
    public TaskListCto findTaskListWithItems(@Parameter(description = "The id of the task list to retrieve", required = true) @PathParam("id") Long id) {
        TaskListCto task = this.ucFindTaskList.findWithItems(id);
        if (task == null) {
            throw new NotFoundException("TaskList with id " + id + " does not exist.");
        }
        return task;
    }

    @DELETE
    @Path("/list/{id}")
    @Operation(operationId = "deleteTaskList", summary = "Delete task list", description = "Deletes an entire task list")
    @APIResponse(responseCode = "204", description = "Task list deleted")
    @APIResponse(responseCode = "201", description = "Task list successfully created")
    @APIResponse(responseCode = "500", description = "Server unavailable or a server-side error occurred")
    public void deleteTaskList(@Parameter(description = "The id of the task list to delete", required = true)@PathParam("id") Long id) {
        this.ucDeleteTaskList.delete(id);
    }

    @POST
    @Path("/list")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create or update task list", description = "Update a task list or creates a new one if the id is empty.")
    @APIResponse(responseCode = "200", description = "Task list successfully updated")
    @APIResponse(responseCode = "201", description = "Task list successfully created")
    @APIResponse(responseCode = "400", description = "Validation error")
    @APIResponse(responseCode = "500", description = "Server unavailable or a server-side error occurred")
    public Response saveTaskList(@Valid TaskListEto taskList) {

        Long taskListId = this.ucSaveTaskList.save(taskList);
        if (taskList.getId() == null || taskList.getId() != taskListId) {
            return Response.created(URI.create("/task/list/" + taskListId)).build();
        }
        return Response.ok().build();
    }

    @GET
    @Path("/item/{id}")
    @Operation(operationId = "findTaskItem", summary = "Fetch task item", description = "Fetch task item")
    @APIResponse(responseCode = "200", description = "Task successfully updated")
    @APIResponse(responseCode = "201", description = "Task successfully created")
    @APIResponse(responseCode = "400", description = "Validation error")
    @APIResponse(responseCode = "500", description = "Server unavailable or a server-side error occurred")
    public TaskItemEto findTaskItem(@Parameter(description = "The id of the task item to fetch", required = true)@PathParam("id") Long id) {

        TaskItemEto item = this.ucFindTaskItem.findById(id);
        if (item == null) {
            throw new NotFoundException("TaskList with id " + id + " does not exist.");
        }
        return item;
    }

    @DELETE
    @Path("/item/{id}")
    @Operation(operationId = "deleteTaskItem", summary = "Delete a task item", description = "Delete a task item")
    @APIResponse(responseCode = "200", description = "Task successfully updated")
    @APIResponse(responseCode = "201", description = "Task successfully created")
    @APIResponse(responseCode = "400", description = "Validation error")
    @APIResponse(responseCode = "500", description = "Server unavailable or a server-side error occurred")
    public void deleteTaskItem(@Parameter(description = "The id of the task item to delete", required = true)@PathParam("id") Long id) {
        this.ucDeleteTaskItem.delete(id);
    }

    @POST
    @Path("item")
    @Operation(operationId = "saveTaskItem", summary = "Add or update task item", description = "Update a task item or add it as a new item if the id is empty")
    @APIResponse(responseCode = "200", description = "Task successfully updated")
    @APIResponse(responseCode = "201", description = "Task successfully created")
    @APIResponse(responseCode = "400", description = "Validation error")
    @APIResponse(responseCode = "500", description = "Server unavailable or a server-side error occurred")
    public Response saveTaskItem(@Valid TaskItemEto taskItem) {

        Long taskListId = this.ucSaveTaskItem.save(taskItem);
        if (taskItem.getId() == null || taskItem.getId() != taskListId) {
            return Response.created(URI.create("/task/item/" + taskListId)).build();
        }
        return Response.ok().build();
    }

}
