package com.faison.services;

import com.faison.DAO.UserDAO;
import com.faison.models.Task;
import com.faison.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public User create(User user) {
        System.out.println(user.getUserId());
        if (exists(user.getUserId())) {
            return null;
        }
        user.setVoided(false);
        return userDAO.save(user);
    }

    public boolean exists(long userId) {
        return userDAO.exists(userId);
    }

    public Page<User> findAll(Pageable pageable) {
        Page<User> userPage = userDAO.findAll(pageable);
        return userPage;
    }

    /**
     * Find the unique user with the given email
     *
     * @param email
     * @return User
     */
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    public List<Task> findTasksByUser(long userId) {
        User user = userDAO.findByUserId(userId);
        List<Task> taskList = user.getTaskList();
        return taskList;
    }

    public User findById(long userId) {
        return userDAO.findByUserId(userId);
    }

    public Page<User> findByUsername(String username, Pageable pageable) {
        Page<User> userPage = userDAO.findByUsernameLikeIgnoreCase(username, pageable);
        return userPage;
    }

    public User update(User user) {
        if (!exists(user.getUserId())) {
            return null;
        }
        return userDAO.save(user);
    }

    public void delete(long userId) {
        userDAO.delete(userId);
    }
}