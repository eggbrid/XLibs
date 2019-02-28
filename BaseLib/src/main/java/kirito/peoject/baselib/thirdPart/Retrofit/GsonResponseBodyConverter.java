package kirito.peoject.baselib.thirdPart.Retrofit;

import com.google.gson.TypeAdapter;
import kirito.peoject.baselib.BaseLib;
import kirito.peoject.baselib.mvp.BaseM;
import kirito.peoject.baselib.util.GetGenericUtil;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;

/**
 * @Description:
 * @Author:kirito
 */
final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;
    private ResponseInterceptor responseInterceptor;

    GsonResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
        try {
            responseInterceptor = BaseLib.xRetrofitConfig.getResponseInterceptorInstance();
        } catch (Exception e) {
            responseInterceptor = null;
        }
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        if (responseInterceptor != null) {
            response = responseInterceptor.doBeforeParse();
        }
        GetGenericUtil<T> getGenericUtil = new GetGenericUtil<T>();
        Class<T> tClass = getGenericUtil.getTClass();
        T t = null;
        try {
            if (tClass != null&& t instanceof BaseM) {
                t = tClass.newInstance();
                t = ((BaseM)t).toBean(response);
            } else {
                t = adapter.fromJson(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (responseInterceptor != null) {
            t = responseInterceptor.doAfterParse(t);
        }
        return t;
    }
}
