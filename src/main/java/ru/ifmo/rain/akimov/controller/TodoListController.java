package ru.ifmo.rain.akimov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ifmo.rain.akimov.dao.TodoListDao;
import ru.ifmo.rain.akimov.model.TodoItem;
import ru.ifmo.rain.akimov.model.TodoList;

@Controller
public class TodoListController {
    private final TodoListDao todoListDao;

    public TodoListController(TodoListDao todoListDao) {
        this.todoListDao = todoListDao;
    }

    @RequestMapping(value = "/add-todolist", method = RequestMethod.POST)
    public String addTodoList(@ModelAttribute("todolist")TodoList todoList) {
        todoListDao.addTodoList(todoList);
        return "redirect:/get-todolists";
    }

    @RequestMapping(value = "/delete-todolist", method = RequestMethod.POST)
    public String deleteTodoList(@RequestParam int id) {
        todoListDao.deleteTodoList(id);
        return "redirect:/get-todolists";
    }

    @RequestMapping(value = "/add-todoitem", method = RequestMethod.POST)
    public String addTodoItem(@ModelAttribute("todoitem")TodoItem todoItem, @RequestParam int todoListId) {
        todoListDao.addTodoItem(todoListId, todoItem);
        return "redirect:/get-todolists";
    }

    @RequestMapping(value = "/delete-todoitem", method = RequestMethod.POST)
    public String deleteTodoItem(@RequestParam int todoItemId, @RequestParam int todoListId) {
        todoListDao.deleteTodoItem(todoListId, todoItemId);
        return "redirect:/get-todolists";
    }

    @RequestMapping(value = "/invert-todoitem", method = RequestMethod.POST)
    public String invertTodoItem(@RequestParam int todoItemId, @RequestParam int todoListId) {
        todoListDao.invertItem(todoListId, todoItemId);
        return "redirect:/get-todolists";
    }

    @RequestMapping(value = "/get-todolists", method = RequestMethod.GET)
    public String getTodoLists(ModelMap modelMap) {
        modelMap.addAttribute("todolists", todoListDao.getTodoLists());
        modelMap.addAttribute("todolist", new TodoList());
        modelMap.addAttribute("todoitem", new TodoItem());
        return "index";
    }
}
