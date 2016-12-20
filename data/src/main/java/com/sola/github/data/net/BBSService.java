package com.sola.github.data.net;

import com.sola.github.data.entity.net.ABaseResponseEntity;
import com.sola.github.data.entity.net.ABaseResponseMoreListEntity;
import com.sola.github.data.entity.net.BBSDataEntity;
import com.sola.github.data.utils.RestConfig;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by zhangluji
 * 2016/12/19.
 */
public interface BBSService {
    // ===========================================================
    // Constants
    // ===========================================================

    String BASE_URL = RestConfig.BASE_URL;

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    /**
     * @param entryStr 请求字段，由于服务的实现方式是将参数 以json的形式放在body里面的，
     *                 所以这里面采用下面这种写法去请求数据
     *                 具体实现方式可以根据服务的实现而定，具体参考文档(http://square.github.io/retrofit/)
     * @return 返回Rx队列
     */
    @POST("/bbs/getBbsBySec")
    Observable<ABaseResponseEntity<ABaseResponseMoreListEntity<BBSDataEntity>>> requestBBSList(@Body String entryStr);

}
