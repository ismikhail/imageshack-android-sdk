package com.imageshack.utility;

import org.json.JSONException;
import org.json.JSONObject;

import com.imageshack.constant.Const;
import com.imageshack.model.AbstractModel;
import com.imageshack.model.SimpleModel;
import com.imageshack.response.ErrorHandler;

public class SimpleResponseJsonParser {
	
	public static AbstractModel parse(String jsonString) {
		SimpleModel model = new SimpleModel();
		JSONObject simpleJson = null;
		
		try {
			simpleJson = new JSONObject(jsonString);
			
			model.setProcessTime(simpleJson.getInt(Const.PROCESS_TIME));
			
			if (!simpleJson.getBoolean(Const.SUCCESS)) {
				return ErrorHandler.getSimpleError(simpleJson, false);
			}
			
			String res = simpleJson.getString(Const.RESULT);
			if ("false".equals(res)) {
				model.setResult(false);
			}
		} catch (JSONException e) {
			return ErrorHandler.getSimpleError(simpleJson, true);
		}
		return model;
	}

}
