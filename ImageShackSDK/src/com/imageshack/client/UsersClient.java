package com.imageshack.client;

import android.util.Log;

import com.imageshack.constant.Const;
import com.imageshack.constant.Models;
import com.imageshack.response.ErrorHandler;
import com.imageshack.response.ImagesResponseHandler;
import com.imageshack.response.ResponseListener;
import com.imageshack.response.SimpleResponseHandler;
import com.imageshack.response.UsersResponseHandler;
import com.loopj.android.http.RequestParams;

public class UsersClient extends ImageShackAbstractClient {

	private static final String TAG = "UsersClient";
	private static final String API_ENDPOINT = ROUTE + Const.USER;
	private String authToken;

	public UsersClient(String apiKey, String authToken) {
		super(apiKey);
		this.authToken = authToken;
	}

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
	public void get(String username, Integer imageLimit,
			ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (imageLimit != null) {
			params.put(Const.IMAGE_LIMIT, imageLimit.toString());
		}

		String url = username == null ? API_ENDPOINT : API_ENDPOINT + "/"
				+ username;

		Log.d(TAG, "getUser: " + url);
		Log.d(TAG, params.toString());

		getInstance().get(url, params,
				new UsersResponseHandler(listener, Models.USER));
	}

	/**
	 * Update user
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
	public void update(String username, String email, String password,
			String firstName, String lastName, String gender, String location,
			String description, String avatarServer, String avatarFilename,
			ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (username != null) {
			params.put(Const.USERNMAE, username);
		}

		if (email != null) {
			params.put(Const.EMAIL, email);
		}

		if (password != null) {
			params.put(Const.PASSWORD, password);
		}

		if (firstName != null) {
			params.put(Const.FIRST_NAME, firstName);
		}

		if (lastName != null) {
			params.put(Const.LAST_NAME, lastName);
		}

		if (gender != null) {
			params.put(Const.GENDER, gender);
		}

		if (location != null) {
			params.put(Const.LOCATION, location);
		}

		if (description != null) {
			params.put(Const.DESCRIPTION, description);
		}

		if (avatarServer != null) {
			params.put(Const.AVATAR_SERVER, avatarServer);
		}

		if (avatarFilename != null) {
			params.put(Const.AVATAR_FILENAME, avatarFilename);
		}

		Log.d(TAG, "updateUser: " + API_ENDPOINT);
		Log.d(TAG, params.toString());

		getInstance().put(API_ENDPOINT, params,
				new SimpleResponseHandler(listener));
	}

	/**
	 * Delete logged in user
	 * 
	 * @param listener
	 *            the listener for async http response
	 */
	public void delete(ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		Log.d(TAG, "deleteUser: " + API_ENDPOINT);
		Log.d(TAG, params.toString());

		getInstance().delete(null, API_ENDPOINT, null, params,
				new SimpleResponseHandler(listener));
	}

	/**
	 * Get user settings
	 * 
	 * @param listener
	 *            the listener for async http response
	 */
	public void getSettings(ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		String url = String.format("%s/%s", API_ENDPOINT, Const.SETTINGS);

		Log.d(TAG, "getUserSettings: " + url);
		Log.d(TAG, params.toString());

		getInstance().get(url, params,
				new UsersResponseHandler(listener, Models.USER_SETTINGS));
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
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (isPrivateDefaultImages != null) {
			params.put(Const.PRIVATE_IMAGES, isPrivateDefaultImages.toString());
		}

		if (isPrivateDefaultAlbums != null) {
			params.put(Const.PRIVATE_ALBUMS, isPrivateDefaultAlbums);
		}

		if (isFollowingAllowed != null) {
			params.put(Const.PRIVATE_FOLLOWING, isFollowingAllowed);
		}

		if (enabledUploadNotifications != null) {
			params.put(Const.UPLOAD_NOTIFICATIONS, enabledUploadNotifications);
		}

		if (enableCommentsNotifications != null) {
			params.put(Const.COMMENTS_NOTIFICATIONS,
					enableCommentsNotifications);
		}

		if (enableLikesNotifications != null) {
			params.put(Const.LIKES_NOTIFICATIONS, enableLikesNotifications);
		}

		if (enableFollowingNotifications != null) {
			params.put(Const.FOLLOWING_NOTIFICATIONS, enableLikesNotifications);
		}

		String url = String.format("%s/%s", API_ENDPOINT, Const.SETTINGS);
		getInstance().put(url, params,
				new UsersResponseHandler(listener, Models.USER_SETTINGS));
	}

	/**
	 * Get a list of liked images buy a given user.
	 * 
	 * @param username
	 * @param limit
	 * @param offset
	 * @param listener
	 */
	public void getLikedImages(String username, Integer limit, String offset,
			ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (limit != null) {
			params.put(Const.LIMIT, limit.toString());
		}

		if (offset != null) {
			params.put(Const.OFFSET, offset);
		}

		if (username != null) {
			String url = String.format("%s/%s/%s/%s", API_ENDPOINT, username,
					Const.LIKED, Const.IMAGES);

			Log.d(TAG, url);
			Log.d(TAG, params.toString());

			getInstance().get(url, params,
					new ImagesResponseHandler(listener, Models.LIKED_IMAGES));
		} else {
			ErrorHandler.returnSimpleError(listener, Const.MISSING_PARAMS);
		}
	}

	/**
	 * Get a list of following images.
	 * 
	 * @param limit
	 * @param offset
	 * @param listener
	 */
	public void getFollowingImages(String username, Integer limit,
			String offset, ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (limit != null) {
			params.put(Const.LIMIT, limit.toString());
		}

		if (offset != null) {
			params.put(Const.OFFSET, offset);
		}

		if (username != null) {
			String url = String.format("%s/%s/%s/%s", API_ENDPOINT, username,
					Const.FOLLOWINGS, Const.IMAGES);

			Log.d(TAG, url);
			Log.d(TAG, params.toString());

			getInstance()
					.get(url,
							params,
							new ImagesResponseHandler(listener,
									Models.FOLLOWING_IMAGES));
		} else {
			ErrorHandler.returnSimpleError(listener, Const.MISSING_PARAMS);
		}
	}

	/**
	 * Get referral URL.
	 * 
	 * @param listener
	 */
	public void getReferralUrl(ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		String url = String.format("%s/%s", API_ENDPOINT, Const.REFERRAL);
		
		Log.d(TAG, url);
		Log.d(TAG, params.toString());

		getInstance().get(url, params,
				new UsersResponseHandler(listener, Models.REFERRAL_URL));
	}

}
