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

package net.sarangnamu.common.ui.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 4. 11.. <p/>
 */
public class BkScrollView extends ScrollView {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(BkScrollView.class);

    private ScrollChangedListener mScrollListener;
    
    public BkScrollView(Context context) {
        super(context);
        initLayout();
    }
    
    public BkScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout();
    }
    
    public BkScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initLayout();
    }
    
    protected void initLayout() {
    
    }

    public void setOnScrollYListener(ScrollChangedListener listener) {
        mScrollListener = listener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if (mScrollListener != null) {
            mScrollListener.onScroll(getScrollY());
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // LISTENER
    //
    ////////////////////////////////////////////////////////////////////////////////////

    public static interface ScrollChangedListener {
        public void onScroll(int value);
    }
}
