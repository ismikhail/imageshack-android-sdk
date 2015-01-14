package com.imageshack.response;

import android.util.Log;

import com.imageshack.constant.Models;
import com.imageshack.model.AbstractModel;
import com.imageshack.utility.UsersJsonParser;

public class UsersResponseHandler extends AbstractResponseHandler {

	private static final String TAG = "UsersResponseHandler";

	public UsersResponseHandler(ResponseListener listener, Models id) {
		super(listener, id);
	}

	@Override
	public void onSuccess(String result) {
		Log.d(TAG, "onSuccess: " + result);
		
		AbstractModel model = null;

		if (id == Models.USER) {
			model = UsersJsonParser.getUser(result);
		} else if (id == Models.USER_SETTINGS) {
			model = UsersJsonParser.getSettings(result);
		} else if (id == Models.USERS) {
			model = UsersJsonParser.getUsers(result);
		} else if (id == Models.REFERRAL_URL) {
			model = UsersJsonParser.getReferralUrl(result);
		}
		
		listener.onResponse(model);
	}

}
