package com.imageshack.model;

import java.util.ArrayList;

public class UploadModel extends AbstractModel {

	private int passed;
	private int failed;
	private int total;
	private ArrayList<ImageModel> images;

	public UploadModel() {
		passed = 0;
		failed = 0;
		images = new ArrayList<ImageModel>();
		processTime = 0;
		success = true;
		error = null;
	}

	/**
	 * @return the passed
	 */
	public int getPassed() {
		return passed;
	}

	/**
	 * @param passed
	 *            the passed to set
	 */
	public void setPassed(int passed) {
		this.passed = passed;
	}

	/**
	 * @return the failed
	 */
	public int getFailed() {
		return failed;
	}

	/**
	 * @param failed
	 *            the failed to set
	 */
	public void setFailed(int failed) {
		this.failed = failed;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * @return the images
	 */
	public ArrayList<ImageModel> getImages() {
		return images;
	}

	/**
	 * @param images
	 *            the images to set
	 */
	public void setImages(ArrayList<ImageModel> images) {
		this.images = images;
	}

	/**
	 * @param add
	 *            image to ArrayList
	 */
	public void add(ImageModel image) {
		images.add(image);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UploadModel [procesTime = " + processTime + ", success = "
				+ success + ", error = " + error + ", passed = " + passed
				+ ", failed = " + failed + ", total = " + total + ", images = "
				+ images + "]";
	}

}
