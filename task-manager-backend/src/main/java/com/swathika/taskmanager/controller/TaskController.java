package com.swathika.taskmanager.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swathika.taskmanager.DTO.Response;
import com.swathika.taskmanager.Service.TaskService;
import com.swathika.taskmanager.entities.Task;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/task")
public class TaskController {

	@Autowired
    private TaskService taskService;
	
	@GetMapping("")
	public ResponseEntity<Response<List<Task>>> getAllTask() {
	    List<Task> allTasks = taskService.getAlltask();
	    Response<List<Task>> response = new Response<>();
	    response.setStatusCode(200);
	    response.setMessage("Tasks retrieved successfully");
	    response.setData(allTasks);
	    return ResponseEntity.ok(response);
	}

	
	@PostMapping
	public ResponseEntity<Response<Task>> createTask(@Valid @RequestBody Task task) {
	    Task createdTask = taskService.createTask(task);
	    Response<Task> response = new Response<>();
	    response.setStatusCode(201);
	    response.setMessage("Task created successfully");
	    response.setData(createdTask);
	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	}


	
	@PutMapping("/{id}")
	public ResponseEntity<Response<Task>> updateTask(@PathVariable Long id, @Valid @RequestBody Task taskDetails) {
	    Task updatedTask = taskService.updateTask(id, taskDetails);
	    Response<Task> response = new Response<>();
	    response.setStatusCode(200);
	    response.setMessage("Task updated successfully");
	    response.setData(updatedTask);
	    return ResponseEntity.ok(response);
	}


	
	 @PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<Void>> deleteTask(@PathVariable Long id) {
	    taskService.deleteTask(id);
	    Response<Void> response = new Response<>();
	    response.setStatusCode(204);
	    response.setMessage("Task deleted successfully");
	    response.setData(null);
	    return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
	}

	@GetMapping("/search")
	public ResponseEntity<Response<List<Task>>> getTasksByStatus(@RequestParam String status) {
	    List<Task> tasks = taskService.getTasksByStatus(status);
	    Response<List<Task>> response = new Response<>();
	    response.setStatusCode(200);
	    response.setMessage("Tasks filtered by status");
	    response.setData(tasks);
	    return ResponseEntity.ok(response);
	}

	
	
	@GetMapping("/paged")
	public ResponseEntity<Response<Page<Task>>> getTasksPaginatedSorted(
	    @RequestParam(defaultValue = "0") int page,
	    @RequestParam(defaultValue = "10") int size,
	    @RequestParam(defaultValue = "id") String sortBy,
	    @RequestParam(defaultValue = "asc") String direction) {
	    
	    Page<Task> taskPage = taskService.getTasksPaginatedSorted(page, size, sortBy, direction);
	    Response<Page<Task>> response = new Response<>();
	    response.setStatusCode(200);
	    response.setMessage("Paginated and sorted tasks retrieved");
	    response.setData(taskPage);
	    return ResponseEntity.ok(response);
	}

	
	
	
	
	
	
	
	
	
	    
}
	
	
	
	
	
	
	

