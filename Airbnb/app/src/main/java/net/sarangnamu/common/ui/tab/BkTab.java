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
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import net.sarangnamu.cloneairbnb.page.sub.MainFrgmt;
import net.sarangnamu.common.FrgmtBase;

import java.util.ArrayList;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 3. 18.. <p/>
 */
public class BkTab extends RadioGroup implements RadioGroup.OnCheckedChangeListener {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(BkTab.class);
    private int mPadding;
    private int mTargetViewId;
    private FragmentActivity mActivity;
    
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

    public void setData(ArrayList<BkTabData> dataList) {
        int i = 0;
        for (BkTabData data : dataList) {
            BkRadioButton btn = new BkRadioButton(getContext());
            btn.setId(i++);

            RadioGroup.LayoutParams lp = getRadioButtonLayoutParams();

            btn.setLayoutParams(lp);
            btn.setGravity(Gravity.CENTER_VERTICAL);
            btn.setButtonDrawable(data.drawid);
            btn.setText(null);
            btn.setDuplicateParentStateEnabled(true);
            btn.setBackgroundColor(0xffdedede);
            btn.setPadding(0, mPadding, 0, mPadding);

            if (data.clazz == null) {
                btn.setTag(data.click);
            } else {
                btn.setTag(data.clazz);
            }

            addView(btn);
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

        if (btn.getTag() instanceof View.OnClickListener) {
            ((OnClickListener) btn.getTag()).onClick(btn);
        } else {
//            if (checkedId == 0) {
                TabPageManager.getInstance(mActivity).add(mTargetViewId, (Class) btn.getTag());
//            } else {
//                TabPageManager.getInstance(mActivity).replace(mTargetViewId, (Class) btn.getTag(), null);
//            }
        }
    }

    public void setTargetView(@IdRes int resid, FragmentActivity activity) {
        mTargetViewId = resid;
        mActivity = activity;
    }

    public static class BkTabData {
        public int drawid;
        public Class<?> clazz;
        public View.OnClickListener click;

        public BkTabData(@DrawableRes int drawid, Class<?> clazz) {
            this.drawid = drawid;
            this.clazz  = clazz;
        }

        public BkTabData(@DrawableRes int drawid, View.OnClickListener click) {
            this.drawid = drawid;
            this.clazz  = null;
            this.click  = click;
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
