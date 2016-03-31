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

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import net.sarangnamu.common.frgmt.FrgmtManager;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 3. 31.. <p/>
 */
public class TravelPageManager extends FrgmtManager {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(TravelPageManager.class);

    private static TravelPageManager mInst;

    public static TravelPageManager getInstance(Fragment frgmt) {
        if (mInst == null) {
            mInst = new TravelPageManager();
        }

        mInst.setFragmentManager(frgmt);
        return mInst;
    }

    private TravelPageManager() {

    }

    public void setFragmentManager(Fragment frgmt) {
        mFrgmtManager = frgmt.getChildFragmentManager();
    }
}
