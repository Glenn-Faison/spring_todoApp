package ReSTAPI.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "users", schema = "spring_todo")
public class User {

    @Column(nullable = false, unique = true)
    private String email;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long userId;

    @NotNull
    private String password;

    private String phoneNumber;


    @Column(nullable = false, unique = true)
    private String username;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Task> taskList;

    @NotNull
    private boolean voided;

    public User() {
        setVoided(false);
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public long getUserId() {
        return userId;
    }

    public User setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public boolean isVoided() {
        return voided;
    }

    public User setVoided(boolean voided) {
        this.voided = voided;
        return this;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
