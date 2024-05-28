package com.teuac.todoweb.repositories;

import com.teuac.todoweb.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    public List<Todo> findByPriority(Integer priority);
    public List<Todo> findByCompleted(Boolean status);

}
