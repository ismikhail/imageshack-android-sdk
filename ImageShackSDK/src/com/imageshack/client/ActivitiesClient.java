package com.imageshack.client;

import com.imageshack.constant.Const;
import com.imageshack.constant.Models;
import com.imageshack.response.ActivitiesResponseHandler;
import com.imageshack.response.ResponseListener;
import com.imageshack.response.SimpleResponseHandler;
import com.loopj.android.http.RequestParams;

public class ActivitiesClient extends ImageShackAbstractClient {

	private static final String API_ENDPOINT = ROUTE + Const.ACTIVITIES;
	private String authToken;

	public ActivitiesClient(String apiKey, String authToken) {
		super(apiKey);
		this.authToken = authToken;
	}

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

		getInstance().get(API_ENDPOINT, params, new ActivitiesResponseHandler(listener, Models.ACTIVITIES));
	}

	/**
	 * Fetch the number of new notifications
	 * 
	 * @param listener
	 *            Async http response listener
	 */
	public void getNewCount(ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		String url = String.format("%s/%s", API_ENDPOINT,
				Const.ACTIVITIES_NEW_COUNT);
		getInstance().get(url, params, new SimpleResponseHandler(listener));
	}

	/**
	 * Mark notifications as viewed
	 * 
	 * @param listener
	 *            Async http response listener
	 */
	public void markAsViewed(ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.AUTH_TOKEN, authToken);

		String url = String.format("%s/%s", API_ENDPOINT,
				Const.ACTIVITIES_MARK_AS_VIEWED);
		getInstance().post(url, params, new SimpleResponseHandler(listener));
	}

}
