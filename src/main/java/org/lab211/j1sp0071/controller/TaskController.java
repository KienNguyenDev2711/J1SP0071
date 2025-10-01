package org.lab211.j1sp0071.controller;

import org.lab211.j1sp0071.service.TaskService;
import org.springframework.stereotype.Component;

/**
 * Thin controller to provide TaskService to the console layer.
 */
@Component
public class TaskController {
	private final TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	public TaskService getTaskService() {
		return taskService;
	}
}


