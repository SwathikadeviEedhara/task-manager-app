package com.swathika.taskmanager.exceptions;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.swathika.taskmanager.DTO.Response;
import com.swathika.taskmanager.entities.Task;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	
	@ExceptionHandler(TaskNotFoundException.class)
	public ResponseEntity<Response<String>> handleTaskNotFoundException(TaskNotFoundException ex) {
	    Response<String> response = new Response<>();
	    response.setStatusCode(404);
	    response.setMessage("Task not available");
	    response.setData(null);
	    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
	    String errorMessage = ex.getBindingResult()
	                            .getAllErrors()
	                            .stream()
	                            .map(ObjectError::getDefaultMessage)
	                            .collect(Collectors.joining(", "));
	    Response<String> response = new Response<>();
	    response.setStatusCode(400);
	    response.setMessage(errorMessage);
	    response.setData(null);
	    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	
	
	
	
	
	
	
	
	
}
