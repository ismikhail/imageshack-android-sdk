package com.imageshack.model;

public class ActivitiesCountModel extends AbstractModel {

	protected int count;

	public ActivitiesCountModel() {
		success = true;
		error = null;
		processTime = 0;
		count = 0;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
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
	 * @param processTime
	 *            the processTime to set
	 */
	public void setProcessTime(int processTime) {
		this.processTime = processTime;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ActivitiesCountModel [success = " + success + ", error = "
				+ error + ", processTime = " + processTime + ", count = " + count
				+ "]";
	}

}
