package com.sola.github.domain.interactor;

import com.sola.github.domain.ErrorDTO;
import com.sola.github.domain.ErrorDelegate;
import com.sola.github.domain.NetExecutorThread;
import com.sola.github.domain.UIExecutorThread;
import com.sola.github.params.BBSDataDTO;

import java.util.Collection;

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

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
