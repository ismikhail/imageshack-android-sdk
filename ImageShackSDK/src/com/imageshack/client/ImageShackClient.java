package com.imageshack.client;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.imageshack.response.ResponseListener;

public class ImageShackClient {

	private ActivitiesClient activitiesClient;
	private AlbumsClient albumsClient;
	private AuthClient authClient;
	private ImagesClient imagesClient;
	private UsersClient userClient;
	private CommentsClient commentsClient;

	/**
	 * Use this constructor to authenticate or register users
	 * 
	 * @param apiKey
	 *            the ImageShack developer key can be obtained at
	 *            https://imageshack.us/api_request/
	 */
	public ImageShackClient(String apiKey) {
		authClient = new AuthClient(apiKey);
	}

	/**
	 * Used for all API calls except authentication and registration
	 * 
	 * @param apiKey
	 *            the ImageShack developer key can be obtained at
	 *            https://imageshack.us/api_request/
	 * @param authToken
	 *            the authentication token obtained from auth method.
	 */
	public ImageShackClient(String apiKey, String authToken) {
		activitiesClient = new ActivitiesClient(apiKey, authToken);
		albumsClient = new AlbumsClient(apiKey, authToken);
		commentsClient = new CommentsClient(apiKey, authToken);
		imagesClient = new ImagesClient(apiKey, authToken);
		userClient = new UsersClient(apiKey, authToken);
	}

	// Start authentication API

	/**
	 * Authenticate with ImageShack
	 * 
	 * @param username
	 *            the username or email
	 * @param password
	 *            the password
	 * @param listener
	 */
	public void login(String username, String password,
			ResponseListener listener) {
		authClient.login(username, password, listener);
	}

	/**
	 * Create a new ImageShack user.
	 * 
	 * @param email
	 *            the email, required
	 * @param username
	 *            the username, optional
	 * @param password
	 *            the passowrd, required
	 * @param listener
	 *            the response listener that is notified when async http request
	 *            is completed
	 */
	public void register(String email, String username, String password,
			ResponseListener listener) {
		authClient.register(email, username, password, listener);
	}

	/**
	 * Checks whether the given email address is registered with ImageShack.
	 * 
	 * @param email
	 * @param listener
	 */
	public void emailExists(String email, ResponseListener listener) {
		authClient.exists(email, listener);
	}

	/**
	 * Send password reset instructions to the given username/email. Either
	 * email or username is needed.
	 * 
	 * @param username
	 * @param email
	 * @param listener
	 */
	public void forgotPassword(String username, String email,
			ResponseListener listener) {
		authClient.forgotPassword(username, email, listener);
	}

	// Start albums API

	/**
	 * Fetch album data
	 * 
	 * @param albumId
	 *            the album id
	 * @param listener
	 *            the listener for async http response
	 */
	public void getAlbum(String albumId, ResponseListener listener) {
		albumsClient.get(albumId, listener);
	}

	/**
	 * Fetch user album
	 * 
	 * @param limit
	 *            the max number of albums to return per request
	 * @param offset
	 *            the offset for pagination
	 * @param listener
	 *            the listener for async http response
	 */
	public void getAlbums(Integer limit, Integer offset,
			ResponseListener listener) {
		albumsClient.get(limit, offset, listener);
	}

	/**
	 * Create an album
	 * 
	 * @param title
	 *            album title, required
	 * @param description
	 *            album description, optional
	 * @param isPublic
	 *            false will make the album private, the default is true,
	 *            optional
	 * @param imageIds
	 *            list of images to be added to the album, optional
	 * @param listener
	 */
	public void createAlbum(String title, String description, Boolean isPublic,
			ArrayList<String> imageIds, ResponseListener listener) {
		albumsClient.create(title, description, isPublic, imageIds, listener);
	}

	/**
	 * Update album
	 * 
	 * @param title
	 *            new title, optional
	 * @param description
	 *            new description, optional
	 * @param isPublic
	 *            false will make the album private, optional
	 * @param listener
	 */
	public void updateAlbum(String albumId, String title, String description,
			Boolean isPublic, ResponseListener listener) {
		albumsClient.update(albumId, title, description, isPublic, listener);
	}

