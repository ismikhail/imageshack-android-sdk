package com.imageshack.response;

import com.imageshack.constant.Models;
import com.imageshack.model.AbstractModel;
import com.imageshack.utility.AuthJsonParser;

public class AuthResponseHandler extends AbstractResponseHandler {

	public AuthResponseHandler(ResponseListener listener, Models id) {
		super(listener, id);
	}

	@Override
	public void onSuccess(String result) {
		AbstractModel model = null;

		if (id == Models.AUTH) {
			model = AuthJsonParser.login(result);
		}

		listener.onResponse(model);
	}

}
