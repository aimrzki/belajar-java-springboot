package com.example.demo.service;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;

    // CRUD CREATE,READ,UPDATE,DELETE

    public Task addTask(Task task){
        task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
        return repository.save(task);
    }

    public List<Task> findAllTasks(){
        return repository.findAll();
    }

    public Task getTaskByTaskId(String taskId){
        return repository.findById(taskId).get();
    }

    public List<Task> getTaskBySeverity(int severity ){
        return repository.findBySeverity(severity);
    }

    public List<Task> getTaskByAssignee(String assignee){
        return repository.getTaskByAssignee(assignee);
    }

    public Task updateTask(String taskId,Task taskRequest){
        //get the existing document from DB
        //populate new value from request to existing object/entity/document
        Task existingTask = repository.findById(taskId).get();
        if (taskRequest.getDescription()!=null){
            existingTask.setDescription(taskRequest.getDescription());
        }

        //existingTask.setDescription(taskRequest.getDescription());
        if (taskRequest.getSeverity()!=0){
            existingTask.setSeverity(taskRequest.getSeverity());
        }

        //existingTask.setSeverity(taskRequest.getSeverity());

        //existingTask.setAssignee(taskRequest.getAssignee());
        if (taskRequest.getAssignee()!=null){
            existingTask.setAssignee((taskRequest.getAssignee()));
        }

        //existingTask.setStoryPoint(taskRequest.getStoryPoint());
        if (taskRequest.getStoryPoint()!=0){
            existingTask.setStoryPoint((taskRequest.getStoryPoint()));
        }

        return repository.save(existingTask);
    }

    public String deleteTask(String taskId){
        repository.deleteById(taskId);
        return taskId + "task delete from dashboard";
    }
}
