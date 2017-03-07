package com.tanvi.api;

import android.support.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.math.BigDecimal;

public class JsonUtil {

    private JsonUtil() {
    }

    public static String optJsonString(JsonObject jsonObject, String key) {
        return isNotEligibleForReturning(jsonObject, key) ? null : jsonObject.get(key).getAsString();
    }

    public static Long optJsonLong(JsonObject jsonObject, String key) {
        return isNotEligibleForReturning(jsonObject, key) ? null : jsonObject.get(key).getAsLong();
    }

    public static JsonArray optJsonArray(JsonObject jsonObject, String key) {
        return isNotEligibleForReturning(jsonObject, key) ? null : jsonObject.get(key).getAsJsonArray();
    }

    public static Integer optJsonInt(JsonObject jsonObject, String key) {
        return isNotEligibleForReturning(jsonObject, key) ? null : jsonObject.get(key).getAsInt();
    }

    public static Float optJsonFloat(JsonObject jsonObject, String key) {
        return isNotEligibleForReturning(jsonObject, key) ? null : jsonObject.get(key).getAsFloat();
    }

    public static Boolean optJsonBoolean(JsonObject jsonObject, String key) {
        return isNotEligibleForReturning(jsonObject, key) ? null : jsonObject.get(key).getAsBoolean();
    }

    public static Double optJsonDouble(JsonObject jsonObject, String key) {
        return isNotEligibleForReturning(jsonObject, key) ? null : jsonObject.get(key).getAsDouble();
    }

    public static JsonObject optJsonObject(JsonObject jsonObject, String key) {
        return isNotEligibleForReturning(jsonObject, key) ? null : jsonObject.get(key).getAsJsonObject();
    }

    public static <T> T optJsonObject(JsonObject jsonObject, String key, @NonNull Class<T> classType) {
        final JsonObject objectForKey = isNotEligibleForReturning(jsonObject, key) ? null : jsonObject.get(key).getAsJsonObject();
        if (objectForKey == null) {
            return null;
        } else {
            return new Gson().fromJson(jsonObject, classType);
        }
    }

    public static BigDecimal optJsonBigDecimal(JsonObject jsonObject, String key) {
        return isNotEligibleForReturning(jsonObject, key) ? null : jsonObject.get(key).getAsBigDecimal();
    }

    public static boolean isJsonArrayOfPrimitive(JsonObject jsonObject, String key) {
        JsonArray extraItems = optJsonArray(jsonObject, key);
        return extraItems != null && (extraItems.size() == 0 || extraItems.get(0).isJsonPrimitive());

    }

    private static boolean isNotEligibleForReturning(JsonObject jsonObject, String key) {
        return jsonObject.get(key) == null || jsonObject.get(key).isJsonNull();
    }

}
