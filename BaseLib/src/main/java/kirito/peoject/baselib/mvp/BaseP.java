package kirito.peoject.baselib.mvp;

import io.reactivex.Observable;
import kirito.peoject.baselib.thirdPart.Retrofit.NetCallBack;
import kirito.peoject.baselib.thirdPart.Retrofit.XRetrofit;
import kirito.peoject.baselib.util.GetGenericUtil;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/2/26 0026
 */
public class BaseP<S> {

    public S getService() {
        GetGenericUtil<S> getGenericUtil=new GetGenericUtil<>();

        return XRetrofit.getServerCall(getGenericUtil.getTClass());
    }

    public <T> Observable<T> request(Observable<T> observable, NetCallBack netCallBack) {
        return XRetrofit.toRequest(observable, netCallBack);
    }
}
