package com.teuac.todoweb.controllers;

import com.teuac.todoweb.entities.Todo;
import com.teuac.todoweb.repositories.TodoRepository;
import com.teuac.todoweb.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TodoController {

    @Autowired
    private TodoService todoService;


    @RequestMapping(value = "/createTodo",method = RequestMethod.GET)
    public String viewCreateTodo(){

        return "viewCreateTodo";
    }

    @RequestMapping(value = "/createTodo",method = RequestMethod.POST)
    public String createNewTodo(Todo todo){

            todoService.createTodo(todo);

            return "redirect:/createTodo";
    }

    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public ModelAndView viewTodos(){
        ModelAndView mav = new ModelAndView("viewTodos");
        mav.addObject("todos", todoService.findAll());
        return mav;
    }
    @RequestMapping(value = "/todosFalse", method = RequestMethod.GET)
    public ModelAndView viewTodosFalse(){
        ModelAndView mav = new ModelAndView("viewTodos");
        mav.addObject("todos", todoService.findComplete(false));
        return mav;
    }
    @RequestMapping(value = "/todosTrue", method = RequestMethod.GET)
    public ModelAndView viewTodosTrue(){
        ModelAndView mav = new ModelAndView("viewTodos");
        mav.addObject("todos", todoService.findComplete(true));
        return mav;
    }

    @RequestMapping(value = "/{id}/update")
    public String updateTodo(@PathVariable Long id, Boolean completed){
        todoService.updateTodoCompleted(id, completed );
        return "redirect:/todos";
    }
    @RequestMapping(value = "/{id}")
    public String deleteTodo(@PathVariable Long id){
        todoService.todoRemove(id);
        return "redirect:/todos";
    }

}



