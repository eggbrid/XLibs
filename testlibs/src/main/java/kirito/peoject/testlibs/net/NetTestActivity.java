package kirito.peoject.testlibs.net;

import android.os.Bundle;
import com.alibaba.android.arouter.facade.annotation.Route;
import kirito.peoject.baselib.UI.BaseActivity;
import kirito.peoject.constantlibs.UIConstant.activity.TestLibs;
import kirito.peoject.testlibs.R;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/2/26 0026
 * @LastModify(最终修改人):kirito
 * @LastModifyTime(最终修改时间):2019/2/26 0026
 * @LastChekedBy: kirito
 * @needingAttention(注意事项):
 */
@Route(path = TestLibs.TestLibs_net_NetTestActivity)
public class NetTestActivity extends BaseActivity {
    @Override
    public void initParams(Bundle savedInstanceState) {

    }

    @Override
    protected int setViewID() {
        return R.layout.net_test_activity;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public Bundle saveParam(Bundle outState) {
        return null;
    }
}
