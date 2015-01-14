package com.imageshack.response;

import org.json.JSONException;
import org.json.JSONObject;

import com.imageshack.constant.Const;
import com.imageshack.model.AbstractModel;
import com.imageshack.model.SimpleModel;

public class ErrorHandler {

	public static void returnSimpleError(ResponseListener listener, String msg) {
		SimpleModel model = new SimpleModel();
		model.setSuccess(false);
		model.setProcessTime(0);
		model.setError(msg);
		listener.onResponse(model);
	}
	
	public static AbstractModel getSimpleError(String error) {
		try {
			JSONObject errorJson = new JSONObject(error);
			return getSimpleError(errorJson, false);
		} catch (JSONException e) {
			return getSimpleError(null, true);
		}
	}
	
	public static AbstractModel getSimpleError(JSONObject json, boolean isException) {
		AbstractModel model = new SimpleModel();
		
		model.setSuccess(false);
		
		if (isException) {
			model.setError(Const.JSON_PARSING_EXCEPTION);
		} else {
			try {
				model.setError(json.getJSONObject(Const.ERROR).getString(
						Const.ERROR_MESSAGE));
			} catch (JSONException e) {
				model.setError(Const.JSON_PARSING_EXCEPTION);
			}
		}
		
		return model;
	}
	
	public static AbstractModel getTimeoutError() {
		AbstractModel model = new SimpleModel();
		
		model.setSuccess(false);
		model.setProcessTime(10000);
		model.setError(Const.TIMEOUT_ERROR);
		
		return model;
	}
}
