package com.khalid.hippoClub.Exception;

import com.khalid.hippoClub.model.User;

import java.util.HashMap;
import java.util.Map;

public class ResponseException {

    public static Map<String, String> jsonResponse(String status, String message){
        Map<String, String> response = new HashMap<>();
        response.put(status, message);

        return response;
    }

    public static Object jsonResponseWithUserInfo(User user) {
        Map<String, Object> response = new HashMap<>();
        response.put("userId",user.getUserId());
        response.put("firstname",user.getFirstname());
        response.put("lastname",user.getLastname());
        response.put("email",user.getEmail());
        response.put("avatar",user.getAvatar());

        return response;
    }
}