package com.imageshack.utility;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.imageshack.constant.Const;
import com.imageshack.model.AbstractModel;
import com.imageshack.model.BasicImageModel;
import com.imageshack.model.FollowingImagesModel;
import com.imageshack.model.ImageModel;
import com.imageshack.model.ImagesModel;
import com.imageshack.model.LikedImagesModel;
import com.imageshack.model.MiniImageModel;
import com.imageshack.model.SimpleAlbumModel;
import com.imageshack.model.SimpleImageModel;
import com.imageshack.model.SimpleImagesModel;
import com.imageshack.model.SimpleUserModel;
import com.imageshack.model.TagsModel;
import com.imageshack.model.UploadModel;
import com.imageshack.response.ErrorHandler;

public class ImagesJsonParser {

	public static AbstractModel getTags(String jsonString) {
		TagsModel tags = new TagsModel();
		JSONObject tagsJson = null;
		
		try {
			tagsJson = new JSONObject(jsonString);
			tags.setProcessTime(tagsJson.getInt(Const.PROCESS_TIME));

			if (!tagsJson.getBoolean(Const.SUCCESS)) {
				return ErrorHandler.getSimpleError(tagsJson, false);
			}
			
			tags.setTags(getTagsFromString(tagsJson.getString(Const.RESULT)));
		} catch (JSONException e) {
			return ErrorHandler.getSimpleError(tagsJson, true);
		}
		
		return tags; 
	}
	
	public static AbstractModel getImage(String jsonString) {
		ImageModel image = new ImageModel();
		JSONObject imageJson = null, json;

		try {
			imageJson = new JSONObject(jsonString);
			image.setProcessTime(imageJson.getInt(Const.PROCESS_TIME));

			if (!imageJson.getBoolean(Const.SUCCESS)) {
				return ErrorHandler.getSimpleError(imageJson, false);
			}

			json = new JSONObject(imageJson.getString(Const.RESULT));
			buildImageFromJson(json, image);

		} catch (JSONException e) {
			return ErrorHandler.getSimpleError(imageJson, true);
		}

		return image;
	}

	public static AbstractModel getImages(String jsonString) {
		ImagesModel images = new ImagesModel();
		ArrayList<BasicImageModel> imageList = new ArrayList<BasicImageModel>();
		JSONObject imagesJson = null, json;

		try {
			imagesJson = new JSONObject(jsonString);
			images.setProcessTime(imagesJson.getInt(Const.PROCESS_TIME));

			if (!imagesJson.getBoolean(Const.SUCCESS)) {
				return ErrorHandler.getSimpleError(imagesJson, false);
			}

			json = new JSONObject(imagesJson.getString(Const.RESULT));
			images.setLimit(json.getInt(Const.LIMIT));
			images.setOffset(json.getInt(Const.OFFSET));
			images.setTotal(json.getInt(Const.TOTAL));

			buildListOfBasicImagesFromJson(json.getJSONArray(Const.IMAGES),
					imageList);
			images.setImages(imageList);

		} catch (JSONException e) {
			return ErrorHandler.getSimpleError(imagesJson, true);
		}

		return images;
	}

	public static AbstractModel getSimpleImages(String jsonString) {
		SimpleImagesModel images = new SimpleImagesModel();
		ArrayList<SimpleImageModel> imageList = new ArrayList<SimpleImageModel>();
		JSONObject imagesJson = null;

		try {
			imagesJson = new JSONObject(jsonString);
			images.setProcessTime(imagesJson.getInt(Const.PROCESS_TIME));

			if (!imagesJson.getBoolean(Const.SUCCESS)) {
				return ErrorHandler.getSimpleError(imagesJson, false);
			}

			buildSimpleImageArrayFromJson(
					imagesJson.getJSONArray(Const.RESULT), imageList);
			images.setImages(imageList);
		} catch (JSONException e) {
			return ErrorHandler.getSimpleError(imagesJson, true);
		}

		return images;
	}

