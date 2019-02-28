package kirito.peoject.testlibs;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import kirito.peoject.baselib.UI.BaseActivity;
import kirito.peoject.baselib.mvp.Persenter;
import kirito.peoject.baselib.thirdPart.Retrofit.NetCallBack;
import kirito.peoject.baselib.util.IntentUtil;
import kirito.peoject.constantlibs.UIConstant.activity.TestLibs;
import kirito.peoject.testlibs.model.TestM;
import kirito.peoject.testlibs.net.NetTestActivity;
import kirito.peoject.testlibs.persenter.TestP;

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
    @Persenter
    TestP testP;
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
        TestP testP=new TestP();
        testP.getTestJson(new NetCallBack<TestM>() {
            @Override
            public void onGetData(TestM testM) {
                Log.e("wangxu",testM.getJson());
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onFailure() {

            }
        });

    }

    @Override
    public Bundle saveParam(Bundle outState) {
        return null;
    }
}
