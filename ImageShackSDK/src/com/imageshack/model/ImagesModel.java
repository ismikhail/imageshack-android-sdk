package com.imageshack.model;

import java.util.ArrayList;

/**
 * @author msmirnov
 * 
 */
public class ImagesModel extends AbstractModel {

	private ArrayList<BasicImageModel> images;
	private int limit;
	private int offset;
	private int total;

	public ImagesModel() {
		images = new ArrayList<BasicImageModel>();
		processTime = 0;
		success = true;
		error = null;
	}

	/**
	 * Adds an image to the ArrayList
	 * 
	 * @param image
	 */
	public void add(BasicImageModel image) {
		images.add(image);
	}

	/**
	 * @return the images
	 */
	public ArrayList<BasicImageModel> getImages() {
		return images;
	}

	/**
	 * @param images
	 *            the images to set
	 */
	public void setImages(ArrayList<BasicImageModel> images) {
		this.images = images;
	}

	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * @param limit
	 *            the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * @param offset
	 *            the offset to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ImagesModel [processTime = " + processTime + ", success = "
				+ success + ", error = " + error + ", limit = " + limit
				+ ", offset = " + offset + ", total = " + total + ", images = "
				+ images + "]";
	}
}