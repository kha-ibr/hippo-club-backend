package com.khalid.fakebook.Exception;

import com.khalid.fakebook.model.Session;
import com.khalid.fakebook.model.User;

import java.util.HashMap;
import java.util.Map;

public class ResponseException {

    public static Map<String, String> jsonResponse(String status, String message){
        Map<String, String> response = new HashMap<>();
        response.put(status, message);

        return response;
    }

    public static Object jsonResponseWithUserInfo(User user, Session session) {
        Map<String, Object> response = new HashMap<>();
        response.put("Auth token", session.getSession());
        response.put("user id",user.getUserId());
        response.put("firstname",user.getFirstname());
        response.put("lastname",user.getLastname());
        return response;
    }
}
