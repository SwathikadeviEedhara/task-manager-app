package com.swathika.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.swathika.taskmanager.entities.Task;
import com.swathika.taskmanager.repository.TaskRepository;

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
	public void createTask(@RequestBody Task task) {
		
		taskRepository.save(task);
	}
	
	
	
}
