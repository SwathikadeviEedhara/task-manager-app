package com.swathika.taskmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.swathika.taskmanager.entities.Task;
import com.swathika.taskmanager.exceptions.TaskNotFoundException;
import com.swathika.taskmanager.repository.TaskRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
@Autowired
	TaskRepository taskRepository;
	
	
	@GetMapping
	public List<Task> getAlltask(){
		
		return taskRepository.findAll();
		
	}
	
	
	
	@PostMapping
	public void createTask(@Valid @RequestBody Task task) {
		
		taskRepository.save(task);
	}
	
	
	@PutMapping("/api/tasks/{id}")
	public Task updateTask(@PathVariable Long id, @Valid @RequestBody Task taskDetails) {
	  

	    Optional<Task> task = taskRepository.findById(id);
	    if (task.isEmpty()) {
	        throw new TaskNotFoundException("Task with ID " + id + " not found");
	    }
	    return task.get();
	}

	
	@DeleteMapping("/api/tasks/{id}")
	public void deleteTask(@PathVariable Long id) {
	    Optional<Task> deleteTask = taskRepository.findById(id);
         
	    if (deleteTask.isEmpty()) {
	        throw new TaskNotFoundException("Task with ID " + id + " not found");
	    }
	       taskRepository.delete(deleteTask.get());
	}
	    
}
	
	
	
	
	
	
	

