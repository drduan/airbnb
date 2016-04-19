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
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.sarangnamu.cloneairbnb.R;
import net.sarangnamu.cloneairbnb.model.Cfg;
import net.sarangnamu.cloneairbnb.page.PageFrgmtBase;
import net.sarangnamu.cloneairbnb.page.sub.main.RecentlyAdapter;
import net.sarangnamu.cloneairbnb.page.sub.main.TitleRecylerView;
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

    @Bind(R.id.hello) TextView mHello;

    @Bind(R.id.recently) TitleRecylerView mRecently;
    @Bind(R.id.recommandation) TitleRecylerView mRecommandation;
    @Bind(R.id.famous) TitleRecylerView mFamous;

    private int mOldValue = 0, mBitmapHeight = 0;
    private boolean mAnimate = false;

    @Override
    protected void initLayout() {
        super.initLayout();

        setFadeImageHeight();
        setImageList();
        setContentLayoutPadding();
        setScrollView();
        setFab();

        setUserInfo();
        setRecentlyList();
        setRecommandationList();
        setFamousList();
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
                setChildVisibility(View.VISIBLE);

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
                mSearch.setAlpha(0);

                mFabDumy.animate().alpha(1).scaleX(1).scaleY(1).setDuration(300).setListener(new AnimatorEndListener() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mAnimate = false;

                        mFab.setVisibility(View.VISIBLE);
                        setChildVisibility(View.GONE);

                        mSearch.setAlpha(1);
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

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // CONTENTS
    //
    ////////////////////////////////////////////////////////////////////////////////////

    private void setUserInfo() {
        String value = String.format(getString(R.string.main_hello), Cfg.USERINFO);
        mHello.setText(value);
    }

    private void setRecentlyList() {
        setListItem(mRecently, R.string.main_recyler_recently, new RecentlyAdapter(getActivity()));
    }

    private void setRecommandationList() {
        setListItem(mRecommandation, R.string.main_recyler_recommandation, null);
    }

    private void setFamousList() {
        setListItem(mFamous, R.string.main_recyler_famous, null);
    }

    private void setListItem(TitleRecylerView view, @StringRes int titleid, RecyclerView.Adapter adapter) {
        view.setTitle(titleid);
        view.setTitleSize(18);
        view.setTitlePadding(dpToPixelInt(15), dpToPixelInt(20), dpToPixelInt(20), dpToPixelInt(15));
        view.setLineViewBackground(0xffdedede);
        view.setAdapter(adapter);
    }

}


