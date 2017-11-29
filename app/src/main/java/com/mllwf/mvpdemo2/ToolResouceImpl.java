package com.mllwf.mvpdemo2;

import java.util.List;

/**
 * <pre>
 *     author: MLLWF
 *     time  : 2017/11/28
 *     desc  : 原材料接口能力实现类
 * </pre>
 */
public class ToolResouceImpl implements ToolResouce {
    @Override
    public List<String> search(List<String> list) {
        if (list != null) {
            return list;
        }
        return null;
    }
}
