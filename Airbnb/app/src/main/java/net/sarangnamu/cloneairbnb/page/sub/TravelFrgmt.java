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

import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import net.sarangnamu.cloneairbnb.R;
import net.sarangnamu.cloneairbnb.TabPageManager;
import net.sarangnamu.cloneairbnb.page.PageFrgmtBase;
import net.sarangnamu.cloneairbnb.page.sub.travel.TravelAfterFrgmt;
import net.sarangnamu.cloneairbnb.page.sub.travel.TravelScheduledFrgmt;
import net.sarangnamu.common.ui.tab.BkTab;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 3. 21.. <p/>
 */
public class TravelFrgmt extends PageFrgmtBase {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(TravelFrgmt.class);

    @Bind(R.id.tab) BkTab mTab;
    @Bind(R.id.travel_main) FrameLayout mLayout;

    @Override
    protected void initLayout() {
        super.initLayout();

        ArrayList<BkTab.BkData> data = new ArrayList<>();

        data.add(new BkTab.BkTextData(R.string.travel_tab1, TravelScheduledFrgmt.class));
        data.add(new BkTab.BkTextData(R.string.travel_tab2, TravelAfterFrgmt.class));

        mTab.setButtonPadding(10);
        mTab.setData(data);
        mTab.setFrgmtManager(R.id.travel_main, TabPageManager.getInstance(getActivity()));
        mTab.setChecked(0);
    }
}
