package com.mllwf.mvpdemo2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.mllwf.mvpdemo.R;

/**
 * <pre>
 *     author: MLLWF
 *     time  : 2017/11/28
 *     desc  :房屋类，实现了View视图接口，也就具备了View视图的能力
 * </pre>
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener, HomeClean {
    //存储工具加工厂实现类，这里采用的是面向对象思想的一个特性：动态绑定，即父类的句柄，但是实际是子类的对象，这样便于对不同子类进行统一的处理
    private ToolFactory mFactory;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_view);
        findViewById(R.id.btn_clean).setOnClickListener(this);
        //实例化工具加工厂，动态绑定
        mFactory = new ToolFactoryImpl(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_clean) {
            //打扫房屋动作
            clean();
        }
    }

    @Override
    public String tellRequest() {
        return "打扫完毕！";
    }

    @Override
    public void clean() {
        String status = mFactory.work();
        Toast.makeText(HomeActivity.this, status, Toast.LENGTH_SHORT).show();
    }
}
