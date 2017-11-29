package com.mllwf.api;

import com.mllwf.model.CouponBo;

import java.util.List;

/**
 * Created by ML on 2017/4/19.
 */

public interface Api {


    // 发送验证码(用来区分不同的接口)
    public final static String SEND_SMS_CODE = "service.sendSmsCode4Register";
    // 注册
    public final static String REGISTER = "customer.registerByPhone";
    // 登录
    public final static String LOGIN = "customer.loginByApp";
    // 券列表
    public final static String LIST_COUPON = "issue.listNewCoupon";


    /**
     * 发送验证码
     *
     * @param phoneNum 手机号码
     * @return 成功时返回：{ "event": "0", "msg":"success" }
     */
    public ApiResponse<Void> sendSmsCode4Register(String phoneNum);


    /**
     * 注册
     *
     * @param phoneNum 手机号码
     * @param code     验证码
     * @param password MD5加密的密码
     * @return 成功时返回：{ "event": "0", "msg":"success" }
     */
    public ApiResponse<Void> registerByPhone(String phoneNum, String code, String password);


    /**
     * 登录
     *
     * @param loginName 登录名（手机号）
     * @param password  MD5加密的密码
     * @param imei      手机IMEI串号
     * @param loginOS   Android为1
     * @return 成功时返回：{ "event": "0", "msg":"success" }
     */
    public ApiResponse<Void> loginByApp(String loginName, String password, String imei, int loginOS);


    /**
     * 券列表
     *
     * @param currentPage 当前页数
     * @param pageSize    每页显示数量
     * @return 成功时返回：{ "event": "0", "msg":"success", "objList":[...] }
     */
    public ApiResponse<List<CouponBo>> listNewCoupon(int currentPage, int pageSize);

}


