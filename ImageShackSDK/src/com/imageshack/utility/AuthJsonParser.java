package com.imageshack.utility;

import org.json.JSONException;
import org.json.JSONObject;

import com.imageshack.constant.Const;
import com.imageshack.model.AbstractModel;
import com.imageshack.model.AuthModel;
import com.imageshack.model.SimpleImageModel;
import com.imageshack.response.ErrorHandler;

public class AuthJsonParser {

	public static AbstractModel login(String jsonString) {
		AuthModel auth = new AuthModel();
		SimpleImageModel avatar = new SimpleImageModel();
		JSONObject authJson = null, json;

		try {
			authJson = new JSONObject(jsonString);
			auth.setProcessTime(authJson.getInt(Const.PROCESS_TIME));

			if (!authJson.getBoolean(Const.SUCCESS)) {
				return ErrorHandler.getSimpleError(authJson, false);
			}
			
			json = new JSONObject(authJson.getString(Const.RESULT));
			auth.setAuthToken(json.getString(Const.AUTH_TOKEN));
			auth.setUserId(json.getLong(Const.USERID));
			auth.setEmail(json.getString(Const.EMAIL));
			auth.setUsername(json.getString(Const.USERNMAE));
			auth.setMembership(json.getString(Const.MEMBERSHIP));
			auth.setMembershipItemNumber(json
					.getString(Const.MEMBERSHIP_ITEM_NUM));

			ImagesJsonParser.buildMiniImageFromJson(new JSONObject(
					json.getString(Const.AVATAR)), avatar);
			
			if (avatar.getId() == null || avatar.getId().equals("")) {
				avatar = null;
			}
			
			auth.setAvatar(avatar);

		} catch (JSONException e) {
			return ErrorHandler.getSimpleError(authJson, false);
		} catch (NullPointerException e) {
			auth.setError(Const.CHECK_NETWORK);
		}

		return auth;
	}

}
