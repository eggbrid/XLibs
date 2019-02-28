package kirito.peoject.testlibs.persenter;

import io.reactivex.Observable;
import kirito.peoject.baselib.mvp.BaseP;
import kirito.peoject.baselib.thirdPart.Retrofit.NetCallBack;
import kirito.peoject.testlibs.model.TestM;
import kirito.peoject.testlibs.server.TestS;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/2/28 0028
 */
public class TestP extends BaseP<TestS> {

    public Observable<TestM> getTestJson(NetCallBack<TestM> callBack) {
        return request(getService().getTestJson(), callBack);
    }
}
