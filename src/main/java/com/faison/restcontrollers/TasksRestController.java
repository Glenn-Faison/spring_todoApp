package com.faison.restcontrollers;

import com.faison.models.Task;
import com.faison.services.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Api(value = "Tasks Rest Controller")
@RestController
@RequestMapping(value = "/api/v1")
public class TasksRestController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    @ApiOperation(value = "Create a new Task.", notes = "Returns the created Task.")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        task = taskService.create(task);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/")
                .buildAndExpand("")
                .toUri());
        return new ResponseEntity<>(task, headers, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get all Tasks or search Tasks by taskName.",
            notes = "Partition results into pages of the given size and return the given page number.")
    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public ResponseEntity<Page<Task>> getTasks(@RequestParam(value = "name", defaultValue = "", required = false) String name,
                                               @RequestParam(value = "pageSize", defaultValue = "10") String pageSize,
                                               @RequestParam(value = "pageNumber", defaultValue = "0") String pageNumber) {
        int pgSize = Integer.valueOf(pageSize);
        int pgNum = Integer.valueOf(pageNumber);
        Page<Task> tasks;
        if (!name.isEmpty()) {
            tasks = taskService.findByDescription(name, new PageRequest(pgNum, pgSize));
        } else {
            tasks = taskService.findAll(new PageRequest(pgNum, pgSize));
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{userId}/tasks/{taskId}", method = RequestMethod.GET)
    @ApiOperation(value = "Get the task with the given taskId")
    public ResponseEntity<Task> getTask(@PathVariable("userId") long userId,
                                        @PathVariable("taskId") long taskId) {
        Task task = taskService.findById(taskId);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{userId}/tasks/{taskId}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update the existing task with given taskId")
    public ResponseEntity<Task> updateTask(@PathVariable("userId") long userId,
                                           @PathVariable("taskId") long taskId,
                                           @RequestBody Task task) {
        if (taskId != task.getTaskId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        task = taskService.update(task);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users/{userId}/tasks/{taskId}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete the Task with given taskId")
    public ResponseEntity<Task> deleteTask(@PathVariable("userId") long userId,
                                           @PathVariable("taskId") long taskId) {
        Task task = taskService.findById(taskId);
        long taskId2 = 0;
        if (task != null) {
            taskId2 = task.getTaskId();
        }
        if (taskId != taskId2) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        taskService.delete(taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
