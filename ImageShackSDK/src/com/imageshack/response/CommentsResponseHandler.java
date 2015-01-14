package com.imageshack.response;

import com.imageshack.constant.Models;
import com.imageshack.model.AbstractModel;
import com.imageshack.utility.CommentsJsonParser;

public class CommentsResponseHandler extends AbstractResponseHandler {

	public CommentsResponseHandler(ResponseListener listener, Models id) {
		super(listener, id);
	}

	@Override
	public void onSuccess(String result) {
		AbstractModel model = null;
		
		if (id == Models.COMMENTS) {
			model = CommentsJsonParser.getComments(result);
		}
		
		listener.onResponse(model);
	}

}
