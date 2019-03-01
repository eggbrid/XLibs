package kirito.peoject.xlibs.demo.view;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import kirito.peoject.baselib.mvp.BaseV;
import kirito.peoject.xlibs.R;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/3/1 0001
 */
public class DemoView extends BaseV {
    public Button jump;
    public DemoView(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public int setViewLayout() {
        return R.layout.demo_activity;
    }

    @Override
    public void initView() {
        jump=findViewById(R.id.jump);
    }
}
