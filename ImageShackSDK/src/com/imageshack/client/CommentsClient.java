package com.imageshack.client;

import android.util.Log;

import com.imageshack.constant.Const;
import com.imageshack.constant.Models;
import com.imageshack.response.CommentsResponseHandler;
import com.imageshack.response.ErrorHandler;
import com.imageshack.response.ResponseListener;
import com.imageshack.response.SimpleResponseHandler;
import com.loopj.android.http.RequestParams;

public class CommentsClient extends ImageShackAbstractClient {

	private static final String TAG = "CommentsClient";
	private static final String API_ENDPOINT = ROUTE + Const.COMMENTS;
	private String authToken;

	public CommentsClient(String apiKey, String authToken) {
		super(apiKey);
		this.authToken = authToken;
	}

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
	public void get(String imageId, Integer limit, String offset,
			ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (limit != null) {
			params.put(Const.LIMIT, limit);
		}

		if (offset != null) {
			params.put(Const.OFFSET, offset);
		}

		if (imageId != null) {
			String url = String.format("%s/%s", API_ENDPOINT, imageId);
			
			Log.d(TAG, "getComments: " + url);
			Log.d(TAG, params.toString());
			
			getInstance().get(url, params, new CommentsResponseHandler(listener, Models.COMMENTS));
		} else {
			ErrorHandler.returnSimpleError(listener, Const.MISSING_PARAMS);
		}
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
	public void add(String imageId, String comment, String source,
			ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);
		params.put(Const.COMMENT, comment);

		if (source != null) {
			params.put(Const.SOURCE, source);
		}

		if (imageId != null) {
			String url = String.format("%s/%s", API_ENDPOINT, imageId);
			
			Log.d(TAG, "addComments: " + url);
			Log.d(TAG, params.toString());
			
			getInstance().post(url, params, new SimpleResponseHandler(listener));
		} else {
			ErrorHandler.returnSimpleError(listener, Const.MISSING_PARAMS);
		}
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
	public void delete(String commentId, ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		if (commentId != null) {
			String url = String.format("%s/%s", API_ENDPOINT, commentId);
			
			Log.d(TAG, "deleteComments: " + url);
			Log.d(TAG, params.toString());
			
			getInstance().delete(null, url, null, params, new SimpleResponseHandler(listener));
		} else {
			ErrorHandler.returnSimpleError(listener, Const.MISSING_PARAMS);
		}
	}

}
