package com.khalid.fakebook.Exception;

import java.util.HashMap;
import java.util.Map;

public class ResponseException {

    public static Map<String, String> jsonResponse(String status, String message){
        Map<String, String> response = new HashMap<>();
        response.put(status, message);

        return response;
    }

}
