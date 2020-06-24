package com.logentries.misc;

import android.os.Build;
import android.util.Log;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utils {

    private static final String TAG = "LogentriesAndroidLogger";

    /**
    *  Via http://stackoverflow.com/a/10174938
    */
    public static boolean isJSONValid(String message) {
        try {
            new JSONObject(message);
        } catch (JSONException ex) {
            try {
                new JSONArray(message);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkValidUUID(String uuid) {
        if (uuid != null && !uuid.isEmpty()) {
            try {

                UUID u = UUID.fromString(uuid);
                return true;

            } catch (IllegalArgumentException e) {
                return false;
            }
        }
        return false;
    }

    public static String[] splitStringToChunks(String source, int chunkLength) {
        if (chunkLength < 0) {
            throw new IllegalArgumentException("Chunk length must be greater or equal to zero!");
        }

        int srcLength = source.length();
        if (chunkLength == 0 || srcLength <= chunkLength) {
            return new String[]{source};
        }

        ArrayList<String> chunkBuffer = new ArrayList<String>();
        int splitSteps = srcLength / chunkLength + (srcLength % chunkLength > 0 ? 1 : 0);

        int lastCutPosition = 0;
        for (int i = 0; i < splitSteps; ++i) {

            if (i < splitSteps - 1) {
                // Cut out the chunk of the requested size.
                chunkBuffer.add(source.substring(lastCutPosition, lastCutPosition + chunkLength));
            } else {
                // Cut out all that left to the end of the string.
                chunkBuffer.add(source.substring(lastCutPosition));
            }

            lastCutPosition += chunkLength;
        }

        return chunkBuffer.toArray(new String[chunkBuffer.size()]);
    }
}
