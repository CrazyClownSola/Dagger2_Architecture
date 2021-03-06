package com.sola.github.domain.interactor;

import com.sola.github.domain.exception.ErrorDTO;
import com.sola.github.domain.exception.ErrorDelegate;
import com.sola.github.domain.executor.NetExecutorThread;
import com.sola.github.domain.executor.UIExecutorThread;
import com.sola.github.domain.params.params.bbs.BBSDataDTO;
import com.sola.github.domain.params.params.bbs.BBSPostsMainReplyDTO;

import java.util.Collection;
import java.util.List;

import rx.functions.Action1;

/**
 * Created by slove
 * 2016/12/19.
 * BBS业务模型
 */
public abstract class ABBSCase extends ABaseConnectionCase {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================

    protected ABBSCase(
            NetExecutorThread threadExecutor,
            UIExecutorThread uiExecutor,
            ErrorDelegate errorDelegate) {
        super(threadExecutor, uiExecutor, errorDelegate);
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    public abstract void searchBBSList(int pageCount, int pageSize, Action1<Collection<BBSDataDTO>> onNext, Action1<ErrorDTO> onError);

    public abstract void getPostsReplyList(
            int postsId, Action1<List<BBSPostsMainReplyDTO>> onNext, Action1<ErrorDTO> onError);

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
