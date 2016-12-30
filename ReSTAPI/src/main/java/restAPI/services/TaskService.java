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
        if (exists(task.getId())) {
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

    public Page<Task> findIncompleteTasks(Pageable pageable) {
        return taskDAO.findByCompletedIsNotTrue(pageable);
    }

    public Page<Task> findTasksByOwnerId(String ownerId, Pageable pageable) {
        return taskDAO.findByOwnerId(ownerId, pageable);
    }

    public Task update(Task task) {
        if (!exists(task.getId())) {
            return null;
        }
        return taskDAO.save(task);
    }

    public void delete(String taskId) {
        delete(taskId);
    }

    public boolean exists(String taskId) {
        if (taskId == null || taskId.isEmpty()) {
            return false;
        }
        return taskDAO.exists(taskId);
    }
}
