package com.faison.DAO;

import com.faison.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface TaskDAO extends PagingAndSortingRepository<Task, String> {

    Task findById(@Param("task_id") String taskId);

    Page<Task> findByDescriptionLike(@Param("description") String description, Pageable pageable);

    Page<Task> findByOwnerId(@Param("owner_id") String ownerId, Pageable pageable);

    Page<Task> findByCompletedIsTrue(Pageable pageable);

    Page<Task> findByCompletedIsNotTrue(Pageable pageable);
}
