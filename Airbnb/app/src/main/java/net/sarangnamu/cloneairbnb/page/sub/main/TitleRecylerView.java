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

package net.sarangnamu.cloneairbnb.page.sub.main;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import net.sarangnamu.common.ui.LinearBase;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 4. 18.. <p/>
 */
public class TitleRecylerView extends LinearBase {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(TitleRecylerView.class);

    private View mLineView;
    private TextView mTitle;
    private RecyclerView mRecyler;
    
    public TitleRecylerView(Context context) {
        super(context);
        initLayout();
    }
    
    public TitleRecylerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout();
    }
    
    public TitleRecylerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initLayout();
    }
    
    protected void initLayout() {
        setOrientation(VERTICAL);

        mLineView = new View(getContext());
        mTitle   = new TextView(getContext());
        mRecyler = new RecyclerView(getContext());

        addView(mLineView, LayoutParams.MATCH_PARENT, dpToPixelInt(1));
        addView(mTitle, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        addView(mRecyler, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    }

    public void setLineViewBackground(int color) {
        mLineView.setBackgroundColor(color);
    }

    public void setShowLine(boolean show) {
        mLineView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void setTitle(@StringRes int resid) {
        mTitle.setText(resid);
    }

    public void setTitlePadding(int l, int t, int r, int b) {
        mTitle.setPadding(l, t, r, b);
    }

    public void setTitleSize(int sp) {
        mTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        mRecyler.setAdapter(adapter);
    }

    public RecyclerView.Adapter getAdapter() {
        return mRecyler.getAdapter();
    }
}
