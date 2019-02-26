package kirito.peoject.baselib.UI;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import kirito.peoject.baselib.manager.permission.PermissionManager;

/**
 * @Description:
 * @Author:王旭
 * @CreatTime:2019/2/26 0026
 * @LastModify(最终修改人):王旭
 * @LastModifyTime(最终修改时间):2019/2/26 0026
 * @LastChekedBy: 王旭
 * @needingAttention(注意事项):
 */
public abstract class BaseFragment extends Fragment {

    /**
     * 请求队列。
     */
    public Activity activity;
    private boolean isInitView = false;//是否与View建立起映射关系[初始化视图]
    private boolean isFirstLoad = true;//第一次加载
    private PermissionManager permissionManager;
    public boolean isFirstLoad() {
        return isFirstLoad;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadData();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     * 权限使用类
     *
     * @return
     */
    public PermissionManager getPermissionManager() {
        if (permissionManager == null) {
            permissionManager = new PermissionManager();

        }

        return permissionManager;
    }

    public boolean isInitBar() {
        return true;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        isInitView = true;//视图view已经初始化完毕
        isCanLoadData();
    }


    public abstract void initView(View view);

    /**
     * 是否可以加载数据
     * 可以加载数据的条件：
     * 1.视图已经初始化
     * 2.视图对用户可见
     */
    private void isCanLoadData() {
        if (!isInitView) {
            return;
        }
        //视图可见 已经初始化 第一次加载去初始化数据
        if (getUserVisibleHint() && isFirstLoad) {
            initData();
            isFirstLoad = false;
        }
    }

    public abstract void initData();

    public boolean isInitView() {
        return isInitView;
    }

    public void setInitView(boolean initView) {
        isInitView = initView;
    }


    public String getClassName() {
        String simpleClassName = this.getClass().getSimpleName();
        return simpleClassName;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionManager != null) {
            permissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            onResume();
        }
    }
}
