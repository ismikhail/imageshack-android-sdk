package com.imageshack.utility;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.imageshack.constant.Const;
import com.imageshack.model.AbstractModel;
import com.imageshack.model.MiniImageModel;
import com.imageshack.model.ReferralModel;
import com.imageshack.model.SimpleImageModel;
import com.imageshack.model.SimpleUserModel;
import com.imageshack.model.UserModel;
import com.imageshack.model.UserSettingsModel;
import com.imageshack.model.UsersModel;
import com.imageshack.response.ErrorHandler;

public class UsersJsonParser {

	public static AbstractModel getReferralUrl(String jsonString) {
		ReferralModel referral = new ReferralModel();
		JSONObject referralJson = null, json;

		try {
			referralJson = new JSONObject(jsonString);
			referral.setProcessTime(referralJson.getInt(Const.PROCESS_TIME));

			if (!referralJson.getBoolean(Const.SUCCESS)) {
				return ErrorHandler.getSimpleError(referralJson, false);
			}
			
			json = new JSONObject(referralJson.getString(Const.RESULT));
			referral.setUrl(json.getString(Const.REFERRAL));
		} catch (JSONException e) {
			return ErrorHandler.getSimpleError(referralJson, true);
		}

		return referral;
	}

	public static AbstractModel getUser(String jsonString) {
		UserModel user = new UserModel();
		JSONObject userJson = null, json;

		try {
			userJson = new JSONObject(jsonString);
			user.setProcessTime(userJson.getInt(Const.PROCESS_TIME));

			if (!userJson.getBoolean(Const.SUCCESS)) {
				return ErrorHandler.getSimpleError(userJson, false);
			}

			json = new JSONObject(userJson.getString(Const.RESULT));
			buildUserFromJson(json, user);

		} catch (JSONException e) {
			return ErrorHandler.getSimpleError(userJson, true);
		}

		return user;
	}

	public static AbstractModel getSettings(String jsonString) {
		UserSettingsModel settings = new UserSettingsModel();
		JSONObject settingsJson = null, json;

		try {
			settingsJson = new JSONObject(jsonString);
			settings.setProcessTime(settingsJson.getInt(Const.PROCESS_TIME));

			if (!settingsJson.getBoolean(Const.SUCCESS)) {
				return ErrorHandler.getSimpleError(settingsJson, false);
			}

			json = new JSONObject(settingsJson.getString(Const.RESULT));
			settings.setPrivateProfile(json.getBoolean(Const.PRIVATE_PROFILE));
			settings.setPrivateDefaultAlbums(json
					.getBoolean(Const.PRIVATE_ALBUMS));
			settings.setPrivateDefaultImages(json
					.getBoolean(Const.PRIVATE_IMAGES));
			settings.setFollowingAllowed(json
					.getBoolean(Const.PRIVATE_FOLLOWING));
			settings.setEnabledUploadNotifications(json
					.getBoolean(Const.UPLOAD_NOTIFICATIONS));
			settings.setEnabledCommentsNotifications(json
					.getBoolean(Const.COMMENTS_NOTIFICATIONS));
			settings.setEnabledLikesNotifications(json
					.getBoolean(Const.LIKES_NOTIFICATIONS));
			settings.setEnabledFollowingNotifications(json
					.getBoolean(Const.FOLLOWING_NOTIFICATIONS));

		} catch (JSONException e) {
			return ErrorHandler.getSimpleError(settingsJson, true);
		}

		return settings;
	}

	public static AbstractModel getUsers(String jsonString) {
		UsersModel users = new UsersModel();
		ArrayList<UserModel> usersList = new ArrayList<UserModel>();
		JSONObject usersJson = null, json;

		try {
			usersJson = new JSONObject(jsonString);
			users.setProcessTime(usersJson.getInt(Const.PROCESS_TIME));

			if (!usersJson.getBoolean(Const.SUCCESS)) {
				return ErrorHandler.getSimpleError(usersJson, false);
			}

			json = new JSONObject(usersJson.getString(Const.RESULT));
			users.setLimit(json.getInt(Const.LIMIT));
			users.setOffset(json.getString(Const.END_OFFSET));
			users.setTotal(json.getInt(Const.TOTAL));

			buildListOfUsersFromJson(json.getJSONArray(Const.USERS), usersList);
			users.setUsers(usersList);

		} catch (JSONException e) {
			return ErrorHandler.getSimpleError(usersJson, true);
		}

		return users;
	}

	public static void buildListOfUsersFromJson(JSONArray usersJsonArray,
			ArrayList<UserModel> users) {
		try {
			for (int i = 0; i < usersJsonArray.length(); i++) {
				UserModel user = new UserModel();
				buildUserFromJson(usersJsonArray.getJSONObject(i), user);

				if (user.getCreationDate() != 0) {
					users.add(user);
				}
			}
		} catch (JSONException e) {
			// skip bad json, this should never happen
		}
	}

	public static void buildUserFromJson(JSONObject userJson, UserModel user) {
		ArrayList<SimpleImageModel> images = new ArrayList<SimpleImageModel>();

		buildSimpleUserFromJson(userJson, user);

		try {
			user.setCreationDate(userJson.getInt(Const.CREATION_DATE));
			user.setDescription(userJson.getString(Const.DESCRIPTION));
			user.setEmail(userJson.getString(Const.EMAIL));
			user.setFollowing(userJson.getBoolean(Const.IS_FOLLOWING));
			user.setFollowingAllowed(userJson
					.getBoolean(Const.FOLLOWING_ALLOWED));
			user.setGender(userJson.getString(Const.GENDER));
			user.setOwner(userJson.getBoolean(Const.IS_OWNER));

			ImagesJsonParser.buildSimpleImageArrayFromJson(
					userJson.getJSONArray(Const.LATEST_IMAGES), images);
			user.setLatestImages(images);

		} catch (JSONException e) {
			user = null;
		}
	}

	public static void buildSimpleUserFromJson(JSONObject userJson,
			SimpleUserModel user) {
		MiniImageModel avatar = new MiniImageModel();

		try {
			user.setUsername(userJson.getString(Const.USERNMAE));
			ImagesJsonParser.buildMiniImageFromJson(
					new JSONObject(userJson.getString(Const.AVATAR)), avatar);

			if (avatar.getServer() == 0) {
				avatar = null;
			}

			user.setAvatar(avatar);

		} catch (JSONException e) {
			user = null;
		}
	}

}
