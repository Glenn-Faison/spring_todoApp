package restAPI.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restAPI.models.Task;
import restAPI.models.User;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/tasks")
@RestController
public class TaskController {

    private List<Task> allTasks;

    private void editTaskInfo(Task task1, final Task task2) {
        task1.setCompleted(task2.isCompleted());
        task1.setDateCreated(task2.getDateCreated());
        task1.setDescription(task2.getDescription());
        task1.setOwner(task2.getOwner());
    }

    public TaskController() {
        this.allTasks = new ArrayList<>();
    }

    private String getNewId() {
        if (this.allTasks.isEmpty()) {
            return "1";
        }
        String lastIdStr = this.allTasks.get(this.allTasks.size() - 1).getId();
        int newIdInt = Integer.parseInt(lastIdStr);
        return Integer.toString(newIdInt + 1);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addTasks(@RequestBody List<Task> tasks) {
        int tasksCreated = 0;
        for (Task task : tasks) {
            int count = 0;
            for (int i = 0; i < this.allTasks.size(); i++) {
                if (!this.allTasks.get(i).is(task)) {
                    count++;
                }
            }
            if (count == this.allTasks.size()){
                task.setId(getNewId());
                this.allTasks.add(task);
                tasksCreated++;
            }
        }
        return new ResponseEntity<String>("\n" + tasksCreated + " Tasks Created\n", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Task> getTasks(@RequestParam(value = "owner_name", defaultValue = "") String owner_name,
                               @RequestParam(value = "description", defaultValue = "") String description){
        List<Task> returnList = new ArrayList<>();
        description = description.toLowerCase();
        for (Task task : this.allTasks){
            if (task.getDescription().toLowerCase().contains(description)) {
                returnList.add(task);
            }
        }
        if (returnList.isEmpty()) {
            return returnList;
        }
        owner_name = owner_name.toLowerCase();
        for (int i = 0; i < returnList.size(); i++) {
            if (!returnList.get(i).getOwner().getFullName().contains(owner_name)) {
                returnList.remove(i);
                i--;
            }
        }
        return returnList;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Task> getTaskById(@PathVariable("id") String id) {
        Task returnValue = null;
        for (Task task : this.allTasks) {
            if (task.getId().equals(id)) {
                returnValue = task;
                break;
            }
        }
        return new ResponseEntity<Task>(returnValue, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Task> editTask(@PathVariable("id") String id, @RequestBody Task newTask) {
        for (Task task : this.allTasks) {
            if (task.getId().equals(id)) {
                editTaskInfo(task, newTask);
                newTask = task;
                break;
            }
        }
        return new ResponseEntity<Task>(newTask, HttpStatus.CREATED);
    }
}
