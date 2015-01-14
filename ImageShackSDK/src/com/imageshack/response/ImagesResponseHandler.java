package com.imageshack.response;

import com.imageshack.constant.Models;
import com.imageshack.model.AbstractModel;
import com.imageshack.utility.ImagesJsonParser;

public class ImagesResponseHandler extends AbstractResponseHandler {

	public ImagesResponseHandler(ResponseListener listener, Models id) {
		super(listener, id);
	}

	@Override
	public void onSuccess(String result) {
		AbstractModel model = null;
		
		if  (id == Models.FOLLOWING_IMAGES) {
			model = ImagesJsonParser.getFollowingImages(result);
		} else if (id == Models.IMAGE) {
			model = ImagesJsonParser.getImage(result);
		} else if (id == Models.IMAGES) {
			model = ImagesJsonParser.getImages(result);
		} else if (id == Models.LIKED_IMAGES) {
			model = ImagesJsonParser.getLikedImages(result);
		} else if (id == Models.SIMPLE_IMAGES) {
			model = ImagesJsonParser.getSimpleImages(result);
		} else if (id == Models.UPLOAD) {
			model = ImagesJsonParser.uploadImage(result);
		} else if (id == Models.TAGS) {
			model = ImagesJsonParser.getTags(result);
		}
		
		listener.onResponse(model);
	}
}
