package com.faison.DAO;

import com.faison.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends PagingAndSortingRepository<User, Long> {

    Page<User> findByUsernameLikeIgnoreCase(String username, Pageable pageable);

    Page<User> findAll(Pageable pageable);

    Page<User> findByVoidedIsFalse(Pageable pageable);

    Page<User> findByVoidedIsTrue(Pageable pageable);

    User findByUserId(@Param("user_id") long userId);

    User findByEmail(String email);
}