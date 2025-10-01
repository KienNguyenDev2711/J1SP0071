package org.kiennguyenfpt.j1sp0071.service;

import org.kiennguyenfpt.j1sp0071.constants.TaskType;
import org.kiennguyenfpt.j1sp0071.dto.TaskDto;
import org.kiennguyenfpt.j1sp0071.model.Task;
import org.kiennguyenfpt.j1sp0071.util.DateTimeUtil;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * In-memory implementation of TaskService with input validation.
 */
@Service
public class TaskServiceImpl implements TaskService {
	private final List<Task> tasks = new ArrayList<>();

	@Override
	public int addTask(String requirementName, String assignee, String reviewer, String taskTypeID, String date, String planFrom, String planTo) throws Exception {
		try {
			validateNonEmpty(requirementName, "Requirement Name");
			validateNonEmpty(assignee, "Assignee");
			validateNonEmpty(reviewer, "Reviewer");
			int typeId = parseIntStrict(taskTypeID, "Task Type ID");
			TaskType type = TaskType.fromId(typeId);
			if (type == null) throw new IllegalArgumentException("Task Type must be in range 1-4");
			Date parsedDate = DateTimeUtil.parseDateStrict(date);
			double from = parseDoubleHalfHour(planFrom, "Plan From");
			double to = parseDoubleHalfHour(planTo, "Plan To");
			validateTimeWindow(from, to);

			Task task = new Task();
			int id = tasks.isEmpty() ? 1 : tasks.get(tasks.size() - 1).getId() + 1;
			task.setId(id);
			task.setTaskType(type);
			task.setRequirementName(requirementName.trim());
			task.setDate(parsedDate);
			task.setPlanFrom(from);
			task.setPlanTo(to);
			task.setAssignee(assignee.trim());
			task.setReviewer(reviewer.trim());

			tasks.add(task);
			return id;
		} catch (NullPointerException | ParseException | IllegalArgumentException ex) {
			throw ex;
		}
	}

	@Override
	public void deleteTask(String id) throws Exception {
		try {
			int parsedId = parseIntStrict(id, "ID");
			Optional<Task> toRemove = tasks.stream().filter(t -> t.getId() == parsedId).findFirst();
			if (toRemove.isEmpty()) throw new IllegalArgumentException("ID does not exist");
			tasks.remove(toRemove.get());
		} catch (NullPointerException | IllegalArgumentException ex) {
			throw ex;
		}
	}

	@Override
	public List<TaskDto> getDataTasks() {
		List<TaskDto> result = new ArrayList<>();
		List<Task> copy = new ArrayList<>(tasks);
		copy.sort(Comparator.comparingInt(Task::getId));
		for (Task t : copy) {
			TaskDto dto = new TaskDto();
			dto.setId(t.getId());
			dto.setTaskType(t.getTaskType().getDisplayName());
			dto.setRequirementName(t.getRequirementName());
			dto.setDate(DateTimeUtil.formatDate(t.getDate()));
			dto.setPlanFrom(t.getPlanFrom());
			dto.setPlanTo(t.getPlanTo());
			dto.setAssignee(t.getAssignee());
			dto.setReviewer(t.getReviewer());
			result.add(dto);
		}
		return result;
	}

	private static void validateNonEmpty(String value, String fieldName) {
		if (value == null) throw new NullPointerException(fieldName + " is null");
		if (value.trim().isEmpty()) throw new IllegalArgumentException(fieldName + " is empty");
	}

	private static int parseIntStrict(String value, String fieldName) {
		if (value == null) throw new NullPointerException(fieldName + " is null");
		return Integer.valueOf(value.trim());
	}

	private static double parseDoubleHalfHour(String value, String fieldName) {
		if (value == null) throw new NullPointerException(fieldName + " is null");
		double d = Double.valueOf(value.trim());
		// normalize to nearest 0.5 step and verify exactness within epsilon
		double doubled = d * 2.0;
		long rounded = Math.round(doubled);
		if (Math.abs(doubled - rounded) > 1e-9) {
			throw new IllegalArgumentException(fieldName + " must be on half-hour increments (.0 or .5)");
		}
		return rounded / 2.0;
	}

	private static void validateTimeWindow(double from, double to) {
		if (!(from >= 8.0 && to <= 17.5)) {
			throw new IllegalArgumentException("Time must be within 8.0 to 17.5");
		}
		if (!(from < to)) {
			throw new IllegalArgumentException("Plan From must be less than Plan To");
		}
	}
}


