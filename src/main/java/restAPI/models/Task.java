package restAPI.models;


import java.util.Date;

public class Task {
    private String id;
    private User owner;
    private String description;
    private Date dateCreated;
    private boolean completed;

    public Task() {
    }

    public Task(String id, User owner, String description, Date dateCreated, boolean completed) {
        this.id = id;
        this.owner = owner;
        this.description = description;
        this.dateCreated = dateCreated;
        this.completed = completed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean is(Task task){
        if (this.id.equals(task.getId())){
            return true;
        }
        return false;
    }
}
