package com.faison.DAO;

import com.faison.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDAO extends PagingAndSortingRepository<Task, Long> {

    Task findByTaskId(@Param("task_id") long taskId);

    Page<Task> findByDescriptionLikeIgnoreCase(String description, Pageable pageable);

    Page<Task> findByCompletedIsTrue(Pageable pageable);

    Page<Task> findByCompletedIsFalse(Pageable pageable);
}
