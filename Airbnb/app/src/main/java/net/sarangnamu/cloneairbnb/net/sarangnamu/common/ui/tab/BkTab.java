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

package net.sarangnamu.cloneairbnb.net.sarangnamu.common.ui.tab;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 3. 18.. <p/>
 */
public class BkTab extends RadioGroup {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(BkTab.class);
    private ArrayList<Integer> mList;
    
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
    }

    public void setButtonPadding(int l, int t, int r, int b) {

    }

    public void setData(ArrayList<Integer> data) {
        int i = 0;
        for (Integer resid : data) {
            RadioButton btn = new RadioButton(getContext());
            btn.setId(i++);

            btn.setLayoutParams(getRadioButtonLayoutParams());
            btn.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
            btn.setButtonDrawable(resid);
            btn.setBackgroundColor(0xff00ff00);

            addView(btn);
        }
    }

    private RadioGroup.LayoutParams getRadioButtonLayoutParams() {
        RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.weight = 1;
        lp.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
        lp.leftMargin = 20;

        return lp;
    }
}
