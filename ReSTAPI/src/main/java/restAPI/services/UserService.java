package com.faison.services;

import com.faison.DAO.UserDAO;
import com.faison.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public void checkUserValidity(User user) {
    }

    public User create(User user) {
        checkUserValidity(user);
        user.setVoided(false);
        return userDAO.save(user);
    }

    public boolean exists(String userId) {
        if (userId == null || userId.isEmpty()) {
            return false;
        }
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

    public User findById(String userId) {
        return userDAO.findById(userId);
    }

    public Page<User> findByUsername(String username, Pageable pageable) {
        Page<User> userPage = userDAO.findByUsernameLike(username, pageable);
        return userPage;
    }

    public User update(User user) {
        if (!exists(user.getId())) {
            return null;
        }
        checkUserValidity(user);
        return userDAO.save(user);
    }

    public void delete(String userId) {
        userDAO.delete(userId);
    }
}