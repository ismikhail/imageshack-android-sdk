package com.imageshack.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import android.util.Log;

import com.imageshack.constant.Const;
import com.imageshack.constant.Models;
import com.imageshack.response.ErrorHandler;
import com.imageshack.response.ImagesResponseHandler;
import com.imageshack.response.ResponseListener;
import com.imageshack.response.SimpleResponseHandler;
import com.imageshack.response.UsersResponseHandler;
import com.imageshack.utility.CommonFunctions;
import com.loopj.android.http.RequestParams;

public class ImagesClient extends ImageShackAbstractClient {

	private static final String TAG = "ImagesClient";
	private static final String API_ENDPOINT = ROUTE + Const.IMAGES;
	private String authToken;

	public ImagesClient(String apiKey, String authToken) {
		super(apiKey);
		this.authToken = authToken;
	}

	/**
	 * Fetch image data
	 * 
	 * @param imageId
	 *            the image id
	 * @param listener
	 *            the listener for async http response
	 */
	public void get(String imageId, ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (imageId != null) {
			String url = String.format("%s/%s", API_ENDPOINT, imageId);

			Log.d(TAG, "getImage: " + url);
			Log.d(TAG, params.toString());

			getInstance().get(url, params,
					new ImagesResponseHandler(listener, Models.IMAGE));
		} else {
			ErrorHandler.returnSimpleError(listener, Const.MISSING_PARAMS);
		}
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
	public void get(Integer limit, Integer offset, ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (limit != null) {
			params.put(Const.LIMIT, limit.toString());
		}

		if (offset != null) {
			params.put(Const.OFFSET, offset.toString());
		}

		Log.d(TAG, "getImages: " + API_ENDPOINT);
		Log.d(TAG, params.toString());

		getInstance().get(API_ENDPOINT, params,
				new ImagesResponseHandler(listener, Models.IMAGES));
	}

	/**
	 * Delete images
	 * 
	 * @param imageIds
	 *            list of image ids
	 * @param listener
	 */
	public void delete(ArrayList<String> imageIds, ResponseListener listener) {
		StringBuilder ids = new StringBuilder();
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (imageIds != null) {
			for (String id : imageIds) {
				ids.append(id).append(",");
			}
			params.put(Const.IDS, ids.toString());
		} else {
			ErrorHandler.returnSimpleError(listener, Const.MISSING_PARAMS);
		}

		Log.d(TAG, "deleteImages: " + API_ENDPOINT);
		Log.d(TAG, params.toString());

		getInstance().delete(null, API_ENDPOINT, null, params,
				new SimpleResponseHandler(listener));
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
	public void upload(String imageURI, String[] tags, String album,
			String title, Boolean commentsDisabled, Boolean isPublic,
			ResponseListener listener) throws FileNotFoundException {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		params.put("file", new File(imageURI));

		if (tags != null) {
			StringBuilder tagsCsv = new StringBuilder();

			for (int i = 0; i < tags.length; i++) {
				tagsCsv.append(tags[i]);
				if (i < tags.length - 1) {
					tagsCsv.append(",");
				}
			}

			params.put(Const.TAGS, tagsCsv.toString());
		}

		if (album != null) {
			params.put(Const.ALBUM, album);
		}

		if (title != null) {
			params.put(Const.TITLE, title);
		}

		if (commentsDisabled != null) {
			params.put(Const.COMMENTS_DISABLED, commentsDisabled.toString());
		}

		if (isPublic != null) {
			params.put(Const.PUBLIC, isPublic.toString());
		}

		Log.d(TAG, "upload: " + API_ENDPOINT);
		Log.d(TAG, params.toString());

		getInstance().post(API_ENDPOINT, params,
				new ImagesResponseHandler(listener, Models.UPLOAD));
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
	public void like(String server, String filename, ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (server != null && filename != null) {
			String url = String.format("%s/%s/%s/%s", API_ENDPOINT, server,
					filename, Const.LIKES);

			Log.d(TAG, "like: " + url);
			Log.d(TAG, params.toString());

			getInstance()
					.post(url, params, new SimpleResponseHandler(listener));
		} else {
			ErrorHandler.returnSimpleError(listener, Const.MISSING_PARAMS);
		}
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
	public void unlike(String server, String filename, ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (server != null && filename != null) {
			String url = String.format("%s/%s/%s/%s", API_ENDPOINT, server,
					filename, Const.UNLIKE);

			Log.d(TAG, "unlike: " + url);
			Log.d(TAG, params.toString());

			getInstance()
					.post(url, params, new SimpleResponseHandler(listener));
		} else {
			ErrorHandler.returnSimpleError(listener, Const.MISSING_PARAMS);
		}
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
	public void rotate(String imageId, Integer angle, ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (imageId != null && angle != null) {
			params.put(Const.ID, imageId);
			params.put(Const.ANGLE, angle.toString());

			String url = String.format("%s/%s", API_ENDPOINT, Const.ROTATE);

			Log.d(TAG, "rotate: " + url);
			Log.d(TAG, params.toString());

			getInstance()
					.post(url, params, new SimpleResponseHandler(listener));
		} else {
			ErrorHandler.returnSimpleError(listener, Const.MISSING_PARAMS);
		}
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
	public void update(String imageId, String title, String description,
			ArrayList<String> tags, String originalFilename, Boolean isPublic,
			Boolean isCommentsDisabled, ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (imageId != null) {
			if (title != null) {
				params.put(Const.TITLE, title);
			}

			if (description != null) {
				params.put(Const.DESCRIPTION, description);
			}

			if (tags != null) {
				params.put(Const.TAGS, CommonFunctions.arrayListToCsv(tags));
			}

			if (originalFilename != null) {
				params.put(Const.ORIGINAL_FILENAME, originalFilename);
			}

			if (isPublic != null) {
				params.put(Const.PUBLIC, isPublic.toString());
			}

			if (isCommentsDisabled != null) {
				params.put(Const.COMMENTS_DISABLED,
						isCommentsDisabled.toString());
			}

			String url = String.format("%s/%s", API_ENDPOINT, imageId);

			Log.d(TAG, "updateImage: " + url);
			Log.d(TAG, params.toString());

			getInstance().put(url, params,
					new ImagesResponseHandler(listener, Models.IMAGE));
		} else {
			ErrorHandler.returnSimpleError(listener, Const.MISSING_PARAMS);
		}
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
	public void getNext(String imageId, Integer limit, Integer offset,
			ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (imageId == null) {
			ErrorHandler.returnSimpleError(listener, Const.MISSING_PARAMS);
		} else {
			if (limit != null) {
				params.put(Const.LIMIT, limit.toString());
			}

			if (offset != null) {
				params.put(Const.OFFSET, offset.toString());
			}

			String url = String.format("%s/%s/%s", API_ENDPOINT, imageId,
					Const.NEXT);

			Log.d(TAG, "getNext: " + url);
			Log.d(TAG, params.toString());

			getInstance().get(url, params,
					new ImagesResponseHandler(listener, Models.SIMPLE_IMAGES));
		}
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
	public void getPrev(String imageId, Integer limit, Integer offset,
			ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (imageId == null) {
			ErrorHandler.returnSimpleError(listener, Const.MISSING_PARAMS);
		} else {
			if (limit != null) {
				params.put(Const.LIMIT, limit.toString());
			}

			if (offset != null) {
				params.put(Const.OFFSET, offset.toString());
			}

			String url = String.format("%s/%s/%s", API_ENDPOINT, imageId,
					Const.PREV);

			Log.d(TAG, "getPrev: " + url);
			Log.d(TAG, params.toString());

			getInstance().get(url, params,
					new ImagesResponseHandler(listener, Models.SIMPLE_IMAGES));
		}
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
	public void getLikes(String server, String filename, Integer limit,
			String offset, ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (server != null && filename != null) {
			if (limit != null) {
				params.put(Const.LIMIT, limit.toString());
			}

			if (offset != null) {
				params.put(Const.OFFSET, offset);
			}

			String url = String.format("%s/%s/%s/%s", API_ENDPOINT, server,
					filename, Const.LIKES);

			Log.d(TAG, "getLikes: " + url);
			Log.d(TAG, params.toString());

			getInstance().get(url, params,
					new UsersResponseHandler(listener, Models.USERS));
		} else {
			ErrorHandler.returnSimpleError(listener, Const.MISSING_PARAMS);
		}
	}

	/**
	 * Retrieves a list of tags associated with a given image.
	 * 
	 * @param imageId
	 *            the image id
	 * @param listener
	 */
	public void getTags(String imageId, ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (imageId == null) {
			ErrorHandler.returnSimpleError(listener, Const.MISSING_PARAMS);
		} else {
			String url = String.format("%s/%s/%s", API_ENDPOINT, imageId, Const.TAGS);
			
			Log.d(TAG, "getTags: " + url);
			Log.d(TAG, params.toString());
			
			getInstance().get(url,
					new ImagesResponseHandler(listener, Models.TAGS));
		}
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
	public void deleteTags(String imageId, ArrayList<String> tags,
			ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (imageId == null) {
			ErrorHandler.returnSimpleError(listener, Const.MISSING_PARAMS);
		} else {
			String tagsCsv = CommonFunctions.arrayListToCsv(tags);
			String url = String.format("%s/%s/%s/%s", API_ENDPOINT, imageId, Const.TAGS,
					tagsCsv);
			
			Log.d(TAG, "deleteTags: " + url);
			Log.d(TAG, params.toString());
			
			getInstance().delete(null, url, null, params, new SimpleResponseHandler(listener));
		}
	}
}
