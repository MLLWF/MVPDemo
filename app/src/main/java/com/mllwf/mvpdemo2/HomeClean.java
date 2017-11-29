package com.mllwf.mvpdemo2;

/**
 * <pre>
 *     author: MLLWF
 *     time  : 2017/11/28
 *     desc  :  定义房屋的能力
 *     @tellRequest ：提供打扫卫生所需要的工具的能力
 *     @clean ：打扫卫生的能力
 * </pre>
 */
public interface HomeClean {

    String tellRequest();

    void clean();

}
