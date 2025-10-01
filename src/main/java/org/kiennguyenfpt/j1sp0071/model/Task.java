package org.kiennguyenfpt.j1sp0071.model;

import org.kiennguyenfpt.j1sp0071.constants.TaskType;

import java.util.Date;

public class Task {
	private int id;
	private TaskType taskType;
	private String requirementName;
	private Date date;
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

	public TaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	public String getRequirementName() {
		return requirementName;
	}

	public void setRequirementName(String requirementName) {
		this.requirementName = requirementName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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