	/**
	 * Add images to an album
	 * 
	 * @param albumId
	 *            the album id, required
	 * @param imageIds
	 *            list of image ids, required
	 * @param listener
	 */
	public void addImagesToAlbum(String albumId, ArrayList<String> imageIds,
			ResponseListener listener) {
		albumsClient.add(albumId, imageIds, listener);
	}

	/**
	 * Delete images to an album
	 * 
	 * @param albumId
	 *            the album id, required
	 * @param imageIds
	 *            list of image ids, required
	 * @param listener
	 */
	public void deleteImagesFromAlbum(String albumId,
			ArrayList<String> imageIds, ResponseListener listener) {
		albumsClient.delete(albumId, imageIds, listener);
	}

	/**
	 * Remove entire album and its contents
	 * 
	 * @param albumId
	 *            the album id
	 * @param listener
	 */
	public void removeAlbum(String albumId, ResponseListener listener) {
		albumsClient.remove(albumId, listener);
	}

	// Start images API

	/**
	 * Fetch image data
	 * 
	 * @param imageId
	 *            the image id
	 * @param listener
	 *            the listener for async http response
	 */
	public void getImage(String imageId, ResponseListener listener) {
		imagesClient.get(imageId, listener);
	}

	/**
	 * Fetch user images
	 * 
	 * @param limit
	 *            the max number of images to return per request
	 * @param offset
	 *            the offset for pagination
	 * @param listener
	 *            the listener for async http response
	 */
	public void getImages(Integer limit, Integer offset,
			ResponseListener listener) {
		imagesClient.get(limit, offset, listener);
	}

	/**
	 * Upload image
	 * 
	 * @param imageURI
	 *            the location of the image in your device, required
	 * @param tags
	 *            array of tags, optional
	 * @param album
	 *            the album id or title to upload the image into, optional
	 * @param title
	 *            image title, optional
	 * @param commentsDisabled
	 *            , optional
	 * @param isPublic
	 *            false will make the image private
	 * @param listener
	 *            the listener for async http response
	 */
	public void uploadImage(String imageURI, String[] tags, String album,
			String title, Boolean commentsDisabled, Boolean isPublic,
			ResponseListener listener) throws FileNotFoundException {
		imagesClient.upload(imageURI, tags, album, title, commentsDisabled,
				isPublic, listener);
	}

	/**
	 * Delete images
	 * 
	 * @param imageIds
	 *            list of image ids
	 * @param listener
	 */
	public void deleteImages(ArrayList<String> imageIds,
			ResponseListener listener) {
		imagesClient.delete(imageIds, listener);
	}

	/**
	 * Like a given image
	 * 
	 * @param server
	 *            image server
	 * @param filename
	 *            image filename
	 * @param listener
	 */
	public void likeImage(String server, String filename,
			ResponseListener listener) {
		imagesClient.like(server, filename, listener);
	}

	/**
	 * Unlike a given image
	 * 
	 * @param server
	 *            image server
	 * @param filename
	 *            image filename
	 * @param listener
	 */
	public void unlikeImage(String server, String filename,
			ResponseListener listener) {
		imagesClient.unlike(server, filename, listener);
	}

	/**
	 * Rotates an image by a given angle
	 * 
	 * @param imageId
	 *            the image id
	 * @param angle
	 *            the angle to rotate; accepted values are 90, 180 and 270
	 * @param listener
	 */
	public void rotateImage(String imageId, Integer angle,
			ResponseListener listener) {
		imagesClient.rotate(imageId, angle, listener);
	}

	/**
	 * Update image properties
	 * 
	 * @param imageId
	 *            the image id to be updated, required
	 * @param title
	 *            new title, optional
	 * @param description
	 *            new description, optional
	 * @param tags
	 *            new tags, optional
	 * @param originalFilename
	 *            new original filename, optional
	 * @param isPublic
	 *            set to public/private, optional
	 * @param isCommentsDisabled
	 *            disable/enable comments, optional
	 * @param listener
	 */
	public void updateImage(String imageId, String title, String description,
			ArrayList<String> tags, String originalFilename, Boolean isPublic,
			Boolean isCommentsDisabled, ResponseListener listener) {
		imagesClient.update(imageId, title, description, tags,
				originalFilename, isPublic, isCommentsDisabled, listener);
	}

