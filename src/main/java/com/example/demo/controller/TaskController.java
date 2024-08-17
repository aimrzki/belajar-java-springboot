package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")

public class TaskController {
    @Autowired
    private TaskService service;
    @Autowired
    private View error;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@Valid @RequestBody Task task) {
        return service.addTask(task);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getTasks(){
        return service.findAllTasks();
    }

    @GetMapping("/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public Task getTask(@PathVariable String taskId){
        return service.getTaskByTaskId(taskId);
    }

    @GetMapping("/severity/{severity}")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> findTaskUsingSeverity(@PathVariable int severity){
        return service.getTaskBySeverity(severity);
    }

    @GetMapping("/assignee/{assignee}")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> findTaskUsingAssignee(@PathVariable String assignee){
        return service.getTaskByAssignee(assignee);
    }

    @PutMapping("/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public Task updateTaskById(@PathVariable String taskId, @RequestBody Task task){
        return service.updateTask(taskId,task);
    }

    @DeleteMapping("/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteTaskById(@PathVariable String taskId){
        return service.deleteTask(taskId);
    }
}
