package com.faison.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "tasks", schema = "spring_todo")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long taskId;

    @NotNull
    private String description;

    @NotNull
    private boolean completed;

    private Date alertDate;

    @OneToOne(optional = false)
    private User owner;

    public Task() {
    }

    public long getTaskId() {
        return taskId;
    }

    public Task setTaskId(long taskId) {
        this.taskId = taskId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Task setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isCompleted() {
        return completed;
    }

    public Task setCompleted(boolean completed) {
        this.completed = completed;
        return this;
    }

    public Date getAlertDate() {
        return alertDate;
    }

    public Task setAlertDate(Date alertDate) {
        this.alertDate = alertDate;
        return this;
    }

    public User getOwner() {
        return owner;
    }

    public Task setOwner(User owner) {
        this.owner = owner;
        return this;
    }
}
