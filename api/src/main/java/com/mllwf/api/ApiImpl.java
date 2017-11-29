package com.mllwf.api;

import com.google.gson.reflect.TypeToken;
import com.mllwf.api.net.HttpEngine;
import com.mllwf.api.utils.EncryptUtil;
import com.mllwf.model.CouponBo;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ML on 2017/4/19.
 * Api接口类实现类，在这个里面处理请求操作，并且将返回接口实体咧
 */

public class ApiImpl implements Api {

    private final static String APP_KEY = "ANDROID_KCOUPON";
    private final static String TIME_OUT_EVENT = "CONNECT_TIME_OUT";
    private final static String TIME_OUT_EVENT_MSG = "连接服务器失败";

    private HttpEngine httpEngine;

    public ApiImpl() {
        this.httpEngine = HttpEngine.getInstance();
    }

    @Override
    public ApiResponse<Void> sendSmsCode4Register(String phoneNum) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("appKey", APP_KEY);
        paramMap.put("method", SEND_SMS_CODE);
        paramMap.put("phoneNum", phoneNum);

        Type type = new TypeToken<ApiResponse<Void>>() {
        }.getType();
        try {
            return httpEngine.postHandle(paramMap, type);
        } catch (IOException e) {
            return new ApiResponse(TIME_OUT_EVENT, TIME_OUT_EVENT_MSG);
        }
    }

    @Override
    public ApiResponse<Void> registerByPhone(String phoneNum, String code, String password) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("appKey", APP_KEY);
        paramMap.put("method", REGISTER);
        paramMap.put("phoneNum", phoneNum);
        paramMap.put("code", code);
        paramMap.put("password", EncryptUtil.makeMD5(password));

        Type type = new TypeToken<ApiResponse<List<CouponBo>>>() {
        }.getType();
        try {
            return httpEngine.postHandle(paramMap, type);
        } catch (IOException e) {
            return new ApiResponse(TIME_OUT_EVENT, TIME_OUT_EVENT_MSG);
        }
    }

    @Override
    public ApiResponse<Void> loginByApp(String loginName, String password, String imei, int loginOS) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("appKey", APP_KEY);
        paramMap.put("method", LOGIN);
        paramMap.put("loginName", loginName);
        paramMap.put("password", EncryptUtil.makeMD5(password));
        paramMap.put("imei", imei);
        paramMap.put("loginOS", String.valueOf(loginOS));

        Type type = new TypeToken<ApiResponse<List<CouponBo>>>() {
        }.getType();
        try {
            return httpEngine.postHandle(paramMap, type);
        } catch (IOException e) {
            return new ApiResponse(TIME_OUT_EVENT, TIME_OUT_EVENT_MSG);
        }
    }

    @Override
    public ApiResponse<List<CouponBo>> listNewCoupon(int currentPage, int pageSize) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("appKey", APP_KEY);
        paramMap.put("method", LIST_COUPON);
        paramMap.put("currentPage", String.valueOf(currentPage));
        paramMap.put("pageSize", String.valueOf(pageSize));

        Type type = new TypeToken<ApiResponse<List<CouponBo>>>() {
        }.getType();
        try {
            return httpEngine.postHandle(paramMap, type);
        } catch (IOException e) {
            return new ApiResponse(TIME_OUT_EVENT, TIME_OUT_EVENT_MSG);
        }
    }
}
