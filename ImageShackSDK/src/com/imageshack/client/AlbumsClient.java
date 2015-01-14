package com.imageshack.client;

import java.util.ArrayList;

import android.util.Log;

import com.imageshack.constant.Const;
import com.imageshack.constant.Models;
import com.imageshack.response.AlbumsResponseHandler;
import com.imageshack.response.ErrorHandler;
import com.imageshack.response.ResponseListener;
import com.imageshack.response.SimpleResponseHandler;
import com.imageshack.utility.CommonFunctions;
import com.loopj.android.http.RequestParams;

public class AlbumsClient extends ImageShackAbstractClient {

	private static final String TAG = "AlbumsClient";
	private static final String API_ENDPOINT = ROUTE + Const.ALBUMS;
	private String authToken;

	public AlbumsClient(String apiKey, String authToken) {
		super(apiKey);
		this.authToken = authToken;
	}

	/**
	 * Fetch an album data.
	 * 
	 * @param albumId
	 *            the album id
	 * @param listener
	 *            the listener for async http response
	 */
	public void get(String albumId, ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (albumId != null) {
			String url = String.format("%s/%s", API_ENDPOINT, albumId);
			
			Log.d(TAG, "getAlbum: " + url);
			Log.d(TAG, params.toString());
			
			getInstance().get(url, params, new AlbumsResponseHandler(listener, Models.ALBUM));
		} else {
			ErrorHandler.returnSimpleError(listener, Const.MISSING_PARAMS);
		}
	}

	/**
	 * Fetch user albums.
	 * 
	 * @param limit
	 *            the max number of albums to return per request
	 * @param offset
	 *            the offset for pagination
	 * @param listener
	 *            the listener for async http response
	 */
	public void get(Integer limit, Integer offset, ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);
		params.put(Const.SHOW_PRIVATE, "1");

		if (limit != null) {
			params.put(Const.LIMIT, limit.toString());
		}

		if (offset != null) {
			params.put(Const.OFFSET, offset.intValue());
		}

		Log.d(TAG, "getAlbums: " + API_ENDPOINT);
		Log.d(TAG, params.toString());
		
		getInstance().get(API_ENDPOINT, params, new AlbumsResponseHandler(listener, Models.ALBUMS));
	}

	/**
	 * Create an album.
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
	public void create(String title, String description, Boolean isPublic,
			ArrayList<String> imageIds, ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (description != null) {
			params.put(Const.DESCRIPTION, description);
		}

		if (isPublic != null) {
			params.put(Const.PUBLIC, isPublic.toString());
		}

		if (imageIds != null) {
			
			params.put(Const.FILES, CommonFunctions.arrayListToCsv(imageIds));
		}

		if (title != null) {
			params.put(Const.TITLE, title);
			
			Log.d(TAG, "createAlbum: " + API_ENDPOINT);
			Log.d(TAG, params.toString());
			
			getInstance().post(API_ENDPOINT, params, new AlbumsResponseHandler(listener, Models.ALBUM));
		} else {
			ErrorHandler.returnSimpleError(listener, Const.MISSING_PARAMS);
		}
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
	public void update(String albumId, String title, String description,
			Boolean isPublic, ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);
		boolean update = false;

		if (title != null) {
			update = true;
			params.put(Const.TITLE, title);
		}

		if (description != null) {
			update = true;
			params.put(Const.DESCRIPTION, description);
		}

		if (isPublic != null) {
			update = true;
			params.put(Const.PUBLIC, isPublic.toString());
		}

		if (albumId != null && update) {
			String url = String.format("%s/%s", API_ENDPOINT, albumId);
			
			Log.d(TAG, "updateAlbum: " + url);
			Log.d(TAG, params.toString());
			
			getInstance().put(url, params, new AlbumsResponseHandler(listener, Models.ALBUM));
		} else {
			ErrorHandler.returnSimpleError(listener, Const.MISSING_PARAMS);
		}

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
	public void add(String albumId, ArrayList<String> imageIds,
			ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (albumId != null && imageIds != null && imageIds.size() > 0) {
			params.put(Const.FILES, CommonFunctions.arrayListToCsv(imageIds));
			String url = String.format("%s/%s/%s", API_ENDPOINT, albumId,
					Const.ALBUMS_ADD_IMAGES);
			
			Log.d(TAG, "addAlbumImage: " + url);
			Log.d(TAG, params.toString());

			getInstance().put(url, params, new AlbumsResponseHandler(listener, Models.ALBUM));
		} else {
			ErrorHandler.returnSimpleError(listener, Const.MISSING_PARAMS);
		}
	}

	/**
	 * Delete images from an album
	 * 
	 * @param albumId
	 *            the album id, required
	 * @param imageIds
	 *            list of image ids, required
	 * @param listener
	 */
	public void delete(String albumId, ArrayList<String> imageIds,
			ResponseListener listener) {
		StringBuilder ids = new StringBuilder();
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (albumId != null && imageIds != null && imageIds.size() > 1) {
			for (String id : imageIds) {
				ids.append(id).append(",");
			}

			params.put(Const.FILES, ids.toString());

			String url = String.format("%s/%s/%s", API_ENDPOINT, albumId,
					Const.ALBUMS_DELETE_IMAGES);
			
			Log.d(TAG, "deleteAlbumImage: " + url);
			Log.d(TAG, params.toString());

			getInstance().put(url, params, new AlbumsResponseHandler(listener, Models.ALBUM));
		} else {
			ErrorHandler.returnSimpleError(listener, Const.MISSING_PARAMS);
		}
	}

	/**
	 * Remove the entire album and its contents
	 * 
	 * @param albumId
	 *            the album id
	 * @param listener
	 */
	public void remove(String albumId, ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (albumId != null) {
			String url = String.format("%s/%s", API_ENDPOINT, albumId);
			
			Log.d(TAG, "removeAlbum: " + url);
			Log.d(TAG, params.toString());
			
			getInstance().delete(null, url, null, params, new SimpleResponseHandler(listener));
		} else {
			ErrorHandler.returnSimpleError(listener, Const.MISSING_PARAMS);
		}
	}

}
