package com.faison.services;

import com.faison.DAO.TaskDAO;
import com.faison.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskDAO taskDAO;

    public Task create(Task task) {
        if (exists(task.getTaskId())) {
            return null;
        }
        return taskDAO.save(task);
    }

    public Page<Task> findAll(Pageable pageable) {
        return taskDAO.findAll(pageable);
    }

    public Page<Task> findCompletedTasks(Pageable pageable) {
        return taskDAO.findByCompletedIsTrue(pageable);
    }

    public Page<Task> findByDescription(String description, Pageable pageable) {
        return taskDAO.findByDescriptionLikeIgnoreCase(description, pageable);
    }

    public Task findById(long taskId) {
        return taskDAO.findByTaskId(taskId);
    }

    public Page<Task> findIncompleteTasks(Pageable pageable) {
        return taskDAO.findByCompletedIsFalse(pageable);
    }

    /*public Page<Task> findTasksByOwnerId(long ownerId, Pageable pageable) {
        User user = userService.findById(ownerId);
        return taskDAO.findByOwner(ownerId, pageable);
    }*/

    public Task update(Task task) {
        if (!exists(task.getTaskId())) {
            return null;
        }
        return taskDAO.save(task);
    }

    public void delete(long taskId) {
        taskDAO.delete(taskId);
    }

    public boolean exists(long taskId) {
        return taskDAO.exists(taskId);
    }
}
