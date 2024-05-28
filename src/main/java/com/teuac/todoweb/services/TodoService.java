package com.teuac.todoweb.services;

import com.teuac.todoweb.entities.Todo;
import com.teuac.todoweb.repositories.TodoRepository;
import jakarta.persistence.OrderBy;
import org.hibernate.metamodel.mapping.EntityIdentifierMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



@Service
public class TodoService {


    @Autowired
    private TodoRepository repository;

    public void createTodo(Todo todo) {

        repository.save(todo);
    }

    public List<Todo> findAll() {
        Sort sort = Sort.by("priority").descending();
        return  repository.findAll(sort);
    }
    public List<Todo> findByPriority(int priority) {
        return repository.findByPriority(priority);
    }

    public List<Todo> findComplete(Boolean completed) {
       return repository.findByCompleted(completed);
    }
    public void updateTodoCompleted(Long id , Boolean completed) {
        Todo todo = repository.getReferenceById(id);
        if (todo.getCompleted() == true) {
            completed = false;
        }
        else{
            completed = true;
        }
        updateTodo(todo, completed);
        repository.save(todo);
    }

    public void todoRemove(Long id) {
        Todo todo = repository.findById(id).orElseThrow();
        repository.delete(todo);

    }

    public void updateTodo(Todo entitiy, Boolean completed ){
        entitiy.setCompleted(completed);
    }



}
