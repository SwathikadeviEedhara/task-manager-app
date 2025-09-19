package com.swathika.taskmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.swathika.taskmanager.DTO.Response;
import com.swathika.taskmanager.entities.Task;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	
	@ExceptionHandler(TaskNotFoundException.class)
	public ResponseEntity<Response<Task>> handleTaskNotFoundException() {
		
		Response<Task> response =new Response<Task>();
		
		response.setMessage("Task not Available");
		response.setStatusCode(404);
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
