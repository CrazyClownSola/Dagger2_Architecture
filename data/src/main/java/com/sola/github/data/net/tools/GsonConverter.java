package com.sola.github.data.net.tools;

import com.google.gson.Gson;
import com.sola.github.tools.utils.LogUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by zhangluji
 * 2016/12/20.
 */
public class GSONConverter extends Converter.Factory {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private final Gson gson;

    // ===========================================================
    // Constructors
    // ===========================================================

    private GSONConverter(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public static GSONConverter create() {
        return create(new Gson());
    }

    public static GSONConverter create(Gson gson) {
        return new GSONConverter(gson);
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new GSONResponseBodyConverter<>(gson, type);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new GSONRequestBodyConverter<>();
    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    private final class GSONResponseBodyConverter<T> implements Converter<ResponseBody, T> {
        private final Gson gson;
        private final Type type;

        GSONResponseBodyConverter(Gson gson, Type type) {
            this.gson = gson;
            this.type = type;
        }

        @Override
        public T convert(ResponseBody value) throws IOException {
            try {
                String resultStr = value.string();
                LogUtils.i("Sola_Connect", "response: [" + resultStr + "]");
                return gson.fromJson(resultStr, type);
            } finally {
                value.close();
            }
        }
    }

    private final class GSONRequestBodyConverter<T> implements Converter<T, RequestBody> {
        private final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

        @Override
        public RequestBody convert(T value) throws IOException {
            RequestBody body = null;
            if (value instanceof String) {
                body = RequestBody.create(MEDIA_TYPE, (String) value);
                LogUtils.i("Sola_Connect", "request convert: [" + value + "]");
            } else {
                String jsonStr = gson.toJson(value);
                if (jsonStr != null) {
                    LogUtils.i("Sola_Connect", "request convert: [" + jsonStr + "]");
                    body = RequestBody.create(MEDIA_TYPE, jsonStr);
                }
            }
            return body;
        }
    }
}
