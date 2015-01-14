package com.imageshack.model;

public abstract class AbstractModel implements
		ModelInterface {
	
	protected boolean success;
	protected String error;
	protected int processTime;
	
	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}
	/**
	 * @return the processTime
	 */
	public int getProcessTime() {
		return processTime;
	}
	/**
	 * @param processTime the processTime to set
	 */
	public void setProcessTime(int processTime) {
		this.processTime = processTime;
	}
	
}
