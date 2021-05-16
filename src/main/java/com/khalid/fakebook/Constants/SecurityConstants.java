package com.khalid.fakebook.Constants;

public class SecurityConstants {
    public static final String SECRET = "36b7f89594648963a8f740eb893e064e866ebc2ead4b8f59feae4157c72e13fb407e5d";
    public static final long EXPIRATION_TIME = 900_000; // 15 mins
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/auth/login";
}
