package org.basak.twitterdemo.constant;

public class EndPoints {
    public static final String VERSION = "/v1";
    public static final String API = "/api";
    public static final String DEV = "/dev";
    public static final String TEST = "/test";
    public static final String PROD = "/prod";

    public static final String ROOT = VERSION + DEV;
    //	Controller Sınıflar:
    public static final String USER = ROOT+ "/user";
    public static final String POST = ROOT+ "/post";
    public static final String LIKE = ROOT+ "/like";
    public static final String COMMENT = ROOT+ "/comment";
    public static final String FOLLOW = ROOT+ "/follow";

//	Metodlar
// USER
    public static final String REGISTER = "/register";
    public static final String LOGIN = "/login";
    public static final String GETPROFILE = "/get-profile";

    //ORTAK:
    public static final String SAVE = "/save";
    public static final String UPDATE = "/update";
    public static final String DELETE = "/delete";
    public static final String FIND_ALL = "/find-all";
    public static final String FIND_BY_ID = "/find-by-id";


}
