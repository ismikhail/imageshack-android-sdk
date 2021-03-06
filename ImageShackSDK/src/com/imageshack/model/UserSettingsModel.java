package com.imageshack.model;

public class UserSettingsModel extends SimpleModel {

	// privacy settings
	private boolean isPrivateProfile;
	private boolean isPrivateDefaultImages;
	private boolean isPrivateDefaultAlbums;
	private boolean isFollowingAllowed;

	// email notifications settings
	private boolean isEnabledUploadNotifications;
	private boolean isEnabledCommentsNotifications;
	private boolean isEnabledLikesNotifications;
	private boolean isEnabledFollowingNotifications;
	
	// marketing
	private boolean usageTips;
	private boolean newFeatureNotification;
	private boolean specialOffers;

	/**
	 * Constructor
	 */
	public UserSettingsModel() {
		isPrivateProfile = false;
		isPrivateDefaultImages = false;
		isPrivateDefaultAlbums = false;
		isFollowingAllowed = false;
		isEnabledUploadNotifications = true;
		isEnabledCommentsNotifications = true;
		isEnabledLikesNotifications = true;
		isEnabledFollowingNotifications = true;
	}

	/**
	 * @return the isPrivateProfile
	 */
	public boolean isPrivateProfile() {
		return isPrivateProfile;
	}

	/**
	 * @param isPrivateProfile
	 *            the isPrivateProfile to set
	 */
	public void setPrivateProfile(boolean isPrivateProfile) {
		this.isPrivateProfile = isPrivateProfile;
	}

	/**
	 * @return the isPrivateDefaultImages
	 */
	public boolean isPrivateDefaultImages() {
		return isPrivateDefaultImages;
	}

	/**
	 * @param isPrivateDefaultImages
	 *            the isPrivateDefaultImages to set
	 */
	public void setPrivateDefaultImages(boolean isPrivateDefaultImages) {
		this.isPrivateDefaultImages = isPrivateDefaultImages;
	}

	/**
	 * @return the isPrivateDefaultAlbums
	 */
	public boolean isPrivateDefaultAlbums() {
		return isPrivateDefaultAlbums;
	}

	/**
	 * @param isPrivateDefaultAlbums
	 *            the isPrivateDefaultAlbums to set
	 */
	public void setPrivateDefaultAlbums(boolean isPrivateDefaultAlbums) {
		this.isPrivateDefaultAlbums = isPrivateDefaultAlbums;
	}

	/**
	 * @return the isFollowingAllowed
	 */
	public boolean isFollowingAllowed() {
		return isFollowingAllowed;
	}

	/**
	 * @param isFollowingAllowed
	 *            the isFollowingAllowed to set
	 */
	public void setFollowingAllowed(boolean isFollowingAllowed) {
		this.isFollowingAllowed = isFollowingAllowed;
	}

	/**
	 * @return the isEnabledUploadNotifications
	 */
	public boolean isEnabledUploadNotifications() {
		return isEnabledUploadNotifications;
	}

	/**
	 * @param isEnabledUploadNotifications
	 *            the isEnabledUploadNotifications to set
	 */
	public void setEnabledUploadNotifications(
			boolean isEnabledUploadNotifications) {
		this.isEnabledUploadNotifications = isEnabledUploadNotifications;
	}

	/**
	 * @return the isEnabledCommentsNotifications
	 */
	public boolean isEnabledCommentsNotifications() {
		return isEnabledCommentsNotifications;
	}

	/**
	 * @param isEnabledCommentsNotifications
	 *            the isEnabledCommentsNotifications to set
	 */
	public void setEnabledCommentsNotifications(
			boolean isEnabledCommentsNotifications) {
		this.isEnabledCommentsNotifications = isEnabledCommentsNotifications;
	}

	/**
	 * @return the isEnableLikesNotifications
	 */
	public boolean isEnabledLikesNotifications() {
		return isEnabledLikesNotifications;
	}

	/**
	 * @param isEnableLikesNotifications
	 *            the isEnableLikesNotifications to set
	 */
	public void setEnabledLikesNotifications(boolean isEnableLikesNotifications) {
		this.isEnabledLikesNotifications = isEnableLikesNotifications;
	}

	/**
	 * @return the isEnabledFollowingNotifications
	 */
	public boolean isEnabledFollowingNotifications() {
		return isEnabledFollowingNotifications;
	}

	/**
	 * @param isEnabledFollowingNotifications
	 *            the isEnabledFollowingNotifications to set
	 */
	public void setEnabledFollowingNotifications(
			boolean isEnabledFollowingNotifications) {
		this.isEnabledFollowingNotifications = isEnabledFollowingNotifications;
	}
	
	/**
	 * @return the usageTips
	 */
	public boolean isUsageTips() {
		return usageTips;
	}

	/**
	 * @param usageTips the usageTips to set
	 */
	public void setUsageTips(boolean usageTips) {
		this.usageTips = usageTips;
	}

	/**
	 * @return the newFeatureNotification
	 */
	public boolean isNewFeatureNotification() {
		return newFeatureNotification;
	}

	/**
	 * @param newFeatureNotification the newFeatureNotification to set
	 */
	public void setNewFeatureNotification(boolean newFeatureNotification) {
		this.newFeatureNotification = newFeatureNotification;
	}

	/**
	 * @return the specialOffers
	 */
	public boolean isSpecialOffers() {
		return specialOffers;
	}

	/**
	 * @param specialOffers the specialOffers to set
	 */
	public void setSpecialOffers(boolean specialOffers) {
		this.specialOffers = specialOffers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserSettingsModel [success = " + success + ", error = " + error
				+ ", processTime = " + processTime + ", isPrivateProfile = "
				+ isPrivateProfile + ", isPrivateDefaultImages = "
				+ isPrivateDefaultImages + ", isPrivateDefaultAlbums = "
				+ isPrivateDefaultAlbums + ", isFollowingAllowed = "
				+ isFollowingAllowed + ", isEnabledUploadNotifications = "
				+ isEnabledUploadNotifications
				+ ", isEnabledCommentsNotifications = "
				+ isEnabledCommentsNotifications
				+ ", isEnabledLikesNotifications = "
				+ isEnabledLikesNotifications
				+ ", isEnabledFollowingNotifications = "
				+ isEnabledFollowingNotifications
				+ ", usageTips = " + usageTips
				+ ", newFeatureNotification = " + newFeatureNotification
				+ ", specialOffers = " + specialOffers + "]";
	}

}