	/**
	 * Retrieves a list of previous images starting at the given image id.
	 * 
	 * @param imageId
	 *            the image id
	 * @param limit
	 *            default 10
	 * @param offset
	 *            default 0
	 * @param listener
	 */
	public void getPrevImages(String imageId, Integer limit, Integer offset,
			ResponseListener listener) {
		imagesClient.getPrev(imageId, limit, offset, listener);
	}

	/**
	 * Retrieves a list of next images starting at the given image id.
	 * 
	 * @param imageId
	 *            the image id
	 * @param limit
	 *            default 10
	 * @param offset
	 *            default 0
	 * @param listener
	 */
	public void getNextImages(String imageId, Integer limit, Integer offset,
			ResponseListener listener) {
		imagesClient.getNext(imageId, limit, offset, listener);
	}

	/**
	 * Retrieves a list of users who liked the give image.
	 * 
	 * @param server
	 *            image server id
	 * @param filename
	 *            image filename
	 * @param limit
	 *            limit likes
	 * @param offset
	 *            offset likes
	 * @param listener
	 */
	public void getImageLikes(String server, String filename, Integer limit,
			String offset, ResponseListener listener) {
		imagesClient.getLikes(server, filename, limit, offset, listener);
	}

	/**
	 * Retrieves a list of tags associated with a given image.
	 * 
	 * @param imageId
	 *            the image id
	 * @param listener
	 */
	public void getImageTags(String imageId, ResponseListener listener) {
		imagesClient.getTags(imageId, listener);
	}

	/**
	 * Delete image tags.
	 * 
	 * @param imageId
	 *            the image id
	 * @param tags
	 *            tags to delete
	 * @param listener
	 */
	public void deleteImageTags(String imageId, ArrayList<String> tags,
			ResponseListener listener) {
		imagesClient.deleteTags(imageId, tags, listener);
	}

	// Start comments API

	/**
	 * Get list of comments for a given image
	 * 
	 * @param imageId
	 *            the image id
	 * @param limit
	 *            max number of comments per call, default is 10
	 * @param offset
	 *            offset for pagination
	 * @param listener
	 */
	public void getImageComments(String imageId, Integer limit, String offset,
			ResponseListener listener) {
		commentsClient.get(imageId, limit, offset, listener);
	}

	/**
	 * Add a comment to an image
	 * 
	 * @param imageId
	 *            the image id, required
	 * @param comment
	 *            the comment, required
	 * @param source
	 *            the source, can be the app name, optional
	 * @param listener
	 */
	public void addImageComment(String imageId, String comment, String source,
			ResponseListener listener) {
		commentsClient.add(imageId, comment, source, listener);
	}

	/**
	 * Delete comment
	 * 
	 * @param imageId
	 *            the image id
	 * @param commentId
	 *            the comment id
	 * @param listener
	 */
	public void deleteImageComment(String imageId, String commentId,
			ResponseListener listener) {
		commentsClient.delete(commentId, listener);
	}

	// Start user API

	/**
	 * Fetches user information from ImageShack and returns UserModel. If
	 * username is null, then returns the UserModel of the authenticated user.
	 * 
	 * @param username
	 *            the username of user to fetch
	 * @param imageLimit
	 *            the image limit for latest images, optional
	 * @param listener
	 *            the response listener that is notified when async http request
	 *            is completed
	 */
	public void getUser(String username, Integer imageLimit,
			ResponseListener listener) {
		userClient.get(username, imageLimit, listener);
	}

