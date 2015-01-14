package com.imageshack.response;

import android.util.Log;

import com.imageshack.constant.Models;
import com.imageshack.model.AbstractModel;
import com.loopj.android.http.AsyncHttpResponseHandler;

public abstract class AbstractResponseHandler extends AsyncHttpResponseHandler {
	
	private static final String TAG = "AbstractResponseHandler";
	
	protected ResponseListener listener;
	protected Models id;

	public AbstractResponseHandler(ResponseListener listener) {
		this.listener = listener;
	}

	public AbstractResponseHandler(ResponseListener listener, Models id) {
		this.listener = listener;
		this.id = id;
	}

	public abstract void onSuccess(String result);

	public void onFailure(Throwable arg0, String errorMsg) {
		Log.d(TAG, "onFailure: " + errorMsg);

		AbstractModel model = null;
		if (errorMsg != null) {
			model = ErrorHandler.getSimpleError(errorMsg);
		} else {
			model = ErrorHandler.getTimeoutError();
		}
		listener.onResponse(model);
	}

	/**
	 * @return the responseListener
	 */
	public ResponseListener getResponseListener() {
		return listener;
	}

	/**
	 * @param responseListener
	 *            the responseListener to set
	 */
	public void setResponseListener(ResponseListener responseListener) {
		this.listener = responseListener;
	}

	/**
	 * @return the model
	 */
	public Models getModel() {
		return id;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(Models model) {
		this.id = model;
	}

}
