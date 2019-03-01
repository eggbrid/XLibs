package kirito.peoject.testlibs.test.net.server;

import io.reactivex.Observable;
import kirito.peoject.testlibs.test.net.model.TestM;
import retrofit2.http.GET;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/2/28 0028
 * @LastModify(最终修改人):kirito
 * @LastModifyTime(最终修改时间):2019/2/28 0028
 * @LastChekedBy: kirito
 * @needingAttention(注意事项):
 */
public interface NetTestS {

    @GET("openapi.do?keyfrom=Yanzhikai&key=2032414398&type=data&doctype=json&version=1.1&q=car")
    Observable<TestM> getTestJson();
}
