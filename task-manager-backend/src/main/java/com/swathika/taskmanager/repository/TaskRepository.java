package com.swathika.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.swathika.taskmanager.entities.Task;


public interface TaskRepository extends JpaRepository<Task, Long> {

	

}
