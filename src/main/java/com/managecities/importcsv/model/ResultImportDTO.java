package com.managecities.importcsv.model;

public class ResultImportDTO {
	private String fileName;
	private String message;
	private Boolean status;

	public ResultImportDTO(String fileName, String message, Boolean status) {

		this.fileName = fileName;
		this.message = message;
		this.status = status;

	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
