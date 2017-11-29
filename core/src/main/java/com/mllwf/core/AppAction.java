package com.mllwf.core;

import com.mllwf.model.CouponBo;

import java.util.List;

/**
 * Created by ML on 2017/4/19.
 */

public interface AppAction {
    // 发送手机验证码
    public void sendSmsCode(String phoneNum, ActionCallbackListener<Void> listener);

    // 注册
    public void register(String phoneNum, String code, String password, ActionCallbackListener<Void> listener);

    // 登录
    public void login(String loginName, String password, ActionCallbackListener<Void> listener);

    // 按分页获取券列表
    public void listCoupon(int currentPage, ActionCallbackListener<List<CouponBo>> listener);
}
