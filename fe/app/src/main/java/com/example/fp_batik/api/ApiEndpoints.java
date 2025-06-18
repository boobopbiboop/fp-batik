package com.example.fp_batik.api;

public class ApiEndpoints {
    private static final String BASE_URL = "http://192.168.148.70:3002";
    public static final String PREDICT = BASE_URL + "/api/predict";
    public static final String GET_BATIK = BASE_URL + "/api/kain/class/%s";
    public static final String GET_ALL_BATIK = BASE_URL + "/api/kain";
    public static final String IMAGE = BASE_URL + "%s";

}
