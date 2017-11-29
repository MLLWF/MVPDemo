package com.mllwf.mvpdemo2;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author: MLLWF
 *     time  : 2017/11/28
 *     desc  : 工具加工厂能力实现类
 * </pre>
 */
public class ToolFactoryImpl implements ToolFactory {
    //存储View部分实现类，这里采用的是面向对象思想的一个特性：动态绑定，即父类的句柄，但是实际是子类的对象，这样便于对不同子类进行统一的处理
    private HomeClean mClean;

    /**
     * 构造方法必须传递一个View视图部分的实现类，完成绑定
     * @param mClean
     */
    public ToolFactoryImpl(HomeClean mClean) {
        this.mClean = mClean;
    }

    @Override
    public List<String> buy() {
        //获取View视图部分告诉的工具的名称
        String tool = mClean.tellRequest();
        if (tool != null) {
            //将工具分解成所需要的原材料集合，并进行购买
            List<String> list = new ArrayList<>();
            list.add("卧室：" + tool);
            list.add("厨房：" + tool);
            list.add("卫生间：" + tool);
            list.add("客厅：" + tool);
            return list;
        }
        return null;
    }

    @Override
    public String work() {
        //获取工具资源实现类
        ToolResouceImpl toolResouce = new ToolResouceImpl();
        List<String> resouceList = new ArrayList<>();
        //根据购买原材料集合名单，进行查询，并返回工具原材料集(这里为了简便，就直接返回原材料的名称)
        resouceList = toolResouce.search(buy());
        //拿到原材料集，进行加工制造，返回View视图部分所需要的打扫工具(这里也只返回工具的名字)
        if (resouceList != null) {
            StringBuffer buffer = new StringBuffer();
            for (String str : resouceList) {
                if (buffer.length() == 0) {
                    buffer.append(str);
                } else {
                    buffer.append(",").append(str);
                }
            }
            return buffer.toString();
        } else {
            return "数据出错了！";
        }
    }
}
