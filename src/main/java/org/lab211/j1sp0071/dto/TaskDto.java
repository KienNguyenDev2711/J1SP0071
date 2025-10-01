package org.lab211.j1sp0071.dto;

/**
 * Data Transfer Object for presenting task data in the view layer.
 */
public class TaskDto {
	private int id;
	private String taskType;
	private String requirementName;
	private String date;
	private double planFrom;
	private double planTo;
	private String assignee;
	private String reviewer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getRequirementName() {
		return requirementName;
	}

	public void setRequirementName(String requirementName) {
		this.requirementName = requirementName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getPlanFrom() {
		return planFrom;
	}

	public void setPlanFrom(double planFrom) {
		this.planFrom = planFrom;
	}

	public double getPlanTo() {
		return planTo;
	}

	public void setPlanTo(double planTo) {
		this.planTo = planTo;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}
}


