package com.imageshack.client;

import com.imageshack.constant.Const;
import com.loopj.android.http.AsyncHttpClient;

public class ImageShackAbstractClient {
	
	protected String apiKey;
	protected static final String ROUTE = Const.PROTOCOL + Const.API;
	
	public ImageShackAbstractClient(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public AsyncHttpClient getInstance() {
		return new AsyncHttpClient();
	}
	
}
