package com.imageshack.client;

import android.util.Log;

import com.imageshack.constant.Const;
import com.imageshack.constant.Models;
import com.imageshack.response.AuthResponseHandler;
import com.imageshack.response.ErrorHandler;
import com.imageshack.response.ResponseListener;
import com.imageshack.response.SimpleResponseHandler;
import com.loopj.android.http.RequestParams;

public class AuthClient extends ImageShackAbstractClient {

	private static final String TAG = "AuthClient";
	private static final String API_ENDPOINT = ROUTE + Const.USER;

	public AuthClient(String apiKey) {
		super(apiKey);
	}

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
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		params.put(Const.USERNMAE, username);
		params.put(Const.PASSWORD, password);

		String url = String.format("%s/%s", API_ENDPOINT, Const.LOGIN);

		Log.d(TAG, "login: " + url);
		Log.d(TAG, params.toString());

		getInstance().post(url, params,
				new AuthResponseHandler(listener, Models.AUTH));
	}

	/**
	 * Create a new ImageShack user.
	 * 
	 * @param email
	 *            the email, required
	 * @param username
	 *            the username, required
	 * @param password
	 *            the passowrd, required
	 * @param listener
	 *            the response listener that is notified when async http request
	 *            is completed
	 */
	public void register(String email, String username, String password,
			ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);

		if (email == null || username == null || password == null) {
			ErrorHandler.returnSimpleError(listener, Const.MISSING_PARAMS);
		} else {
			params.put(Const.EMAIL, email);
			params.put(Const.PASSWORD, password);
			params.put(Const.USERNMAE, username);

			Log.d(TAG, "register: " + API_ENDPOINT);
			Log.d(TAG, params.toString());

			getInstance().post(API_ENDPOINT, params,
					new SimpleResponseHandler(listener));
		}
	}

	/**
	 * Checks whether the given email address is registered with ImageShack.
	 * 
	 * @param email
	 * @param listener
	 */
	public void exists(String email, ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);

		if (email != null) {
			String url = String.format("%s/%s/%s", API_ENDPOINT, email,
					Const.VERIFY);

			Log.d(TAG, url);
			Log.d(TAG, params.toString());
			
			getInstance().get(url, params, new SimpleResponseHandler(listener));
			
		} else {
			ErrorHandler.returnSimpleError(listener, Const.MISSING_PARAMS);
		}
	}
	
	/**
	 * Send password reset instructions to the given username/email. Either
	 * email or username is needed.
	 * 
	 * @param username
	 * @param email
	 * @param listener
	 */
	public void forgotPassword(String username, String email, ResponseListener listener) {
		RequestParams params = new RequestParams();
		params.put(Const.API_KEY, apiKey);
		
		if (username == null && email == null) {
			ErrorHandler.returnSimpleError(listener, Const.MISSING_PARAMS);
		} else {
			if (username != null) {
				params.put(Const.USERNMAE, username);
			} else if (email != null) {
				params.put(Const.EMAIL, email);
			}
			
			String url = String.format("%s/%s", API_ENDPOINT, Const.FORGOT);
			getInstance().post(url, params, new SimpleResponseHandler(listener));
		}
	}

}
