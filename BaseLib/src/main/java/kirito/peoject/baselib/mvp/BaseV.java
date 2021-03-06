package kirito.peoject.baselib.mvp;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/2/26 0026
 */
public abstract class BaseV {
    protected AppCompatActivity activity;
    protected View fragmentView;

    public void setFragmentView(View fragmentView) {
        this.fragmentView = fragmentView;
    }

    public BaseV(AppCompatActivity activity) {
        this.activity = activity;
    }

    public abstract int setViewLayout();

    public abstract void initView();

    public <T extends View> T findViewById(@IdRes int id) {
        if (fragmentView != null) {
            return fragmentView.findViewById(id);
        }
        return activity.getDelegate().findViewById(id);
    }

}
