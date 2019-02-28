package kirito.peoject.baselib.UI;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import kirito.peoject.baselib.manager.permission.PermissionManager;
import kirito.peoject.baselib.manager.permission.enums.PermissionEnum;
import kirito.peoject.baselib.mvp.Persenter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author:王旭
 * @CreatTime:2019/2/26 0026
 * @LastModify(最终修改人):王旭
 * @LastModifyTime(最终修改时间):2019/2/26 0026
 * @LastChekedBy: 王旭
 * @needingAttention(注意事项):
 */
public abstract class BaseActivity extends AppCompatActivity {
    private PermissionManager permissionManager;


    public abstract void initParams(Bundle savedInstanceState);    //参数初始化

    protected abstract int setViewID();//初始化布局

    protected abstract void initView();//初始化布局

    public abstract void initData();//初始化数据

    public abstract Bundle saveParam(Bundle outState);//保存数据，原本可以封装到此处，不外抛出，由于害怕用户有其他需要保存的数据，目前抛出


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置键盘遮挡界面
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        try {
            initParams(savedInstanceState);
            initPersenter();

        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(setViewID());
    }

    public void initPersenter() throws InstantiationException, IllegalAccessException {
        Field[] fields = getClass().getDeclaredFields();
        List<Field> result = new ArrayList<Field>();
        for (Field field : fields) {
            if (field.getAnnotation(Persenter.class) != null)
                result.add(field);
        }

        for (Field list : result) {
            list.set(list.getClass().newInstance(), list.getClass().newInstance());
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        //调用接口，向服务器上传登录时间和类名

    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        permissionManager = null;
        System.gc();
    }


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initView();
        initData();

    }


    //获取权限使用类
    public PermissionManager getPermissionManager() {
        if (permissionManager == null) {
            permissionManager = new PermissionManager();
        }
        return permissionManager;
    }


    public void onBackClick(View view) {
        finish();
    }

    public String getClassName() {
        String simpleClassName = this.getClass().getSimpleName();
        return simpleClassName;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        Bundle outStates = saveParam(outState);
        if (outStates != null) {
            super.onSaveInstanceState(outStates);
        } else {
            super.onSaveInstanceState(outState);
        }
    }

    /**
     * 辅助子类权限请求
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (PermissionEnum.getPermissionEnumByCode(requestCode) != null) {
            if (permissionManager != null) {
                permissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }

            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public boolean hasEdittextChange = true;

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (hasEdittextChange && v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

}
