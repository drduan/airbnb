/*
 * Copyright 2016. Burke Choi All rights reserved.
 *             http://www.sarangnamu.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.sarangnamu.cloneairbnb.page.sub;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import net.sarangnamu.cloneairbnb.R;
import net.sarangnamu.cloneairbnb.page.PageFrgmtBase;
import net.sarangnamu.common.ani.AnimatorEndListener;
import net.sarangnamu.common.ui.image.BkFadeImageView;
import net.sarangnamu.common.ui.scroll.BkScrollView;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 3. 21.. <p/>
 */
public class MainFrgmt extends PageFrgmtBase {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(MainFrgmt.class);

    @Bind(R.id.fade_image) BkFadeImageView mFadeImageView;
    @Bind(R.id.content_layout) LinearLayout mContentLayout;
    @Bind(R.id.scroll) BkScrollView mScroll;

    @Bind(R.id.fab) FloatingActionButton mFab;
    @Bind(R.id.fab_layout) RelativeLayout mFabLayout;
    @Bind(R.id.fab_dumy) ImageView mFabDumy;
    @Bind(R.id.search) EditText mSearch;
    @Bind(R.id.ic_search) ImageView mIconSearch;
    @Bind(R.id.search_underline) View mSearchUnderline;

    private int mOldValue = 0, mBitmapHeight = 0;
    private int mScreenWidth;
    private boolean mAnimate = false;

    @Override
    protected void initLayout() {
        super.initLayout();

        getScreenWidth();
        setFadeImageHeight();
        setImageList();
        setContentLayoutPadding();
        setScrollView();
        setFab();
    }

    private void getScreenWidth() {
        WindowManager manager = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Point pt = new Point();
        manager.getDefaultDisplay().getSize(pt);

        mScreenWidth = pt.x;
    }

    private void setFadeImageHeight() {
        Drawable draw;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            draw = getResources().getDrawable(R.drawable.test1, null);
        } else {
            draw = getResources().getDrawable(R.drawable.test1);
        }

        mBitmapHeight = draw.getIntrinsicHeight();
    }

    private void setImageList() {
        ArrayList<Integer> imgList = new ArrayList<>();
        imgList.add(R.drawable.test1);
        imgList.add(R.drawable.test2);
        imgList.add(R.drawable.test3);

        mFadeImageView.setImageViewHeight(mBitmapHeight);
        mFadeImageView.setScaleAnimation(1.05f);
        mFadeImageView.setImageList(imgList, 5000);
    }

    private void setContentLayoutPadding() {
        mContentLayout.setPadding(0, mBitmapHeight, 0, 0);
    }

    private void setScrollView() {
        mScroll.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mScroll.setOnScrollYListener((value) -> {
            if (mOldValue == value) {
                return;
            }

            mOldValue = value;

            moveFadeImageView(value);
            moveFab(value);
        });
    }

    private void moveFadeImageView(int value) {
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mFadeImageView.getLayoutParams();
        if (lp == null) {
            mLog.error("ERROR the fade image view layout param is null");
            return;
        }

        int newHeight = mBitmapHeight - value;
        lp.height = newHeight <= 0 ? 0 : newHeight;

        mFadeImageView.setLayoutParams(lp);
    }

    private int getBaseFabMargin() {
        return mBitmapHeight - (getResources().getDimensionPixelSize(R.dimen.main_fab_size) / 2);
    }

    private void setFab() {
        mFab.setTranslationY(getBaseFabMargin());
        mFabLayout.setTranslationY(getBaseFabMargin());
    }

    private void moveFab(int value) {
        int fabY = getBaseFabMargin() - value;

        mFab.setTranslationY(fabY);
        mFabLayout.setTranslationY(fabY <= 0 ? 0 : fabY);

        if (mFab.getTranslationY() <= 0 && mSearch.getVisibility() == View.GONE && mAnimate == false) {
            if (mFabDumy.getVisibility() == View.GONE) {
                mAnimate = true;

                mFab.setVisibility(View.GONE);
                mFabLayout.setBackgroundResource(android.R.color.white);
                mFabLayout.setVisibility(View.VISIBLE);

                mFabDumy.setVisibility(View.VISIBLE);
                mIconSearch.setVisibility(View.VISIBLE);
                mSearch.setVisibility(View.VISIBLE);
                mSearchUnderline.setVisibility(View.VISIBLE);

                mFabDumy.animate().alpha(0).scaleX(20).scaleY(20).setDuration(400).setListener(new AnimatorEndListener() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mAnimate = false;
                    }
                });
            }
        } else {
            if (mFab.getTranslationY() > 0 && mSearch.getVisibility() == View.VISIBLE && mAnimate == false) {
                mAnimate = true;

                mFabLayout.setBackgroundResource(android.R.color.transparent);
                mSearchUnderline.setVisibility(View.GONE);

                mFabDumy.animate().alpha(1).scaleX(1).scaleY(1).setDuration(300).setListener(new AnimatorEndListener() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mAnimate = false;

                        mFab.setVisibility(View.VISIBLE);
                        mFabDumy.setVisibility(View.GONE);
                        mIconSearch.setVisibility(View.GONE);
                        mSearch.setVisibility(View.GONE);
                        mFabLayout.setVisibility(View.GONE);
                    }
                });
            }
        }
    }

    private void setChildVisibility(int visible) {
        int size = mFabLayout.getChildCount();
        for (int i=0; i<size; ++i) {
            View view = mFabLayout.getChildAt(i);
            view.setVisibility(visible);
        }
    }
}


