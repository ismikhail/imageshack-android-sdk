package com.imageshack.utility;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.imageshack.constant.Const;
import com.imageshack.model.AbstractModel;
import com.imageshack.model.AlbumModel;
import com.imageshack.model.AlbumsModel;
import com.imageshack.model.BasicImageModel;
import com.imageshack.model.SimpleAlbumModel;
import com.imageshack.model.SimpleUserModel;
import com.imageshack.response.ErrorHandler;

public class AlbumsJsonParser {

	public static AbstractModel getAlbum(String jsonString) {
		AlbumModel album = new AlbumModel();
		JSONObject albumJson = null, json;

		try {
			albumJson = new JSONObject(jsonString);
			album.setProcessTime(albumJson.getInt(Const.PROCESS_TIME));

			if (!albumJson.getBoolean(Const.SUCCESS)) {
				return ErrorHandler.getSimpleError(albumJson, false);
			}

			json = new JSONObject(albumJson.getString(Const.RESULT));
			buildAlbumFromJson(json, album);

		} catch (JSONException e) {
			return ErrorHandler.getSimpleError(albumJson, false);
		}
		return album;
	}

	public static AbstractModel getAlbums(String jsonString) {
		AlbumsModel albums = new AlbumsModel();
		ArrayList<AlbumModel> albumList = new ArrayList<AlbumModel>();
		JSONObject albumsJson = null, json;

		try {
			albumsJson = new JSONObject(jsonString);

			albums.setProcessTime(albumsJson.getInt(Const.PROCESS_TIME));

			if (!albumsJson.getBoolean(Const.SUCCESS)) {
				return ErrorHandler.getSimpleError(albumsJson, false);
			}

			json = new JSONObject(albumsJson.getString(Const.RESULT));
			albums.setLimit(json.getInt(Const.LIMIT));
			albums.setOffset(json.getInt(Const.OFFSET));
			albums.setTotal(json.getInt(Const.TOTAL));

			buildAlbumListFromJson(json.getJSONArray(Const.ALBUMS), albumList);
			albums.setAlbums(albumList);

		} catch (JSONException e) {
			return ErrorHandler.getSimpleError(albumsJson, false);
		}

		return albums;
	}

	public static void buildSimpleAlbumFromJson(JSONObject json,
			SimpleAlbumModel album) {
		try {
			album.setId(json.getString(Const.ID));
			if (album.getId() == null || "".equals(album.getId())) {
				album.setId("");
			} else {
				album.setTitle(json.getString(Const.TITLE));
				album.setPublic(json.getBoolean(Const.PUBLIC));
			}
		} catch (JSONException e) {
			album.setId("");
		}
	}

	public static void buildAlbumFromJson(JSONObject json, AlbumModel album) {
		SimpleUserModel owner = new SimpleUserModel();
		ArrayList<BasicImageModel> images = new ArrayList<BasicImageModel>();

		buildSimpleAlbumFromJson(json, album);

		if (album != null) {
			try {
				album.setCreationDate(json.getInt(Const.CREATION_DATE));
				album.setOwner(json.getBoolean(Const.IS_OWNER));

				UsersJsonParser.buildSimpleUserFromJson(
						new JSONObject(json.getString(Const.OWNER)), owner);
				album.setOwner(owner);

				ImagesJsonParser.buildListOfBasicImagesFromJson(
						json.getJSONArray(Const.IMAGES), images);
				album.setImages(images);

			} catch (JSONException e) {
				album = null;
			}
		}
	}

	public static void buildAlbumListFromJson(JSONArray albumsJsonArray,
			ArrayList<AlbumModel> albums) {
		try {
			for (int i = 0; i < albumsJsonArray.length(); i++) {
				AlbumModel album = new AlbumModel();
				buildAlbumFromJson(albumsJsonArray.getJSONObject(i), album);
				if (album != null) {
					albums.add(album);
				}
			}
		} catch (JSONException e) {
			// skip bad json, should never happen
		}
	}

}
