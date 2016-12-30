package com.faison.DAO;

import com.faison.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserDAO extends PagingAndSortingRepository<User, String> {

    Page<User> findByUsernameLike(@Param("username") String username, Pageable pageable);

    Page<User> findAll(Pageable pageable);

    Page<User> findByVoidedIsFalse(Pageable pageable);

    Page<User> findByVoidedIsTrue(Pageable pageable);

    User findById(@Param("user_id") String userId);

    User findByEmail(@Param("email") String email);
}