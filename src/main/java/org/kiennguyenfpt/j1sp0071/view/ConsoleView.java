package org.kiennguyenfpt.j1sp0071.view;

import org.kiennguyenfpt.j1sp0071.dto.TaskDto;
import org.kiennguyenfpt.j1sp0071.service.TaskService;

import java.util.List;
import java.util.Scanner;

/**
 * Console-based UI to interact with the Task program.
 */
public class ConsoleView {
	private final TaskService taskService;
	private final Scanner scanner = new Scanner(System.in);

	public ConsoleView(TaskService taskService) {
		this.taskService = taskService;
	}

	public void run() {
		while (true) {
			printMenu();
			String choice = scanner.nextLine();
			switch (choice) {
				case "1":
					addTaskFlow();
					break;
				case "2":
					deleteTaskFlow();
					break;
				case "3":
					showTasksFlow();
					break;
				case "4":
					System.out.println("Exiting...");
					return;
				default:
					System.out.println("Invalid option. Try again.");
			}
		}
	}

	private void printMenu() {
		System.out.println("====== Task program ======");
		System.out.println("1. Add Task");
		System.out.println("2. Delete Task");
		System.out.println("3. Show Task");
		System.out.println("4. Exit");
		System.out.print("Your choice: ");
	}

	private void addTaskFlow() {
		try {
			System.out.print("Requirement Name: ");
			String requirementName = scanner.nextLine();
			System.out.print("Task Type (1-4): ");
			String type = scanner.nextLine();
			System.out.print("Date (dd-MM-yyyy): ");
			String date = scanner.nextLine();
			System.out.print("From (8.0 .. 17.5 step .5): ");
			String from = scanner.nextLine();
			System.out.print("To (8.0 .. 17.5 step .5): ");
			String to = scanner.nextLine();
			System.out.print("Assignee: ");
			String assignee = scanner.nextLine();
			System.out.print("Reviewer: ");
			String reviewer = scanner.nextLine();

			int id = taskService.addTask(requirementName, assignee, reviewer, type, date, from, to);
			System.out.println("Added successfully! ID: " + id);
		} catch (Exception ex) {
			System.out.println("Add failed: " + ex.getMessage());
		}
	}

	private void deleteTaskFlow() {
		try {
			System.out.print("Enter task ID to delete: ");
			String id = scanner.nextLine();
			taskService.deleteTask(id);
			System.out.println("Deleted successfully.");
		} catch (Exception ex) {
			System.out.println("Delete failed: " + ex.getMessage());
		}
	}

	private void showTasksFlow() {
		List<TaskDto> list = taskService.getDataTasks();
		if (list.isEmpty()) {
			System.out.println("No tasks found.");
			return;
		}
		System.out.printf("%-5s%-15s%-20s%-15s%-10s%-10s%-15s%-15s%n", "ID", "Task Type", "Requirement Name", "Date", "From", "To", "Assignee", "Reviewer");
		for (TaskDto t : list) {
			System.out.printf("%-5d%-15s%-20s%-15s%-10.1f%-10.1f%-15s%-15s%n",
					t.getId(), t.getTaskType(), t.getRequirementName(), t.getDate(), t.getPlanFrom(), t.getPlanTo(), t.getAssignee(), t.getReviewer());
		}
	}
}


