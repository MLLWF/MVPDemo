package com.mllwf.mvpdemo2;

import java.util.List;

/**
 * <pre>
 *     author: MLLWF
 *     time  : 2017/11/28
 *     desc  :  定义原材料提供厂商的能力
 *     @search：根据订单查询原材料的能力
 * </pre>
 */
public interface ToolResouce {
    /**
     * 根据原材料订单查找所需要的原材料
     * @param list 原材料订单集
     * @return 订单所需要的原材料
     */
    List<String> search(List<String> list);
}
