package com.mllwf.core;

import android.content.Context;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.mllwf.api.Api;
import com.mllwf.api.ApiImpl;
import com.mllwf.api.ApiResponse;
import com.mllwf.model.CouponBo;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ML on 2017/4/19.
 * 向下调用Api,向上提供Action(供界面调用)，它的核心任务就是处理复杂的业务逻辑！
 */

public class AppActionImpl implements AppAction {

    private final static int LOGIN_OS = 1; // 表示Android
    private final static int PAGE_SIZE = 20; // 默认每页20条
    private Context context;
    private Api api;

    public AppActionImpl(Context context) {
        this.context = context;
        //        初始化的时候，获取到Api实现类的实例
        api = new ApiImpl();
    }

    @Override
    public void sendSmsCode(final String phoneNum, final ActionCallbackListener<Void> listener) {
        // 参数为空检查
        if (TextUtils.isEmpty(phoneNum)) {
            if (listener != null) {
                listener.onFailure(ErrorEvent.PARAM_NULL, "手机号为空");
            }
            return;
        }
        // 参数合法性检查
        Pattern pattern = Pattern.compile("1\\d{10}");
        Matcher matcher = pattern.matcher(phoneNum);
        if (!matcher.matches()) {
            if (listener != null) {
                listener.onFailure(ErrorEvent.PARAM_ILLEGAL, "手机号不正确");
            }
            return;
        }

        //TODO: 异步请求Api，并将服务端返回的实体类通过回调接口向上提供给界面
        new AsyncTask<Void, Void, ApiResponse<Void>>() {
            @Override
            protected ApiResponse<Void> doInBackground(Void... voids) {
                return api.sendSmsCode4Register(phoneNum);
            }

            @Override
            protected void onPostExecute(ApiResponse<Void> response) {
                if (listener != null && response != null) {
                    if (response.isSuccess()) {
                        listener.onSuccess(null);
                    } else {
                        listener.onFailure(response.getEvent(), response.getMsg());
                    }
                }
            }
        }.execute();

    }

    @Override
    public void register(final String phoneNum, final String code, final String password, final ActionCallbackListener<Void> listener) {
        // 参数为空检查
        if (TextUtils.isEmpty(phoneNum)) {
            if (listener != null) {
                listener.onFailure(ErrorEvent.PARAM_NULL, "手机号为空");
            }
            return;
        }
        if (TextUtils.isEmpty(code)) {
            if (listener != null) {
                listener.onFailure(ErrorEvent.PARAM_NULL, "验证码为空");
            }
            return;
        }
        if (TextUtils.isEmpty(password)) {
            if (listener != null) {
                listener.onFailure(ErrorEvent.PARAM_NULL, "密码为空");
            }
            return;
        }

        // 参数合法性检查
        Pattern pattern = Pattern.compile("1\\d{10}");
        Matcher matcher = pattern.matcher(phoneNum);
        if (!matcher.matches()) {
            if (listener != null) {
                listener.onFailure(ErrorEvent.PARAM_ILLEGAL, "手机号不正确");
            }
            return;
        }

        // TODO 长度检查，密码有效性检查等

        // 请求Api
        new AsyncTask<Void, Void, ApiResponse<Void>>() {
            @Override
            protected ApiResponse<Void> doInBackground(Void... voids) {
                return api.registerByPhone(phoneNum, code, password);
            }

            @Override
            protected void onPostExecute(ApiResponse<Void> response) {
                if (listener != null && response != null) {
                    if (response.isSuccess()) {
                        listener.onSuccess(null);
                    } else {
                        listener.onFailure(response.getEvent(), response.getMsg());
                    }
                }
            }
        }.execute();
    }

    @Override
    public void login(final String loginName, final String password, final ActionCallbackListener<Void> listener) {
        // 参数为空检查
        if (TextUtils.isEmpty(loginName)) {
            if (listener != null) {
                listener.onFailure(ErrorEvent.PARAM_NULL, "登录名为空");
            }
            return;
        }
        if (TextUtils.isEmpty(password)) {
            if (listener != null) {
                listener.onFailure(ErrorEvent.PARAM_NULL, "密码为空");
            }
            return;
        }

        // TODO 长度检查，密码有效性检查等

        // 请求Api
        new AsyncTask<Void, Void, ApiResponse<Void>>() {
            @Override
            protected ApiResponse<Void> doInBackground(Void... voids) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                String imei = telephonyManager.getDeviceId();
                return api.loginByApp(loginName, password, imei, LOGIN_OS);
            }

            @Override
            protected void onPostExecute(ApiResponse<Void> response) {
                if (listener != null && response != null) {
                    if (response.isSuccess()) {
                        listener.onSuccess(null);
                    } else {
                        listener.onFailure(response.getEvent(), response.getMsg());
                    }
                }
            }
        }.execute();
    }

    @Override
    public void listCoupon(final int currentPage, final ActionCallbackListener<List<CouponBo>> listener) {
        // 参数检查
        if (currentPage < 0) {
            if (listener != null) {
                listener.onFailure(ErrorEvent.PARAM_ILLEGAL, "当前页数小于零");
            }
        }

        // TODO 添加缓存

        // 请求Api
        new AsyncTask<Void, Void, ApiResponse<List<CouponBo>>>() {
            @Override
            protected ApiResponse<List<CouponBo>> doInBackground(Void... voids) {
                return api.listNewCoupon(currentPage, PAGE_SIZE);
            }

            @Override
            protected void onPostExecute(ApiResponse<List<CouponBo>> response) {
                if (listener != null && response != null) {
                    if (response.isSuccess()) {
                        listener.onSuccess(response.getObjList());
                    } else {
                        listener.onFailure(response.getEvent(), response.getMsg());
                    }
                }
            }
        }.execute();
    }
}
