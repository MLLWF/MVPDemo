package com.mllwf.mvpdemo2;

import java.util.List;

/**
 * <pre>
 *     author: MLLWF
 *     time  : 2017/11/28
 *     desc  : 定义工具加工厂的能力
 *     @buy ：购买原材料的能力
 *     @work：加工制造工具的能力
 * </pre>
 */
public interface ToolFactory {

    /**
     * 购买制造工具所需要的原材料
     * @return 制造工具所需要的原材料名称集合
     */
    List<String> buy();

    /**
     * 加工原材料，返回工具名称
     */
    String work();


}
