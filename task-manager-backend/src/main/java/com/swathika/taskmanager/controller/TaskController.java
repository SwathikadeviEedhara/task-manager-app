package com.swathika.taskmanager.controller;

import java.util.List;


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
import com.swathika.taskmanager.Service.TaskService;
import com.swathika.taskmanager.entities.Task;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/task")
public class TaskController {

	@Autowired
    private TaskService taskservice;
	
	
	@GetMapping
	public ResponseEntity<List<Task>> getAlltask(){
		 
		List<Task> allTasks =taskservice.getAlltask();		
		 return ResponseEntity.ok(allTasks); 
	}
	
	
	
	@PostMapping
	public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
		
	Task newtask=taskservice.createTask(task);
	 return ResponseEntity.ok(newtask); 
		
	}

	
	
	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable Long id, @Valid @RequestBody Task taskDetails) {
	  
		return ResponseEntity.ok(taskservice.updateTask(id, taskDetails));
	    
	}

	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
	        
	    
	        return ResponseEntity.noContent().build();
	}
	    
}
	
	
	
	
	
	
	

