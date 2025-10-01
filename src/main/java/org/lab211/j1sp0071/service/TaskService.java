package org.lab211.j1sp0071.service;

import org.lab211.j1sp0071.dto.TaskDto;

import java.util.List;

/**
 * Application service for managing tasks: add, delete, and list.
 */
public interface TaskService {
	/**
	 * Add a new task with validation.
	 */
	int addTask(String requirementName, String assignee, String reviewer, String taskTypeID, String date, String planFrom, String planTo) throws Exception;

	/**
	 * Delete a task by identifier.
	 */
	void deleteTask(String id) throws Exception;

	/**
	 * Get all tasks prepared for display, sorted by id asc.
	 */
	List<TaskDto> getDataTasks();
}


