package org.kiennguyenfpt.j1sp0071.service;

import org.kiennguyenfpt.j1sp0071.constants.TaskType;
import org.kiennguyenfpt.j1sp0071.dto.TaskDto;
import org.kiennguyenfpt.j1sp0071.model.Task;
import org.kiennguyenfpt.j1sp0071.model.Message;
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
	public int addTask(String requirementName, String assignee, String reviewer, String taskTypeID, String date, String planFrom, String planTo) {
		validateNonEmpty(requirementName, "Requirement Name");
		validateNonEmpty(assignee, "Assignee");
		validateNonEmpty(reviewer, "Reviewer");
		int typeId = parseIntStrict(taskTypeID, "Task Type ID");
		TaskType type = TaskType.fromId(typeId);
		if (type == null) throw new IllegalArgumentException(Message.ERR_TASKTYPE_RANGE);
		Date parsedDate;
		try {
			parsedDate = DateTimeUtil.parseDateStrict(date);
		} catch (ParseException pe) {
			throw new IllegalArgumentException(Message.ERR_DATE_FORMAT);
		}
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
	}

	@Override
	public void deleteTask(String id) {
		int parsedId = parseIntStrict(id, "ID");
		Optional<Task> toRemove = tasks.stream().filter(t -> t.getId() == parsedId).findFirst();
		if (toRemove.isEmpty()) throw new IllegalArgumentException(Message.ERR_ID_NOT_EXIST);
		tasks.remove(toRemove.get());
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
		if (value == null) throw new NullPointerException(String.format(Message.ERR_FIELD_NULL, fieldName));
		if (value.trim().isEmpty()) throw new IllegalArgumentException(fieldName + " is empty");
	}

	private static int parseIntStrict(String value, String fieldName) {
		if (value == null) throw new NullPointerException(String.format(Message.ERR_FIELD_NULL, fieldName));
		return Integer.parseInt(value.trim());
	}

	private static double parseDoubleHalfHour(String value, String fieldName) {
		if (value == null) throw new NullPointerException(String.format(Message.ERR_FIELD_NULL, fieldName));
		double d = Double.parseDouble(value.trim());
		// normalize to nearest 0.5 step and verify exactness within epsilon
		double doubled = d * 2.0;
		long rounded = Math.round(doubled);
		if (Math.abs(doubled - rounded) > 1e-9) {
			throw new IllegalArgumentException(Message.ERR_HALF_HOUR);
		}
		return rounded / 2.0;
	}

	private static void validateTimeWindow(double from, double to) {
		if (!(from >= 8.0 && to <= 17.5)) {
			throw new IllegalArgumentException(Message.ERR_TIME_WINDOW);
		}
		if (!(from < to)) {
			throw new IllegalArgumentException(Message.ERR_FROM_LT_TO);
		}
	}
}
