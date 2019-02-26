package kirito.peoject.xlibs;

import android.app.Application;
import kirito.peoject.baselib.BaseLib;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/2/26 0026
 * @LastModify(最终修改人):kirito
 * @LastModifyTime(最终修改时间):2019/2/26 0026
 * @LastChekedBy: kirito
 * @needingAttention(注意事项):
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BaseLib.init(this, BuildConfig.DEBUG);
    }
}
