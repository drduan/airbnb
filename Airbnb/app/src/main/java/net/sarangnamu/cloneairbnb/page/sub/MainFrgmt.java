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

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import net.sarangnamu.cloneairbnb.R;
import net.sarangnamu.cloneairbnb.page.PageFrgmtBase;
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
    @Bind(R.id.contentLayout) LinearLayout mContentLayout;
    @Bind(R.id.dumy) View mDumyView;
    @Bind(R.id.scroll) BkScrollView mScroll;

    int mOldValue = 0, mBitmapHeight = 0;

    @Override
    protected void initLayout() {
        super.initLayout();

        setFadeImageHeight();
        setImageList();
        setDumyView();
        setScrollView();
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

    private void setDumyView() {
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, mBitmapHeight);
        mDumyView.setLayoutParams(lp);
    }

    private void setScrollView() {
        mScroll.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mScroll.setOnScrollYListener((value) -> {
            if (mOldValue == value) {
                return ;
            }

            mOldValue = value;

            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mFadeImageView.getLayoutParams();
            if (lp == null) {
                mLog.error("ERROR the fade image view layout param is null");
                return ;
            }

            // resize fade image view
            int newHeight = mBitmapHeight - value;
            lp.height = newHeight <= 0 ? 0 : newHeight;

            mFadeImageView.setLayoutParams(lp);
        });
    }
}


