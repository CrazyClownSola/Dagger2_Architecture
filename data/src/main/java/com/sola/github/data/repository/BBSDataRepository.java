package com.sola.github.data.repository;

import com.sola.github.data.entity.net.BBSDataEntity;
import com.sola.github.data.entity.net.BBSPostsEntity;
import com.sola.github.data.entity.net.BBSPostsReplyEntity;
import com.sola.github.data.net.AApiConnection;
import com.sola.github.data.net.BBSService;
import com.sola.github.data.scope.HttpRestAdapter;
import com.sola.github.domain.params.params.bbs.BBSDataDTO;
import com.sola.github.domain.params.params.bbs.BBSPostsMainReplyDTO;
import com.sola.github.domain.params.params.bbs.BBSPostsSingleReplyDTO;
import com.sola.github.domain.repository.repository.BBSRepository;

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

    /**
     * @param apiConnection {@link HttpRestAdapter}定义了实现为ApiHttpConnection
     */
    @Inject
    BBSDataRepository(@HttpRestAdapter AApiConnection apiConnection) {
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

    @Override
    public Observable<Collection<BBSPostsMainReplyDTO>> requestBBSPostsList(int postsId) {
        Map<String, Object> map = new HashMap<>();
        map.put("postsId", postsId);
        map.put("orderBy", 1);
        return apiConnection.createService(BBSService.BASE_URL, BBSService.class)
                .requestBBSPostsList(getEncryptStrByMap(map))
                .flatMap(this::defaultErrorMapper)
                .flatMap(response -> {
                    if (response.getData() == null)
                        return Observable.error(new NullPointerException("request raiders response is null"));
                    else
                        return Observable.just(replyListTransform(response.getData().getList()));
                });
    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ------------- 分割线(这里的transform可以考虑使用一个同意的mapper 类去做处理，当然也可以就直接写在这里) --------------

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
        retDto.setDisplayTime(entity.getTimeStr());
        retDto.setPic(entity.getPic());
        retDto.setServiceId(entity.getServiceId());
        retDto.setTitle(entity.getTitle());

        retDto.setAppLink(entity.getAppLink());
        retDto.setAppPkgName(entity.getAppApkName());
        retDto.setReplyCount(entity.getReplyCount());

        return retDto;
    }

    private Collection<BBSPostsMainReplyDTO> replyListTransform(List<BBSPostsEntity> list) {
        Collection<BBSPostsMainReplyDTO> retList = new LinkedList<>();
        if (list != null)
            for (BBSPostsEntity entity : list) {
                retList.add(replyTransform(entity));
            }
        return retList;
    }

    private BBSPostsMainReplyDTO replyTransform(BBSPostsEntity entity) {
        BBSPostsMainReplyDTO retDto = new BBSPostsMainReplyDTO();
        retDto.setDateTime(entity.getTimeStr());
        retDto.setTimeStamp(entity.getTimestamp());
        retDto.setLikeCount(entity.getLikeCount());
        retDto.setReplyCount(entity.getReplyCount());
        retDto.setStatus(entity.getStatus());
        retDto.setBbsId(entity.getBbsId());
        retDto.setContent(entity.getContent());
        retDto.setId(entity.getId());
        retDto.setPostsId(entity.getPostsId());
        retDto.setUserId(entity.getUserId());
        retDto.setUserName(entity.getUserName());
        retDto.setUserPic(entity.getUserPic());
        retDto.setReplyList(itemTransform(entity.getReplyList()));
        return retDto;
    }

    private List<BBSPostsSingleReplyDTO> itemTransform(List<BBSPostsReplyEntity> replyList) {
        List<BBSPostsSingleReplyDTO> retList = new LinkedList<>();
        if (replyList != null) {
            for (BBSPostsReplyEntity entity :
                    replyList) {
                retList.add(itemTransform(entity));
            }
        }
        return retList;
    }

    private BBSPostsSingleReplyDTO itemTransform(BBSPostsReplyEntity entity) {
        BBSPostsSingleReplyDTO retDto = new BBSPostsSingleReplyDTO();
        retDto.setUserPic(entity.getUserPic());
        retDto.setUserName(entity.getUserName());
        retDto.setUserId(entity.getUserId());
        retDto.setReplyUserId(entity.getReplyUserId());
        retDto.setReplyUserName(entity.getReplyUserName());
        retDto.setBbsId(entity.getBbsId());
        retDto.setContent(entity.getContent());
        retDto.setDateTime(entity.getTimeStr());
        retDto.setTimeStamp(entity.getTimestamp());
        retDto.setId(entity.getId());
        retDto.setPostsId(entity.getPostsId());
        return retDto;
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
