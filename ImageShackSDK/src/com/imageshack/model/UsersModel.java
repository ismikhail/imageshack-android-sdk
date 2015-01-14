package com.imageshack.model;

import java.util.ArrayList;

public class UsersModel extends AbstractModel {

	private ArrayList<UserModel> users;
	private String offset;
	private int limit;
	private int total;

	public UsersModel() {
		users = new ArrayList<UserModel>();
		processTime = 0;
		offset = null;
		limit = 0;
		total = 0;
		success = true;
		error = null;
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

	/**
	 * @return the users
	 */
	public ArrayList<UserModel> getUsers() {
		return users;
	}

	/**
	 * @param users
	 *            the users to set
	 */
	public void setUsers(ArrayList<UserModel> users) {
		this.users = users;
	}

	/**
	 * @return the offset
	 */
	public String getOffset() {
		return offset;
	}

	/**
	 * @param offset
	 *            the offset to set
	 */
	public void setOffset(String offset) {
		this.offset = offset;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UsersModel [offset = " + offset + ", limit = " + limit
				+ ", processTime = " + processTime + ", success = " + success
				+ ", error = " + error + ", users = " + users + "]";
	}

}
