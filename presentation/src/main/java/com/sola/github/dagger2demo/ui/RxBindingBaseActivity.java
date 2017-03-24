package com.sola.github.dagger2demo.ui;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.sola.github.dagger2demo.R;

import java.lang.ref.WeakReference;

/**
 * Created by Sola
 * 2017/3/17.
 */
@SuppressWarnings("unused")
public abstract class RxBindingBaseActivity extends AppCompatActivity {

    // ===========================================================
    // Constants
    // ===========================================================

    private static final String TITLE_EXTRA = "title";

    private static final String MENU_ID_EXTRA = "menu_id";

    // ===========================================================
    // Fields
    // ===========================================================

    protected Toolbar id_tool_bar;

    protected int menu_id = -1;

    protected String title;

    protected final WeakReference<Context> mContext = new WeakReference<>(this);

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (menu_id == -1)
            return super.onCreateOptionsMenu(menu);
        else {
            getMenuInflater().inflate(menu_id, menu);
            return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //注意这里是设置了getSupportActionBar().setDisplayHomeAsUpEnabled(true);之后才有效的
                onBackPressed();// 当返回键被主动点击之后
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setIntent(Intent newIntent) {
        super.setIntent(newIntent);
        Bundle extras_ = getIntent().getExtras();
        if (extras_ != null) {
            if (extras_.containsKey(TITLE_EXTRA))
                title = extras_.getString(TITLE_EXTRA, "");
            if (extras_.containsKey(MENU_ID_EXTRA))
                menu_id = extras_.getInt(MENU_ID_EXTRA, -1);
        }
        initExtras(newIntent);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        initView(layoutResID);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.fade_in_left, R.anim.activity_back);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, R.anim.push_right_out);
    }

    // ===========================================================
    // Methods
    // ===========================================================

    protected Context getContext() {
        return mContext.get();
    }

    protected void initView(@LayoutRes int resId) {
        injectBinding(resId);
        injectExtras_();
        // 暂且没找到很好的替代方法
        id_tool_bar = (Toolbar) findViewById(R.id.id_tool_bar);
        if (id_tool_bar != null) {
            setSupportActionBar(id_tool_bar);
            if (getSupportActionBar() != null) // 纯粹是为了去除一个警告
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        doAfterView();
    }

    private void injectExtras_() {
        Bundle extras_ = getIntent().getExtras();
        if (extras_ != null) {
            if (extras_.containsKey(TITLE_EXTRA)) {
                title = extras_.getString(TITLE_EXTRA);
            }
            if (extras_.containsKey(MENU_ID_EXTRA)) {
                menu_id = extras_.getInt(MENU_ID_EXTRA);
            }
        }
        initExtras(getIntent());
    }

    protected <T extends ViewDataBinding> T buildBinding(@LayoutRes int resId) {
//        return DataBindingUtil.inflate(
//                getLayoutInflater(), resId,
//                (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content), true);
        ViewGroup rootView = (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
        View view = rootView.getChildAt(0);

//        if (view. == resId) {
//
//        }
        return DataBindingUtil.bind(view);
    }

    protected void initExtras(Intent intent) {
        //自定义实现
    }

    /**
     * @param newFrag    新的Fragment
     * @param isDestory  <code>false</code> 自定义返回键操作
     *                   {@link FragmentManager#popBackStack()},会依次唤醒在堆栈内,标志位为false的
     *                   {@link Fragment}; 当popBackStack()调用的是自身的时候,进入Fragment的自行销毁流程
     *                   <code>true</code> 自定义返回键操作
     *                   {@link FragmentManager#popBackStack()}
     *                   ,不会对该标志位为true的任何Fragment产生影响,不会调用该Fragment的生命周期,想要调用该声明周期的销毁流程
     *                   ,请参见方法{@link FragmentTransaction#remove(Fragment)}
     * @param resourceId 所要替换的Fragment的ResourceId
     */
    public void replaceFragment(Fragment newFrag, boolean isDestory,
                                int resourceId) {
        if (getSupportFragmentManager() != null) {
            // mManager.
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(resourceId, newFrag);
            if (!isDestory)
            /*
             * 在后退栈中保存被替换的Fragment的状态 添加这句话后， 用户按返回键会将前面的所有动作反向执行一次（事务回溯）
             */
                transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
        }
    }

    /**
     * 单向性质的添加{@link Fragment}到 @param resourceId中去,只是添加
     *
     * @param newFrag    新的Fragment
     * @param isDestory  {@see replaceFragment()}
     * @param resourceId 所要替换的Fragment的ResourceId
     */
    @SuppressWarnings("UnusedDeclaration")
    public void addFragment(Fragment newFrag, boolean isDestory, int resourceId) {
        if (getSupportFragmentManager() != null) {
            // mManager.
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(resourceId, newFrag, null);
            if (!isDestory)
            /*
             * 在后退栈中保存被替换的Fragment的状态 添加这句话后， 用户按返回键会将前面的所有动作反向执行一次（事务回溯）
             */
                transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
        }
    }


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    protected abstract void doAfterView();

    protected abstract void injectBinding(@LayoutRes int resId);
}
