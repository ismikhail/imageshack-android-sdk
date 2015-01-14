package com.imageshack.response;

import com.imageshack.constant.Models;
import com.imageshack.model.AbstractModel;
import com.imageshack.utility.ActivitiesJsonParser;

public class ActivitiesResponseHandler extends AbstractResponseHandler {

	public ActivitiesResponseHandler(ResponseListener listener,
			Models id) {
		super(listener, id);
	}

	@Override
	public void onSuccess(String result) {
		AbstractModel model = null;

		if (id == Models.ACTIVITIES) {
			model = ActivitiesJsonParser.getActivities(result);
		} else if (id == Models.ACTIVITIES_COUNT) {
			model = ActivitiesJsonParser.getNewCount(result);
		}

		listener.onResponse(model);
	}

}
