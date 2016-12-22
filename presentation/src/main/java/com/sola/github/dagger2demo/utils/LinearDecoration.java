package com.sola.github.dagger2demo.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zhangluji
 * 2016/12/22.
 */
public class LinearDecoration extends RecyclerView.ItemDecoration {
    // ===========================================================
    // Constants
    // ===========================================================

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private static final int DEFAULT_OFFSET = 2;

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    // ===========================================================
    // Fields
    // ===========================================================

    private Drawable mDivider;

    private int mOrientation;

    private int itemDivider;

    private int[] offsets = new int[4];

    // ===========================================================
    // Constructors
    // ===========================================================

    public LinearDecoration(Context context, int orientation) {
        this(context, orientation, -1);
    }

    public LinearDecoration(Context context, int orientation, int diverRes) {
        if (diverRes == -1) {
            TypedArray typedArray = null;
            try {
                typedArray = context.obtainStyledAttributes(ATTRS);
                mDivider = typedArray.getDrawable(0);
            } finally {
                if (typedArray != null)
                    typedArray.recycle();
            }
        } else {
            mDivider = ContextCompat.getDrawable(context, diverRes);
        }
        itemDivider = DEFAULT_OFFSET;
        setOrientation(orientation);
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public LinearDecoration setMargins(int left, int top, int right, int bottom) {
        offsets[0] = left;
        offsets[1] = top;
        offsets[2] = right;
        offsets[3] = bottom;
        return this;
    }

    public LinearDecoration setItemDivider(int itemDivider) {
        this.itemDivider = itemDivider;
        return this;
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }
    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (mOrientation == VERTICAL_LIST) { // 垂直分布的情况下
            outRect.set(0, 0, 0, itemDivider);
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        }
    }

    // ===========================================================
    // Methods
    // ===========================================================

    private void drawVertical(Canvas c, RecyclerView parent) {
        // 在研究的过程中总会发现，这个方法被调用的次数过多

        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
        //没有子view或者没有没有颜色直接
        if (mDivider == null || layoutManager.getChildCount() == 0) {
            return;
        }
        int left;
        int right;
        int top;
        int bottom;
        final int childCount = parent.getChildCount();
        if (layoutManager.getOrientation() == LinearDecoration.VERTICAL_LIST) {
            for (int i = 0; i < childCount - 1; i++) {
                final View child = parent.getChildAt(i);
                //将有颜色的分割线处于中间位置
                left = layoutManager.getLeftDecorationWidth(child) + offsets[0]; // 这里的值是 offset当中设置的left
                right = parent.getWidth() - layoutManager.getRightDecorationWidth(child) - offsets[2];
                top = child.getBottom() + offsets[1];
                bottom = top + layoutManager.getBottomDecorationHeight(child) + offsets[3];
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
