package com.imageshack.model;

import java.util.ArrayList;

public class CommentsModel extends AbstractModel {
	
	private ArrayList<CommentModel> comments;
	private int limit;
	private String startOffset;
	private String endOffset;
	private int total;
	
	public CommentsModel() {
		comments = new ArrayList<CommentModel>();
		limit = 0;
		total = 0;
		startOffset = null;
		endOffset = null;
		processTime = 0;
		success = true;
		error = null;
	}

	/**
	 * @return the comments
	 */
	public ArrayList<CommentModel> getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(ArrayList<CommentModel> comments) {
		this.comments = comments;
	}
	
	/**
	 * Adds a comment to the ArrayList
	 * 
	 * @param comment
	 */
	public void add(CommentModel comment) {
		this.comments.add(comment);
	}

	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * @param limit the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * @return the startOffset
	 */
	public String getStartOffset() {
		return startOffset;
	}

	/**
	 * @param startOffset the startOffset to set
	 */
	public void setStartOffset(String startOffset) {
		this.startOffset = startOffset;
	}

	/**
	 * @return the endOffset
	 */
	public String getEndOffset() {
		return endOffset;
	}

	/**
	 * @param endOffset the endOffset to set
	 */
	public void setEndOffset(String endOffset) {
		this.endOffset = endOffset;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CommentsModel [limit = " + limit
				+ ", startOffset = " + startOffset + ", endOffset = " + endOffset
				+ ", total = " + total + ", processTime = " + processTime
				+ ", success = " + success + ", error = " + error +
				"comments = " + comments + "]";
	}

}
