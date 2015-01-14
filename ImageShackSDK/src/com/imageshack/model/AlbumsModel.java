package com.imageshack.model;

import java.util.ArrayList;

public class AlbumsModel extends AbstractModel {

	private ArrayList<AlbumModel> albums;
	private int limit;
	private int offset;
	private int total;

	public AlbumsModel() {
		albums = new ArrayList<AlbumModel>();
		processTime = 0;
		success = true;
		error = null;
	}

	/**
	 * @return the albums
	 */
	public ArrayList<AlbumModel> getAlbums() {
		return albums;
	}

	/**
	 * @param albums
	 *            the albums to set
	 */
	public void setAlbums(ArrayList<AlbumModel> albums) {
		this.albums = albums;
	}

	/**
	 * 
	 * @param album
	 *            the album to add
	 */
	public void addAlbum(AlbumModel album) {
		albums.add(album);
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
		return "AlbumsModel [processTime = " + processTime + ", success = "
				+ success + ", error = " + error + ", limit = " + limit
				+ ", offset = " + offset + ", total = " + total + ", albums = "
				+ albums + "]";
	}

}
