package kirito.peoject.baselib.mvp;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import kirito.peoject.baselib.thirdPart.Retrofit.NetCallBack;
import kirito.peoject.baselib.thirdPart.Retrofit.XRetrofit;
import kirito.peoject.baselib.util.GetGenericUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/2/26 0026
 */
public class BaseP<S> {
    private List<Disposable> disposables = new ArrayList<>();

    public S getService() {
        return XRetrofit.getServerCall(getTClass());
    }

    public <T> Observable<T> request(Observable<T> observable, NetCallBack netCallBack) {
        return XRetrofit.toRequest(observable, netCallBack, disposables);
    }

    public void cancel() {
        if (disposables != null && disposables.size() > 0) {
            for (Disposable disposable : disposables) {
                if (disposable != null && !disposable.isDisposed()) {
                    disposable.dispose();
                }
            }
            disposables.clear();
        }
    }

    //得到泛型类T
    public Class<S> getTClass() {
        Type type = getClass().getGenericSuperclass(); // 判断 是否泛型
        if (type instanceof ParameterizedType) { // 返回表示此类型实际类型参数的Type对象的数组. // 当有多个泛型类时，数组的长度就不是1了
            Type[] ptype = ((ParameterizedType) type).getActualTypeArguments();
            return (Class) ptype[0]; //将第一个泛型T对应的类返回（这里只有一个）
        } else if (type instanceof Class) {
            return (Class) type;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type rawType = parameterizedType.getRawType();
            return (Class) rawType;
        } else {
            return null;


        }
    }

}
