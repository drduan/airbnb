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

import android.widget.ImageView;

import net.sarangnamu.cloneairbnb.R;
import net.sarangnamu.common.BkApp;
import net.sarangnamu.common.InflateFrgmtBase;
import net.sarangnamu.common.ui.LpInst;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import butterknife.Bind;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 5. 10.. <p/>
 */
public class SplashFrgmt extends InflateFrgmtBase {
    private static final Logger mLog = LoggerFactory.getLogger(SplashFrgmt.class);

    @Bind(R.id.splash_icon) ImageView mIcon;

    @Override
    protected void initLayout() {
        mBaseView.setLayoutParams(LpInst.frame(BkApp.screenX(), BkApp.screenY()));
    }

    public void setChangedIcon(boolean enable) {
        mIcon.setBackgroundResource(enable ?
                R.drawable.ic_directions_bus_pink_140dp :
                R.drawable.ic_directions_bus_black_140dp);
        mIcon.invalidate();
    }
}
