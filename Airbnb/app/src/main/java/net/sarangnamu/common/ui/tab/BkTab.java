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

package net.sarangnamu.common.ui.tab;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import net.sarangnamu.common.frgmt.FrgmtManager;

import java.util.ArrayList;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 3. 18.. <p/>
 */
public class BkTab extends RadioGroup implements RadioGroup.OnCheckedChangeListener {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(BkTab.class);

    private int mPadding;
    private int mTargetViewId;
    private FrgmtManager mFrgmtManager;

    public BkTab(Context context) {
        super(context);
        initLayout();
    }
    
    public BkTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout();
    }

    protected void initLayout() {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        setOnCheckedChangeListener(this);
    }

    public int dpToPixel(float dp) {
        return (int) (dp * getContext().getResources().getDisplayMetrics().density);
    }

    public void setButtonPadding(int dp) {
        mPadding = dpToPixel(dp);
    }

    public void setData(ArrayList<BkData> dataList) {
        int i = 0;
        for (BkData data : dataList) {
            View view;

            if (data.clazz == null) {
                ImageButton imgbtn = new ImageButton(getContext());

                if (data instanceof BkImageData) {
                    imgbtn.setImageResource(data.getResId());
//                    imgbtn.setText(null);
                } else {
//                    imgbtn.setText(data.getResId());
                }

                imgbtn.setOnClickListener(data.click);
//                imgbtn.setGravity(Gravity.CENTER_VERTICAL);

                view = imgbtn;
            } else {
                BkRadioButton bkbtn = new BkRadioButton(getContext());

                if (data instanceof BkImageData) {
                    bkbtn.setButtonDrawable(data.getResId());
                    bkbtn.setText(null);
                } else {
                    bkbtn.setText(data.getResId());
                }

                if (data.clazz == null) {
                    bkbtn.setTag(data.click);
                } else {
                    bkbtn.setTag(data.clazz);
                }

                bkbtn.setGravity(Gravity.CENTER_VERTICAL);

                view = bkbtn;
            }

            view.setId(i++);
            view.setPadding(0, mPadding, 0, mPadding);
            view.setLayoutParams(getRadioButtonLayoutParams());
            view.setBackgroundColor(0xffdedede); // custom 필요
            view.setDuplicateParentStateEnabled(true);

            addView(view);
        }
    }

    private RadioGroup.LayoutParams getRadioButtonLayoutParams() {
        RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        lp.weight  = 1;
        lp.gravity = Gravity.CENTER;

        return lp;
    }

    public void setChecked(int check) {
        if (check < 0 && getChildCount() < check) {
            return ;
        }

        RadioButton btn = (RadioButton) getChildAt(check);
        if (btn == null) {
            return ;
        }

        btn.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (mTargetViewId == 0) {
            return ;
        }

        BkRadioButton btn = (BkRadioButton) group.getChildAt(checkedId);
        if (btn == null) {
            mLog.error("btn == null");
            return ;
        }

        if (mFrgmtManager != null) {
            mFrgmtManager.add(mTargetViewId, (Class) btn.getTag());
        }
    }

    public void setFrgmtManager(@IdRes int resid, FrgmtManager manager) {
        mTargetViewId = resid;
        mFrgmtManager = manager;
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // DATA TYPE
    //
    ////////////////////////////////////////////////////////////////////////////////////

    public static class BkImageData extends BkData {
        public BkImageData(@DrawableRes int drawid, Class<?> clazz) {
            this.resid = drawid;
            this.clazz = clazz;
        }

        public BkImageData(@DrawableRes int drawid, View.OnClickListener click) {
            this.resid = drawid;
            this.clazz = null;
            this.click = click;
        }
    }

    public static class BkTextData extends BkData {
        public BkTextData(@StringRes int strid, Class<?> clazz) {
            this.resid = strid;
            this.clazz = clazz;
        }

        public BkTextData(@StringRes int strid, View.OnClickListener click) {
            this.resid = strid;
            this.clazz = null;
            this.click = click;
        }
    }

    public static class BkData {
        protected int resid;
        protected Class<?> clazz;
        protected View.OnClickListener click;

        public int getResId() {
            return resid;
        }

        public Class<?> getClazz() {
            return clazz;
        }

        public View.OnClickListener getClickListener() {
            return click;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // BkRadioButton
    //
    ////////////////////////////////////////////////////////////////////////////////////

    class BkRadioButton extends RadioButton {
        private Drawable mDrawable;

        public BkRadioButton(Context context) {
            super(context);
            initLayout();
        }
        
        public BkRadioButton(Context context, AttributeSet attrs) {
            super(context, attrs);
            initLayout();
        }
        
        public BkRadioButton(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            initLayout();
        }
        
        protected void initLayout() {
        
        }

        @Nullable
        @Override
        public void setButtonDrawable(int resid) {
            mDrawable = getDrawable(resid);
        }

        private Drawable getDrawable(int resid) {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                return getContext().getResources().getDrawable(resid, null);
            } else {
                return getContext().getResources().getDrawable(resid);
            }
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // @see http://blog.mohitkanwal.com/blog/2013/10/02/making-a-custom-centre-aligned-radio-button-with-a-state-list-drawable/

            if (mDrawable != null) {
                mDrawable.setState(getDrawableState());
                final int verticalGravity = getGravity() & Gravity.VERTICAL_GRAVITY_MASK;
                final int height = mDrawable.getIntrinsicHeight();

                int y = 0;

                switch (verticalGravity) {
                    case Gravity.BOTTOM:
                        y = getHeight() - height;
                        break;
                    case Gravity.CENTER_VERTICAL:
                        y = (getHeight() - height) / 2;
                        break;
                }

                int buttonWidth = mDrawable.getIntrinsicWidth();
                int buttonLeft = (getWidth() - buttonWidth) / 2;
                mDrawable.setBounds(buttonLeft, y, buttonLeft+buttonWidth, y + height);
                mDrawable.draw(canvas);
            }
        }
    }
}