	public static AbstractModel getLikedImages(String jsonString) {
		LikedImagesModel images = new LikedImagesModel();
		ArrayList<BasicImageModel> imageList = new ArrayList<BasicImageModel>();
		JSONObject imagesJson = null, json;

		try {
			imagesJson = new JSONObject(jsonString);
			images.setProcessTime(imagesJson.getInt(Const.PROCESS_TIME));

			if (!imagesJson.getBoolean(Const.SUCCESS)) {
				return ErrorHandler.getSimpleError(imagesJson, false);
			}

			json = new JSONObject(imagesJson.getString(Const.RESULT));
			images.setLimit(json.getInt(Const.LIMIT));
			images.setTotal(json.getInt(Const.TOTAL));
			images.setEndOffset(json.getString(Const.END_OFFSET));

			buildListOfBasicImagesFromJson(json.getJSONArray(Const.IMAGES),
					imageList);
			images.setImages(imageList);

		} catch (JSONException e) {
			return ErrorHandler.getSimpleError(imagesJson, true);
		}

		return images;
	}

	public static AbstractModel getFollowingImages(String jsonString) {
		FollowingImagesModel images = new FollowingImagesModel();
		ArrayList<BasicImageModel> imageList = new ArrayList<BasicImageModel>();
		JSONObject imagesJson = null, json;

		try {
			imagesJson = new JSONObject(jsonString);
			images.setProcessTime(imagesJson.getInt(Const.PROCESS_TIME));

			if (!imagesJson.getBoolean(Const.SUCCESS)) {
				return ErrorHandler.getSimpleError(imagesJson, false);
			}

			json = new JSONObject(imagesJson.getString(Const.RESULT));
			images.setLimit(json.getInt(Const.LIMIT));
			images.setEndOffset(json.getString(Const.END_OFFSET));

			buildListOfBasicImagesFromJson(json.getJSONArray(Const.IMAGES),
					imageList);
			images.setImages(imageList);

		} catch (JSONException e) {
			return ErrorHandler.getSimpleError(imagesJson, true);
		}

		return images;
	}

	public static void buildListOfBasicImagesFromJson(
			JSONArray imagesJsonArray, ArrayList<BasicImageModel> images) {
		try {
			for (int i = 0; i < imagesJsonArray.length(); i++) {
				BasicImageModel image = new BasicImageModel();
				buildBasicImageFromJson(imagesJsonArray.getJSONObject(i), image);

				if (image.getServer() != 0) {
					images.add(image);
				}
			}
		} catch (JSONException e) {
			// skip bad json, this should never happen
		}
	}

	public static AbstractModel uploadImage(String jsonString) {
		UploadModel upload = new UploadModel();

		JSONObject uploadJson = null, json;
		JSONArray imagesArray;

		try {
			uploadJson = new JSONObject(jsonString);
			upload.setProcessTime(uploadJson.getInt(Const.PROCESS_TIME));

			if (!uploadJson.getBoolean(Const.SUCCESS)) {
				return ErrorHandler.getSimpleError(uploadJson, false);
			}

			json = new JSONObject(uploadJson.getString(Const.RESULT));
			upload.setPassed(json.getInt(Const.PASSED));
			upload.setFailed(json.getInt(Const.FAILED));
			upload.setTotal(json.getInt(Const.TOTAL));

			imagesArray = json.getJSONArray(Const.IMAGES);
			for (int i = 0; i < imagesArray.length(); i++) {
				ImageModel image = new ImageModel();
				buildImageFromJson(imagesArray.getJSONObject(i), image);
				if (image != null) {
					upload.add(image);
				}
			}

		} catch (JSONException e) {
			return ErrorHandler.getSimpleError(uploadJson, true);
		}

		return upload;

	}

