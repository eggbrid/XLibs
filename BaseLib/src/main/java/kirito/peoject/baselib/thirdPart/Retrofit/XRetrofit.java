package kirito.peoject.baselib.thirdPart.Retrofit;

import kirito.peoject.baselib.BaseLib;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public static  <T> void toRequset(Call<T> call,Callback<T> callback){
        call.enqueue(callback);
    }
}
