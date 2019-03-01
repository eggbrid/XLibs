package kirito.peoject.xlibs.demo.activity;

import android.view.View;
import kirito.peoject.baselib.UI.BaseActivity;
import kirito.peoject.baselib.thirdPart.ARouter.LibJumpHelper;
import kirito.peoject.constantlibs.UIConstant.activity.TestLibs;
import kirito.peoject.xlibs.demo.view.DemoView;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/2/26 0026
 */
public class DemoActivity extends BaseActivity<DemoView> {

    @Override
    public void afterInitView(DemoView v) {
       v.jump.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == view.jump) {
            LibJumpHelper.startActivity(TestLibs.TestLibs_MainActivity);
        }
    }
}
