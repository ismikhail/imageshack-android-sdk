package com.imageshack.model;

import java.util.ArrayList;

public class TagsModel extends AbstractModel {

	private ArrayList<String> tags;
	
	public TagsModel() {
		processTime = 0;
		success = true;
		error = null;
		tags = new ArrayList<String>();
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
	
}
