package kirito.peoject.baselib.thirdPart.Retrofit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kirito.peoject.baselib.BaseLib;

import java.util.List;

/**
 * @Description:
 * @Author:kirito
 * @CreatTime:2019/2/27 0027
 * @LastModify(最终修改人):kirito
 * @LastModifyTime(最终修改时间):2019/2/27 0027
 * @LastChekedBy: kirito
 * @needingAttention(注意事项):
 */
public class XRetrofit {
    /**
     *before use this method you need init{@link BaseLib} XRetrofitConfig in application
     *
     *
     *in Retrofit you need use code like this
     * <p>
     * <pre>{@code
     * Retrofit retrofit = new Retrofit.Builder()
     *     .baseUrl("http://api.example.com")
     *     .addConverterFactory(GsonConverterFactory.create())
     *     .build();
     *
     * MyApi api = retrofit.create(MyApi.class);
     * Response<User> user = api.getUser().execute();
     * }</pre>
     * <p>
     * but,in XRetrofit you only use this method to create server instance
     *
     *
     * @param server
     */
    public static <T> T getServerCall(Class<T> server) {
        RetrofitHelper.Builder retrofitHelperBuilder = new RetrofitHelper.Builder();
        retrofitHelperBuilder = retrofitHelperBuilder.baseUrl(BaseLib.xRetrofitConfig.httpUrl);
        try {
            retrofitHelperBuilder = retrofitHelperBuilder.retrofitInterceptor(BaseLib.xRetrofitConfig.getInterceptorInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            retrofitHelperBuilder = retrofitHelperBuilder.commentParamsAdapter(BaseLib.xRetrofitConfig.getCommentParamsAdapterInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        retrofitHelperBuilder = retrofitHelperBuilder.converterFactory(BaseLib.xRetrofitConfig.factory);
        T tImpl = retrofitHelperBuilder.build().toRetrofit().create(server);
        return tImpl;
    }

    public  static <T> Observable<T> toRequest(Observable <T> observable, NetCallBack netCallBack , List<Disposable> disposables){
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new ResponseCallBack<T>(netCallBack,disposables){

        });
        return observable;
    }
}
