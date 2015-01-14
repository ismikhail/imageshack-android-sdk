package com.imageshack.model;

public class SimpleModel extends AbstractModel {
	
	private boolean result;
	
	public SimpleModel() {
		success = true;
		error = null;
		processTime = 0;
		result = true;
	}

	/**
	 * @return the result
	 */
	public boolean isResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(boolean result) {
		this.result = result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SimpleModel [isSuccess = " + success + ", error = " + error
				+ ", processTime = " + processTime
				+ ", result = " + result + "]";
	}
	
}