	public static void buildSimpleImageArrayFromJson(JSONArray imageJsonArray,
			ArrayList<SimpleImageModel> images) {
		JSONObject imageJson;

		try {
			for (int i = 0; i < imageJsonArray.length(); i++) {
				SimpleImageModel image = new SimpleImageModel();
				imageJson = imageJsonArray.getJSONObject(i);
				buildSimpleImageFromJson(imageJson, image);
				images.add(image);
			}
		} catch (JSONException e) {
			// skip bad imageJson
		}
	}

	public static void buildMiniImageFromJson(JSONObject imageJson,
			MiniImageModel image) {
		try {
			image.setServer(imageJson.getInt(Const.SERVER));
			image.setFilename(imageJson.getString(Const.FILENAME));
		} catch (JSONException e) {
			image.setServer(0);
		}
	}

	public static void buildSimpleImageFromJson(JSONObject imageJson,
			SimpleImageModel image) {

		buildMiniImageFromJson(imageJson, image);
		try {
			image.setId(imageJson.getString(Const.ID));
			image.setBucket(imageJson.getInt(Const.BUCKET));
			image.setWidth(imageJson.getInt(Const.WIDTH));
			image.setHeight(imageJson.getInt(Const.HEIGHT));
		} catch (JSONException e) {
			image = null;
		}
	}

	public static void buildBasicImageFromJson(JSONObject json,
			BasicImageModel image) {
		SimpleAlbumModel album = new SimpleAlbumModel();
		SimpleUserModel owner = new SimpleUserModel();

		buildSimpleImageFromJson(json, image);

		try {
			image.setOriginalFilename(json.getString(Const.ORIGINAL_FILENAME));
			image.setTitle(json.getString(Const.TITLE));
			image.setLikes(json.getInt(Const.LIKES));
			image.setFilesize(json.getInt(Const.FILESIZE));
			image.setCreationDate(json.getInt(Const.CREATION_DATE));
			image.setPublic(json.getBoolean(Const.PUBLIC));
			image.setOwner(json.getBoolean(Const.IS_OWNER));

			AlbumsJsonParser.buildSimpleAlbumFromJson(
					new JSONObject(json.getString(Const.ALBUM)), album);

			if ("".equals(album.getId())) {
				album = null;
			}

			image.setAlbum(album);

			UsersJsonParser.buildSimpleUserFromJson(
					new JSONObject(json.getString(Const.OWNER)), owner);
			image.setOwner(owner);

		} catch (JSONException e) {
			image = null;
		}
	}

	private static void buildImageFromJson(JSONObject json, ImageModel image) {
		ArrayList<SimpleImageModel> prev = new ArrayList<SimpleImageModel>(), next = new ArrayList<SimpleImageModel>();

		buildBasicImageFromJson(json, image);

		try {
			image.setDirectLink(json.getString(Const.DIRECT_LINK));
			image.setDescription(json.getString(Const.DESCRIPTION));
			image.setTags(getTagsFromString(json.getString(Const.TAGS)));
			image.setLiked(json.getBoolean(Const.LIKED));
			image.setViews(json.getInt(Const.VIEWS));
			image.setFilter(json.getInt(Const.FILTER));
			image.setCommentsCount(json.getInt(Const.COMMENTS_COUNT));
			image.setCommentsDisabled(json.getBoolean(Const.COMMENTS_DISABLED));

			buildSimpleImageArrayFromJson(json.getJSONArray(Const.PREV_IMAGES),
					prev);
			image.setPrev(prev);
			buildSimpleImageArrayFromJson(json.getJSONArray(Const.NEXT_IMAGES),
					next);
			image.setNext(next);

		} catch (JSONException e) {
			image = null;
		}
	}

	private static ArrayList<String> getTagsFromString(String tagsArray) {
		ArrayList<String> tags = new ArrayList<String>();

		if (tagsArray.length() > 2) {
			// strip '["' '"]'
			String tmp = tagsArray.substring(2, tagsArray.length() - 2);

			// split string by '","'
			String[] array = tmp.split("\",\"");

			// strip quotes and add to list
			for (String s : array) {
				tags.add(s);
			}
		}
		
		return tags;
	}

}