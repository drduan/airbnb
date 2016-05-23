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

package net.sarangnamu.common.ui.image;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import net.sarangnamu.common.ani.AnimatorEndListener;

import java.util.ArrayList;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 4. 6.. <p/>
 */
public class BkFadeImageView extends FrameLayout {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(BkFadeImageView.class);

    private static final int EVENT_FADE = 1;

    private int mIndex, mImageViewHeight = 0, mImageViewGravity = Gravity.CENTER_VERTICAL;
    private ArrayList<Integer> mImageList;
    private Handler mHandler;

    protected int mFadeDelay = 800;
    protected float mScale = 0f;

    private class ProcessHandler implements Handler.Callback {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
            case EVENT_FADE:
                next(msg.arg1);
                break;
            }

            return true;
        }
    }

    private void sendMessage(int type, int delay) {
        Message msg = mHandler.obtainMessage();
        msg.what = type;
        msg.arg1 = delay;

        if (delay == 0) {
            mHandler.sendMessage(msg);
        } else {
            mHandler.sendMessageDelayed(msg, delay);
        }
    }

    public BkFadeImageView(Context context) {
        super(context);
        initLayout();
    }

    public BkFadeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout();
    }

    public BkFadeImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initLayout();
    }

    protected void initLayout() {
        setLayoutTransition(null);
        setBackgroundColor(0x7f0000ff);
    }

    public void setImageList(ArrayList<Integer> list, int delay) {
        mImageList = list;

        run(delay);
    }

    public void setFadeValue(int fade) {
        mFadeDelay = fade;
    }

    public void setScaleAnimation(float scale) {
        mScale = scale;
    }

    public void setImageViewHeight(int height) {
        mImageViewHeight = height;
    }

    public void setImageViewGravity(int gravity) {
        mImageViewGravity = gravity;
    }

    private void run(int delay) {
        mIndex = 0;

        if (mImageList == null || mImageList.size() == 0) {
            mLog.debug("Pls add image list");
            return ;
        }

        ImageView img = getImageView();
        scaleAnimation(img, delay);

        addView(img, 0);

        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper(), new ProcessHandler());
        }

        sendMessage(EVENT_FADE, delay);
    }

    private ImageView getImageView() {
        if (mImageList == null || mImageList.size() == 0) {
            mLog.debug("Pls add image list");
            return null;
        }

        int resid = mImageList.get(mIndex);
        ImageView img = new ImageView(getContext());
        img.setImageDrawable(null);
        img.setBackgroundResource(resid);

        if (++mIndex >= mImageList.size()) {
            mIndex = 0;
        }

        int layoutHeight = mImageViewHeight != 0 ? mImageViewHeight : LayoutParams.WRAP_CONTENT;

        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, layoutHeight);
        lp.gravity = mImageViewGravity;
        img.setLayoutParams(lp);

        if (mScale != 0f) {
            img.setScaleX(mScale);
            img.setScaleY(mScale);
        }

        return img;
    }

    private ImageView getCurrentImageView() {
        return (ImageView) getChildAt(1);
    }

    protected void next(int delay) {
        ImageView img = getImageView();

        addView(img, 0); // target index 0

        final ImageView oldview = getCurrentImageView();
        if (oldview == null) {
            mLog.error("ERROR next image == null");
            return ;
        }

        scaleAnimation(img, delay);
        oldview.animate().alpha(0).setDuration(mFadeDelay).setListener(new AnimatorEndListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                removeView(oldview);
                sendMessage(EVENT_FADE, delay);
            }
        });
    }

    protected void scaleAnimation(View view, int delay) {
        if (mScale == 0f) {
            return;
        }

        AnimatorSet aniset = new AnimatorSet();

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1);

        aniset.play(scaleX).with(scaleY);
        aniset.setDuration(delay);
        aniset.start();
    }
}
