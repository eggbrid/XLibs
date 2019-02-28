package kirito.peoject.xlibs;

import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import kirito.peoject.baselib.UI.BaseActivity;
import kirito.peoject.baselib.thirdPart.ARouter.ARouterHelper;
import kirito.peoject.baselib.thirdPart.Retrofit.XRetrofit;
import kirito.peoject.constantlibs.UIConstant.activity.TestLibs;

import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Response;

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
        View view = findViewById(R.id.jump);
        view.setOnClickListener(new View.OnClickListener() {
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
