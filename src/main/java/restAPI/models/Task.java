package restAPI.models;


import java.util.Date;

public class Task {
    private String id;
    private String ownerId;
    private String description;
    private Date dateCreated;
    private boolean completed;

    public Task() {
    }

    public Task(String id, String ownerId, String description, Date dateCreated, boolean completed) {
        this.id = id;
        this.ownerId = ownerId;
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

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
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
}
