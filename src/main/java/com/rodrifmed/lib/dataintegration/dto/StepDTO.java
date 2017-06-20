package com.rodrifmed.lib.dataintegration.dto;

import java.util.List;

public class StepDTO {
	private String stepName;
	private List<String> stepDestinationNameList;
	private Long linesInput;
	private Long linesOutput;
	private Long linesRead;
	private Long linesRejected;
	private Long linesUpdated;

	public StepDTO() {
	}

	public String toString() {
		return this.getStepName();
	}

	public String getStepName() {
		return this.stepName == null ? "Problem: Step without name" : this.stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public List<String> getStepDestinationNameList() {
		return this.stepDestinationNameList;
	}

	public void setStepDestinationNameList(List<String> stepDestinationNameList) {
		this.stepDestinationNameList = stepDestinationNameList;
	}

	public Long getLinesInput() {
		return this.linesInput;
	}

	public void setLinesInput(Long linesInput) {
		this.linesInput = linesInput;
	}

	public Long getLinesOutput() {
		return this.linesOutput;
	}

	public void setLinesOutput(Long linesOutuput) {
		this.linesOutput = linesOutuput;
	}

	public Long getLinesRead() {
		return this.linesRead;
	}

	public void setLinesRead(Long linesRead) {
		this.linesRead = linesRead;
	}

	public Long getLinesRejected() {
		return this.linesRejected;
	}

	public void setLinesRejected(Long linesRejected) {
		this.linesRejected = linesRejected;
	}

	public Long getLinesUpdated() {
		return this.linesUpdated;
	}

	public void setLinesUpdated(Long linesUpdated) {
		this.linesUpdated = linesUpdated;
	}
}
