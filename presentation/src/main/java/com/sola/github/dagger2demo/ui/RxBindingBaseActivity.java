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
        injectExtras_();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        initView(layoutResID);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
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

    /**
     * 如非必要，建议不要重写该方法
     *
     * @param resId {@link #setContentView(int)} 布局resourceId
     */
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

    /**
     * @param resId 无用参数，可以用做校验和安全性处理
     * @param <T>   {@link #setContentView(int)}所定义的布局所对应的Binding
     * @return {@link #setContentView(int)}定义的布局所以对应的Binding实例
     */
    protected <T extends ViewDataBinding> T buildBinding(@LayoutRes int resId) {
        ViewGroup rootView = (ViewGroup) findViewById(android.R.id.content);
        // 这里缺乏一定的严谨性
        // 这里更加合理的办法应该是给自己所定义的布局设定一个Id，通过这个id去获取view，但是这样做不能做到通配
        View view = rootView.getChildAt(0);
        return DataBindingUtil.bind(view);
    }

    /**
     * @param newFrag    新的Fragment
     * @param isDestroy  <code>false</code> 自定义返回键操作
     *                   {@link FragmentManager#popBackStack()},会依次唤醒在堆栈内,标志位为false的
     *                   {@link Fragment}; 当popBackStack()调用的是自身的时候,进入Fragment的自行销毁流程
     *                   <code>true</code> 自定义返回键操作
     *                   {@link FragmentManager#popBackStack()}
     *                   ,不会对该标志位为true的任何Fragment产生影响,不会调用该Fragment的生命周期,想要调用该声明周期的销毁流程
     *                   ,请参见方法{@link FragmentTransaction#remove(Fragment)}
     * @param resourceId 所要替换的Fragment的ResourceId
     */
    public void replaceFragment(Fragment newFrag, boolean isDestroy,
                                int resourceId) {
        if (getSupportFragmentManager() != null) {
            // mManager.
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(resourceId, newFrag);
            if (!isDestroy)
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
     * @param isDestroy  {@see replaceFragment()}
     * @param resourceId 所要替换的Fragment的ResourceId
     */
    @SuppressWarnings("UnusedDeclaration")
    public void addFragment(Fragment newFrag, boolean isDestroy, int resourceId) {
        if (getSupportFragmentManager() != null) {
            // mManager.
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(resourceId, newFrag, null);
            if (!isDestroy)
            /*
             * 在后退栈中保存被替换的Fragment的状态 添加这句话后， 用户按返回键会将前面的所有动作反向执行一次（事务回溯）
             */
                transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
        }
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

    // ===========================================================
    // Inner and Anonymous Classes
    // 下面三个抽象方法实现的顺序如下
    // ===========================================================

    /**
     * @param resId 用于安全性判定的
     */
    protected abstract void injectBinding(@LayoutRes int resId);

    /**
     * @param intent 处理Intent传参的请求
     */
    protected abstract void initExtras(Intent intent);

    /**
     * 更新界面操作，此方法会在binding初始化之后调用
     */
    protected abstract void doAfterView();

}