	/**
	 * Update logged in user
	 * 
	 * @param username
	 *            the current username, optional
	 * @param email
	 *            new email, optional
	 * @param newUsername
	 *            new username, optional
	 * @param password
	 *            new password, optional
	 * @param firstName
	 *            new first name, optional
	 * @param lastName
	 *            new last name, optional
	 * @param gender
	 *            new gender, optional
	 * @param location
	 *            new location, optional
	 * @param description
	 *            new description, optional
	 * @param avatarServer
	 *            new avatar server, optional
	 * @param avatarFilename
	 *            new avatar filename, optional
	 * @param listener
	 *            the listener for async http response
	 */
	public void updateUser(String username, String email, String password,
			String firstName, String lastName, String gender, String location,
			String description, String avatarServer, String avatarFilename,
			ResponseListener listener) {
		userClient.update(username, email, password, firstName, lastName,
				gender, location, description, avatarServer, avatarFilename,
				listener);
	}

	/**
	 * Delete logged in user
	 * 
	 * @param listener
	 *            the listener for async http response
	 */
	public void deleteUser(ResponseListener listener) {
		userClient.delete(listener);
	}

	/**
	 * Get user settings
	 * 
	 * @param listener
	 *            the listener for async http response
	 */
	public void getUserSettings(ResponseListener listener) {
		userClient.getSettings(listener);
	}

	/**
	 * Update user settings
	 * 
	 * @param isPrivateDefaultImages
	 *            make new images private by default
	 * @param isPrivateDefaultAlbums
	 *            make new albums private by default
	 * @param isFollowingAllowed
	 *            opt out of following feature
	 * @param enabledUploadNotifications
	 *            enable/disable new uploads email notifications
	 * @param enableCommentsNotifications
	 *            enable/disable comments email notifications
	 * @param enableLikesNotifications
	 *            enable/disable likes email notifications
	 * @param enableFollowingNotifications
	 *            enable/disable new followers email notifications
	 * @param listener
	 */
	public void updateSettings(Boolean isPrivateDefaultImages,
			Boolean isPrivateDefaultAlbums, Boolean isFollowingAllowed,
			Boolean enabledUploadNotifications,
			Boolean enableCommentsNotifications,
			Boolean enableLikesNotifications,
			Boolean enableFollowingNotifications, ResponseListener listener) {
		userClient.updateSettings(isPrivateDefaultImages,
				isPrivateDefaultAlbums, isFollowingAllowed,
				enabledUploadNotifications, enableCommentsNotifications,
				enableLikesNotifications, enableFollowingNotifications,
				listener);
	}

	/**
	 * Get a list of liked images buy a given user.
	 * 
	 * @param username
	 * @param limit
	 * @param offset
	 * @param listener
	 */
	public void getUserLikedImages(String username, Integer limit,
			String offset, ResponseListener listener) {
		userClient.getLikedImages(username, limit, offset, listener);
	}

	/**
	 * Get a list of following images.
	 * 
	 * @param limit
	 * @param offset
	 * @param listener
	 */
	public void getUserFollowingImages(String username, Integer limit,
			String offset, ResponseListener listener) {
		userClient.getFollowingImages(username, limit, offset, listener);
	}

	/**
	 * Get referral URL. Three successful referrals grant 1 month of premium
	 * service.
	 * 
	 * @param listener
	 */
	public void getReferralUrl(ResponseListener listener) {
		userClient.getReferralUrl(listener);
	}

	// Start activities API

	/**
	 * Fetch user activities.
	 * 
	 * @param limit
	 *            the limit
	 * @param offset
	 *            the offset
	 * @param listener
	 *            Async http response listener
	 */
	public void getActivities(Integer limit, Integer offset,
			ResponseListener listener) {
		activitiesClient.get(limit, offset, listener);
	}

	/**
	 * Fetch the number of new notifications.
	 * 
	 * @param listener
	 *            Async http response listener
	 */
	public void getNewActivitiesCount(ResponseListener listener) {
		activitiesClient.getNewCount(listener);
	}

	/**
	 * Mark all notifications as viewed.
	 * 
	 * @param listener
	 *            Async http response listener
	 */
	public void markActivitiesAsViewed(ResponseListener listener) {
		activitiesClient.markAsViewed(listener);
	}

}