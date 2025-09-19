package com.swathika.taskmanager.Service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import com.swathika.taskmanager.entities.Task;
import com.swathika.taskmanager.exceptions.TaskNotFoundException;
import com.swathika.taskmanager.repository.TaskRepository;


public class TaskService {
	
	
	@Autowired
	TaskRepository taskRepository;
	
   public List<Task> getAlltask(){
		
		List<Task> task=taskRepository.findAll();
		
		 return task; 
	}
	
   public Task createTask(Task task) {
		
		taskRepository.save(task);
		return task;
	}


   public Task updateTask( Long id,  Task taskDetails) {
		  

	    Optional<Task> task = taskRepository.findById(id);
	    if (task.isEmpty()) {
	        throw new TaskNotFoundException("Task with ID " + id + " not found");
	    }
	  
	 Task updatetask = task.get();
	 updatetask.setDescription( taskDetails.getDescription());
	 updatetask.setStatus(taskDetails.getStatus());
	 updatetask.setDueDate(taskDetails.getDueDate());
	 updatetask.setTitle(taskDetails.getTitle());
	
	return taskRepository.save(updatetask);    
	    
	
	}


	
   public  void  deleteTask(Long id) {
	    Optional<Task> deleteTask = taskRepository.findById(id);
        
	    if (deleteTask.isEmpty()) {
	        throw new TaskNotFoundException("Task with ID " + id + " not found");
	    }
	     
	    taskRepository.delete(deleteTask.get());
	    
	        
	}
	    
	
	

   public Task getTaskById(Long id) {
       return taskRepository.findById(id)
               .orElseThrow(() -> new TaskNotFoundException("Task not found with id " + id));
   }
	
	
	
	
	
	

}
