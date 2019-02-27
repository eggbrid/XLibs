package kirito.peoject.baselib.thirdPart.Retrofit;

import com.google.gson.TypeAdapter;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;
import java.io.Reader;

/**
 * @Description:
 * @Author:kirito
 */
final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override public T convert(ResponseBody value) throws IOException {
        Reader reader = value.charStream();
        try {
            return adapter.fromJson(reader);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignored) {
                }
            }
        }
    }
}
