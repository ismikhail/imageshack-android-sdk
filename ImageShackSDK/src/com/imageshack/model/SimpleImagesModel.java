package com.imageshack.model;

import java.util.ArrayList;

public class SimpleImagesModel extends AbstractModel {

	private ArrayList<SimpleImageModel> images;

	public SimpleImagesModel() {
		images = new ArrayList<SimpleImageModel>();
		processTime = 0;
		success = true;
		error = null;
	}

	public ArrayList<SimpleImageModel> getImages() {
		return images;
	}

	public void setImages(ArrayList<SimpleImageModel> images) {
		this.images = images;
	}

	public void add(SimpleImageModel image) {
		images.add(image);
	}

	@Override
	public String toString() {
		return "SimpleImagesModel [processTime = " + processTime
				+ ", success = " + success + ", error = " + error
				+ ", images = " + images + "]";
	}

}
