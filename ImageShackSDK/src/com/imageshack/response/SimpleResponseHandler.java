package com.imageshack.response;

import com.imageshack.model.AbstractModel;
import com.imageshack.utility.SimpleResponseJsonParser;

public class SimpleResponseHandler extends AbstractResponseHandler {
	
	public SimpleResponseHandler(ResponseListener listener) {
		super(listener);
	}

	@Override
	public void onSuccess(String result) {
		AbstractModel model = SimpleResponseJsonParser.parse(result);
		listener.onResponse(model);
	}

}
