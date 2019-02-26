package kirito.peoject.testlibs;

import android.os.Bundle;
import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import kirito.peoject.baselib.UI.BaseActivity;
import kirito.peoject.baselib.util.IntentUtil;
import kirito.peoject.constantlibs.UIConstant.activity.TestLibs;
import kirito.peoject.testlibs.net.NetTestActivity;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/2/26 0026
 * @LastModify(最终修改人):kirito
 * @LastModifyTime(最终修改时间):2019/2/26 0026
 * @LastChekedBy: kirito
 * @needingAttention(注意事项):
 */
@Route(path = TestLibs.TestLibs_MainActivity)
public class MainActivity extends BaseActivity {
    @Override
    public void initParams(Bundle savedInstanceState) {

    }

    @Override
    protected int setViewID() {
        return R.layout.test_main;
    }

    @Override
    protected void initView() {
        findViewById(R.id.net).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtil.startActivity(MainActivity.this,NetTestActivity.class);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public Bundle saveParam(Bundle outState) {
        return null;
    }
}
