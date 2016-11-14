package restAPI.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import restAPI.models.Task;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/tasks")
public class TaskController {

    List<Task> allTasks;

    public TaskController() {
        this.allTasks = new ArrayList<>();
    }
}
