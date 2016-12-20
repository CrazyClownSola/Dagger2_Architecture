package com.sola.github.data.repository;

import com.sola.github.data.entity.net.BBSDataEntity;
import com.sola.github.data.net.ApiConnection;
import com.sola.github.data.net.BBSService;
import com.sola.github.params.BBSDataDTO;
import com.sola.github.repository.BBSRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by zhangluji
 * 2016/12/19.
 */
public class BBSDataRepository extends AConnectionRepository implements BBSRepository {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================

    @Inject
    BBSDataRepository(ApiConnection apiConnection) {
        super(apiConnection);
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public Observable<Collection<BBSDataDTO>> requestBBSList(int sectionId, int pageNo, int pageSize) {
        // 这个方法是一个简单使用，内容只是将网络当中获取到的数据进行一次数据转换之后输出
        // 嘛……代码是这样写的，但是可能跑不同，由于没有找到一个合适的api(自己公司项目处于保密原因，api不开放，请见谅)，这方法运行了理论是报错的
        // 所以在代码上仅供参考
        Map<String, Object> map = new HashMap<>();
        map.put("sectionId", sectionId);
        map.put("pageNo", pageNo);
        map.put("pageSize", pageSize);
        return apiConnection.createService(BBSService.BASE_URL, BBSService.class)
                .requestBBSList(getEncryptStr(map))
                .flatMap(this::defaultErrorMapper)
                .flatMap(response ->
                        Observable.just(transform(response.getData().getList())));
    }

    // ===========================================================
    // Methods
    // ===========================================================

    private Collection<BBSDataDTO> transform(List<BBSDataEntity> data) {
        Collection<BBSDataDTO> retList = new LinkedList<>();
        if (data != null && data.size() != 0) {
            for (BBSDataEntity entity : data) {
                retList.add(transform(entity));
            }
        }
        return retList;
    }

    private BBSDataDTO transform(BBSDataEntity entity) {
        BBSDataDTO retDto = new BBSDataDTO();
        retDto.setId(entity.getId());
        retDto.setContent(entity.getContent());
        retDto.setCreateTime(entity.getCreateTime());
        retDto.setPic(entity.getPic());
        retDto.setServiceId(entity.getServiceId());
        retDto.setTitle(entity.getTitle());
        retDto.setUpdateTime(entity.getUpdateTime());
        return retDto;
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
