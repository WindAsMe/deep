package com.deep.api.Utils;

import com.deep.api.response.Responses;

public class StringToLongUtil {
    public static long stringToLong(String user) {
        long uid;
        try {
            uid = Integer.parseInt(user);
            if (uid < 0) {
                return -1;
            }
        } catch (Exception e) {
            return -1;
        }
        return uid;
    }
    public static int stringToInt(String user) {
        int uid;
        try {
            uid = Integer.parseInt(user);
            if (uid < 0) {
                return -1;
            }
        } catch (Exception e) {
            return -1;
        }
        return uid;
    }
}
