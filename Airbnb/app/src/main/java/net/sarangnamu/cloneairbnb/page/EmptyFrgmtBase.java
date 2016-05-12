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

package net.sarangnamu.cloneairbnb.page;

import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import net.sarangnamu.cloneairbnb.R;
import net.sarangnamu.cloneairbnb.TabPageManager;
import net.sarangnamu.cloneairbnb.page.sub.MainFrgmt;
import net.sarangnamu.common.ui.LpInst;

import butterknife.ButterKnife;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 3. 23.. <p/>
 */
public class EmptyFrgmtBase extends PageFrgmtBase implements View.OnClickListener {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(EmptyFrgmtBase.class);

    protected FrameLayout mEmptyLayout;
    protected TextView mEmptyBigTitle;
    protected TextView mEmptyTitle;
    protected TextView mEmptyDetail;
    protected Button mEmptySearch;

    protected void showEmptyLayout(String[] msg, @DrawableRes int bgId) {
        View empty = inflate(R.layout.empty, mBaseView);

        mEmptyLayout   = ButterKnife.findById(empty, R.id.empty_layout);
        mEmptyBigTitle = ButterKnife.findById(empty, R.id.empty_big_title);
        mEmptyTitle    = ButterKnife.findById(empty, R.id.empty_title);
        mEmptyDetail   = ButterKnife.findById(empty, R.id.empty_detail);
        mEmptySearch   = ButterKnife.findById(empty, R.id.empty_search);

        int i = 0;
        empty.setLayoutParams(LpInst.frameMm());
        mEmptyLayout.setLayoutParams(LpInst.frameMm());
        mEmptyLayout.setBackgroundResource(bgId);
        mEmptyBigTitle.setText(msg[i++]);
        mEmptyTitle.setText(msg[i++]);
        mEmptyDetail.setText(msg[i++]);
        mEmptySearch.setText(msg[i]);
        mEmptySearch.setOnClickListener(this);

        mBaseView.addView(empty);
    }

    @Override
    public void onClick(View v) {
        TabPageManager.getInstance(getActivity()).add(R.id.main, MainFrgmt.class);
    }
}
