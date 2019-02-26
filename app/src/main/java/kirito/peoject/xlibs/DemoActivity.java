package kirito.peoject.xlibs;

import android.os.Bundle;
import android.view.View;
import kirito.peoject.baselib.UI.BaseActivity;
import kirito.peoject.baselib.thirdPart.ARouter.ARouterHelper;
import kirito.peoject.constantlibs.UIConstant.activity.BaseLibsActivitys;
import kirito.peoject.constantlibs.UIConstant.activity.TestLibs;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/2/26 0026
 * @LastModify(最终修改人):kirito
 * @LastModifyTime(最终修改时间):2019/2/26 0026
 * @LastChekedBy: kirito
 * @needingAttention(注意事项):
 */
public class DemoActivity extends BaseActivity {
    @Override
    public void initParams(Bundle savedInstanceState) {

    }

    @Override
    protected int setViewID() {
        return R.layout.demo_activity;
    }

    @Override
    protected void initView() {
        findViewById(R.id.jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouterHelper.startActivity(TestLibs.TestLibs_MainActivity);
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
