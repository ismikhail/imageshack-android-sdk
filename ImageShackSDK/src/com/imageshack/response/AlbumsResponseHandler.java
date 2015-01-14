package com.imageshack.response;

import com.imageshack.constant.Models;
import com.imageshack.model.AbstractModel;
import com.imageshack.utility.AlbumsJsonParser;

public class AlbumsResponseHandler extends AbstractResponseHandler {

	public AlbumsResponseHandler(ResponseListener listener, Models id) {
		super(listener, id);
	}

	@Override
	public void onSuccess(String result) {
		AbstractModel model = null;

		if (id == Models.ALBUM) {
			model = AlbumsJsonParser.getAlbum(result);
		} else if (id == Models.ALBUMS) {
			model = AlbumsJsonParser.getAlbums(result);
		} 
		
		listener.onResponse(model);
	}

}
