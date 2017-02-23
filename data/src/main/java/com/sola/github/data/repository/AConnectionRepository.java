package com.sola.github.data.repository;

import android.accounts.NetworkErrorException;

import com.google.gson.Gson;
import com.sola.github.data.entity.BaseResultEntity;
import com.sola.github.data.net.AApiConnection;
import com.sola.github.data.net.ApiConnection;
import com.sola.github.tools.utils.LogUtils;

import java.util.Map;

import rx.Observable;

/**
 * Created by zhangluji
 * 2016/12/19.
 */
@SuppressWarnings("unused")
abstract class AConnectionRepository {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    final AApiConnection apiConnection;

    private final Gson mGson;

    // ===========================================================
    // Constructors
    // ===========================================================

    AConnectionRepository(AApiConnection apiConnection) {
        this.apiConnection = apiConnection;
        this.mGson = new Gson();
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    /**
     * @param map 暂且不支持复杂参数
     * @return fanhuiStr
     */
    String getEncryptStrByMap(Map<String, Object> map) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        if (map != null && map.size() != 0) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                builder.append("\"").append(entry.getKey()).append("\":");
                if (entry.getValue() instanceof String) {
                    builder.append("\"").append(entry.getValue()).append("\"");
                } else builder.append(entry.getValue());
                if (i != map.size() - 1)
                    builder.append(",");
                i++;
            }
            builder.insert(0, "{").append("}");
        }
        String str = builder.toString();
        LogUtils.i(str);
        return str;
    }

    /**
     * @param src 传入需要转换的参数
     * @return 返回经过Gson转换之后的字符串
     */
    String getEncryptStr(Object src) {
        String str = mGson.toJson(src);
        LogUtils.i(str);
        return str;
    }

    <T extends BaseResultEntity> Observable<T> defaultErrorMapper(T dto) {
        if (dto == null) {
            return Observable.error(new NullPointerException());
        } else if (!dto.isSuccess()) {
            return Observable.error(new NetworkErrorException(dto.getMessage()));
        } else
            return Observable.just(dto);
    }


    <T extends BaseResultEntity, R> Observable<R> defaultErrorMapper(T dto, R ret) {
        if (dto == null) {
            return Observable.error(new NullPointerException());
        } else if (!dto.isSuccess()) {
            return Observable.error(new NetworkErrorException(dto.getMessage()));
        } else
            return Observable.just(ret);
    }


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
