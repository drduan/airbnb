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

import android.support.annotation.StringRes;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.sarangnamu.cloneairbnb.R;

import butterknife.ButterKnife;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 3. 23.. <p/>
 */
public class EmptyFrgmtBase extends PageFrgmtBase {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(EmptyFrgmtBase.class);

    protected TextView mTitle;
    protected TextView mMsgTitle;
    protected TextView mMsgContent;
    protected Button mMsgButton;

    @Override
    protected void initLayout() {
        super.initLayout();
    }

    protected void showEmptyLayout(String[] msg) {
        View empty = inflate(R.layout.empty);

        mTitle      = ButterKnife.findById(empty, R.id.title);
        mMsgTitle   = ButterKnife.findById(empty, R.id.msg_title);
        mMsgContent = ButterKnife.findById(empty, R.id.msg_content);
        mMsgButton  = ButterKnife.findById(empty, R.id.msg_btn);

        int i = 0;
        mTitle.setText(msg[i++]);
        mMsgTitle.setText(msg[i++]);
        mMsgContent.setText(msg[i++]);
        mMsgButton.setText(msg[i++]);

        mBaseView.addView(empty);
    }
}
